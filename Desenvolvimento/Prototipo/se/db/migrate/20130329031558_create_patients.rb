class CreatePatients < ActiveRecord::Migration
  def change
    create_table :patients do |t|
      t.string :nome
      t.string :cpf
      t.date :data_nasc

      t.timestamps
    end
  end
end
