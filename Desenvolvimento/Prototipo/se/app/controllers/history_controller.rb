class HistoryController < ApplicationController
  def home
    @principal = Category.find($id_principal)
    if params[:id].nil?
      redirect_to '/patients'
    else
      @patient = Patient.find(params[:id])
      @intercorrencia_variables = Variable.where(:category_id => $id_ocorrencia, :status => 1).each
      @ocorrencia_variables = Variable.where(:category_id => $id_intercorrencia, :status => 1).each
      @categories = Category.where(:category_id => @principal.id, :status => 1)
    end
  end

  def selecionar_paciente
    @patients = Patient.all
  end

end
