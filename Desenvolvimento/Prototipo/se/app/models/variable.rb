class Variable < ActiveRecord::Base
  attr_accessible :category_id, :description, :value, :status
end
