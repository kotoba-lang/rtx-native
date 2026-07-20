# ADR 0001: Kotoba owns RTX fallback reservation metadata

- Status: accepted
- Date: 2026-07-20

## Decision

`src/kotoba/rtx_native.kotoba` is the only production source. The former CLJC
constants are exposed as explicitly typed, zero-argument Kotoba getters. The
two compatibility targets use separate getters, avoiding an ambient or
unbounded host collection ABI. `activated?` explicitly returns `:bool`; it must
not inherit the default `:i64` function contract.

The repository contains reservation metadata, not an RTX renderer. Kami owns
future 3D/rendering providers; Kotoba owns this portable policy declaration and
its safe target artifacts.

## Runtime and verification

Production compiles to restricted JavaScript or typed Wasm and has no JVM or
ClojureScript runtime dependency. JVM Clojure remains build/test tooling only.
Reference, JavaScript, and independently instantiated Wasm must return the same
exact strings and boolean. CI rejects production `.clj`, `.cljc`, and `.cljs`.

Wasm byte identity is not required. The contract is validation,
instantiability, typed ABI conformance, and observable semantic equality.
