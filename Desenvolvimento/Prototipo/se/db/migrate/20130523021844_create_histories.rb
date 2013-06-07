class CreateHistories < ActiveRecord::Migration
  def change
    create_table :histories do |t|
      t.integer :patient_id
      t.integer :variable_id

      t.timestamps
    end
  end
end
