class CreateUsers < ActiveRecord::Migration[7.2]
  def change
    create_table :users do |t|
      t.string :username, null: false, limit: 16
      t.string :password_digest, null: false
      t.string :avatar_url
      t.timestamps
    end

    add_index :users, :username, unique: true
  end
end