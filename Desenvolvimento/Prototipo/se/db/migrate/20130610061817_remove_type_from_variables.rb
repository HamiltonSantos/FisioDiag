class RemoveTypeFromVariables < ActiveRecord::Migration
  def up
    remove_column :variables, :type
  end

  def down
    add_column :variables, :type, :integer
  end
end
