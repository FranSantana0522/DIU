import axios from "axios";

export default axios.create({
  //Agenda-env.eba-r52h5aj9.us-east-1.elasticbeanstalk.com
  //192.168.100.11:8099
  baseURL: "http://Agenda-env.eba-r52h5aj9.us-east-1.elasticbeanstalk.com/api/v1",
  headers: {
    "Content-type": "application/json"
  }
});