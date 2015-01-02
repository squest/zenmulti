(ns zenmulti.alfa.routes
	(:gen-class)
	(:require [compojure.core :refer :all]
						[compojure.route :refer [resources not-found]]
						[noir.session :as sess]
						[noir.response :as resp]
						[zenmulti.alfa.cbdb :refer [get-number]]
						[zenmulti.alfa.config :refer [app-state]]))

(def backoffice
	(context "/backoffice" req
					 (GET "/" req
								"Hello this is backoffice")))

(defroutes
	main-site
	(GET "/json" req
			 (resp/json {:name "jojon" :other "what?"}))
	(GET "/" [req]
			 "Hello this is the main site")
	(GET "/session-test" [req]
			 (do (sess/put! :username "dodol")
					 "Hellow"))
	(GET "/session-get" [req]
			 (str "Hello " (sess/get :username)))
	(GET "/number/:number" [number]
			 (resp/json (get-number @app-state number)))
	(not-found "Kagak nemu nyet"))

(def all-routes
	"All the routes for the application"
	(routes backoffice main-site))

