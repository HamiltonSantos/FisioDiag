class AddTypeToVariables < ActiveRecord::Migration
  def change
    add_column :variables, :type, :integer
  end
end
