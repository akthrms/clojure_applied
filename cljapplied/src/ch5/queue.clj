(ns ch5.queue
  (:import [clojure.lang PersistentQueue]))

(defn queue
  "Create a new stateful queue"
  []
  (ref PersistentQueue/EMPTY))

(defn enq
  "Enqueue item in q"
  [q item]
  (dosync
    (alter q conj item)))

(defn deq
  "Dequeue item from q (nil if none)"
  [q]
  (dosync
    (let [item (peek @q)]
      (alter q pop)
      item)))
