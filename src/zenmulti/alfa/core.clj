(ns zenmulti.alfa.core
	(:gen-class)
	(:require [org.httpkit.server :refer [run-server]]
						[noir.session :refer [wrap-noir-session*]]
						[zenmulti.alfa.routes :refer [all-routes]]
						[ring.middleware.resource :refer [wrap-resource]]
						[ring.middleware.defaults :refer [wrap-defaults site-defaults]]
						[zenmulti.alfa.config :refer [config]]))

(defonce ^:private server (atom nil))

(defn- init-app
	"The necessary actions before firing up the app"
	[conf]
	nil)

(defn- shutdown-app
	"The necessary actions before shutting-down the app"
	[]
	nil)

(defn- wrap-all
	"Wrap the main-routes with default setting"
	[main-routes]
	(-> main-routes
			(wrap-resource "public")
			wrap-noir-session*
			(wrap-defaults site-defaults)))

(defn -main [& [port]]
	"The java entry point to the application"
	(do (init-app config)
			(->> {:port (or port 3000)}
					 (run-server (wrap-all all-routes))
					 (reset! server))))

(defn stop
	"The stopping function"
	[]
	(do (shutdown-app)
			(reset! server (@server))))


