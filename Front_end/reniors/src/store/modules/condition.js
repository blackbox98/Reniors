import http from "@/api/http";
import router from "@/router";
import axios from "axios";

export default {
  namespaced: true,
  state: {
    conditions: [],
    jobopenings: [],
    editData: {},
  },
  getters: {
    conditions: (state) => state.conditions,
    jobopenings: (state) => state.jobopenings,
    editData: (state) => state.editData,
  },
  mutations: {
    CONDITIONS: (state, conditions) => (state.conditions = conditions),
    JOBOPENINGS: (state, jobopenings) => (state.jobopenings = jobopenings),
    EDITDATA: (state, data) => (state.editData = data),
  },
  actions: {
    async fetchConditions({ commit }) {
      await axios({
        url: `https://i7b307.p.ssafy.io/api/jobopening/condition`,
        method: "get",
        headers: 
        {
          Authorization : "Bearer "+localStorage.getItem("token")
        },
        })
        .then((response) => {
          commit("CONDITIONS", response.data);
          router.push({ name: "Condition" });
        })
        .catch((error) => {
          console.log(error);
        });
    },
    async createCondition({ dispatch }, payload) {
      const data = {
        name: payload.name,
        jobParentCategoryId: payload.parent,
        lastEdu: payload.lastEdu,
        minCareer: payload.minCareer,
        minSalary: payload.minSalary,
        typeEmployment: payload.type,
        workingDay: payload.day,
      };

      await axios({
        url: `https://i7b307.p.ssafy.io/api/jobopening/condition`,
        method: "post",
        headers: 
        {
          Authorization : "Bearer "+localStorage.getItem("token")
        },
        data: data,
        })
        .then((response) => {
          const conditionId = response.data;
          payload.hopeareas.map(async (hopearea) => {
            await http.post(`/jobopening/condition/${conditionId}/hopearea`, {
              gugunId: hopearea,
              id: payload.selectSido.data,
            });
          });
          dispatch("fetchConditions");
        })
        .catch((error) => {
          console.log(error);
        });
    },
    async deleteCondition({ dispatch }, conditionId) {

      await axios({
        url: `https://i7b307.p.ssafy.io/api/jobopening/condition/${conditionId}`,
        method: "delete",
        headers: 
        {
          Authorization : "Bearer "+localStorage.getItem("token")
        },
        })
        .then( () => {
          dispatch("fetchConditions");
        })
        .catch((error) => {
          console.log(error);
        });
    },
    async fetchEdit({ commit }, id) {
      await axios({
        url: `https://i7b307.p.ssafy.io/api/jobopening/condition/${id}`,
        method: "put",
        headers: 
        {
          Authorization : "Bearer "+localStorage.getItem("token")
        },
        })
        .then((response) => {
          commit("EDITDATA", response.data);
        })
        .catch((error) => {
          console.log(error);
        });
    },

    // jobopenings
    async search({ commit }, conditionId) {

      await axios({
        url: `https://i7b307.p.ssafy.io/api/jobopening/search/${conditionId}`,
        method: "get",
        headers: 
        {
          Authorization : "Bearer "+localStorage.getItem("token")
        },
        })
        .then((response) => {
          commit("JOBOPENINGS", response.data.content);
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
