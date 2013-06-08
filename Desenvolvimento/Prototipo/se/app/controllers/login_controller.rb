class LoginController < ApplicationController

  def login
  	usuario = params[:usuario]
  	senha = params[:senha]
  	if usuario == senha && !usuario.nil?
  		session[:usuario] = usuario
  		redirect_to :controller => 'categories'
  	else
  		render :action => 'login', :layout => false
  	end
  end

  # Action config_login
  def config_login

  end

  # Action sair
  def sair
  	session[:usuario] = nil
  	redirect_to :controller => 'login', :action => 'login'
  end

end
