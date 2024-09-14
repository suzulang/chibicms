import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useArticleStore = defineStore('articles', () => {
  // 定义状态
  const articles = ref([])

  // 设置文章列表
  const setArticles = (newArticles) => {
    articles.value = newArticles
  }

  // 异步加载文章数据（模拟）
  const loadArticles = async () => {
    // 假设这是从API获取数据的异步操作
    const response = await fetch('/api/article/all');
    const result = await response.json();
    setArticles(result.data); // 确保这里正确地引用了 data 字段

  }

  return { articles, setArticles, loadArticles }
}, {
  // 启用持久化状态
  persist: true
});
