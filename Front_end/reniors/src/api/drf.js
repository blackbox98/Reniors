const HOST = "https://i7b307.p.ssafy.io/api";

const USER = "/users";
const RESUME = "/resume";
const COMPANY = "/company";
const EVAL = "/eval";
const JOB = "/jobopening";
const BOARD = "/boards";
const ROOM = "/rooms";
const CATEGORY = "/categories";
const PRACTICE = "/practice";
const CONDITION = "/jobopening/condition";
const RECOMMEND = "/recommendcondition";

export default {
  user: {
    signup: () => HOST + USER,
    login: () => HOST + USER,
    delete: () => HOST + USER,
    kakaoSignup: () => HOST + USER + "/kakao",
    kakaoLogin: () => HOST + USER + "/kakao",
    userid: (name, phone) => HOST + USER + `/${name}` + "/findid" + `/${phone}`,
    idcheck: (userAppId) => HOST + USER + "/idCheck" + `/${userAppId}`,
    userpwd: (name, userAppId) =>
      HOST + USER + `/${name}` + "/findpwd" + `/${userAppId}`,
    changePwd: (userAppPwd) => HOST + USER + `/${userAppPwd}`,
    userList: () => HOST + USER + "/list",
    bookmark: (userId) => HOST + USER + `/${userId}` + "/bookmark",
    bookmarkEdit: (userId, jobOpeningId) =>
      HOST + USER + `/${userId}` + "/bookmark" + `/${jobOpeningId}`,
    hopearea: (userId) => HOST + USER + `/${userId}` + "/hopearea",
    hopeareaEdit: (userId, gugunId) =>
      HOST + USER + `/${userId}` + "/hopearea" + `/${gugunId}`,
    jobinterest: (userId) => HOST + USER + `/${userId}` + "/jobinterest",
    jobinterestEdit: (userId, jobSmallCategoryId) =>
      HOST + USER + `/${userId}` + "jobinterest" + `/${jobSmallCategoryId}`,
    recruithistory: (userId) => HOST + USER + `/${userId}` + "/recruithistory",
  },

  recommendcondition: {
    recommend: () => HOST + RECOMMEND,
  },

  resume: {
    get: (userId) => HOST + RESUME + `/${userId}`,
    edit: (userId) => HOST + RESUME + `/${userId}`,
    career: () => HOST + RESUME + "/career",
    careerEdit: (careerDetailId) =>
      HOST + RESUME + "/career" + `/${careerDetailId}`,
    awards: () => HOST + RESUME + "/awards",
    awardsEdit: (awardDetailId) =>
      HOST + RESUME + "/awards" + `/${awardDetailId}`,
    license: () => HOST + RESUME + "/license",
    licenseEdit: (licenseDetailId) =>
      HOST + RESUME + "/license" + `/${licenseDetailId}`,
    // portfolios: resumeId => HOST + RESUME + `/${resumeId}` + 'portfolio',
    // portfolio: (resumeId, portfolioName) => HOST + RESUME + `/${resumeId}` + 'portfolio' + `/${portfolioName}`,
  },
  company: {
    signup: () => HOST + COMPANY,
    login: () => HOST + COMPANY + "/login",
    detail: () => HOST + COMPANY,
    jobopening: () => HOST + COMPANY + "/jobopening",
    jobopeningEdit: (jobOpeningId) =>
      HOST + COMPANY + "/jobopening" + `/${jobOpeningId}`,
    jobopeningApplicant: (jobOpeningId) =>
      HOST + COMPANY + "/jobopening" + `/${jobOpeningId}` + "/applicant",
    jobopeningApplicantEdit: (jobOpeningId, applicantId) =>
      HOST +
      COMPANY +
      "/jobopening" +
      `/${jobOpeningId}` +
      "/applicant" +
      `/${applicantId}`,
  },
  eval: {
    new: () => HOST + EVAL,
    get: () => HOST + EVAL,
    detail: (evalId) => HOST + EVAL + `/${evalId}`,
    question: (evalId) => HOST + EVAL + `/${evalId}` + "/question",
    questionEdit: (evalId, evalQuestionId) =>
      HOST + EVAL + `/${evalId}` + "/question" + `/${evalQuestionId}`,
    questionReview: (evalId, evalQuestionId) =>
      HOST + EVAL + `/${evalId}` + "/question" + `/${evalQuestionId}` + "/user",
    questionReviewDelete: (evalId, evalQuestionId, userEvalId) =>
      HOST +
      EVAL +
      `/${evalId}` +
      "/question" +
      `/${evalQuestionId}` +
      "/user" +
      `/${userEvalId}`,
  },
  jobopening: {
    get: () => HOST + JOB + "/search",
    detail: (jobOpeningId) => HOST + JOB + `/${jobOpeningId}`,
    apply: (jobopeningId) => HOST + JOB + `/${jobopeningId}` + "/apply",
    getBookmark: () => HOST + JOB + "/bookmark",
    addBookmark: (jobOpeningId) =>
      HOST + JOB + "/bookmark" + `/${jobOpeningId}`,
    applicant: (jobOpeningId) => HOST + JOB + `/${jobOpeningId}` + "/applicant",
  },
  condition: {
    new: () => HOST + CONDITION,
    get: () => HOST + CONDITION,
    detail: (searchConditionId) => HOST + CONDITION + `/${searchConditionId}`,
  },
  board: {
    get: () => HOST + BOARD + "/list",
    new: () => HOST + BOARD,
    detail: (board_id) => HOST + BOARD + `/${board_id}`,
    // jobBoardId를 중복해서 쓰길래, 뒤에꺼 안씀
    comment: (board_id) => HOST + BOARD + `/${board_id}` + "/comments",
    commentEdit: (board_id, commentid) =>
      HOST + BOARD + `/${board_id}` + "/comments" + `/${commentid}`,
  },
  room: {
    get: (companyId) => HOST + ROOM + `/${companyId}`,
    edit: (roomId) => HOST + ROOM + `/${roomId}`,
    users: (roomId) => HOST + ROOM + `/${roomId}` + "/users",
    // evalhistory: roomId => HOST + ROOM + '/evalhistory',
  },
  category: {
    sido: () => HOST + CATEGORY + "/sido",
    sidoEdit: (sidoId) => HOST + CATEGORY + "/sido" + `/${sidoId}`,
    gugun: (sidoId) => HOST + CATEGORY + "/sido" + `/${sidoId}` + "/gugun",
    gugunEdit: (sidoId, gugunId) =>
      HOST + CATEGORY + "/sido" + `/${sidoId}` + "/gugun" + `/${gugunId}`,
    jobsLarge: () => HOST + CATEGORY + "/parent",
    // get, post
    jobsLargeEdit: (parent_id) => HOST + CATEGORY + "/parent" + `/${parent_id}`,
    jobsSmall: (parent_id) =>
      HOST + CATEGORY + "/parent" + `/${parent_id}` + "/child",
    jobsSmallEdit: (parent_id, child_id) =>
      HOST + CATEGORY + "/parent" + `/${parent_id}` + "/child" + `/${child_id}`,
  },
  practice: {
    question: (interviewQuestionCategory) =>
      HOST + PRACTICE + "/question" + `/${interviewQuestionCategory}`,
    answer: (interviewQuestionCategory) =>
      HOST +
      PRACTICE +
      "/question" +
      `/${interviewQuestionCategory}` +
      "/answer",
    answerEdit: (interviewQuestionCategory, interviewQuestionAnswerId) =>
      HOST +
      PRACTICE +
      "/question" +
      `/${interviewQuestionCategory}` +
      "/answer" +
      `/${interviewQuestionAnswerId}`,
    questionCategory: () => HOST + PRACTICE + "/questioncategories",
    video: (userId) => HOST + PRACTICE + "/list" + `/${userId}`,
    videoDetail: (practiceInterviewId) =>
      HOST + PRACTICE + `/${practiceInterviewId}`,
  },
};
