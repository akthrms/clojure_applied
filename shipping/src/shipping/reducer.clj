(ns shipping.reducer
  (:require [clojure.core.reducers :as r]
            [shipping.domain :refer [ground?]]))

(defn ground-weight [products]
  (->> products
       (r/filter ground?)
       (r/map :weight)
       (r/fold +)))
