class CreateValores < ActiveRecord::Migration
  def change
    create_table :valores do |t|
      t.integer :value_min
      t.integer :value_max
      t.integer :value
      t.string :description

      t.timestamps
    end
  end
end
