class Article < ApplicationRecord
  belongs_to :user
  
  validates :title, presence: true
  validates :content, presence: true
  
  enum status: { draft: 0, published: 1 }
end