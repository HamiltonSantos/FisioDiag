class AddTipoToVariable < ActiveRecord::Migration
  def change
    add_column :variables, :tipo, :integer
  end
end
