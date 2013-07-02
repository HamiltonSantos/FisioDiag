class PrognosticoController < ApplicationController
  def home
  	@valores = Valore.where(:id_variable => $id_prognostico)
  	render "index"
  end

  def new
  	@valore = Valore.new(:id_variable => $id_prognostico)
  	render "new"
  end

  def show
  	@valore = Valore.find(params[:id])
  	render "show"
  end
  
end
