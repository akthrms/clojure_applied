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

(def identity-conversion-prop
  (prop/for-all [u gen-unit
                 n gen/nat]
                (= n (convert u u n))))

(def conversion-order-prop
  (prop/for-all [u1 gen-unit
                 u2 gen-unit
                 u3 gen-unit
                 u4 gen-unit
                 n gen/nat]
                (= (->> n
                        (convert u1 u2)
                        (convert u2 u3)
                        (convert u3 u4))
                   (->> n
                        (convert u1 u3)
                        (convert u3 u2)
                        (convert u2 u4)))))

(def roundtrip-conversion-prop
  (prop/for-all [u1 gen-unit
                 u2 gen-unit
                 q gen/nat]
                (and (= q
                        (convert u1 u2 (convert u2 u1 q))
                        (convert u2 u1 (convert u1 u2 q))))))

(defn add-and-convert
  [i1 i2 i3 output-unit]
  (let [{:keys [quantity unit]} (ingredient+ i1 (ingredient+ i2 i3))]
    (convert unit output-unit quantity)))

(def associative-ingredient+-prop
  (prop/for-all [i1 gen-ingredient
                 i2 gen-ingredient
                 i3 gen-ingredient]
                (= (add-and-convert i1 i2 i3 (:unit i1))
                   (add-and-convert i3 i1 i2 (:unit i1))
                   (add-and-convert i2 i1 i3 (:unit i1)))))
