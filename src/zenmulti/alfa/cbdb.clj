(ns zenmulti.alfa.cbdb
	(:gen-class)
	(:require [couchbase-clj.client :refer [create-client] :as cc]
						[zenmulti.alfa.config :refer :all]))

(defn make-couchbase
	"The act of instantiating a new couchbase connection based on conf,
	the available choices of which-couchbase? can be seen in config.clj"
	[conf which-couchbase?]
	(assoc conf
		:couchbase-instance
		(create-client (which-couchbase? (:database conf)))))

(defn cbkey
	"Turn string into keyword"
	[dkey]
	(if (string? dkey)
		(keyword dkey)
		dkey))

(defn get-number
	[app-state number]
	(let [cdb (:couchbase-instance app-state)]
		(cc/get-json cdb (cbkey (str "num" number)))))

(defn shutdown-cdb
	[app-state]
	(cc/shutdown (:couchbase-instance app-state)))
