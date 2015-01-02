(ns zenmulti.alfa.routes
	(:gen-class)
	(:require [compojure.core :refer :all]
						[compojure.route :refer [resources not-found]]
						[noir.session :as sess]
						[noir.response :as resp]
						[zenmulti.alfa.cbdb :refer [get-number]]
						[zenmulti.alfa.config :refer [app-state]]
						[selmer.parser :refer [render-file]]
						[zenmulti.alfa.seed :refer [get-primes]]))

(def backoffice
	(context "/backoffice" req
					 (GET "/" req
								"Hello this is backoffice")))

(defn homepage
	[app]
	(let [lim 1000]
		(render-file "templates/primes.html"
								 {:primes (get-primes app lim)})))

(defroutes
	main-site
	(GET "/" req
			 (homepage app-state))
	(not-found "Kagak nemu nyet"))

(def all-routes
	"All the routes for the application"
	(routes backoffice main-site))

