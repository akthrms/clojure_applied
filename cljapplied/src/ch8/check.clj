(ns ch8.check
  (:require [clojure.test.check :as tc]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            [ch1.convert :refer :all]
            [ch1.validate :refer :all]))

(def range-count-eq-n
  (prop/for-all [n gen/small-integer]
                (= n (count (range n)))))

; (tc/quick-check 100 range-count-eq-n)
; =>
; {:shrunk {:total-nodes-visited 1,
;           :depth 0,
;           :pass? false,
;           :result false,
;           :result-data nil,
;           :time-shrinking-ms 0,
;           :smallest [-1]},
;  :failed-after-ms 1,
;  :num-tests 3,
;  :seed 1630251726322,
;  :fail [-1],
;  :result false,
;  :result-data nil,
;  :failing-size 2,
;  :pass? false}

(def range-count-eq-pos
  (prop/for-all [n gen/nat]
                (= n (count (range n)))))

; (tc/quick-check 100 range-count-eq-pos)
; => {:result true, :pass? true, :num-tests 100, :time-elapsed-ms 9, :seed 1630251846157}

(def gen-food (gen/elements ["flour" "sugar" "butter"]))

(def gen-unit (gen/elements [:oz :lb]))

(def gen-ingredient
  (gen/fmap map->Ingredient
            (gen/hash-map :name gen-food
                          :quantity gen/nat
                          :unit gen-unit)))
