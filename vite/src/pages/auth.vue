<template>

  <div>
    <var-icon class="theme button var-elevation--5" :name="icon" @click="toggleTheme()" size=50 transition="300"/>
    <div class="box var-elevation--5">
      <transition name="sign-in-container">
        <div v-if="show" class="sign-in container">  
        <h3>用户登陆</h3>
        <!-- <var-space align="center" justify="center">
          <var-icon name="checkbox-marked-circle" />
          <var-icon name="checkbox-marked-circle" />
          <var-icon name="checkbox-marked-circle" />
        </var-space>
        <p>可以使用以上方式登陆</p> -->
        <var-input clearable placeholder="邮箱" :rules="[v => $regexEmail.test(v) || '请填写正确的邮箱']" v-model="login.email"/>
        <var-input clearable placeholder="密码" :rules="[v => $regexPwd.test(v) || '密码格式错误,请使用字母或数字或下划线并且长度大于8位']" type="password" v-model="login.pwd"/>
        <p class="button rest">忘记密码?</p>
        <var-button :loading="load.login" type="primary" @click="signIn()">登陆</var-button>
      </div>

      </transition>
      
      <transition name="sign-in-toggle">
        <div v-if="!show" class="sign-up toggle">
        <h3>已有账号</h3>
        <p>赶快登陆吧</p>
        <span class="text_button button" @click="show = !show">登陆</span>
      </div>

      </transition>
      
      <transition name="sign-up-toggle">
        <div v-if="show" class="sign-in toggle">
        <h3>没有账号?</h3>
        <p>赶快去注册一个属于你的账号吧</p>
        <span class="text_button button" @click="show = !show">注册</span>
      </div>
      </transition>
      
      <transition name="sign-up-container">
        <div v-if="!show" class="sign-up container">
        <h3>用户注册</h3>
        <!-- <var-space align="center" justify="center">
          <var-icon name="checkbox-marked-circle" />
          <var-icon name="checkbox-marked-circle" />
          <var-icon name="checkbox-marked-circle" />
        </var-space>
        <p>可以使用以上方式注册</p> -->
        <var-input clearable placeholder="昵称" v-model="register.name"/>
        <var-input :rules="[v => $regexPwd.test(v) || '请使用长度大于8位的密码']" clearable type="password" placeholder="密码" v-model="register.pwd"/>
        <var-input :rules="[v => $regexEmail.test(v) || '请填写正确的邮箱地址']"  clearable placeholder="邮箱" v-model="register.email"/>
        <var-button :loading="load.register" clearable type="primary" @click="signUp()">注册</var-button>
      </div>
      </transition>
      
    </div>
  </div>
  
</template>

<script setup>
  import {getCurrentInstance, onBeforeMount, reactive, ref} from "vue";
  import dark from '@varlet/ui/es/themes/dark'
  import {Snackbar, StyleProvider} from "@varlet/ui";
  import {request} from '../js/request'


  const theme = window.location.href.split('?')[1]
  const {proxy} = getCurrentInstance()

  let show = ref(true)
  let icon = ref('weather-night')
  let currentTheme = null
  let load = reactive({})
  let login = reactive({})

  let register = reactive({})

  

  onBeforeMount(() =>{
    if(theme === 'dark'){
      StyleProvider(dark)
      icon.value = 'weather-night'
      currentTheme = dark
    }else{
      icon.value = 'white-balance-sunny'
    }
  })

  function toggleTheme(){
    icon.value = currentTheme ? 'white-balance-sunny'  :'weather-night'
    currentTheme = currentTheme ? null : dark
    StyleProvider(currentTheme)
  }
  
  function signIn(){
    load.login = true
    if(proxy.$regexPwd.test(login.pwd) && proxy.$regexEmail.test(login.email)){
      login.pwd = CryptoJS.MD5(login.pwd).toString()
      request.post('/login',login).then(res =>{
        if(res.data.state){
          Snackbar.success("登陆成功,正在跳转")
          window.location.href = 'https://store.spingas.com/#/oauth?'+res.data.message
        }else{
          Snackbar.error(res.data.message)
        }
      }).finally(() =>{
        load.login = false
      })
    }else{
      Snackbar.error('请填写正确的信息')
      load.login = false
    }
  }

  function signUp(){
    load.register = true 
    if(proxy.$regexPwd.test(register.pwd) && proxy.$regexEmail.test(register.email)){
      register.pwd = CryptoJS.MD5(register.pwd).toString()
      request.post('/users',register).then(res =>{
        Snackbar.success('注册成功,快去登陆吧')
        show.value = !show.value
      }).catch(() =>{
        Snackbar.error('此邮箱已被占用')
      }).finally(() =>{
        register.name = '',
        register.pwd = ''
        register.email = '',
        register.code = 0
        load.register = false
      })
    }else{
      Snackbar.error('请填写正确的信息')
      load.register = false
    }
  }
</script>
