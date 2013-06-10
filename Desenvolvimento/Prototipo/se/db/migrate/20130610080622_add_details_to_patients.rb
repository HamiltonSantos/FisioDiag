class AddDetailsToPatients < ActiveRecord::Migration
  def change
    add_column :patients, :sexo, :integer
    add_column :patients, :numRegistro, :string
  end
end
