class CreateConfigures < ActiveRecord::Migration
  def change
    create_table :configures do |t|
      t.string :key
      t.string :value

      t.timestamps
    end
  end
end
