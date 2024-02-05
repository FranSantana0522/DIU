import axios from "axios";

export default axios.create({
  baseURL: "http://Tutorial-env.eba-umqmvbca.us-east-1.elasticbeanstalk.com/api/v1",
  headers: {
    "Content-type": "application/json"
  }
});