import { createStore } from 'vuex'
import { instance } from '@/api/index.js'
const store = createStore({
    state() {
        return {
            isLogin: false,
            isLoginError: false,
            credentials: {
                memberId: null,
                memberPwd: null,
            }
        }
    },
    mutations :{ // state 수정방법 정의하는 곳 // 함수식으로 만들기
       SET_IS_LOGIN(state, isLogin) {
           state.isLogin = isLogin;
       },
    },
    actions :{  // ajax 요청 하는 곳, 또는 오래 걸리는 작업들, axios 는 여기다가 하기
        async userConfirm({ commit }) {
            // console.log('여기')
            await instance({
                method: 'post',
                url: '/api/members/signin',
                data: this.credentials,
            })
            .then((res) => {
                console.log(res)
                if (res.data === "success") {
                  let token = res.data.token;
                  commit("SET_IS_LOGIN", true);
                  localStorage.setItem('jwt', token);
                } else {
                  console.log(res)
                  commit("SET_IS_LOGIN", false);
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
    }
})
export default store;