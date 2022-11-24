import axios from "axios";
import { API_BASE_URL } from "@/config";

let token = localStorage.getItem("token") || "";

export default axios.create({
  baseURL:API_BASE_URL,
  headers: {
    "Content-Type": "multipart/form-data",
    Authorization: "Bearer " + token,
  },
});
