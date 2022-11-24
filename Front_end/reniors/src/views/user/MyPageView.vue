<template>
    <div class="header">
      <i @click="prevpage()" class="bi bi-arrow-left-circle-fill"></i>
      <router-link class="header-logo" :to="{ name: 'home' }">
        <img src="@/assets/logo_cut.png" alt="logo" />
      </router-link>
      <div></div>
    </div>
  <br />
  <br />
  <div class="mypage-box">
    <br />
    <br />
    <div style="width: 312px; margin: auto">
      <div class="profileimg">
        <img
          class="person"
          :src="this.currentUser.baseURL + this.currentUser.userProfile"
          alt="person"
        />
      </div>
      <br />
      <div class="myinfo">
        <div class="name">{{ this.currentUser.name }}</div>
        <router-link
          :to="{ name: 'MyinfoEdit' }"
          style="
            text-align: left;
            text-decoration: none;
            color: #6d6d6d;
            font-weight: 700;
            font-size: 13px;
          "
          ><i class="bi bi-gear-fill" style="color: #37bf99"></i>&nbsp;내정보
          수정</router-link
        >
      </div>

      <button class="resume">
        <router-link
          :to="{ name: 'ResumeDetail' }"
          style="
            text-decoration: none;
            color: white;
            font-weight: 700;
            font-size: 18px;
          "
          ><img
            style="
              align: absmiddle;
              filter: drop-shadow(0px 0.5px 0.5px rgba(0, 0, 0, 0.25));
              margin-right: 10px;
            "
            src="@/assets/note.svg"
            alt="note"
          />내 이력서</router-link
        >
      </button>
      <div v-if="this.prefer.jobParentCategoryResponse.name">
        <div class="interest">
          <div class="interest1">
            <p
              style="
                margin: 8px;
                font-size: 14px;
                color: #2a2a2a;
                font-weight: 700;
              "
            >
              {{ this.prefer.jobParentCategoryResponse.name }}
            </p>
          </div>
          <div class="interest1">
            <p
              style="
                margin: 8px;
                font-size: 14px;
                color: #2a2a2a;
                font-weight: 700;
              "
            >
              {{ this.prefer.gugunResponse.name }}
            </p>
          </div>
        </div>
        <div class="rechoice">
          <router-link
            :to="{ name: 'UpdateSetting' }"
            style="
              text-decoration: none;
              color: #6d6d6d;
              font-weight: 800;
              font-size: 13px;
            "
            >관심 다시 설정하기
            <i class="bi bi-reply-all-fill" style="color: #37bf99"></i
          ></router-link>
        </div>
      </div>
      <div v-else>
        <router-link
          :to="{ name: 'CreateSetting' }"
          style="
            text-decoration: none;
            color: #6d6d6d;
            font-weight: 600;
            font-size: 14px;
            color: #37bf99;
          "
          >관심 설정하기</router-link
        >
      </div>
    </div>

    <hr />

    <div style="width: 312px; margin: auto" class="ing">
      <div style="display: flex; justify-content: flex-end">
        <router-link :to="{ name: 'MyCalendarView' }" class="calendar"
          ><img
            style="
              width: 23px;
              height: 23px;
              align: absmiddle;
              filter: drop-shadow(0px 0.5px 0.5px rgba(0, 0, 0, 0.25));
            "
            src="@/assets/calendar.svg"
            alt="calendar"
          />
          나의 <span>일정</span> 한눈에 보기!</router-link
        >
      </div>
      <br />
      <div style="float: left; margin-left: 5px; font-size: 14px">
        <button
          @click="applyshow"
          v-if="showleft"
          class="show"
          style="margin-right: 7px"
        >
          지원 이력
        </button>
        <button
          @click="applyshow"
          v-else
          class="nonshow"
          style="margin-right: 7px"
        >
          지원 이력
        </button>
        <button @click="bookmarkshow" v-if="showleft" class="nonshow">
          관심 공고
        </button>
        <button @click="bookmarkshow" class="show" v-else>관심 공고</button>
      </div>
      <br />
      <br />
      <apply-history-view v-show="showleft" :applies="applies">
      </apply-history-view>
      <bookmark-history-view v-show="showleft == false">
      </bookmark-history-view>
    </div>

    <br />
    <br />
  </div>
