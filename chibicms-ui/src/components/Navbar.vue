<script setup lang='ts'>
  document.addEventListener('DOMContentLoaded', () => {
    const menuToggle = document.getElementById('menu-toggle');
    const mobileMenu = document.getElementById('mobile-menu');

    menuToggle.addEventListener('click', function () {
      mobileMenu.classList.toggle('hidden');
    });
  });
</script>
<template>
  <nav class="bg-gradient-to-r from-indigo-500 to-purple-500 px-2 sm:px-4 py-2.5 shadow">
    <div class="container flex flex-wrap justify-between items-center mx-auto">
      <div class="flex items-center">
        <button
          id="menu-toggle"
          type="button"
          class="inline-flex items-center p-2 ml-3 text-sm text-gray-500 rounded-lg hover:bg-gray-100 focus:outline-none focus:ring-2 focus:ring-gray-200 dark:text-gray-400 dark:hover:bg-gray-700 dark:focus:ring-gray-600 md:hidden"
        >
          <span class="sr-only">Open main menu</span>
          <!-- Hamburger icon -->
          <svg class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
            <path
              stroke-linecap="round"
              stroke-linejoin="round"
              stroke-width="2"
              d="M4 6h16M4 12h16m-7 6h7"
            />
          </svg>
        </button>
      </div>

      <div
        class="w-full md:block md:w-auto hidden"
        id="mobile-menu"
      >
        <ul class="flex flex-col mt-4 md:flex-row md:space-x-8 md:mt-0 md:text-sm md:font-medium">
          <li>
            <a
              href="#"
              class="block py-2 pr-4 pl-3 text-white hover:bg-white/20 rounded md:bg-transparent md:p-0"
              aria-current="page"
            >
              主页
            </a>
          </li>
          <li>
            <router-link
              to="/login"
              class="block py-2 pr-4 pl-3 text-white hover:bg-white/20 rounded md:bg-transparent md:p-0"
            >
              登录
            </router-link>
          </li>
        </ul>
      </div>

    </div>
  </nav>
</template>

<style scoped>

</style>