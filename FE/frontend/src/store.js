import { createStore } from 'vuex'
import { instance } from '@/api/index.js'
import createPersistedState from 'vuex-persistedstate';

const store = createStore({
    state() {
        return {
            isLogin: false,
            signinIdx: '',
            nickname: '',
            

        }
    },
    mutations :{ // state 수정하는 곳 // 함수식으로 만들기
       SET_IS_LOGIN(state, isLogin) {
           state.isLogin = isLogin;
       },
       SET_GET_USER(state, signinIdx) {
           state.signinIdx = signinIdx;
       },
       SET_NICKNAME(state, nickname) {
           state.nickname = nickname;
       }

    },
    actions :{  // ajax 요청 하는 곳, 또는 오래 걸리는 작업들, axios 는 여기다가 하기
        async userConfirm(context, credentials) {
            await instance({
                method: 'post',
                url: '/api/members/signin',
                data: JSON.stringify(credentials),
            })
            .then((res) => {
                console.log(res.data)
                if (res.data["status"] == true) {
                  let token = res.headers["jwt-auth-token"];
                  context.commit("SET_IS_LOGIN", true);
                  context.commit("SET_GET_USER", res.data["signinIdx"])
                  context.commit("SET_NICKNAME", res.data["nickName"])
                  console.log(res)
                  localStorage.setItem('jwt', token);
                } else {
                  context.commit("SET_IS_LOGIN", false);
                }
              })
              .catch(() => {});

        },

    },
    plugins: [
        createPersistedState({
            storage: localStorage,
        })
    ]
})
export default store;