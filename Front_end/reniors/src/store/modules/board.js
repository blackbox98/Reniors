import router from "@/router";
import axios from "axios";
import _ from "lodash";
import drf from "@/api/drf";

export default {
  state: {
    article: {},
    articles: [],
    comment: {},
    comments: [],
    interest: {},
    parents: [],
    parent: {},
    isLast: false,
    currPage: 0,
  },

  getters: {
    article: (state) => state.article,
    articles: (state) => state.articles,
    isArticle: (state) => !_.isEmpty(state.article),
    isAuthor: (state, getters) => {
      return state.article.userId === getters.currentUser.id;
    },
    comment: (state) => state.comment,
    comments: (state) => state.comments,
    interest: (state) => state.interest,
    parents: (state) => state.parents,
    parent: (state) => state.parent,
    isLast: (state) => state.isLast,
    currPage: (state) => state.currPage,
  },

  mutations: {
    SET_TOKEN: (state, token) => (state.token = token),
    SET_CURRENT_USER: (state, user) => (state.currentUser = user),
    SET_ARTICLE: (state, article) => (state.article = article),
    SET_ARTICLES: (state, articles) =>{
      if(articles){
        articles.forEach((element) => {
          state.articles.push(element);
        })
      }else{
        state.isLast = true;
      }
    },
    CLEAR_ARTICLES: (state) => (state.articles = []),
    SET_COMMENT: (state, comment) => (state.comment = comment),
    SET_COMMENTS: (state, comments) => (state.comments = comments),
    SET_INTEREST: (state, interest) => (state.interest = interest),
    SET_PARENTS: (state, parents) => (state.parents = parents),
    SET_PARENT: (state, parent) => (state.parent = parent),
    SET_IS_LAST: (state, isLast) => (state.isLast = isLast),
    SET_CURR_PAGE: (state, currPage) => (state.currPage = currPage),
  },

  actions: {
    fetchArticles({ commit, getters }, request) {
      axios({
        url: drf.board.get(),
        method: "post",
        data: JSON.stringify({
          categoryId: request.categoryId,
        }),
        params: {
          page: request.page,
          size: 10,
        },
        headers: getters.authHeader,
      })
        .then((res) => {
          commit("SET_IS_LAST", res.data.last);
          commit("SET_ARTICLES", res.data.content);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    async clearArticles({ commit }) {
      await commit("CLEAR_ARTICLES");
    },
    setCurrPage({ commit }, page) {
      commit("SET_CURR_PAGE", page);
    },
    fetchArticle({ commit, getters }, article_pk) {
      axios({
        url: drf.board.detail(article_pk),
        method: "get",
        headers: getters.authHeader,
      })
        .then(async (res) => {
          await commit("SET_ARTICLE", res.data);
        })
        .catch((err) => console.error(err.response));
    },

    async createArticle(
      { getters, dispatch },
      { categoryId, contents, title },
    ) {
      await axios({
        url: drf.board.new(),
        method: "post",
        data: JSON.stringify({
          categoryId: categoryId,
          contents: contents,
          title: title,
        }),
        headers: getters.authHeader,
      }).then((res) => {
        dispatch("fetchArticle", res.data.boardId);
        router.push({
          name: "boardDetail",
          params: {
            category_id: categoryId,
            board_id: res.data.boardId,
          },
        });
      });
    },
    updateArticle(
      { getters, dispatch, commit },
      { categoryId, article_pk, title, contents },
    ) {
      axios({
        url: drf.board.detail(article_pk),
        method: "put",
        data: JSON.stringify({
          title: title,
          contents: contents,
        }),
        headers: getters.authHeader,
      })
        .then(dispatch("fetchArticle", article_pk))
        .then((res) => {
          commit("SET_ARTICLE", res.data);
          router.push({
            name: "boardDetail",
            params: {
              category_id: categoryId,
              board_id: article_pk,
            },
          });
        });
    },
    deleteArticle({ commit, getters }, { article_pk, categoryId }) {
      if (confirm("정말 삭제하시겠습니까?")) {
        axios({
          url: drf.board.detail(article_pk),
          method: "delete",
          headers: getters.authHeader,
        }).then(() => {
          commit("SET_ARTICLE", {});
          router.push({
            name: "boardMain",
            params: { category_id: categoryId },
          });
        });
      }
    },

    fetchComments({ commit, getters }, article_pk) {
      axios({
        url: drf.board.comment(article_pk),
        method: "get",
        headers: getters.authHeader,
      })
        .then((res) => {
          commit("SET_COMMENTS", res.data);
        })
        .catch((err) => console.error(err.response));
    },

    createComment(
      { getters, commit, dispatch },
      { categoryId, boardId, content },
    ) {
      axios({
        url: drf.board.comment(boardId),
        method: "post",
        data: JSON.stringify({
          contents: content,
        }),
        headers: getters.authHeader,
      })
        .then(dispatch("fetchComments", boardId))
        .then((res) => {
          commit("SET_COMMENTS", res.data);
          router.push({
            name: "boardDetail",
            params: { category_id: categoryId, board_id: getters.boardId },
          });
        });
    },
    updateComment(
      { getters, dispatch },
      { categoryId, boardId, commentId, contents },
    ) {
      axios({
        url: drf.board.commentEdit(boardId, commentId),
        method: "put",
        data: JSON.stringify({
          contents: contents,
        }),
        headers: getters.authHeader,
      }).then(() => {
        dispatch("fetchComments", boardId);
        router.push({
          name: "boardDetail",
          params: { category_id: categoryId, board_id: boardId },
        });
      });
    },
    deleteComment(
      { commit, getters, dispatch },
      { categoryId, boardId, commentId },
    ) {
      if (confirm("정말 삭제하시겠습니까?")) {
        axios({
          url: drf.board.commentEdit(boardId, commentId),
          method: "delete",
          headers: getters.authHeader,
        })
          .then(dispatch("fetchComments", boardId))
          .then((res) => {
            commit("SET_COMMENT", res.data);
            router.push({
              name: "boardDetail",
              params: { category_id: categoryId, board_id: getters.boardId },
            });
          });
      }
    },
    fetchInterest({ commit, getters }) {
      axios({
        url: "https://i7b307.p.ssafy.io/api/recommendcondition",
        method: "get",
        headers: getters.authHeader,
      }).then((res) => {
        if (res.data.jobChildCategoryResponse) {
          commit("SET_INTEREST", res.data.jobChildCategoryResponse);
        } else {
          commit("SET_INTEREST", { id: 1 });
        }
      });
    },
    //직무 대분류 가져오기
    fetchParents({ commit, getters }) {
      axios({
        url: drf.category.jobsLarge(),
        method: "get",
        headers: getters.authHeader,
      }).then((res) => {
        commit("SET_PARENTS", res.data);
      });
    },
  },
};
