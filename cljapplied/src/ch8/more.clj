(ns ch8.more
  (:require [clojure.test :refer :all]))

(deftest test-range
  (is (= '(0 1 2 3 4) (range 5))
      "Got 0-indexed sequence when only end specified.")
  (is (= '() (range 0))
      "Got empty sequence when end index  = 0"))

(deftest test-range-group
  (testing "Testing range(endIndex)"
    (is (= '(0 1 2 3 4) (range 5))
        "Got 0-indexed sequence when only end specified.")
    (is (= '() (range 0))
        "Got empty sequence when end index  = 0")))

(deftest test-range-fail
  (testing "Testing range(endIndex)"
    (is (= '(0 1 2 3 4) (range 5))
        "Got 0-indexed sequence when only end specified.")
    (is (= '() (range 0))
        "Got empty sequence when end index  = 0")
    (is (= '(0 2) (range 0)))))

(deftest test-range-are
  (testing "Testing range(endIndex)"
    (are [expected endIndex]
      (= expected (range endIndex))
      '(0 1 2 3 4) 5
      '() 0)))

; (deftest test-range-exception
;   (try
;     (doall (range "boom"))
;     (is nil) (catch ClassCastException e
;                (is true))
;     (catch Throwable t
;       (is nil))))

(deftest test-range-exception
  (is (thrown? ClassCastException
               (doall (range "boom")))))

(deftest test-range-exception-msg
  (is (thrown-with-msg? ClassCastException
                        #"java.lang.String cannot cast to java.lang.Number"
                        (doall (range "boom")))))
