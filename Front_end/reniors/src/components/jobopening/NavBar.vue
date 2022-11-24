<template>
  <div class="jobopening-navbar">
    <div class="jobopening-navbar-left" @click="back">
      <svg class="navbar-svg" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512"><path d="M256 0C114.6 0 0 114.6 0 256c0 141.4 114.6 256 256 256s256-114.6 256-256C512 114.6 397.4 0 256 0zM384 288H205.3l49.38 49.38c12.5 12.5 12.5 32.75 0 45.25s-32.75 12.5-45.25 0L105.4 278.6C97.4 270.7 96 260.9 96 256c0-4.883 1.391-14.66 9.398-22.65l103.1-103.1c12.5-12.5 32.75-12.5 45.25 0s12.5 32.75 0 45.25L205.3 224H384c17.69 0 32 14.33 32 32S401.7 288 384 288z"/></svg>
      <h2>채용공고</h2>
    </div>
    <div class="jobopening-navbar-right">
      <font-awesome-icon class="bookmark-icon" v-if="isBookmarked" icon="fa-solid fa-bookmark" @click="deleteBookmark(bookmarkId)" />
      <font-awesome-icon class="bookmark-icon" v-else icon="fa-regular fa-bookmark" @click="addBookmark(jobopeningId)" />

    </div>
  </div>
</template>

<script>
import { computed } from 'vue'
import { useStore } from 'vuex'
import { useToast } from 'bootstrap-vue-3'
import { useRouter } from 'vue-router'

export default {
  name: 'NavBar',
  props: {
    jobopeningId: [String, Number],
  },
  setup() {
    const store = useStore()
    const toast = useToast()
    const router = useRouter()

    const currentUser = computed(() => store.getters['currentUser'])

    const isLogin = computed(() => store.getters['isLogginedIn'])
    const fetchBookmark = () => store.dispatch('jobopening/fetchBookmark')
    if (isLogin.value) {
      fetchBookmark()
    }
    const bookmarks = computed(() => store.getters['jobopening/bookmarks'])
    const bookmarkId = computed(() => store.getters['jobopening/bookmarkId'])
    const isBookmarked = computed(() => {
      if (bookmarkId.value === undefined) return false
      else return true
    })
    const addBookmark = (jobopeningId) => {
      toast.show({body: '북마크가 설정되었습니다.'}, {variant: 'warning', pos: 'top-center', delay: 1000})
      return store.dispatch('jobopening/addBookmark', jobopeningId)
    }
    const deleteBookmark = (bookmarkId) => {
      toast.show({body: '북마크가 해제되었습니다.'}, {variant: 'warning', pos: 'top-center', delay: 1000})
      store.dispatch('jobopening/deleteBookmark', bookmarkId)
    }

    const back = () => router.go(-1)

    return {
      currentUser,
      addBookmark, deleteBookmark, isLogin, bookmarks, bookmarkId, isBookmarked, back,
    }
  }
}
</script>

<style scoped>
.jobopening-navbar {
  position: sticky;
  top: 0;
  display: flex;
  justify-content: space-between;
  background-color: white;
  padding: 12px;
  box-shadow: rgba(0, 0, 0, 0.16) 0px 1px -4px;
}

.jobopening-navbar-left, .jobopening-navbar-right {
  display: flex;
  text-decoration: none;
}

.jobopening-navbar-left:hover {
  cursor: pointer;
}

.navbar-svg {
  filter: invert(87%) sepia(3%) saturate(6218%) hue-rotate(325deg) brightness(106%) contrast(100%);
  height: 24px;
  width: 24px;
}

.jobopening-navbar-right > .bookmark-icon {
  height: 24px;
  width: 24px;
  color: var(--color-yellow-1);
}

.jobopening-navbar-right > .bookmark-icon:hover {
  cursor: pointer;
}

.jobopening-navbar h2 {
  color: var(--color-yellow-1);
  font-size: 22px;
  font-weight: bold;
  margin: 0;
  margin-left: 10px;
}

.navbar-profile {
  position: relative;
  margin-left: 10px;
  height: 24px;
  width: 24px;
}

.navbar-profile > img {
  position: absolute;
  left: 12px;
  top: 12px;
  transform: translate(-50%, -50%);
  border-radius: 5rem;
  height: 32px;
  width: 32px;
}

.toast-message {
  position: fixed;
  display: flex;
  justify-content: center;
  top: 5px;
  left: 0;
  right: 0;
  margin: 0 auto;
}
</style>