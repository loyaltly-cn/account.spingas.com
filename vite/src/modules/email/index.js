import {Email} from "./stmp.js";

const email = {
    send:(email) =>{
        Email.send({
            Host : "smtp.elasticemail.com",
            Username : "loyaltly.cn@gmail.com",
            Password : "9A464BB5FF70F00C31FC127936826B62A27F",
            To : email,
            From : "loyaltly.cn@gmail.com",
            Subject : "感谢关注SpinGas!",
            Body : 'test...'
        })
    }
}


export {
    email
}