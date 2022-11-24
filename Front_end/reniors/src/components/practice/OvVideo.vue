<template>
  <video :class="{ smallVideo: isAnswer }" autoplay></video>
</template>

<script>
export default {
  name: "OvVideo",

  props: {
    streamManager: Object,
    isAnswer: Boolean,
  },
  watch: {},
  mounted() {
    this.streamManager.addVideoElement(this.$el);
  },
};
</script>

<style scoped>
.smallVideo {
  background-color: white;
  width: 600px;
  height: 400px;
  margin: 8px auto 4px auto;
  border: solid #ffd39b 1px;
  border-radius: 5px;
}
video {
  background-color: white;
  width: 600px;
  height: 560px;
  margin: 8px auto 4px auto;
  border: solid #ffd39b 1px;
  border-radius: 5px;
}
@media (max-width: 760px) {
  .smallVideo {
    width: 344px;
    height: 400px;
  }
  video {
    width: 344px;
    height: 560px;
  }
}
</style>
