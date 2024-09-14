require 'rails_helper'
require 'factory_bot_rails'
RSpec.describe Article, type: :model do
  describe '关联' do
    it { should belong_to(:user) }
  end

  describe '验证' do
    it { should validate_presence_of(:title) }
    it { should validate_presence_of(:content) }
  end

  describe '枚举' do
    it { should define_enum_for(:status).with_values(draft: 0, published: 1) }
  end

  describe '创建文章' do
    let(:user) { create(:user) }
    let(:article) { build(:article, user: user) }

    it '创建有效的文章' do
      expect(article).to be_valid
    end

    it '没有标题时无效' do
      article.title = nil
      expect(article).to be_invalid
    end

    it '没有内容时无效' do
      article.content = nil
      expect(article).to be_invalid
    end
  end

  describe '状态' do
    let(:article) { create(:article) }

    it '默认状态为草稿' do
      expect(article.status).to eq('draft')
    end

    it '可以将状态更改为已发布' do
      article.published!
      expect(article.status).to eq('published')
    end
  end
end