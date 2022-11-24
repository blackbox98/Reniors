<template>
  <div class="home-notice">
    <div v-if="login">
      <p>
        🔔 오늘 확인하셔야 할 알림이
        <span class="home-notice-count" v-b-modal.noticeModal
          >{{ noticeNotReaded }}개</span
        >
        있어요!
      </p>
      <HomeCalendarList :login="login" />
    </div>
    <p v-else>
      지금 <router-link to="/login" class="now-login-btn">로그인</router-link>을
      하고<br />더 정확한 추천공고와 관리를 받아보세요!
    </p>
    <b-modal
      id="noticeModal"
      v-if="login"
      v-model="show"
      title="🔔 알림"
      hide-footer
    >
      <div v-if="notices.length != 0" class="notice-list d-block">
        <a v-for="(notice, index) in notices" :key="index">
          <div v-if="notice.isRead === 'READ'" class="notice-item-read">
            <p class="notice-item-company">
              <span
                @click="readNotification(notice)"
                class="notice-item-company-title"
                >[{{ notice.jobOpeningResponse.companyName }}]</span
              >
              <i @click="deleteNotification(notice.id)" class="bi bi-trash"></i>
            </p>
            <p class="notice-item-title" @click="readNotification(notice)">
              공고 : <span>{{ notice.jobOpeningResponse.title }}</span>
            </p>
            <p class="notice-item-result" @click="readNotification(notice)">
              지원 현황 :
              <span>{{ notice.jobOpeningProcess }}</span
              >으로 변경되었습니다.
            </p>
          </div>
          <div v-else class="notice-item-not-read">
            <p class="notice-item-company">
              <span
                @click="readNotification(notice)"
                class="notice-item-company-title"
                >[{{ notice.jobOpeningResponse.companyName }}]</span
              >
              <i @click="deleteNotification(notice.id)" class="bi bi-trash"></i>
            </p>
            <p class="notice-item-title" @click="readNotification(notice)">
              공고 : <span>{{ notice.jobOpeningResponse.title }}</span>
            </p>
            <p class="notice-item-result" @click="readNotification(notice)">
              지원 현황 :
              <span>{{ notice.jobOpeningProcess }}</span
              >으로 변경되었습니다.
            </p>
          </div>
        </a>
      </div>
      <div v-else class="notice-list d-block">
        <p>새로운 알림이 없습니다.</p>
      </div>
    </b-modal>
  </div>
</template>

<script>
import { mapGetters, mapActions } from "vuex";
import HomeCalendarList from "./HomeCalendarList.vue";

export default {
  name: "HomeNotice",
  components: {
    HomeCalendarList,
  },
  props: {
    login: Boolean,
  },
  watch: {
    async login() {
      if (this.login) {
        await this.fetchNotices();
      }
    },
  },
  computed: {
    ...mapGetters("home", ["noticeNotReaded", "notices"]),
  },
  methods: {
    ...mapActions("home", ["readNotice", "deleteNotice", "fetchNotices"]),
    readNotification(notice) {
      this.readNotice(notice.id);
      window.location.href =
        "https://i7b307.p.ssafy.io/jobopening/" +
        notice.applyResponse.jobOpeningId;
    },
    deleteNotification(notificationId) {
      if (confirm("삭제하시겠습니까?")) {
        this.deleteNotice(notificationId);
      }
    },
  },
  async created() {
    if (this.login) {
      await this.fetchNotices();
    }
  },
};
</script>

<style scoped>
.home-notice {
  padding: 0;
}

.home-notice p {
  text-align: left;
  margin-left: 10px;
  font-weight: bold;
}

.home-notice > div > p {
  font-weight: bold;
}

.home-notice > div > p > a {
  color: var(--color-green-1);
}

.home-notice-count {
  color: var(--color-green-1);
  text-decoration: none;
  font-size: 20px;
}

.home-notice-count:hover {
  cursor: pointer;
}

.notice-list > a {
  text-decoration: none;
  color: black;
}

.notice-list > a:hover {
  color: black;
}

.notice-item-read {
  background-color: var(--color-black-4);
  border-radius: 0.5rem;
  padding: 10px;
  margin-bottom: 10px;
  box-shadow: rgba(50, 50, 93, 0.25) 0px 2px 5px -1px,
    rgba(0, 0, 0, 0.3) 0px 1px 3px -1px;
}

.notice-item-read > p {
  margin: 0;
}

.notice-item-not-read {
  background-color: var(--color-red-3);
  border-radius: 0.5rem;
  padding: 10px;
  margin-bottom: 10px;
  box-shadow: rgba(50, 50, 93, 0.25) 0px 2px 5px -1px,
    rgba(0, 0, 0, 0.3) 0px 1px 3px -1px;
}

.notice-item-not-read > p {
  margin: 0;
}

.notice-item-company {
  display: flex;
  justify-content: space-between;
}

.notice-item-company-title {
  font-weight: bold;
  font-size: 18px;
  margin-bottom: 5px;
}

.notice-item-company > .bi-trash {
  width: 30px;
  margin-right: 5px;
  font-size: 20px;
  text-align: right;
}

.notice-item p {
  margin: 0;
}

.now-login-btn {
  color: var(--color-green-1);
  font-weight: bold;
  font-size: 18px;
}

.now-login-btn:hover {
  color: var(--color-green-1);
}
</style>
