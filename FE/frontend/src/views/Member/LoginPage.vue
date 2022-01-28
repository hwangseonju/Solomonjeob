<template>
	<AppHeader></AppHeader>

	<div>
		<LoginForm></LoginForm>
	</div>
</template>

<script>
import AppHeader from '@/components/common/AppHeader.vue';

import LoginForm from '@/components/LoginForm.vue';
export default {
  name: 'Login',
  components: {
		LoginForm,
		AppHeader,

	},
};
</script>

<style></style>
