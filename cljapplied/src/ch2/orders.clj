(ns ch2.orders
  (:import [clojure.lang PersistentQueue]))

(defn cook [order])

(defn new-orders []
  [])

(defn add-order [orders order]
  (conj orders order))

(defn cook-order [orders]
  (cook (first orders))
  (rest orders))

(defn new-orders []
  '())

(defn add-order [orders order]
  (concat orders (list order)))

(def new-orders PersistentQueue/EMPTY)

(defn add-order [orders order]
  (conj orders order))

(defn cook-order [orders]
  (cook (peek orders))
  (pop orders))
