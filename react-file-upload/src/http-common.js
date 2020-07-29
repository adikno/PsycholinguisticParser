import axios from "axios";

export default axios.create({
  baseURL: "http://localhost:8080",
  //baseURL: "http://132.70.30.35:8080",
  headers: {
    "Content-type": "application/json"
  }
});