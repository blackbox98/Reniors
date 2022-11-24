import { createRouter, createWebHistory } from "vue-router";

import ResumeStepOne from "../components/resume/ResumeStepOne.vue";
import ResumeStepTwo from "../components/resume/ResumeStepTwo.vue";
import ResumeStepThree from "../components/resume/ResumeStepThree.vue";
import ResumeStepFour from "../components/resume/ResumeStepFour.vue";
import ResumeStepFive from "../components/resume/ResumeStepFive.vue";
import ResumeDetail from "../components/resume/ResumeDetail.vue";
import LoginMainView from "../views/user/LoginMainView.vue";
import LoginUserView from "../views/user/LoginUserView.vue";
import LoginCompanyView from "../views/user/LoginCompanyView.vue";
import SignupView from "../views/user/SignupView.vue";
import SignupCompleteView from "../views/user/SignupCompleteView.vue";
import FindPasswordView from "../views/user/FindPasswordView.vue";
import FindUsernameView from "../views/user/FindUsernameView.vue";
import MyPageView from "../views/user/MyPageView.vue";
import MyinfoEdit from "../components/user/MyinfoEdit.vue";
import FindUsernameResult from "../components/user/FindUsernameResult.vue";
import FindPasswordResult from "../components/user/FindPasswordResult.vue";
import PreferSetting from "../components/user/PreferSetting";
import PreferSettingComplete from "../views/user/PreferSettingCompleteView.vue";
import MyCalendarView from "../views/user/MyCalendarView.vue";

import CreateSetting from "../views/user/CreateSetting.vue";
import UpdateSetting from "../views/user/UpdateSetting.vue";

import CompanyHomeView from "@/views/home/CompanyHomeView.vue";
import HomeView from "../views/home/HomeView.vue";
import CompanyInterviewView from "@/views/CompanyInterviewView.vue";
import CompanyJobOpeningView from "@/views/CompanyJobOpeningView.vue";
import CompanyMyPageView from "@/views/CompanyMyPageView.vue";

import CompanyMyPage from "@/components/Company/Info/CompanyMyPage.vue";
import CompanyRegist from "@/components/Company/Info/CompanyRegist.vue";
import CompanyUpdate from "@/components/Company/Info/CompanyUpdate.vue";
import CompanyJobOpeningDetail from "@/components/Company/JobOpening/JobOpeningDetail.vue";
import CompanyJobOpeningList from "@/components/Company/JobOpening/JobOpeningList.vue";
import CompanyJobOpeningRegist from "@/components/Company/JobOpening/JobOpeningRegist.vue";
import CompanyJobOpeningUpdate from "@/components/Company/JobOpening/JobOpeningUpdate.vue";
import ApplyList from "@/components/Company/JobOpening/ApplyList.vue";
import UserEvalList from "@/components/Company/JobOpening/UserEvalList.vue";
import ResumeView from "@/components/Company/interview/ResumeView.vue";
import JobOpeningInterviewList from "@/components/Company/interview/JobOpeningInterviewList.vue";
import CompanyInterviewList from "@/components/Company/interview/InterviewList.vue";
import CompanyInterview from "@/views/video/CompanyInterview.vue";
import EvalList from "@/components/Company/interview/EvalList.vue";
import EvalRegist from "@/components/Company/interview/EvalRegist.vue";

//boards
import BoardMain from "@/views/board/BoardMain.vue";
import BoardDetail from "@/views/board/BoardDetail.vue";
import BoardCreate from "@/views/board/BoardCreate.vue";
import BoardUpdate from "@/views/board/BoardUpdate.vue";

//video
import VideoMain from "@/views/video/VideoMain.vue";
import UserInterview from "@/views/video/UserIntereview.vue";

//practice
import QuestionList from "@/views/practice/QuestionList.vue";
import QuestionAnswer from "@/views/practice/QuestionAnswer.vue";
import VideoPractice from "@/views/practice/VideoPractice.vue";
import VideoPracticeList from "@/views/practice/VideoPracticeList.vue";
import AnswerUpdate from "@/views/practice/AnswerUpdate.vue";
import PracticePage from "@/views/practice/PracticePage.vue";
import PracticeBox from "@/views/practice/PracticeBox.vue";
import VideoConfirm from "@/views/practice/VideoConfirm.vue";

