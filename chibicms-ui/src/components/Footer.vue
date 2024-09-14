<template>
  <footer class="w-full text-gray-800 bg-white">
    <div class="container px-5 py-4 mx-auto">
      <p class="text-sm capitalize text-center">© {{ currentYear }} 版权所有</p>
    </div>
  </footer>
</template>

<script setup>
import { ref } from 'vue';

const currentYear = ref(new Date().getFullYear());
</script>

<style scoped>
/* 如果需要的话，这里可以添加一些样式 */
</style>