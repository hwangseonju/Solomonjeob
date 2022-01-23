<template>

  <div>
    <AppHeader></AppHeader>
    <router-view></router-view>
  </div>
</template>

<script>
import AppHeader from '@/components/common/AppHeader.vue';
// import Home from '@/views/Home.vue';

export default {
  components: {
    AppHeader,
    
  },
};
</script>

<style></style>
