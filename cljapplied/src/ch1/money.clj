(ns ch1.money)

(declare validate-same-currency)

(defrecord Currency [divisor sym desc])

(defrecord Money [amount ^Currency currency]
  Comparable
  (compareTo [m1 m2]
    (validate-same-currency m1 m2)
    (compare (:amount m1) (:amount m2))))

(def currencies {:usd (->Currency 100 "USD" "US Dollars")
                 :eur (->Currency 100 "EUR" "Euro")})

(defn- validate-same-currency [m1 m2]
  (or (= (:currency m1) (:currency m2))
      (throw (ex-info "Currencies do not match." {:m1 m1 :m2 m2}))))

(defn- =$
  ([_] true)
  ([m1 m2] (zero? (.compareTo m1 m2)))
  ([m1 m2 & monies] (every? zero? (map #(.compareTo m1 %) (conj monies m2)))))

(defn- +$
  ([m1] m1)
  ([m1 m2]
   (validate-same-currency m1 m2)
   (->Money (+ (:amount m1) (:amount m2)) (:currency m1)))
  ([m1 m2 & monies]
   (reduce +$ m1 (conj monies m2))))

(defn- *$ [m n]
  (->Money (* n (:amount m)) (:currency m)))

(defn make-money
  ([] (make-money 0))
  ([amount] (make-money amount :usd))
  ([amount currency] (->Money amount currency)))

(def zero-dollars (make-money 0 :usd))
