<template>

	<video autoplay v-if="this.audioDetect" class="speakon"/>
	<video autoplay v-else-if="!this.audioDetect" class="speakoff" />

</template>

<script>
export default {
	name: 'OvVideo',
	props: {
		streamManager: Object,
		audioDetect: Boolean,
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