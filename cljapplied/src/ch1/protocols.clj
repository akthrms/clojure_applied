(ns ch1.protocols
  (:require [ch1.money :refer [+$ zero-dollars]])
  (:import [ch1.validate Ingredient Recipe]))

(defrecord Store [])

(defn cost-of [store ingredient])

(defprotocol Cost
  (cost [entity store]))

(extend-protocol Cost
  Recipe
  (cost [recipe store]
    (->> (:ingredients recipe)
         (map #(cost % store))
         (reduce +$ zero-dollars)))
  Ingredient
  (cost [ingredient store]
    (cost-of store ingredient)))
