import { createStore } from 'vuex'
import { instance } from '@/api/index.js'
import createPersistedState from 'vuex-persistedstate';



const store = createStore({
    state() {
        return {
            isLogin: false,
            signinIdx: '',
            vidieoActive: true,
            audioActive: true,
            jwtToken: '',
            // audioDetect: '',



        }
    },
    mutations :{ // state 수정하는 곳 // 함수식으로 만들기
       SET_IS_LOGIN(state, isLogin) {
           state.isLogin = isLogin;
       },
       SET_GET_USER(state, signinIdx) {
           state.signinIdx = signinIdx;
       },
       SET_VIDEO(state, vidieoActive) {
           state.vidieoActive = vidieoActive;
       },
       SET_AUDIO(state, audioActive) {
            state.audioActive = audioActive;
        },
        SET_JWT_TOKEN(state, jwtToken) {
            state.jwtToken = jwtToken;
        },
        // SET_AUDIO_DETECT(state, audioDetect) {
        //     state.audioDetect = audioDetect;
        // },
        SET_SESSION(state, session) {
            state.session = session;
        },


        // toggleVideo(state, videoActive) {   // 토글 시도
        //     state.videoActive = !videoActive;
        //     this.publisher.publishVideo(state.videoActive);
        // },
        // toggleAudio(state, audioActive) {   // 토글 시도
        //     state.audioActive = !audioActive;
        //     state.publisher.publishAudio(state.audioActive);
        // },
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
                // console.log(res.data)
                if (res.data["status"] == true) {
                  let token = res.headers["jwt-auth-token"];
                //   this.state.jwtToken = token
                // console.log(token)
                  context.commit("SET_JWT_TOKEN", token)
                //   console.log(this.state.jwtToken)
                  context.commit("SET_IS_LOGIN", true);
                //   console.log(res)
                  context.commit("SET_GET_USER", res.data["signinIdx"])

                //   localStorage.setItem('jwt', token);
                //   console.log(111111111111111)

                } else {
                //   console.log(7)
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