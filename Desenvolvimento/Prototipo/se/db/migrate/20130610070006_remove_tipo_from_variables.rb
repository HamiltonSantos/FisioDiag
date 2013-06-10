class RemoveTipoFromVariables < ActiveRecord::Migration
  def up
    remove_column :variables, :tipo
  end

  def down
    add_column :variables, :tipo, :integer
  end
end
