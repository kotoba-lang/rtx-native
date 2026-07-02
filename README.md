# kotoba-lang/rtx-native

[![CI](https://github.com/kotoba-lang/rtx-native/actions/workflows/ci.yml/badge.svg)](https://github.com/kotoba-lang/rtx-native/actions/workflows/ci.yml)

**Contingent fallback** path tracer + differentiable rendering, ported pure
to `.cljc` from the Rust crate `kami-rtx-native`
(kotoba-lang/kami-engine) per [ADR-2607010930](../../90-docs/adr/)
(clj-wgsl migration Phase 4).

**Status**: R1.0 path reservation (ADR-2605261800 §D10.4).
**Activation**: Council Lv6+ ≥3 attestation after Mitsuba 3 wgpu upstream PR
viability gate failure at R1.2 (Cornell box 30 fps Chrome 121+, PSNR ≥35dB).

## What's ported

The upstream Rust crate is exactly this: reservation/status metadata, no
runtime algorithm. `kami-rtx-native/src/lib.rs` is five `pub const`
declarations and a doc comment — no `use` statements, no functions, no
`#[cfg(test)]` module. `kotoba.rtx-native` mirrors it 1:1: the same
constants, plus one `activated?` predicate (always `false`, since there is
no activation path in the source either) so the reservation state is
queryable instead of implicit.

There is nothing GPU/adapter-only to split out — the same reasoning as
[`kotoba-lang/raytrace`](https://github.com/kotoba-lang/raytrace) applies
(no such code ever existed in this crate).

**Note on the `kami-rt` relationship**: this crate's README says it's
"from-scratch on kami-rt (WGSL ray-query + LBVH)", but `Cargo.toml` does
not depend on `kami-rt` and `lib.rs` never references it — that's
aspirational future scope for *if* the fallback activates, not a current
compiled dependency. This port matches that exactly: no `deps.edn`
dependency on `kotoba-lang/raytrace` is declared.

## Scope (if activated)

- Forward path tracing (uni-directional with MIS, next-event estimation)
- Differentiable rendering (reverse-mode auto-diff via WGSL compute)
- OptiX-equivalent acceleration structure API on top of `kotoba-lang/raytrace`
- RTX Renderer-equivalent denoiser (OIDN-style WGSL port)

None of the above exists yet upstream or here — this repo currently only
carries the reservation metadata.

## Test / lint

```sh
clojure -M:test
clojure -M:lint
```

## License

Apache License 2.0.
