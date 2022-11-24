<template>
  <div>
    <bookmark-history-card v-for="bookmark in bookmarks" :bookmark="bookmark" :key="bookmark">
    </bookmark-history-card>


  </div>
</template>
<script>
import BookmarkHistoryCard from '@/components/user/BookmarkHistoryCard.vue'
import { mapActions, mapGetters } from 'vuex'
export default {
  name: 'BookmarkHistoryView',
  components: { BookmarkHistoryCard },
  data() {
    return {
      example: '',
    }
  },
  computed: {
    ...mapGetters('jobopening', ['bookmarks'])
  },
  setup() {},
  created() {
    this.fetchBookmark()
  },
  mounted() {},
  unmounted() {},
  methods: {
    ...mapActions('jobopening', ['fetchBookmark'])
  }
}
</script>