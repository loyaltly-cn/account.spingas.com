import axios from "axios";

const url = 'https://account.spingas.com/accountApi'
// const url = 'http://localhost:8001'
const request = axios.create({
    baseURL:url,
    timeout:100000,
    headers: {'Content-Type':'application/x-www-form-urlencoded'}
})


export {
    request,
}