class AddStatusToCategories < ActiveRecord::Migration
  def change
    add_column :categories, :status, :integer
  end
end
