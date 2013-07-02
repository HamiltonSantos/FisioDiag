class AddCamposToValores < ActiveRecord::Migration
  def change
    add_column :valores, :id_variable, :integer
  end
end
