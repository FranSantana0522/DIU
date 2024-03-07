import axios from "axios";

export default axios.create({
  baseURL: "http://Agenda-env.eba-r52h5aj9.us-east-1.elasticbeanstalk.com/api/v1",
  headers: {
    "Content-type": "application/json"
  }
});