(ns ch8.test
  (:require [clojure.test :refer :all]))

(deftest test-range
  (is (= '(0 1 2 3 4) (range 5))
      "Got 0-indexed sequence when only end specified."))
