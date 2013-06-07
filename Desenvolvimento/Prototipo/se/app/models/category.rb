class Category < ActiveRecord::Base
  attr_accessible :category_id, :description
  has_many :variables
end
