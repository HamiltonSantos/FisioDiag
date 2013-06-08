class AddDetailsToCategories < ActiveRecord::Migration
  def change
    add_column :categories, :ativa, :boolean
    add_column :categories, :tipo, :integer
  end
end
