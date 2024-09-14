FactoryBot.define do
  factory :article do
    sequence(:title) { |n| "文章标题 #{n}" }
    content { "这是文章的内容。" }
    status { :draft }
    association :user

    trait :published do
      status { :published }
    end
  end
end