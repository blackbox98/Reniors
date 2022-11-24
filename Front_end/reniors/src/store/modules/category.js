import http from "@/api/http";

export default {
  namespaced: true,
  state: {
    token: localStorage.getItem("jwt") || "",
    jobparents: [{ value: null, text: "선택하세요" }],
    jobchilds: [{ value: null, text: "선택하세요" }],
    sidos: [{ value: null, text: "선택하세요" }],
    guguns: [{ value: null, text: "선택하세요" }],
    lastedus: [
      { value: "학력무관", text: "최종학력을 선택해주세요" },
      { value: "고교졸업이하", text: "고교졸업 이하" },
      { value: "고등학교졸업", text: "고등학교졸업" },
      { value: "대학교졸업_4년제", text: "대학교졸업(4년)" },
      { value: "대학졸업_2_3년제", text: "대학졸업(2,3년)" },
      { value: "대학원석사졸업", text: "대학원 석사졸업" },
      { value: "대학원박사졸업", text: "대학원 박사졸업" },
      { value: "박사졸업이상", text: "박사졸업 이상" },
      { value: "학력무관", text: "학력무관" },
    ],
    typeemployments: [
      { value: null, text: "선택하세요" },
      { value: "정규직", text: "정규직" },
      { value: "계약직", text: "계약직" },
      { value: "인턴", text: "인턴" },
      { value: "파견직", text: "파견직" },
      { value: "프리랜서", text: "프리랜서" },
      { value: "아르바이트", text: "아르바이트" },
    ],
    typecompanies: [
      { value: null, text: "선택하세요" },
      { value: "중소기업", text: "중소기업" },
      { value: "중견기업", text: "중견기업" },
      { value: "강소기업", text: "강소기업" },
      { value: "대기업", text: "대기업" },
      { value: "공기업", text: "공기업" },
      { value: "상장기업", text: "상장기업" },
    ],
  },

  getters: {
    jobparents: (state) => state.jobparents,
    jobchilds: (state) => state.jobchilds,
    sidos: (state) => state.sidos,
    guguns: (state) => state.guguns,
    lastedus: (state) => state.lastedus,
    typeemployments: (state) => state.typeemployments,
    typecompanies: (state) => state.typecompanies,
  },

  mutations: {
    SET_JOBPARENT_LIST: (state, jobparents) => {
      state.jobparents = jobparents;
    },
    SET_JOBCHILD_LIST: (state, jobchilds) => (state.jobchilds = jobchilds),
    SET_SIDO_LIST: (state, sidos) => (state.sidos = sidos),
    SET_GUGUN_LIST: (state, guguns) => (state.guguns = guguns),
  },

  actions: {
    getJobParent: ({ commit }) => {
      http
        .get(`/categories/parent`)
        .then(({ data }) => {
          commit("SET_JOBPARENT_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getJobChild: ({ commit }, no) => {
      http
        .get(`/categories/parent/${no}/child`)
        .then(({ data }) => {
          commit("SET_JOBCHILD_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getSido: ({ commit }) => {
      http
        .get(`/categories/sido`)
        .then(({ data }) => {
          commit("SET_SIDO_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getGugun: ({ commit }, no) => {
      http
        .get(`/categories/sido/${no}/gugun`)
        .then(({ data }) => {
          commit("SET_GUGUN_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
