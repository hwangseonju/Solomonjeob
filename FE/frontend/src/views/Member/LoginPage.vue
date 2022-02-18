<template>
	<AppHeader></AppHeader>

	<div v-if="!isLogin">
		<LoginForm></LoginForm> 
		<!-- 로그인 상태가 아니면 로그인폼을 보여주고
		로그인 상태면 어떤 페이지를 보여줘야할까 -->
	</div>
</template>

<script>
import AppHeader from '@/components/common/AppHeader.vue';
import LoginForm from '@/components/LoginForm.vue';
import { mapState, mapMutations } from 'vuex';
export default {
  name: 'Login',
  components: {
		LoginForm,
		AppHeader,
		

	},
	computed : {
	...mapState(["isLogin"],)
	},
   methods: {
	...mapMutations(["SET_IS_LOGIN"]),
   }
};
</script>

<style></style>
