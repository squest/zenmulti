(ns zenmulti.alfa.seed
	(:gen-class)
	(:require [couchbase-clj.client :as cc]
						[zenmulti.alfa.config :refer [app-state]]))

(defn prime?
	[p]
	(cond (== p 2) true
				(even? p) false
				:else (loop [i (int 3)]
								(if (> (* i i) p)
									true
									(if (== 0 (rem p i))
										false
										(recur (+ i 2)))))))

(defn seed-data!
	[app lim]
	(let [cdb (:couchbase-instance @app)]
		(doseq [i (range 2 lim)]
			(cc/set-json cdb
									 (keyword (str "num" i))
									 {:number i :status (prime? i)}))))

(defn get-primes
	[app lim]
	(let [cdb (:couchbase-instance @app)]
		(for [i (range 2 lim)]
			(cc/get-json cdb (keyword (str "num" i))))))




