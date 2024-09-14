<template>
  <section class="bg-white">
    <div class="container px-6 py-10 mx-auto">
      <h2 class="text-3xl font-semibold text-center text-gray-800 mb-8">{{ title }}</h2>
      <div class="grid grid-cols-1 gap-8 mt-8 md:mt-16 md:grid-cols-2">
        <div v-for="post in posts" :key="post.id" class="lg:flex bg-gray-50 rounded-lg shadow-md overflow-hidden">
          <img class="object-cover w-full h-56 lg:w-64" :src="post.image" :alt="post.title">

          <div class="flex flex-col justify-between p-6">
            <a :href="post.link" class="text-xl font-semibold text-indigo-600 hover:underline">
              {{ post.title }}
            </a>
            
            <span class="text-sm text-gray-500">发布于: {{ post.date }}</span>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script setup>
import { defineProps } from 'vue';

const props = defineProps({
  title: {
    type: String,
    default: '博客文章'
  },
  posts: {
    type: Array,
    required: true
  }
});
</script>
