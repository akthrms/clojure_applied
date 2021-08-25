(ns shipping.seq
  (:require [shipping.domain :refer [ground?]]))

(defn ground-weight [products]
  (->> products
       (filter ground?)
       (map :weight)
       (reduce +)))
