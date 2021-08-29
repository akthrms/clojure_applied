(ns ch8.expect
  (:require [expectations.clojure.test :refer :all]))

(expect '(0 1 2 3 4) (range 5))

(expect '() (range 0))

(expect ClassCastException (doall (range "boom")))

(expect #"java.lang.String cannot be cast ti java.lang.Number"
        (try (doall (range "boom"))
             (catch ClassCastException e (.getMessage e))))

(expect {:a {:b 2}} (update-in {:a {:b 1}} [:a :b] dec))
