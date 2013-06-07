module HistoryHelper

  def gerar_menu_teste

    field_set_tag 'Dados Paciente' do

      content_tag(:div) do

        label_tag "Nome Paciente:"
        label_tag "Data Internacao:"
      end

    end
  end

end
