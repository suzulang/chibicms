class User < ApplicationRecord
  has_many :articles

  has_secure_password

  validates :username, presence: true, uniqueness: true, length: { minimum: 5, maximum: 16 }
  validates :password, presence: true, length: { minimum: 5, maximum: 16 }, on: :create
  validates :avatar_url, url: true, allow_blank: true

  # 如果你想在更新时也验证密码长度，但允许为空（即不修改密码），可以添加：
  # validates :password, length: { minimum: 5, maximum: 16 }, allow_blank: true, on: :update

  def update_avatar(url)
    update(avatar_url: url)
  end

  def update_password(old_password, new_password)
    if authenticate(old_password)
      update(password: new_password)
    else
      false
    end
  end
end