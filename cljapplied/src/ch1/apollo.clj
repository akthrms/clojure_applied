(ns ch1.apollo)

(defn make-mission [name system launched manned? opts]
  (let [{:keys [cm-name lm-name orbit evas]} opts]))

(def apollo-4 (make-mission "Apollo 4"
                            "Saturn V"
                            #inst "1967-11-09T12:00:01-00:00"
                            false
                            {:orbits 3}))

(def mission-default {:orbits 0
                      :evas   0})

(defn make-mission [name system launched manned? opts]
  (let [{:keys [cm-name lm-name orbit evas]} (merge mission-default opts)]))

(defn make-mission [name system launched manned? & opts]
  (let [{:keys [cm-name lm-name orbit evas]} opts]))

(def apollo-4 (make-mission "Apollo 4"
                            "Saturn V"
                            #inst "1967-11-09T12:00:01-00:00"
                            false
                            :orbits 3))

(def apollo-11 (make-mission "Apollo 11"
                             "Saturn V"
                             #inst "1969-07-16T13:32:00-00:00"
                             true
                             :cm-name "Columbia"
                             :lm-name "Eagle"
                             :orbits 30
                             :evas 1))

(defn make-mission [name system launched manned? & opts]
  (let [{:keys [cm-name lm-name orbit evas] :or {orbit 0 evas 0}} opts]))

(def apollo-4 (make-mission "Apollo 4"
                            "Saturn V"
                            #inst "1967-11-09T12:00:01-00:00"
                            false
                            :orbits 3))

(defn euclidean-norm [ecc-vector])

(defrecord Planet [name moons volume mass aphelion perihelion orbital-eccentricity])

(defn make-planet
  "Make a planet from field values and an eccentricity vector"
  [name moons volume mass aphelion perihelion ecc-vector]
  (->Planet name moons volume mass aphelion perihelion (euclidean-norm ecc-vector)))
