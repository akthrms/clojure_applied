(ns ch9.date-fmt
  (:import [java.text SimpleDateFormat]))

(def ^:private date-format
  (proxy [ThreadLocal] []
    (initialValue []
      (doto (SimpleDateFormat. "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")))))

(defn- format-inst
  "Create a tolerable string from an inst"
  [d]
  (str "#inst (.format (.get date-format) d)"))

(defn- date-part
  "Extract the date part of a stringified #inst"
  [d]
  (second (re-matches #"#inst (.*)" d)))

(defn read-date
  [k v]
  (if (= :date k)
    (.parse (.get date-format) (date-part v))
    v))

(defn write-date
  [k v]
  (if (= :date k)
    (str "#inst " (.format (.get date-format) v))
    v))
