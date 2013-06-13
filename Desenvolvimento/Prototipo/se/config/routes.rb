Se::Application.routes.draw do
  
  # controller relatorios
  match "relatorios/home"
  match "relatorios/estatistica"

  # Controller Login
  match 'login/config_login'
  match 'login/sair' => 'login#sair'
  match 'login' => 'login#login'

  # Controller History
  match "history/home"
  match 'history/home/:id' => 'history#home'
  match "history/selecionar_paciente"

  resources :physiotherapists

  resources :patients

  resources :variables

  # Controller Categories
  match 'categories/sugerir'
  match 'categories/sugeridas'
  match 'categories/intercorrencias'
  match 'categories/intercorrencias/new' => 'categories#new_intercorrencia'
  match 'categories/intercorrencias/sugeridas' => 'categories#sugeridas_intercorrencia'
  match 'categories/intercorrencias/sugerir' => 'categories#sugerir_intercorrencia'
  match 'categories/ocorrencias'
  match 'categories/ocorrencias/new' => 'categories#new_ocorrencia'
  match 'categories/ocorrencias/sugerir' => 'categories#sugerir_ocorrencia'
  match 'categories/ocorrencias/sugeridas' => 'categories#sugeridas_ocorrencia'
  resources :categories

  # The priority is based upon order of creation:
  # first created -> highest priority.

  # Sample of regular route:
  #   match 'products/:id' => 'catalog#view'
  # Keep in mind you can assign values other than :controller and :action

  # Sample of named route:
  #   match 'products/:id/purchase' => 'catalog#purchase', :as => :purchase
  # This route can be invoked with purchase_url(:id => product.id)

  # Sample resource route (maps HTTP verbs to controller actions automatically):
  #   resources :products

  # Sample resource route with options:
  #   resources :products do
  #     member do
  #       get 'short'
  #       post 'toggle'
  #     end
  #
  #     collection do
  #       get 'sold'
  #     end
  #   end

  # Sample resource route with sub-resources:
  #   resources :products do
  #     resources :comments, :sales
  #     resource :seller
  #   end

  # Sample resource route with more complex sub-resources
  #   resources :products do
  #     resources :comments
  #     resources :sales do
  #       get 'recent', :on => :collection
  #     end
  #   end

  # Sample resource route within a namespace:
  #   namespace :admin do
  #     # Directs /admin/products/* to Admin::ProductsController
  #     # (app/controllers/admin/products_controller.rb)
  #     resources :products
  #   end

  # You can have the root of your site routed with "root"
  # just remember to delete public/index.html.
  # root :to => 'welcome#index'

  # See how all your routes lay out with "rake routes"

  # This is a legacy wild controller route that's not recommended for RESTful applications.
  # Note: This route will make all actions in every controller accessible via GET requests.
  # match ':controller(/:action(/:id))(.:format)'
end
