import axios from "axios";

export default axios.create({
  baseURL: "http://192.168.100.11:8099/api/v1",
  headers: {
    "Content-type": "application/json"
  }
});