<template>

	<video autoplay v-if="this.audioDetect" class="speakon"/>
	<video autoplay v-else-if="!this.audioDetect" />

</template>

<script>

export default {
	name: 'OvVideo',
	props: {
		streamManager: Object,
		audioDetect: Boolean,
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

</style>