class CreateArticles < ActiveRecord::Migration[7.2]
  def change
    create_table :articles do |t|
      t.string :title, null: false
      t.text :content, null: false
      t.references :user, null: false, foreign_key: true
      t.integer :status, default: 0
      t.integer :likes_count, default: 0
      t.integer :comments_count, default: 0
      t.timestamps
    end

    add_index :articles, :title
    add_index :articles, :status
  end
end