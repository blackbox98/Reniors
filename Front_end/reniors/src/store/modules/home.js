import axios from "axios";
import _ from "lodash";
import http from "@/api/http";

export default {
  namespaced: true,
  state: {
    isLogin: false,
    hotJobopenings: [],
    recommendJobopenings: [],
    youtubes: [],
    isYoutube: false,
    notices: [],
    noticeNotReaded: null,
  },
  getters: {
    isLogin: (state) => state.isLogin,
    hotJobopenings: (state) => state.hotJobopenings,
    recommendJobopenings: (state) => state.recommendJobopenings,
    youtubes: (state) => state.youtubes,
    isYoutube: (state) => !_.isEmpty(state.youtubes),
    notices: (state) => state.notices,
    noticeNotReaded: (state) => state.noticeNotReaded,
  },
  mutations: {
    IS_LOGIN: (state, value) => (state.isLogin = value),
    YOUTUBES: (state, youtubes) => (state.youtubes = youtubes),
    HOTJOBOPENINGS: (state, hots) => (state.hotJobopenings = hots),
    NOTICES: (state, notices) => (state.notices = notices),
    NOTICE_NOT_READED: (state, noticeNotReaded) =>
      (state.noticeNotReaded = noticeNotReaded),
    DUMMY: () => 0,
  },
  actions: {
    async createNotice({ commit }, notice) {
      const response = await http.post("/notification", notice);
      commit("NOTICES", response.data);
    },
    async readNotice({ dispatch }, notificationId) {
      await http.get(`/notification/${notificationId}`);
      dispatch("fetchNotices");
    },
    async deleteNotice({ dispatch }, notificationId) {
      await http.delete(`/notification/${notificationId}`);
      dispatch("fetchNotices");
    },
    async fetchYoutubes({ commit }, keyword) {
      commit("YOUTUBES", []);
      const response = await axios.get(
        "https://www.googleapis.com/youtube/v3/search",
        {
          params: {
            part: "snippet",
            type: "video",
            q: keyword,
            key: process.env.VUE_APP_YOUTUBE_API_KEY,
          },
        },
      );
      const youtubes = response.data.items;
      commit("YOUTUBES", youtubes);
    },
    async fetchHot({ commit }) {
      const response = await http.get("/jobopening/search/viewsDesc");
      commit("HOTJOBOPENINGS", response.data);
    },
    async search({ commit }, keyword) {
      console.log(keyword);
      commit("DUMMY");
    },
    async fetchNotices({ commit }) {
          axios({
            url: "https://i7b307.p.ssafy.io/api/notification",
            method: "get",
            headers: {
              "Content-Type" : "application/json",
              Authorization : "Bearer "+localStorage.getItem("token"),
            }
          })
          .then(async (response) => {
            await commit("NOTICES", response.data);
            let notReadNotices = 0;
            await response.data.forEach((notice) => {
              if (notice.isRead === "NOT_READ") {
                notReadNotices += 1;
              }
            });
            await commit("NOTICE_NOT_READED", notReadNotices);
          })
          .catch((err)=>{
            console.log(err);
          })
    },
  },
};
