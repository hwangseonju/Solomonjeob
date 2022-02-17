<template>
	<video autoplay v-if="!this.selected" />  
	<video width="300" height="250" autoplay v-else></video>
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