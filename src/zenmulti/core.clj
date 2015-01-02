(ns zenmulti.core
	(:gen-class)
	(:require [zenmulti.alfa.core :as alfa]
						[zenmulti.alfa.config :as alfa-config]))

(defn -main
	"The main entry to the application"
	[& args]
	(do (println "Starting listening on port 3000")
			(if (nil? args)
				(alfa/main {:port 3000 :which-couchbase? :couchbase-online})
				(alfa/main {:port 3000 :which-couchbase? :couchbase-online}))))

(defn stop
	"Stop all instances of the app"
	[]
	(do (alfa/stop @alfa-config/app-state)))


