(ns ch2.pair.print
  (:require [ch2.pair])
  (:import [ch2.pair Pair]
           [java.io Writer]))

(defmethod print-method Pair
  [pair ^Writer w]
  (.write w "#ch2.pair.Pair")
  (print-method (vec (seq pair)) w))

(defmethod print-dup Pair
  [pair ^Writer w]
  (print-method pair w))
