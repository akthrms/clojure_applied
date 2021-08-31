(ns cards.cards
  (:import [java.io Writer]))

(defrecord Card [rank suit])

(def ranks "23456789TJQKA")

(def suits "hdcs")

(defn- check
  [val vals]
  (if (some #{val} (set vals))
    val
    (throw (IllegalArgumentException. (format "Invalid value: %s, expected: %s" val val)))))

(defn card-reader
  [card]
  (let [[rank suit] card]
    (->Card (check rank ranks) (check suit suits))))

(defn card-str
  [card]
  (str "#my/card \"" (:rank card) (:suit card) "\""))

(defmethod print-method Card [card ^Writer w]
  (.write w ^String (card-str card)))

(defmethod print-dup Card [card w]
  (print-method card w))
