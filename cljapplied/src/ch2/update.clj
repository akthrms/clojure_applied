(ns ch2.update
  (:require [medley.core :refer [map-keys map-vals]]))

(defn keywordize-entity [entity]
  (map-keys keyword entity))

(defn compute-calories [recipe])

(defn- update-calories [recipe]
  (assoc recipe :calories (compute-calories recipe)))

(defn include-calories [recipe-index]
  (map-vals update-calories recipe-index))
