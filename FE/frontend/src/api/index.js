import axios from 'axios';
const instance = axios.create({
  baseURL: 'https://i6c207.p.ssafy.io',
  // baseURL: 'http://localhost:8080',

  headers: {
    "Content-type": "application/json",
  },
});


export { instance };