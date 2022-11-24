import drf from "@/api/drf";
import multipart from "@/api/multipart";
import router from "@/router";
import axios from "axios";

export const user = {
  state: {
    token: localStorage.getItem("token") || "",
    currentUser: {},
    authError: null,

    profile: {},
    prefer: {},
    id: "",

    rooms: [],
  },

  getters: {
    isLogginedIn: (state, _, rootState) =>
      !!state.token || !!rootState.company.token,
    currentUser: (state) => state.currentUser,
    authError: (state) => state.authError,
    authHeader: (state) => ({
      Authorization: `Bearer ${state.token}`,
      "Content-type": "Application/JSON",
    }),

    profile: (state) => state.profile,
    prefer: (state) => state.prefer,
    id: (state) => state.id,

    rooms: (state) => state.rooms,
  },

  mutations: {
    SET_TOKEN: (state, token) => (state.token = token),
    SET_CURRENT_USER: (state, user) => (state.currentUser = user),
    SET_AUTH_ERROR: (state, error) => (state.authError = error),

    SET_PROFILE: (state, profile) => (state.profile = profile),
    SET_PREFER: (state, prefer) => (state.prefer = prefer),
    SET_ID: (state, id) => (state.id = id),

    SET_ROOMS: (state, rooms) => (state.rooms = rooms),
  },

  actions: {
    saveToken({ commit }, token) {
      commit("SET_TOKEN", token);
      localStorage.setItem("token", token);
    },

    async removeToken({ commit }) {
      await commit("SET_TOKEN", "");
      await commit("SET_CURRENT_USER", {});
      localStorage.setItem("token", "");
    },

    login({ dispatch }, credentials) {
      axios({
        url: "https://i7b307.p.ssafy.io/api/users/login",
        method: "post",
        data: credentials,
      })
        .then(async (res) => {
          const token = res.headers["authorization"];
          await dispatch("saveToken", token);
          await dispatch("fetchCurrentUser");
        })
        .catch((err) => {
          alert(
            "아이디 또는 비밀번호를 잘못 입력하셨습니다.\n입력하신 내용을 다시 확인해주세요.",
          );
          console.log(err);
        });
    },

    updateUser({ dispatch }, formData) {
      multipart
        .put(`/users`, formData)
        .then(() => {
          dispatch("fetchCurrentUser");
          router.push({ name: "ResumeStepTwo" });
        })
        .catch((err) => {
          console.log(err);
        });
    },

    fetchCurrentUser({ commit, getters, dispatch }) {
      if (getters.isLogginedIn) {
        axios({
          url: "https://i7b307.p.ssafy.io/api/users/",
          method: "get",
          headers: getters.authHeader,
        })
          .then((res) => {
            commit("SET_CURRENT_USER", res.data);
          })
          .catch((err) => {
            if (err.response.status === 401) {
              dispatch("removeToken");
              router.push({ name: "Login" });
            }
          });
      }
    },
    kakaoRegist({ dispatch }, formData) {
      multipart
        .post(`/users/kakao/regist`, formData)
        .then((res) => {
          const token = res.headers["authorization"];
          dispatch("saveToken", token);
          dispatch("fetchCurrentUser");
          router.push({ name: "home" });
        })
        .catch((err) => {
          console.log(err);
        });
    },

    registUser({ getters }, formData) {
      axios({
        url: `https://i7b307.p.ssafy.io/api/users/regist`,
        method: "post",
        headers: {
          "Content-Type": "multipart/form-data",
          Authorization: getters.authHeader,
        },
        data: formData,
      })
        .then(() => {
          router.push({ name: "Login" });
        })
        .catch((err) => {
          console.log(err);
        });
    },

    fetchPrefer({ commit, getters }) {
      axios({
        url: drf.recommendcondition.recommend(),
        method: "get",
        headers: getters.authHeader,
      })
        .then((res) => {
          if (res.data == "") {
            commit("SET_PREFER", {
              id: null,
              jobParentCategoryResponse: { id: null, name: null },
              gugunResponse: { id: null, name: null, code: null },
              workingDay: null,
              minSalary: null,
            });
          } else {
            commit("SET_PREFER", res.data);
          }
        })
        .catch((err) => {
          commit("SET_PREFER", {
            id: null,
            jobParentCategoryResponse: { id: null, name: null },
            gugunResponse: { id: null, name: null, code: null },
            workingDay: null,
            minSalary: null,
          });
          console.log(err);
        });
    },

    createPrefer({ getters, dispatch }, data) {
      axios({
        url: drf.recommendcondition.recommend(),
        method: "post",
        data: JSON.stringify(data),
        headers: getters.authHeader,
      })
        .then(() => {
          dispatch("fetchPrefer");
          router.push({ name: "MyPage" });
        })
        .catch((err) => {
          console.log(err);
        });
    },

    updatePrefer({ getters, dispatch }, data) {
      axios({
        url: drf.recommendcondition.recommend(),
        method: "put",
        data: JSON.stringify(data),
        headers: getters.authHeader,
      })
        .then(() => {
          dispatch("fetchPrefer");
          router.push({ name: "MyPage" });
        })
        .catch((err) => {
          console.log(err);
        });
    },

    findId({ commit }, credentials) {
      axios({
        url: drf.user.userid(credentials.name, credentials.phone),
        method: "get",
      })
        .then((res) => {
          commit("SET_ID", res.data);
          router.push({ name: "FindUsernameResult" });
        })
        .catch(() => {
          alert(
            "이름 또는 전화번호를 잘못 입력하셨습니다.\n입력하신 내용을 다시 확인해주세요.",
          );
        });
    },

    findPwd({ dispatch }, credentials) {
      axios({
        url: drf.user.userpwd(credentials.name, credentials.userAppId),
        method: "get",
      })
        .then(() => {
          dispatch("removeToken");
          router.push({ name: "FindPasswordResult" });
        })
        .catch(() => {
          alert(
            "이름 또는 이메일을 잘못 입력하셨습니다.\n입력하신 내용을 다시 확인해주세요.",
          );
        });
    },

    changePassword({ getters }, data) {
      axios({
        url: drf.user.changePwd(data),
        method: "put",
        headers: getters.authHeader,
      })
        .then(() => {
          alert("변경되었습니다!");
        })
        .catch((err) => console.log(err));
    },

    fetchRooms({ getters, commit }) {
      axios({
        url: "https://i7b307.p.ssafy.io/api" + "/jobopening/apply/dateAsc",
        method: "get",
        headers: getters.authHeader,
      })
        .then((res) => {
          commit("SET_ROOMS", res.data);
        })
        .catch((err) => console.error(err.response));
    },

    kakaologin({ dispatch }, forms) {
      axios({
        url: "https://i7b307.p.ssafy.io/api/users/kakao/login",
        method: "post",
        data: JSON.stringify(forms),
      })
        .then((res) => {
          const token = res.headers["authorization"];
          dispatch("saveToken", token);
          dispatch("fetchCurrentUser");
          router.push({ name: "home" });
        })
        .catch((err) => {
          console.log(err);
        });
    },

    deleteUser({ dispatch, getters }) {
      axios({
        url: drf.user.delete(),
        method: "delete",
        headers: getters.authHeader,
      })
        .then(() => {
          dispatch("removeToken");
          router.push({ name: "home" });
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },

  modules: {},
};
