<template>

  <div>
    <router-view></router-view>
    <Footer></Footer>
  </div>
</template>

<script>
import Footer from '@/components/Footer.vue'
// import Home from '@/views/Home.vue';

export default {
  components: {
    Footer
    
  },
};
</script>

<style></style>
