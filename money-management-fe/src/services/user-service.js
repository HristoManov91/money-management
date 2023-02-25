import axios from "axios";

const USER_CONTROLLER_BASE_URL = 'http://localhost:8081/users/'

class UserService {

    login(user) {
        return axios.post(USER_CONTROLLER_BASE_URL + 'login' , user).then((resp) => {
            console.log('resp', resp)
            if (resp.data.accessToken){
                localStorage.setItem('user' , JSON.stringify(resp.data))
                console.log('localStorage', localStorage)
            }

            return resp.data;
        })
    }

    logout(){
        localStorage.removeItem('user');
    }

    async register(user){
        let response = {};

        await axios.post(USER_CONTROLLER_BASE_URL + 'register', user).then(() => {
            response.status = 'OK'
            console.log('ok')
        }).catch((err) => {
            console.log('error', err)
        })

        return response;
    }
}

export default new UserService();