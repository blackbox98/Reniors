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
  height: 35vh;
  width: 85vw;
}
</style>
