import axios from "axios";
import drf from "@/api/drf";
import _ from "lodash";
import http from "@/api/http";
import router from "@/router";
import { user } from "./user";

export default {
  namespaced: true,
  state: {
    tags: ["#서울 송파구", "#연봉 3000", "#주5일", "#웹디자인", "#백엔드"],
    jobopenings: [],
    selectedJobopening: {},
    recommendJobopenings: [],
    isrecommend: null,
    isrecommendheight: null,
    conditionJobopenings: [],
    bookmarks: [],
    applies: [],
    interview: [],
    bookmarksdate: [],
    isLast: false,
    currPage: 0,
    setRecommendCondition: false,
  },
  getters: {
    tags: (state) => state.tags,
    isJobopenings: (state) => !_.isEmpty(state.jobopenings),
    jobopenings: (state) => state.jobopenings,
    authHeader: () => ({
      Authorization: `Bearer ${user.state.token}`,
      "Content-type": "Application/JSON",
    }),
    recommendJobopenings: (state) => state.recommendJobopenings,
    isrecommend: (state) => state.isrecommend,
    isrecommendheight: (state) => state.isrecommendheight,
    conditionJobopenings: (state) => state.conditionJobopenings,
    selectedJobopening: (state) => state.selectedJobopening,
    bookmarks: (state) => state.bookmarks,
    bookmarkId: (state) =>
      state.bookmarks.find(
        (bookmark) =>
          bookmark.jobOpeningResponse.id === state.selectedJobopening.id,
      )?.id,
    applies: (state) => state.applies,
    isApply: (state) => {
      if (
        state.applies.find(
          (apply) => apply.jobOpeningId === state.selectedJobopening.id,
        ) === undefined
      )
        return false;
      else return true;
    },
    interview: (state) => state.interview,
    bookmarksdate: (state) => state.bookmarksdate,
    isLast: (state) => state.isLast,
    currPage: (state) => state.currPage,
    setRecommendCondition: (state) => state.setRecommendCondition,
  },
  mutations: {
    TAGS: (state, tags) => (state.tags = tags),
    JOBOPENINGS: (state, jobopenings) =>
      jobopenings.forEach((element) => {
        state.jobopenings.push(element);
      }),
    RECOMMENDCLEAR: (state) => {
      (state.isrecommend = null), (state.isrecommendheight = null);
      state.recommendJobopenings = [];
    },
    RECOMMENDJOBOPENINGS: (state, recommendJobopenings) => {
      state.recommendJobopenings = recommendJobopenings;
      if (!recommendJobopenings) {
        state.isrecommend = false;
        state.isrecommendheight = 76;
      } else {
        state.isrecommend = true;
        state.isrecommendheight = 380;
      }
    },
    CLEAR_JOBOPENINGS: (state) => (state.jobopenings = []),
    CONDITIONJOBOPENINGS: (state, conditionJobopenings) =>
      (state.conditionJobopenings = conditionJobopenings),
    SELECTJOB: (state, jobopening) => (state.selectedJobopening = jobopening),
    BOOKMARKS: (state, bookmarks) => (state.bookmarks = bookmarks),
    APPLIES: (state, applies) => (state.applies = applies),
    INTERVIEW: (state, applies) => {
      state.interview = [];
      applies.forEach((apply) => {
        const object = {
          title: apply.jobOpeningName,
          date: apply.interviewDate,
          child: apply.jobChildCategoryName,
        };
        state.interview.push(object);
      });
    },
    BOOKMARKSDATE: (state, bookmarks) => {
      state.bookmarksdate = [];
      bookmarks.forEach((bookmark) => {
        const object1 = {
          title: bookmark.jobOpeningResponse.title,
          date: bookmark.jobOpeningResponse.finishedDate,
          company: bookmark.jobOpeningResponse.companyName,
          link:
            "https://i7b307.p.ssafy.io/jobopening/" +
            bookmark.jobOpeningResponse.id,
        };

        state.bookmarksdate.push(object1);
      });
    },
    SET_IS_LAST: (state, isLast) => (state.isLast = isLast),
    SET_CURR_PAGE: (state, currPage) => (state.currPage = currPage),
    SET_RECOMMENDCONDITION: (state, setRecommendCondition) =>
      (state.setRecommendCondition = setRecommendCondition),
  },
  actions: {
    async clearJobopenings({ commit }) {
      await commit("CLEAR_JOBOPENINGS");
    },
    setCurrPage({ commit }, page) {
      commit("SET_CURR_PAGE", page);
    },
    async fetchJobopenings({ commit }, request) {
      await axios({
        url: drf.jobopening.get(),
        method: "get",
        params: {
          page: request.page,
          size: 10,
        },
      }).then((res) => {
        commit("SET_IS_LAST", res.data.last);
        commit("JOBOPENINGS", res.data.content);
      });
    },
    async fetchJobopeningsName({ commit, getters }, data) {
      await axios({
        url: `https://i7b307.p.ssafy.io/api/jobopening/search/keyword`,
        method: "post",
        headers: getters.authHeader,
        data: data,
      })
        .then(({ data }) => {
          commit("CLEAR_JOBOPENINGS");
          commit("SET_IS_LAST", true);
          commit("JOBOPENINGS", data.content);
        })
        .catch((error) => {
          console.log(error);
        });
    },

    async clearRecommend({ commit }) {
      commit("RECOMMENDCLEAR");
    },

    async fetchRecommend({ commit, getters, dispatch }) {
      await axios({
        url: `https://i7b307.p.ssafy.io/api/recommendcondition`,
        method: "get",
        headers: getters.authHeader,
      })
        .then(async ({ data }) => {
          if (data) {
            await dispatch("fetchJobOpeningRecommend", data.id);
            commit("SET_RECOMMENDCONDITION", true);
          } else {
            commit("SET_RECOMMENDCONDITION", false);
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
    async fetchJobOpeningRecommend({ commit, getters }, data) {
      await axios({
        url: `https://i7b307.p.ssafy.io/api/jobopening/search/recommend/${data}`,
        method: "get",
        headers: getters.authHeader,
      })
        .then(({ data }) => {
          if (data) {
            commit("RECOMMENDJOBOPENINGS", data.content);
          } else {
            commit("RECOMMENDJOBOPENINGS", null);
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },

    async fetchJobOpeningCondition({ commit, getters }, data) {
      await axios({
        url: `https://i7b307.p.ssafy.io/api/jobopening/search/${data}`,
        method: "get",
        headers: getters.authHeader,
      })
        .then(({ data }) => {
          commit("CONDITIONJOBOPENINGS", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },

    async fetchApplied({ commit }) {
      commit("JOBOPENINGS", []);
      const response = await http.get("/jobopening/apply");
      commit("JOBOPENINGS", response.data);
    },

    async fetchBookmarked({ commit }) {
      commit("JOBOPENINGS", []);
      const response = await http.get("/jobopening/bookmark");
      commit("JOBOPENINGS", response.data);
    },
    async selectJobopening({ commit }, id) {
      const response = await axios.get(drf.jobopening.detail(id));
      const data = response.data;
      commit("SELECTJOB", data);
    },
    async fetchApply({ commit }) {
      const response = await http.get("/jobopening/apply");
      commit("APPLIES", response.data);
      commit("INTERVIEW", response.data);
    },
    async apply({ dispatch }, jobopeningId) {
      await http.post(`/jobopening/${jobopeningId}/apply`);
      alert("지원 성공!");
      dispatch("fetchApply");
      router.go(0);
    },
    async fetchBookmark({ commit }) {
      axios({
        url: "https://i7b307.p.ssafy.io/api/jobopening/bookmark",
        method: "get",
        headers: {
          "Content-Type": "application/json",
          Authorization: "Bearer " + localStorage.getItem("token"),
        },
      })
        .then(async (response) => {
          await commit("BOOKMARKS", response.data);
          await commit("BOOKMARKSDATE", response.data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    async addBookmark({ dispatch }, id) {
      await http.post(`/jobopening/bookmark/${id}`);
      dispatch("fetchBookmark");
    },
    async deleteBookmark({ dispatch }, id) {
      await http.delete(`/jobopening/bookmark/${id}`);
      dispatch("fetchBookmark");
    },
  },
};
