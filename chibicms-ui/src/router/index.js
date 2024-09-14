import { createRouter, createWebHistory } from 'vue-router'

//导入组件
import LoginVue from '@/views/Login.vue'
import LayoutVue from '@/views/Layout.vue'
import ArticleCategoryVue from '@/views/article/ArticleCategory.vue'
import ArticleManageVue from '@/views/article/ArticleManage.vue'
import UserAvatarVue from '@/views/user/UserAvatar.vue'
import UserInfoVue from '@/views/user/UserInfo.vue'
import UserResetPasswordVue from '@/views/user/UserResetPassword.vue'
import IndexVue from '@/views/Index.vue'
import ArticleDetailVue from '@/views/article/ArticleDetail.vue'
import { useTokenStore } from '@/stores/token.js';

//定义路由关系
const routes = [
    { path: '/login', component: LoginVue },
    {
        path: '/', component: LayoutVue,redirect: '/index', children: [
            { path: '/article/category', component: ArticleCategoryVue },
            { path: '/article/manage', component: ArticleManageVue },
            { path: '/user/info', component: UserInfoVue },
            { path: '/user/avatar', component: UserAvatarVue },
            { path: '/user/resetPassword', component: UserResetPasswordVue },
        ]
    },
    { path: '/index', component: IndexVue},
    { path: '/article/detail/:id', component: ArticleDetailVue },
]


//创建路由器
const router = createRouter({
    history: createWebHistory(),
    routes: routes
})
router.beforeEach((to, from, next) => {
    const tokenStore = useTokenStore();
    if (to.path === '/login' && tokenStore.token) {
      // 如果用户尝试访问登录页但已经有token，则重定向到/article/manage
      next('/article/manage');
    } else {
      // 其他情况正常处理
      next();
    }
  });
//导出路由
export default router
