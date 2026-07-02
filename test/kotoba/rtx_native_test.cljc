(ns kotoba.rtx-native-test
  "Parity port of the const values kami-rtx-native's `lib.rs` declares.
   The Rust source has no `#[cfg(test)]` module (there is no runtime code
   to test) — these assertions pin the reservation metadata instead."
  (:require [clojure.test :refer [deftest is]]
            [kotoba.rtx-native :as rtx]))

(deftest reservation-consts-match-rust-source
  (is (= "ADR-2605261800" rtx/adr))
  (is (= "R1.0-path-reservation" rtx/phase))
  (is (= "kami-rtx-native" rtx/kami-name))
  (is (= "contingent-fallback-pending-viability-gate" rtx/status))
  (is (= "Mitsuba 3 wgpu backend gate fail (R1.2)" rtx/triggered-by))
  (is (= ["OptiX" "RTX Renderer"] rtx/nv-compat-targets)))

(deftest not-activated-yet
  (is (false? (rtx/activated?))))
