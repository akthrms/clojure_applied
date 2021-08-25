(ns shipping.domain)

(defn ground? [product]
  (= :ground (:class product)))
