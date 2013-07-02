class HistoryController < ApplicationController
  
  def home
    @principal = Category.find($id_principal)
    if params[:id].nil?
      redirect_to '/patients'
    else
      @patient = Patient.find(params[:id])
      @intercorrencia_valores = Valore.where(:id_variable => $id_intercorrencia).each
      @ocorrencia_valores = Valore.where(:id_variable => $id_ocorrencia).each
      @categories = Category.where(:category_id => @principal.id, :status => 1)
    end
  end

  def selecionar_paciente
    @patients = Patient.all
  end

  def show
    @patient = Patient.find(params[:id])
    render "show"
  end

end
