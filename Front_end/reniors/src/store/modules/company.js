// import http from "@/api/http";
// import multipart from "@/api/multipart";
import router from "@/router";
import axios from "axios";
// import jobopening from "./jobopening";

export default {
  namespaced: true,
  state: {
    token: localStorage.getItem("token") || "",
    isCompanyLogin: null,
    header: "",
    currentUser: {},
    profile: {},
    authError: null,
    datastate: "",
    jobopeninglisting: [],
    jobopeninglisted: [],
    jobopening: null,
    companyinfo: {},
    companyimg: "",
    applylist: [],
    apply: null,
    interviewapplylist: [],
    interviewapplylistasc: [],
    applyuser: null,
    evalquestionlist: [],
    userevallist: [],
    interviewer: null,
    resume: null,
    isMobile: /Mobi/i.test(window.navigator.userAgent),
  },

  getters: {
    isLogginedIn: (state) => !!state.token,
    currentUser: (state) => state.currentUser,
    isCompanyLogin: (state) => state.isCompanyLogin,
    profile: (state) => state.profile,
    authError: (state) => state.authError,
    // Authorization: `Token ${state.token}`
    token: (state) => state.token,
    authHeader: (state) => ({ Authorization: `Bearer ${state.token}` }),
    header: (state) => state.header,
    jobopeninglisting: (state) => state.jobopeninglisting,
    jobopeninglisted: (state) => state.jobopeninglisted,
    jobopening: (state) => state.jobopening,
    companyinfo: (state) => state.companyinfo,
    companyimg: (state) => state.companyimg,
    applylist: (state) => state.applylist,
    apply: (state) => state.apply,
    applyuser: (state) => state.applyuser,
    evalquestionlist: (state) => state.evalquestionlist,
    userevallist: (state) => state.userevallist,
    interviewer: (state) => state.interviewer,
    resume: (state) => state.resume,
    interviewapplylist: (state) => state.interviewapplylist,
    interviewapplylistasc: (state) => state.interviewapplylistasc,
    isMobile: (state) => state.isMobile,
  },

  mutations: {
    SET_TOKEN: (state, token) => (state.token = token),
    GET_TOKEN: (state) => console.log(state.token),
    IS_COMPANY_LOGIN_TRUE: (state) => (state.isCompanyLogin = true),
    IS_COMPANY_LOGIN_FALSE: (state) => (state.isCompanyLogin = false),
    SET_CURRENT_USER: (state, user) => (state.currentUser = user),
    SET_PROFILE: (state, profile) => (state.profile = profile),
    SET_AUTH_ERROR: (state, error) => (state.authError = error),
    SET_DATASTATE(state, data) {
      state.datastate = data;
    },
    CLEAR_JOBOPENING_LIST(state) {
      state.jobopeninglisting = [];
      state.jobopeninglisted = [];
    },
    SET_JOBOPENING_LIST(state, datas) {
      datas.forEach((data) => {
        if (data.isFinish == "F") {
          state.jobopeninglisting.push(data);
        } else {
          state.jobopeninglisted.push(data);
        }
      });
    },
    SET_JOBOPENING(state, data) {
      state.jobopening = data;
    },
    SET_COMPANY(state, data) {
      state.companyinfo = data;
      state.companyimg = data.baseURL + data.companyProfile;
    },
    SET_APPLY_LIST(state, data) {
      state.applylist = data;
    },
    SET_APPLY(state, data) {
      state.apply = data;
    },
    SET_APPLY_USER(state, data) {
      state.applyuser = data;
    },
    CLEAR_INTERVIEW_APPLY_LIST(state) {
      state.interviewapplylist = [];
    },
    SET_INTERVIEW_APPLY_LIST(state, datas) {
      datas.forEach((data) => {
        if (
          data.jobOpeningProcess == "면접" ||
          data.jobOpeningProcess == "면접심사중"
        ) {
          state.interviewapplylist.push(data);
        }
      });
      // state.interviewapplylist = datas;
    },
    CLEAR_INTERVIEW_APPLY_LIST_ASC(state) {
      state.interviewapplylistasc = [];
    },
    SET_INTERVIEW_APPLY_LIST_ASC(state, datas) {
      datas.forEach((data) => {
        if (
          data.jobOpeningProcess == "면접" ||
          data.jobOpeningProcess == "면접심사중"
        ) {
          state.interviewapplylistasc.push(data);
        }
      });
      // state.interviewapplylist = datas;
    },
    SET_EVAL_QUESTION_LIST(state, datas) {
      state.evalquestionlist = datas;
    },
    SET_USER_EVAL_LIST(state, datas) {
      state.userevallist = datas;
    },
    CLEAR_INTERVIEWER(state) {
      state.interviewer = null;
    },
    SET_INTERVIEWER(state, data) {
      state.interviewer = data;
    },
    SET_RESUME(state, data) {
      state.resume = data;
    },
    SET_HEADER(state, data) {
      state.header = data;
    },
    SET_IS_MOBILE(state){
      state.isMobile = /Mobi/i.test(window.navigator.userAgent);
    }
  },

  actions: {
    setheader({ commit }, data) {
      commit("SET_HEADER", data);
    },
    saveToken({ commit }, token) {
      commit("SET_TOKEN", token);
      commit("IS_COMPANY_LOGIN_TRUE");
      localStorage.setItem("token", token);
    },

    removeToken({ commit }) {
      commit("SET_TOKEN", "");
      commit("SET_COMPANY_INFO", {});
      commit("IS_COMPANY_LOGIN_FALSE");
      localStorage.setItem("token", "");
    },

    companylogin({ dispatch }, credentials) {
      axios({
        url: "https://i7b307.p.ssafy.io/api/company/login",
        method: "post",
        data: credentials,
      })
        .then((res) => {
          const token = res.headers["authorization"];
          dispatch("saveToken", token);
          router.push({ name: "company" });
        })
        .catch(() => {
          alert(
            "아이디 또는 비밀번호를 잘못 입력하셨습니다.\n입력하신 내용을 다시 확인해주세요.",
          );
        });
    },

    companylogout: ({ dispatch }) => {
      dispatch("removeToken");
    },

    async registCompany({ commit, getters }, formData){
      await axios({
        url: `https://i7b307.p.ssafy.io/api/company`,
        method: "post",
        headers: {
          "Content-Type": "multipart/form-data",
          Authorization: getters.authHeader,
        },
        data: formData,
      })
        .then(async ({ data }) => {
          await commit("SET_DATASTATE", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    async getCompany({ commit, getters }){
      await axios({
        url: "https://i7b307.p.ssafy.io/api/company",
        method: "get",
        headers: getters.authHeader,
      })
        .then(async ({ data }) => {
          await commit("SET_COMPANY", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    async updateCompany({ commit, getters }, formData){
      await axios({
        url: `https://i7b307.p.ssafy.io/api/company`,
        method: "put",
        headers: {
          "Content-Type": "multipart/form-data",
          Authorization: getters.authHeader,
        },
        data: formData,
      })
        .then(async ({ data }) => {
          await commit("SET_DATASTATE", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },

    async registJobOpening({ commit, getters }, formData){
      await axios({
        url: `https://i7b307.p.ssafy.io/api/company/jobopening`,
        method: "post",
        headers: {
          "Content-Type": "multipart/form-data",
          Authorization: "Bearer " + getters.token,
        },
        data: formData,
      })
        .then(async({ data }) => {
          await commit("SET_DATASTATE", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    async updateJobOpening({ commit, getters }, data){
      await axios({
        url: `https://i7b307.p.ssafy.io/api/company/jobopening/${data.no}`,
        method: "put",
        headers: {
          "Content-Type": "multipart/form-data",
          Authorization: `Bearer ${getters.token}`,
        },
        data: data.formData,
      })
        .then(async ({ data }) => {
          await commit("SET_DATASTATE", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    async getJobOpeningList({ commit, getters }){
      await axios({
        url: `https://i7b307.p.ssafy.io/api/company/jobopening`,
        method: "get",
        headers: getters.authHeader,
      })
        .then(async ({ data }) => {
          await commit("SET_JOBOPENING_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    async getJobOpening({ commit, getters }, no){
      await axios({
        url: `https://i7b307.p.ssafy.io/api/company/jobopening/${no}`,
        method: "get",
        headers: getters.authHeader,
      })
        .then(async ({ data }) => {
          await commit("SET_JOBOPENING", data);
        })

        .catch((error) => {
          console.log(error);
        });
    },
    async deleteJobOpening({ commit, getters }, no){
      await axios({
        url: `https://i7b307.p.ssafy.io/api/company/jobopening/${no}`,
        method: "delete",
        headers: getters.authHeader,
      })
        .then(async({ data }) => {
          await commit("SET_JOBOPENING", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    async finishJobOpening({ commit, getters }, no){
      await axios({
        url: `https://i7b307.p.ssafy.io/api/company/jobopening/${no}/finish`,
        method: "put",
        headers: getters.authHeader,
      })
        .then(async ({ data }) => {
          await commit("SET_DATASTATE", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    async progressJobOpening({ commit, getters }, data){
      await axios({
        url: `https://i7b307.p.ssafy.io/api/company/progress/${data.no}`,
        method: "put",
        headers: getters.authHeader,
        data: data.progress,
      })
        .then(async({ data }) => {
          await commit("SET_DATASTATE", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    async getapplylist({ commit, getters }, no){
      await axios({
        url: `https://i7b307.p.ssafy.io/api/company/jobopening/${no}/apply`,
        method: "get",
        headers: getters.authHeader,
      })
        .then(async ({ data }) => {
          await commit("SET_APPLY_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    async getapply({ commit, getters }, data){
      await axios({
        url: `https://i7b307.p.ssafy.io/api/company/jobopening/${data.jobOpeningId}/apply/${data.applyId}`,
        method: "get",
        headers: getters.authHeader,
      })
        .then(async ({ data }) => {
          await commit("SET_APPLY", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    async getinterviewapplylist({ commit, getters }, no){
      await axios({
        url: `https://i7b307.p.ssafy.io/api/company/jobopening/${no}/apply`,
        method: "get",
        headers: getters.authHeader,
      })
        .then(async({ data }) => {
          await commit("SET_INTERVIEW_APPLY_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    async getinterviewapplylistasc({ commit, getters }, no){
      await axios({
        url: `https://i7b307.p.ssafy.io/api/company/jobopening/${no}/apply/dateAsc`,
        method: "get",
        headers: getters.authHeader,
      })
        .then(async ({ data }) => {
          await commit("SET_INTERVIEW_APPLY_LIST_ASC", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    async updateApply({ commit, getters }, data){
      await axios({
        url: `https://i7b307.p.ssafy.io/api/company/jobopening/${data.jobOpeningId}/apply/${data.applyId}`,
        method: "put",
        headers: getters.authHeader,
        data: data.apply,
      })
        .then(async ({ data }) => {
          await commit("SET_DATASTATE", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    async finishInterview({ commit, getters }, data){
      await axios({
        url: `https://i7b307.p.ssafy.io/api/company/jobopening/${data.jobOpeningId}/apply/${data.applyId}/finishInterview`,
        method: "put",
        headers: getters.authHeader,
      })
        .then(async({ data }) => {
          await commit("SET_DATASTATE", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    async getResume({ commit, getters }, no){
      await axios({
        url: `https://i7b307.p.ssafy.io/api/company/userdetail/${no}`,
        method: "get",
        headers: getters.authHeader,
      })
        .then(async ({ data }) => {
          await commit("SET_RESUME", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    async registRoom({ commit, getters }, data){
      await axios({
        url: `https://i7b307.p.ssafy.io/api/room/company`,
        method: "post",
        headers: getters.authHeader,
        data: data,
      })
        .then(async ({ data }) => {
          await commit("SET_DATASTATE", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    async registEvalQuestion({ commit, getters }, evalquestion){
      await axios({
        url: `https://i7b307.p.ssafy.io/api/eval`,
        method: "post",
        headers: getters.authHeader,
        data: evalquestion,
      })
        .then(async ({ data }) => {
          await commit("SET_DATASTATE", data);
          window.location.reload();
        })
        .catch((error) => {
          console.log(error);
        });
    },
    async getEvalQuestionList({ commit, getters }, no){
      await axios({
        url: `https://i7b307.p.ssafy.io/api/eval/search/${no}`,
        method: "get",
        headers: getters.authHeader,
      })
        .then(async ({ data }) => {
          await commit("SET_EVAL_QUESTION_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    async updateEvalQuestion({ commit, getters }, data){
      await axios({
        url: `https://i7b307.p.ssafy.io/api/eval/${data.no}`,
        method: "put",
        headers: getters.authHeader,
        data: data.evalquestion,
      })
        .then(async ({ data }) => {
          await commit("SET_DATASTATE", data);
          window.location.reload();
        })
        .catch((error) => {
          console.log(error);
        });
    },
    async deleteEvalQuestion({ commit, getters }, no){
      await axios({
        url: `https://i7b307.p.ssafy.io/api/eval/${no}`,
        method: "delete",
        headers: getters.authHeader,
      })
        .then(async ({ data }) => {
          await commit("SET_DATASTATE", data);
          window.location.reload();
        })
        .catch((error) => {
          console.log(error);
        });
    },
    async registUserEval({ commit, getters }, data) {
      await axios({
        url: `https://i7b307.p.ssafy.io/api/eval/${data.jobOpeningId}/usereval/${data.userId}`,
        method: "post",
        headers: getters.authHeader,
        data: data.usereval,
      })
        .then(async ({ data }) => {
          await commit("SET_DATASTATE", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    async getUserEvalList({ commit, getters }, data){
      await axios({
        url: `https://i7b307.p.ssafy.io/api/eval/${data.jobOpeningId}/usereval/${data.userId}`,
        method: "get",
        headers: getters.authHeader,
      })
        .then(async ({ data }) => {
          await commit("SET_USER_EVAL_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    async deleteUserEvalList({ commit, getters }, data){
      await axios({
        url: `https://i7b307.p.ssafy.io/api/eval/${data.jobOpeningId}/usereval/${data.userId}`,
        method: "delete",
        headers: getters.authHeader,
      })
        .then(async ({ data }) => {
          await commit("SET_DATASTATE", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    async setInterviewer({ commit }, data){
      await commit("SET_INTERVIEWER", data);
    },
    setIsMobile({commit}){
      commit("SET_IS_MOBILE");
    }
  },

  modules: {},
};
