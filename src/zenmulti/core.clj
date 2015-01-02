(ns zenmulti.core
	(:require [zenmulti.alfa.core :as alfa]
						[zenmulti.alfa.config :as alfa-config]))

(defn -main
	"The main entry to the application"
	[& [args]]
	(let [opts (read-string args)]
		(do (alfa/main (first opts)))))

(defn stop
	"Stop all instances of the app"
	[]
	(do (alfa/stop @alfa-config/app-state)))


