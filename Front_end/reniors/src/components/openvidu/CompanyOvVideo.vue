<template>
  <video autoplay></video>
</template>

<script>
export default {
  name: "OvVideo",

  props: {
    streamManager: Object,
  },

  mounted() {
    this.streamManager.addVideoElement(this.$el);
  },
};
</script>

<style scoped>
video {
  height: 38vh;
  width: auto;
}
</style>
