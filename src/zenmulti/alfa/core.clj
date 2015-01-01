(ns zenmulti.alfa.core
	(:gen-class)
	(:require [org.httpkit.server :refer [run-server]]
						[noir.session :refer [wrap-noir-session*]]
						[zenmulti.alfa.routes :refer [all-routes]]
						[ring.middleware.resource :refer [wrap-resource]]
						[ring.middleware.defaults :refer [wrap-defaults site-defaults]]
						[zenmulti.alfa.config :refer [config app-state]]
						[zenmulti.alfa.cbdb :refer [make-couchbase shutdown-cdb]]))

(defonce ^:private server (atom nil))

(defn- init-app
	"The necessary actions before firing up the app"
	[conf options]
	(do (swap! app-state
						 merge conf)
			(swap! app-state
						 make-couchbase
						 (:which-couchbase? options))))

(defn- shutdown-app
	"The necessary actions before shutting-down the app"
	[app-state]
	(shutdown-cdb app-state))

(defn- wrap-all
	"Wrap the main-routes with default setting"
	[main-routes]
	(-> main-routes
			(wrap-resource "public")
			wrap-noir-session*
			(wrap-defaults site-defaults)))

(defn -main [& [opts]]
	"The java entry point to the application"
	(do (init-app config (read-string opts))
			(->> {:port (or (:port opts) 3000)}
					 (run-server (wrap-all all-routes))
					 (reset! server))))

(defn stop
	"The stopping function"
	[app-state]
	(do (shutdown-app app-state)
			(reset! server (@server))))


