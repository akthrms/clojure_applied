(ns ch8.fixture
  (:require [clojure.test :refer :all]))

(defn create-db-conn
  [])

(defn load-test-data
  [conn])

(defn add-user
  [conn user])

(defn check-user
  [conn user])

(defn destroy-test-data
  [conn])

(defn close-db-conn
  [conn])

(deftest test-setup-db-add-user
  (let [conn (create-db-conn)]
    (load-test-data conn)
    (add-user conn "user")
    (check-user conn "user")
    (destroy-test-data conn)
    (close-db-conn conn)))

(def ^:dynamic *conn*)

(defn db-fixture
  [test-function]
  (binding [*conn* (create-db-conn)]
    (load-test-data *conn*)
    (test-function)
    (destroy-test-data *conn*)
    (close-db-conn *conn*)))

(use-fixtures :each db-fixture)

(deftest test-db-add-user
  (add-user *conn* "user")
  (check-user *conn* "user"))
