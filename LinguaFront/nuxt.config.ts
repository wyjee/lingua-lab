// https://nuxt.com/docs/api/configuration/nuxt-config
export default defineNuxtConfig({
  ssr: true,
  modules: ['@pinia/nuxt'],
  routeRules: {
    '/api/translate-text': { proxy: 'http://nmt-demo.aidmtlabs.com/translate-text' }, // 외부 서버
    '/api/tts': { proxy: 'http://tts-demo.aidmtlabs.com/tts' },
    '/api/history': { proxy: 'http://localhost:8080/history' }, // 백엔드 서버 주소
    '/api/stat': { proxy: 'http://localhost:8080/stat' },

  },
  compatibilityDate: '2024-04-03',
  devtools: { enabled: true },
  pinia: {
    autoImports: ['defineStore', 'acceptHMRUpdate']
  },
  serverDir: './server',
  app: {
    head: {
      title: 'LinguaLab',
      meta: [
        { name: 'description', content: 'LinguaLab - Translator' }
      ],
    }
  },
})