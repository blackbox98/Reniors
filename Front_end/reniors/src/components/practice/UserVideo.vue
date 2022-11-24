<template>
  <div v-if="streamManager">
    <ov-video :stream-manager="streamManager" :isAnswer="isAnswer" />

  </div>
</template>

<script>
import OvVideo from "./OvVideo";

export default {
  name: "UserVideo",
  components: {
    OvVideo,
  },

  props: {
    streamManager: Object,
    isAnswer: Boolean
  },

  computed: {
    clientData() {
      const { clientData } = this.getConnectionData();
      return clientData;
    },
  },

  methods: {
    getConnectionData() {
      const { connection } = this.streamManager.stream;
      return JSON.parse(connection.data);
    },
  },
};
</script>
