FactoryBot.define do
  factory :user do
    sequence(:username) { |n| "user#{n}" }
    password { "password123" }
    password_confirmation { "password123" }
    avatar_url { "https://example.com/avatar.jpg" }

    trait :with_articles do
      after(:create) do |user|
        create_list(:article, 3, user: user)
      end
    end
  end
end