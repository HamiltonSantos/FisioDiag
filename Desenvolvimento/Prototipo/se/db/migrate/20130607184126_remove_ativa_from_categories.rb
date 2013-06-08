class RemoveAtivaFromCategories < ActiveRecord::Migration
  def up
    remove_column :categories, :ativa
  end

  def down
    add_column :categories, :ativa, :boolean
  end
end
