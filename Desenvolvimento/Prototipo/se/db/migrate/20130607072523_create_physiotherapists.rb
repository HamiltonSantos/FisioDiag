class CreatePhysiotherapists < ActiveRecord::Migration
  def change
    create_table :physiotherapists do |t|
      t.string :nome
      t.string :login
      t.string :senha

      t.timestamps
    end
  end
end
