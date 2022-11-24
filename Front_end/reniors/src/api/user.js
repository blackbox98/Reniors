
import { apiInstance } from "./index.js"

const api = apiInstance()


async function login(credentials, success, fail){
  console.log(JSON.stringify(credentials))
  await api
  .post(`/users/login`, JSON.stringify(credentials))
  .then(success)
  .catch(fail)
}

async function register(credentials, success, fail){
  await api
  .post(`users/regist`, JSON.stringify(credentials))
  .then(success)
  .catch(fail)
}

async function findById(id, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.get(`/users/info/${id}`).then(success).catch(fail);
  // await api.get(`/users`).then(success).catch(fail);
}



export {
  login, register, findById
}