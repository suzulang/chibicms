<script setup lang='ts'>
import Navbar from '@/components/Navbar.vue';
import BlogSection from '@/components/BlogSection.vue';
import Footer from '@/components/Footer.vue';
import { ref } from 'vue';

const blogPosts = ref([
  {
    id: 1,
    title: 'How to use sticky note for problem solving',
    image: 'https://images.unsplash.com/photo-1515378960530-7c0da6231fb1?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1470&q=80',
    link: '#',
    date: '20 October 2019'
  },
  // ... 添加更多博客文章数据
]);
</script>

<template>
  <div class="flex flex-col min-h-screen">
    <Navbar />
    <main class="flex-grow flex">
      <BlogSection class="w-full" :posts="blogPosts" />
    </main>
    <Footer />
  </div>
</template>

<style scoped>
/* 你的样式 */
</style>
