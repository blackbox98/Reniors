import { createStore } from "vuex";
import { user } from "./modules/user";

import home from "./modules/home";
import jobopening from "./modules/jobopening";
import board from "./modules/board";
import category from "./modules/category";
import practice from "./modules/practice";
import company from "./modules/company";
import resume from './modules/resume'
import createPersistedState from 'vuex-persistedstate'
import condition from './modules/condition'


export default createStore({
  modules: { home, user, board, jobopening, category, practice, company, resume, condition },
  plugins:[createPersistedState({

})],
})
