import axios from 'axios';
const instance = axios.create({
  baseURL: 'https://i6c207.p.ssafy.io',
  headers: {
    "Content-type": "application/json",
  },
});


export { instance };