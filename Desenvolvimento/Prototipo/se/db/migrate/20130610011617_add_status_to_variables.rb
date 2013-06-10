class AddStatusToVariables < ActiveRecord::Migration
  def change
    add_column :variables, :status, :integer
  end
end
