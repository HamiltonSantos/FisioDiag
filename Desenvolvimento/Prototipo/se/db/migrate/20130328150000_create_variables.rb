class CreateVariables < ActiveRecord::Migration
  def change
    create_table :variables do |t|
      t.integer :category_id
      t.string :description
      t.integer :value

      t.timestamps
    end
  end
end
