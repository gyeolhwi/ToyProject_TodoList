import axios from "axios";

const  api = axios.create({
    baseURL: "http://localhost:8080/api/v1",
    withCredentials: true
});
export default api;

// 요청시에 api.get("/computer") 하면 baseURL기본참조됨
