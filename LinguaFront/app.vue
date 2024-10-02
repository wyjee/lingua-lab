<template>
  <div>
    <NuxtLayout>
      <Header />
      <NuxtPage/>
      <Footer />
    </NuxtLayout>
  </div>
</template>

<script>
import Header from '~/components/Header.vue'
import Footer from '~/components/Footer.vue'

export default {
  components: {
    Header,
    Footer,
  }
}
</script>
<style src="assets/style/global.css" lang="css">
</style>