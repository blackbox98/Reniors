import axios from "axios";
import router from "@/router";
import qs from "qs";

export default {
  state: {
    questions: [],
    answer: {},
    checklist: [],
    records: [],
    selected: [],
    vitotoken: {},
    vitoId: {},
    subtitles: {},
  },

  getters: {
    questions: (state) => state.questions,
    answer: (state) => state.answer,
    checklist: (state) => {
      var list = [];
      for (let index = 0; index < state.checklist.length; index++) {
        list.push(state.checklist[index].questionId);
      }
      return list.sort();
    },
    records: (state) => state.records,
    selected: (state) => state.selected,
    vitotoken: (state) => state.vitotoken,
    vitoId: (state) => state.vitoId,
    subtitles: (state) => state.subtitles,
  },

  mutations: {
    CLEAR_QUESTIONS: (state) => {
      state.questions = null;
    },
    SET_QUESTIONS: (state, questions) => {
      state.questions = questions;
    },
    SET_ANSWER: (state, answer) => (state.answer = answer),
    CLEAR_ANSWER: (state) => (state.answer = ""),
    SET_CHECKLIST: (state, checklist) => (state.checklist = checklist),
    SET_RECORDS: (state, records) => (state.records = records),
    CLEAR_SELECTED: (state, data) => {
      state.selected = data;
    },
    SET_SELECTED: (state, selected) => {
      state.selected = selected;
    },
    PUT_SELECTED: (state, selected) => state.selected.push(selected),
    SET_VITOTOKEN: (state, token) => (state.vitotoken = token),
    SET_VITOID: (state, id) => (state.vitoId = id),
    SET_SUBTITLES: (state, sub) => (state.subtitles = sub),
  },

  actions: {
    clearSelected({ commit }) {
      commit("CLEAR_SELECTED", []);
    },
    fetchQuestions({ getters, commit }) {
      axios({
        url: "https://i7b307.p.ssafy.io/api" + "/questions",
        method: "get",
        headers: getters.authHeader,
      })
        .then((res) => {
          commit("SET_QUESTIONS", res.data);
        })
        .catch((err) => console.error(err.response));
    },
    createAnswer({ getters, commit }, { questionId, content }) {
      axios({
        url:
          "https://i7b307.p.ssafy.io/api" +
          "/questions" +
          `/${questionId}` +
          "/answers",
        method: "post",
        data: JSON.stringify({ answer: content }),
        headers: getters.authHeader,
      }).then((res) => {
        commit("SET_ANSWER", res.data);
        router.push({ name: "QuestionList" });
      });
    },
    async fetchAnswer({ getters, commit }, questionId) {
      await axios({
        url:
          "https://i7b307.p.ssafy.io/api" +
          "/questions" +
          `/${questionId}` +
          "/answers",
        method: "get",
        headers: getters.authHeader,
      })
        .then((res) => {
          commit("SET_ANSWER", res.data);
        })
        .catch((err) => {
          commit("SET_ANSWER", "");
          console.log(err);
        });
    },
    updateAnswer({ getters, commit }, { questionId, content }) {
      axios({
        url:
          "https://i7b307.p.ssafy.io/api" +
          "/questions" +
          `/${questionId}` +
          "/answers",
        method: "put",
        data: JSON.stringify({ answer: content }),
        headers: getters.authHeader,
      }).then((res) => {
        commit("SET_ANSWER", res.data);
        router.push({ name: "QuestionList" });
      });
    },
    clearAnswer({ commit }) {
      commit("CLEAR_ANSWER");
    },
    fetchChecklist({ getters, commit }) {
      axios({
        url:
          "https://i7b307.p.ssafy.io/api" + "/questions" + "/answers" + "/list",
        method: "get",
        headers: getters.authHeader,
      }).then((res) => {
        commit("SET_CHECKLIST", res.data);
      });
    },

    pushSelected({ commit, getters }, questionId) {
      if (getters.selected.includes(questionId)) {
        let filtered = getters.selected.filter((el) => el !== questionId);
        commit("SET_SELECTED", filtered);
      } else {
        commit("PUT_SELECTED", questionId);
      }
    },

    async saveRecording({ getters, dispatch }, { fileName, URL }) {
      let reId = "";
      axios({
        url: "https://i7b307.p.ssafy.io/api/recording",
        method: "post",
        data: JSON.stringify({
          fileName: fileName,
          recordURL: URL,
        }),
        headers: getters.authHeader,
      })
        .then((res) => {
          reId = res.data.recordingId;
          return reId;
        })
        .then(async (res) => {
          await dispatch("fetchvitoId", { videoUrl: URL, recordingId: res });
          return res;
        })
        .then(async (res) => {
          await dispatch("putVitoId", res);
        })
        .then(await dispatch("fetchSubtitles"))
        .then(await dispatch("fetchRecording"))
        .then(router.push({ name: "PracticeBox" }));
    },
    async fetchRecording({ getters, commit }) {
      await axios({
        url: "https://i7b307.p.ssafy.io/api/recording/list",
        method: "get",
        headers: getters.authHeader,
      })
        .then((res) => {
          commit("SET_RECORDS", res.data);
        })
        .catch((err) => console.error(err.response));
    },

    async issueToken({ commit }) {
      await axios({
        url: "https://openapi.vito.ai/v1/authenticate",
        method: "post",
        data: qs.stringify({
          client_id: process.env.VUE_APP_VITO_CLIENT_ID,
          client_secret: process.env.VUE_APP_VITO_CLIENT_SECRET,
        }),
        headers: {
          "Content-Type": "application/x-www-form-urlencoded",
        },
      })
        .then((res) => {
          commit("SET_VITOTOKEN", res.data.access_token);
        })
        .catch((err) => console.error(err.response));
    },

    async fetchvitoId({ commit, state, getters }, { videoUrl }) {
      await axios({
        url: "https://i7b307.p.ssafy.io/api/vito/videoId",
        method: "post",
        data: JSON.stringify({ token: state.vitotoken, videoUrl: videoUrl }),
        headers: getters.authHeader,
      })
        .then(async (res) => {
          await commit("SET_VITOID", res.data);
        })
        .catch((err) => console.error(err));
    },

    async fetchSubtitles({ commit, getters, state }) {
      await axios({
        url: "https://i7b307.p.ssafy.io/api/vito/audioMsg",
        method: "post",
        data: JSON.stringify({
          token: state.vitotoken,
          videoId: state.vitoId.id,
        }),
        headers: getters.authHeader,
      }).then((res) => {
        commit("SET_SUBTITLES", res.data);
      });
    },
    async fetchSubtitles2({ commit, getters, state }, videoId) {
      await axios({
        url: "https://i7b307.p.ssafy.io/api/vito/audioMsg",
        method: "post",
        data: JSON.stringify({
          token: state.vitotoken,
          videoId: videoId,
        }),
        headers: getters.authHeader,
      }).then((res) => {
        commit("SET_SUBTITLES", res.data.results.utterances);
      });
    },

    async putVitoId({ getters, state }, recordingId) {
      await axios({
        url:
          "https://i7b307.p.ssafy.io/api/recording/" +
          `${recordingId}` +
          "/videoId",
        method: "put",
        headers: getters.authHeader,
        data: JSON.stringify({
          videoId: state.vitoId.id,
        }),
      }).then();
    },
  },
};
