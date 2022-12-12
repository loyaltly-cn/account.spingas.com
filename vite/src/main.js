import { createApp } from 'vue'
import App from './App.vue'
import '@varlet/ui/es/style.js'
import '@varlet/touch-emulator'
import Varlet from '@varlet/ui'
import './css/moblie.css'
import './css/pubilc.css'
import './css/transition.css'

const app = createApp(App)

app.use(Varlet)
Snackbar.allowMultiple(true)

app.config.globalProperties.$regexEmail = /\w[-\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\.)+[A-Za-z]{2,14}/
app.config.globalProperties.$regexPwd = /\w{8,}/

app.mount('#app')
