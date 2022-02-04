import { createStore } from 'vuex'
import { instance } from '@/api/index.js'
import createPersistedState from 'vuex-persistedstate';

const store = createStore({
    state() {
        return {
            isLogin: false,
            // credentials: {
            //     memberId: null,
            //     memberPwd: null,
            // }
        }
    },
    mutations :{ // state 수정하는 곳 // 함수식으로 만들기
       SET_IS_LOGIN(state, isLogin) {
           state.isLogin = isLogin;
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
                  localStorage.setItem('jwt', token);
                } else {
                  console.log(7)
                  context.commit("SET_IS_LOGIN", false);
                }
              })
              .catch(() => {});
            // .then(res => {
            //     console.log(res)
            //     localStorage.setItem('jwt', res.data.token)
            //     this.$router.push('Home')
      
            //   })
            //   .catch(err => {
            //     console.log(err)
            //     alert('회원정보가 올바르지 않습니다.')
            //   })            }
        }
    },
    plugins: [
        createPersistedState({
            storage: localStorage,
        })
    ]
})
export default store;