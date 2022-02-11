import { createStore } from 'vuex'
import { instance } from '@/api/index.js'
import createPersistedState from 'vuex-persistedstate';

const store = createStore({
    state() {
        return {
            isLogin: false,
            signinIdx: '',

            

        }
    },
    mutations :{ // state 수정하는 곳 // 함수식으로 만들기
       SET_IS_LOGIN(state, isLogin) {
           state.isLogin = isLogin;
       },
       SET_GET_USER(state, signinIdx) {
           state.signinIdx = signinIdx;
       },

    },
    actions :{  // ajax 요청 하는 곳, 또는 오래 걸리는 작업들, axios 는 여기다가 하기
        async userConfirm(context, credentials) {
            console.log(3)
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
                  console.log(res)
                  context.commit("SET_GET_USER", res.data["signinIdx"])
                  console.log(res)
                  console.log(99999999)
                  console.log(res.headers.data["memberId"])

                  console.log(res.data["memberId"])

                //   console.log()
                //   console.log(78978797987)
                //   console.log(res)
                //   console.log(res.config.data)
                //   console.log(res.config.data["memberPwd"])

                  localStorage.setItem('jwt', token);
                  console.log(111111111111111)

                } else {
                  console.log(7)
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