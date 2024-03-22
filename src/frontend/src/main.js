import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css'
import '../public/assets/css/style.css'

import 'boxicons'
import 'boxicons/css/boxicons.min.css'

// createApp(App).use(router).mount('#app').use(store)
const app = createApp(App);
app.use(router).mount('#app')

