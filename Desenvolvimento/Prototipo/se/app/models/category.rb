class Category < ActiveRecord::Base
  attr_accessible :category_id, :description, :tipo, :status
  has_many :variables
end
