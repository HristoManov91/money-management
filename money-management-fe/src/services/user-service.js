import axios from "axios";

const USER_CONTROLLER_BASE_URL = 'http://localhost:8081/users/'

class UserService {

    login(user) {
        return axios.post(USER_CONTROLLER_BASE_URL + 'login' , user).then((resp) => {
            if (resp.data.accessToken){
                localStorage.setItem('user' , JSON.stringify(resp.data))
            }

            return resp.data;
        })
    }

    logout(){
        localStorage.removeItem('user');
    }

    async register(user){
        let response = {};

        await axios.post(USER_CONTROLLER_BASE_URL, user).then(() => {
            response.status = 'OK'
            console.log('ok')
        }).catch((err) => {
            console.log('error')
            response.status = 'ERROR'
            response.message = err.response.data;
        })

        return response;
    }
}

export default new UserService();