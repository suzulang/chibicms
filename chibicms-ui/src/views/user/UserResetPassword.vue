<script setup>
import { ref } from 'vue'
import useUserInfoStore from '@/stores/userInfo.js'
import {useTokenStore} from '@/stores/token.js'
import {useRouter} from 'vue-router'
const router = useRouter();
const tokenStore = useTokenStore();
const userInfoStore = useUserInfoStore();
const pwdData = ref({
    old_pwd: '',
    new_pwd: '',
    re_pwd: '',
})
const rules = {
    old_pwd: [
        { required: true, message: '请输入原密码', trigger: 'blur' },
        {
            pattern: /^\S{5,16}$/,
            message: '原密码必须是5-16位的非空字符串',
            trigger: 'blur'
        }
    ],
    new_pwd: [
        { required: true, message: '请输入新密码', trigger: 'blur' },
        {
            pattern: /^\S{5,16}$/,
            message: '新密码必须是5-16位的非空字符串',
            trigger: 'blur'
        }
    ],
    re_pwd: [
        { required: true, message: '请再次输入新密码', trigger: 'blur' },
        {
            pattern: /^\S{5,16}$/,
            message: '确认新密码必须是5-16位的非空字符串',
            trigger: 'blur'
        },
        {
            validator: (rule, value, callback) => {
                if (value !== pwdData.value.new_pwd) {
                    callback(new Error('两次输入的新密码不一致'));
                } else {
                    callback();
                }
            },
            trigger: 'blur'
        }
    ]
}
//修改个人信息
import {userUpdatePwdService} from '@/api/user.js'
import {ElMessage} from 'element-plus'
const userUpdatePwd = async ()=>{
    console.log(pwdData);
    //调用接口
    let result = await userUpdatePwdService(pwdData.value);
    //1.清空pinia中存储的token以及个人信息
    tokenStore.removeToken()
    userInfoStore.removeInfo()

    //2.跳转到登录页面
    router.push('/login')
    ElMessage.success(result.msg? result.msg : '修改成功');
}
</script>
<template>
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>基本资料</span>
            </div>
        </template>
        <el-row>
            <el-col :span="12">
                <el-form :model="pwdData" :rules="rules" label-width="100px" size="large">
                    <el-form-item label="原密码" prop="old_pwd">
                        <el-input type="password" v-model="pwdData.old_pwd"></el-input>
                    </el-form-item>
                    <el-form-item label="新密码" prop="new_pwd">
                        <el-input type="password" v-model="pwdData.new_pwd"></el-input>
                    </el-form-item>
                    <el-form-item label="确认新密码" prop="re_pwd">
                        <el-input type="password" v-model="pwdData.re_pwd"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="userUpdatePwd">提交修改</el-button>
                    </el-form-item>
                </el-form>
            </el-col>
        </el-row>
    </el-card>
</template>
