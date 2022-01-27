<template>

  <div>
    <AppHeader></AppHeader>
    <router-view></router-view>
    <Footer></Footer>
  </div>
</template>

<script>
import AppHeader from '@/components/common/AppHeader.vue';
import Footer from '@/components/Footer.vue'
// import QuestionPage from '@/views/QuestionPage.vue'
// import LoginPage from '@/vies/Member/LoginPage.vue'
// import Home from '@/views/Home.vue';

export default {
  components: {
    AppHeader,
    Footer,
    // QuestionPage,
    // LoginPage
  },
  data() {
    return {
      // memberIdx: 0,
    }
  },
  // methods: {
  //   getMemberIdx(Idx) {
  //     this.memberIdx = Idx
  //   }

  // }
};
</script>

<style></style>
