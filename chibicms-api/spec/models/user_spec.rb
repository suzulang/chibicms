# spec/models/user_spec.rb

require 'rails_helper'

RSpec.describe User, type: :model do
  describe "验证" do
    it "使用有效属性时是有效的" do
      user = User.new(username: "testuser", password: "password123")
      expect(user).to be_valid
    end

    it "没有用户名时无效" do
      user = User.new(password: "password123")
      expect(user).to_not be_valid
    end

    it "没有密码时无效" do
      user = User.new(username: "testuser")
      expect(user).to_not be_valid
    end

    it "用户名少于5个字符时无效" do
      user = User.new(username: "test", password: "password123")
      expect(user).to_not be_valid
    end

    it "用户名超过16个字符时无效" do
      user = User.new(username: "a" * 17, password: "password123")
      expect(user).to_not be_valid
    end

    it "密码少于5个字符时无效" do
      user = User.new(username: "testuser", password: "pass")
      expect(user).to_not be_valid
    end

    it "密码超过16个字符时无效" do
      user = User.new(username: "testuser", password: "a" * 17)
      expect(user).to_not be_valid
    end

    it "用户名已存在时无效" do
      User.create(username: "existinguser", password: "password123")
      user = User.new(username: "existinguser", password: "anotherpassword")
      expect(user).to_not be_valid
    end
  end

  describe "方法" do
    it "应该能够更新头像URL" do
      user = User.create(username: "testuser", password: "password123")
      user.update_avatar("http://example.com/avatar.jpg")
      expect(user.avatar_url).to eq("http://example.com/avatar.jpg")
    end

    it "应该能够更新密码" do
      user = User.create(username: "testuser", password: "oldpassword")
      expect(user.update_password("oldpassword", "newpassword")).to be true
      expect(user.authenticate("newpassword")).to_not be false
    end

    it "使用错误的旧密码时不应更新密码" do
      user = User.create(username: "testuser", password: "oldpassword")
      expect(user.update_password("wrongpassword", "newpassword")).to be false
    end
  end
end