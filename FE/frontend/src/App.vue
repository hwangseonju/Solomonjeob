<template>

  <div>
    <AppHeader></AppHeader>
    <router-view></router-view>
    <!-- <Footer></Footer> -->
  </div>
</template>

<script>
import AppHeader from '@/components/common/AppHeader.vue';
// import Footer from '@/components/Footer.vue'
// import Home from '@/views/Home.vue';

export default {
  components: {
    AppHeader,
    // Footer
    
  },
};
</script>

<style></style>
