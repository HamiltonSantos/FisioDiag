class Patient < ActiveRecord::Base
  attr_accessible :cpf, :data_nasc, :nome
end
