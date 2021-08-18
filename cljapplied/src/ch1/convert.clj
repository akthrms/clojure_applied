(ns ch1.convert)

(defmulti convert
          "Convert quantity from unit1 to unit2, matching on [unit1 unit2]"
          (fn [unit1 unit2 quantity] [unit1 unit2]))

(defmethod convert [:lb :oz] [_ _ lb] (* lb 16))

(defmethod convert [:oz :lb] [_ _ oz] (/ oz 16))

(defmethod convert :default [u1 u2 q]
  (if (= u1 u2)
    q
    (assert false (str "Unknown unit conversion from " u1 " to " u2))))

(defn ingredient+
  "Add two ingredients into a single ingredient,
   combining their quantities with unit conversion if necessary."
  [{q1 :quantity u1 :unit :as i1} {q2 :quantity u2 :unit :as i2}]
  (assoc i1 :quantity (+ q1 (convert u2 u1 q2))))
