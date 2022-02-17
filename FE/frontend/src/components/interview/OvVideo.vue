<template>
	<video autoplay v-if="!this.selected" />  
	<video width="300" height="300"  autoplay v-else></video>
</template>

<script>
import { mapState } from 'vuex'
export default {
	name: 'OvVideo',
	
	props: {
		streamManager: Object,
		},
	computed: {
		...mapState(["selected"]),
	},
	methods: {
	},
	mounted () {
		this.streamManager.addVideoElement(this.$el);
		console.log('안녕' + this.selected)
	},
};
</script>
<style scoped>
.speakon {
	border-style: dashed;
}
.speakoff {

}

</style>