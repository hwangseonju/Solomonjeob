<template>
  <AppHeader></AppHeader>

  <div>
    <SignupForm></SignupForm>

  </div>
</template>

<script>
import SignupForm from '@/components/SignupForm.vue'
import AppHeader from '@/components/common/AppHeader.vue';

export default {
    components: {
      SignupForm,
      AppHeader,

    },
}
</script>


<style>

</style>