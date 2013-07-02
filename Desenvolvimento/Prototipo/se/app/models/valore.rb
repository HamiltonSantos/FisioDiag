class Valore < ActiveRecord::Base
  attr_accessible :description, :value, :value_max, :value_min
end
