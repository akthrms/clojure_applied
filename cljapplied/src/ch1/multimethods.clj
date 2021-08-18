(ns ch1.multimethods
  (:require [ch1.money :refer [+$ zero-dollars]])
  (:import [ch1.validate Ingredient Recipe]))

(defrecord Store [])

(defn cost-of [store ingredient])

(defmulti cost (fn [entity store] (class entity)))

(defmethod cost Recipe [recipe store]
  (->> (:ingredients recipe)
       (map #(cost % store))
       (reduce +$ zero-dollars)))

(defmethod cost Ingredient [ingredient store]
  (cost-of store ingredient))