//interview
import OpenVidu from "@/views/openvidu/OpenVidu.vue";

//jobopening
import WholeJobOpeningView from "@/views/jobopening/WholeJobOpeningView.vue";

import IntroView from "@/views/home/IntroView.vue";

const routes = [
  {
    path: "/",
    name: "intro",
    component: IntroView,
  },
  {
    path: "/home",
    name: "home",
    component: HomeView,
  },
  {
    path: "/about",
    name: "about",
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/AboutView.vue"),
  },

  //user

  {
    path: "/mypage/calendar",
    name: "MyCalendarView",
    component: MyCalendarView,
  },
  {
    path: "/kakaologin",
    name: "kakaoLogin",
    component: () => import("../views/user/kakaoLogin.vue"),
  },
  {
    path: "/login",
    name: "Login",
    component: LoginMainView,
  },
  {
    path: "/login/user",
    name: "LoginUser",
    component: LoginUserView,
  },
  {
    path: "/login/company",
    name: "LoginCompany",
    component: LoginCompanyView,
  },
  {
    path: "/signup",
    name: "Signup",
    component: SignupView,
  },
  {
    path: "/signup/complete",
    name: "SignupComplete",
    component: SignupCompleteView,
  },
  {
    path: "/find/password",
    name: "FindPassword",
    component: FindPasswordView,
  },
  {
    path: "/find/password",
    name: "FindPasswordResult",
    component: FindPasswordResult,
  },
  {
    path: "/find/username",
    name: "FindUsername",
    component: FindUsernameView,
  },
  {
    path: "/find/username",
    name: "FindUsernameResult",
    component: FindUsernameResult,
  },
  {
    path: "/mypage",
    name: "MyPage",
    component: MyPageView,
  },
  {
    path: "/myinfo",
    name: "MyinfoEdit",
    component: MyinfoEdit,
  },
  {
    path: "/setting",
    name: "PreferSetting",
    component: PreferSetting,
  },

  {
    path: "/setting/complete",
    name: "PreferSettingComplete",
    component: PreferSettingComplete,
  },

  {
    path: "/setting/create",
    name: "CreateSetting",
    component: CreateSetting,
  },

  {
    path: "/setting/update",
    name: "UpdateSetting",
    component: UpdateSetting,
  },

  // resume
  {
    path: "/resume/detail",
    component: ResumeDetail,
    name: "ResumeDetail",
  },
  {
    path: "/resume/edit/1",
    component: ResumeStepOne,
    name: "ResumeStepOne",
  },
  {
    path: "/resume/edit/2",
    component: ResumeStepTwo,
    name: "ResumeStepTwo",
  },
  {
    path: "/resume/edit/3",
    component: ResumeStepThree,
    name: "ResumeStepThree",
  },
  {
    path: "/resume/edit/4",
    component: ResumeStepFour,
    name: "ResumeStepFour",
  },
  {
    path: "/resume/edit/5",
    component: ResumeStepFive,
    name: "ResumeStepFive",
  },

  // condition
  {
    path: "/jobopening/condition",
    name: "Condition",
    component: () => import("@/views/condition/ConditionView.vue"),
    children: [
      {
        path: "create",
        name: "ConditionCreate",
        component: () => import("@/views/condition/ConditionCreateView.vue"),
      },
      {
        path: "result/:conditionId",
        name: "ConditionResult",
        component: () => import("@/views/condition/ConditionResultView.vue"),
      },
      {
        path: "edit/:conditionId",
        name: "ConditionEdit",
        component: () => import("@/views/condition/ConditionEditView.vue"),
      },
    ],
  },

  // jobopening
  {
    path: "/jobopening",
    name: "Jobopening",
    component: () => import("../views/jobopening/JobopeningView.vue"),
  },
  {
    path: "/jobopening/whole",
    name: "WholeJobOpening",
    component: WholeJobOpeningView,
  },
  {
    path: "/jobopening/:jobopeningId",
    name: "JobopeningDetail",
    component: () => import("../views/jobopening/JobopeningDetailView.vue"),
  },

  //company
  {
    path: "/company",
    name: "company",
    component: CompanyHomeView,
  },
  {
    path: "/regist",
    name: "companyregist",
    component: CompanyRegist,
  },

  {
    path: "/company/mypage",
    name: "companymypage",
    component: CompanyMyPageView,
    redirect: "/company/mypage/main",
    children: [
      {
        path: "main",
        name: "companymypage",
        component: CompanyMyPage,
      },
      {
        path: "update",
        name: "companyupdate",
        component: CompanyUpdate,
      },
    ],
  },
  {
    path: "/company/jobopening",
    name: "companyjobopening",
    component: CompanyJobOpeningView,
    redirect: "/company/jobopening/list",
    children: [
      {
        path: "list",
        name: "companyjobopeninglist",
        component: CompanyJobOpeningList,
      },
      {
        path: "regist",
        name: "companyjobopeningregist",
        component: CompanyJobOpeningRegist,
      },
      {
        path: "detail/:no",
        name: "companyjobopeningdetail",
        component: CompanyJobOpeningDetail,
      },
      {
        path: "update/:no",
        name: "companyjobopeningupdate",
        component: CompanyJobOpeningUpdate,
      },
      {
        path: "apply/list/:no",
        name: "applylist",
        component: ApplyList,
      },
      {
        path: "resume/:no",
        name: "resumeview",
        component: ResumeView,
      },
      {
        path: "eval/:no",
        name: "usereval",
        component: UserEvalList,
      },
    ],
  },
  {
    path: "/company/interview",
    name: "companyinterview",
    component: CompanyInterviewView,
    redirect: "/company/interview/list",
    children: [
      {
        path: "list",
        name: "jobopeninginterviewlist",
        component: JobOpeningInterviewList,
      },
      {
        path: "interviewlist/:no",
        name: "companyinterviewlist",
        component: CompanyInterviewList,
      },
      {
        path: "evallist/:no",
        name: "companyeval",
        component: EvalList,
      },
      {
        path: "evalregist",
        name: "evalregist",
        component: EvalRegist,
      },
    ],
  },
  {
    path: "/boards/:category_id",
    component: BoardMain,
    name: "boardMain",
  },
  {
    path: "/boards/:category_id/:board_id",
    component: BoardDetail,
    name: "boardDetail",
  },
  {
    path: "/boards/:category_id/create",
    component: BoardCreate,
    name: "boardCreate",
  },
  {
    path: "/boards/:category_id/:board_id/update",
    component: BoardUpdate,
    name: "boardUpdate",
  },

  //video
  {
    path: "/video",
    component: VideoMain,
    name: "VideoMain",
  },
  {
    path: "/video/user",
    component: UserInterview,
    name: "userInterview",
  },
  {
    path: "/video/company/:no/:jobOpeningId",
    component: CompanyInterview,
    name: "companyInterviewVideo",
  },

  //video practice
  {
    path: "/practice/questions",
    name: "QuestionList",
    component: QuestionList,
  },
  {
    path: "/practice/question/:question_id/answer",
    component: QuestionAnswer,
    name: "QuestionAnswer",
  },
  {
    path: "/practice/question/:question_id/answer/update",
    component: AnswerUpdate,
    name: "AnswerUpdate",
  },
  {
    path: "/practice/videopractices",
    component: VideoPracticeList,
    name: "VideoPracticeList",
  },
  {
    path: "/practice/videopractice",
    component: VideoPractice,
    name: "VideoPractice",
  },
  {
    path: "/practice/video",
    component: PracticePage,
    name: "PracticePage",
  },
  {
    path: "/practice/box",
    component: PracticeBox,
    name: "PracticeBox",
  },
  {
    path: "/practice/box/:videoId",
    component: VideoConfirm,
    name: "videoConfirm",
  },

  //open vidu

  {
    path: "/interview",
    component: OpenVidu,
    name: "openVidu",
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
