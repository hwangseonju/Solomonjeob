<template>
<div v-if="streamManager">
	<ov-video :stream-manager="streamManager"/>
	<div><p>면접자 : {{ clientData }}</p></div>

</div>
</template>

<script>
import OvVideo from '@/components/interview/OvVideo.vue';
export default {
	name: 'UserVideo',
	components: {
		OvVideo,
	},
	props: {
		streamManager: Object,
	},
	computed: {
		clientData () {
			const { clientData } = this.getConnectionData();
			console.log(clientData)
			console.log('77777777777777')
			return clientData;
		},
	},
	methods: {
		getConnectionData () {
			const { connection } = this.streamManager.stream;
			return JSON.parse(connection.data);
		},
	},
};
</script>
<style>

</style>