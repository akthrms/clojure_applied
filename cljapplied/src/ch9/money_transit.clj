(ns ch9.money-transit
  (:require [ch1.money :refer :all])
  (:import [com.cognitect.transit ReadHandler WriteHandler]
           [ch1.money Currency Money]))

(def write-handlers
  {Currency (reify WriteHandler
              (tag [_ _] "currency")
              (rep [_ c] [(:divisor c) (:sym c) (:desc c)])
              (stringRep [_ _] nil)
              (getVerboseHandler [_] nil))
   Money    (reify WriteHandler
              (tag [_ m] "money")
              (rep [_ m] [(:amount m) (:currency m)])
              (stringRep [_ _] nil)
              (getVerboseHandler [_] nil))})

(def read-handlers
  {"currency" (reify ReadHandler
                (fromRep [_ c] (apply ->Currency c)))
   "money"    (reify ReadHandler
                (fromRep [_ m] (apply ->Money m)))})
