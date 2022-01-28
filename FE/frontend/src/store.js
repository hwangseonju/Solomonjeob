import { createStore } from 'vuex'

const store = createStore({
    state() {
        return {
            isLogin: false,
        }
    },
    mutations :{ // state 수정방법 정의하는 곳 // 함수식으로 만들기
       SET_IS_LOGIN(state, isLogin) {
           state.isLogin = isLogin;
       }
    },
    actions :{  // ajax 요청 하는 곳, 또는 오래 걸리는 작업들, axios 는 여기다가 하기

        }
    
})
export default store;