</template>
<script>
import ApplyHistoryView from "@/views/user/ApplyHistoryView.vue";
import BookmarkHistoryView from "@/views/user/BookmarkHistoryView.vue";
import { mapActions, mapGetters } from "vuex";

export default {
  name: "MyPageView",
  components: { ApplyHistoryView, BookmarkHistoryView },
  data() {
    return {
      showleft: true,
    };
  },
  computed: {
    ...mapGetters(["currentUser", "prefer"]),
    ...mapGetters("jobopening", ["bookmarks", "applies"]),
  },
  mounted() {},
  unmounted() {},
  created() {
    this.fetchCurrentUser();
    this.fetchPrefer();
    this.fetchBookmark();
    this.fetchApply();
  },
  methods: {
    ...mapActions([
      "fetchCurrentUser",
      "fetchPrefer",
      "fetchBookmark",
      "fetchApply",
    ]),
    ...mapActions("jobopening", ["fetchBookmark", "fetchApply"]),
    applyshow() {
      this.showleft = true;
    },
    bookmarkshow() {
      this.showleft = false;
    },
        prevpage() {
      this.$router.go(-1);
    },
  },
};
</script>

<style scoped>
.header {
  height: 48px;
  background-color: white;
  box-shadow: rgba(99, 99, 99, 0.2) 0px 2px 8px -2px;
  display: flex;
  align-items: center;
  justify-content: space-around;
  position: fixed;
  width: 100vw;
  top: 0;
}
.header img {
  height: 38px;
  margin: auto;
  width: auto;
}
.header > .bi {
  color: var(--color-red-1);
  font-size: 30px;
  cursor: pointer;
}
.header > div {
  width: 30px;
}
.mypage-box{
  width: 312px;
}
.title {
  font-size: 22px;
  margin-top: 4px;
  color: #ffb400;
  font-weight: 900;
}
.person {
  width: 80px;
  height: 80px;
  border-radius: 500px;
  float: left;
  box-shadow: rgba(149, 157, 165, 0.2) 0px 8px 24px;
}

.myinfo {
  display: flex;
  flex-direction: column;
  padding-left: 30px;
}

button {
  border-radius: 10px;
  border-style: none;
  filter: drop-shadow(0px 1px 1px rgba(0, 0, 0, 0.25));
}

.myinfo > button {
  width: 108px;
  height: 32px;
  font-size: 13px;
  font-weight: 700;
  border-style: solid;
  border-color: #37bf99;
  background-color: white;
}

.name {
  text-align: left;
  font-size: 18px;
  font-weight: 600;
  color: #ffb252;
}

.resume {
  width: 312px;
  height: 48px;
  margin-top: 20px;
  background-color: #ffb252;
  margin-bottom: 10px;
}

.interest {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  margin-bottom: 10px;
}

.interest1 {
  width: 150px;
  height: 40px;
  background-color: #f9f9f9;
  border-radius: 10px;
}

.rechoice {
  display: flex;
  justify-content: flex-end;
  filter: drop-shadow(0px 0.5px 0.5px rgba(0, 0, 0, 0.25));
}

.calendar {
  text-decoration: none;
  color: black;
  font-weight: 800;
  font-size: 15px;
  filter: drop-shadow(0px 0.5px 0.5px rgba(0, 0, 0, 0.25));
}

.calendar > span {
  color: #ff843e;
}

.show {
  background-color: #ff843e;
  color: white;
  padding: 5px;
  font-weight: 700;
}

.nonshow {
  background-color: #f9f9f9;
  padding: 5px;
  color: #6d6d6d;
  font-weight: 500;
}
</style>
