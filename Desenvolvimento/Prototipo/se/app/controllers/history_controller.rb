class HistoryController < ApplicationController
  def home
    @principal = Category.first
    if params[:id].nil?
      @patient = Patient.new
    else
      @patient = Patient.find(params[:id])
    end
    @categories = Category.where(:category_id => @principal.id, :status => 1)
  end

  def selecionar_paciente
    @patients = Patient.all
  end

end
