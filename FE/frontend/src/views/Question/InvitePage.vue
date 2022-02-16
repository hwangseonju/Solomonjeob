<template>
  <div class="container-fluid">
    <div class="row">
      <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
        <div
          class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3"
        >
          <div id="main-container" class="container">
				<div id="join" v-if="!session">
					<div id="img-div"><img src="resources/images/openvidu_grey_bg_transp_cropped.png" /></div>
					<div id="join-dialog" class="jumbotron vertical-center">
						<h1>초대 링크!</h1>
						<div class="form-group">
							<p>
								<label>Nickname</label>
								<input v-model="myUserName" class="form-control" type="text" required>
							</p>
							<p class="text-center">
								<button class="btn btn-lg btn-success" @click="checkSession(mySessionId)">Join!</button>
							</p>
						</div>
					</div>
				</div>

				<div v-if="subscribers.length!==0">
					<div id="session" v-if="session">
						<div id="session-header">
							<h1 id="session-title">{{ mySessionId }}</h1>
							<input class="btn btn-large btn-danger" type="button" id="buttonLeaveSession" @click="leaveSession" value="Leave session">
						</div>
						<div id="main-video" class="col-md-6">
							<user-video :stream-manager="publisher" @click="updateMainVideoStreamManager(publisher)"/>
						</div>
						<div id="video-container" class="col-md-6">
							<user-video v-for="sub in subscribers" :key="sub.stream.connection.connectionId" :stream-manager="sub" @click="updateMainVideoStreamManager(sub)"/>
						</div>
					</div>
				</div>
			</div>
        </div>
      </main>
    </div>
  </div>
</template>

<script>
//import myQuestionList from '@/components/interview/myQuestionList.vue';
import axios from 'axios';
import { instance } from '@/api/index.js'
// import stopWatch from '@/components/interview/stopWatch.vue';
import { OpenVidu } from 'openvidu-browser';
import UserVideo from '@/components/interview/UserVideo.vue';
import {  mapMutations, mapState } from 'vuex';

axios.defaults.headers.post['Content-Type'] = 'application/json';
axios.defaults.headers.get['Content-Type'] = 'application/json';
const OPENVIDU_SERVER_URL = "https://i6c207.p.ssafy.io";
const OPENVIDU_SERVER_SECRET = "Ss2o0l7o";
export default {

	components: {
		UserVideo,
		//myQuestionList,
		// stopWatch
	},
	data () {
		return {
			questionStop:[],
			selected : false,
			OV: undefined,
			session: undefined,
			mainStreamManager: undefined,
			publisher: undefined,
			subscribers: [],
			// mySessionId: 'SessionA',
			mySessionId: '',
			myUserName: 'solomonjeob' + Math.floor(Math.random() * 100),
			checkVideo: true,
			checkAudio: false,
			audioDetect: false,
			audioActive: false,
			elapsedTime: 0,
			timer: undefined,

		}
	},
	computed: {
		...mapState(["isLogin", "signinIdx",]),
		formattedElapsedTime() {
      const date = new Date(null);
      date.setSeconds(this.elapsedTime / 1000);
      const utc = date.toUTCString();
      return utc.substr(utc.indexOf(":") - 2, 8);
    }
	},

	methods: {
		...mapState(["isLogin", "signinIdx", "vidieoActive",  "session"]),
		...mapMutations(["SET_VIDEO", "SET_AUDIO", "toggleVideo", "toggleAudio", "SET_AUDIO_DETECT", "SET_SESSION"]),
		changeForm(propSelected){
			this.selected= propSelected
		},
		speechDetect() {
			this.session.on('publisherStartSpeaking', () => {
				this.audioDetect = true

				
			});

			this.session.on('publisherStopSpeaking', () => {
				this.audioDetect = false


			});

		},
		togglepublisherAudio() {   // 토글 시도

			this.audioActive = !this.audioActive;

			this.publisher.publishAudio(this.audioActive);
			this.checkAudio = !this.checkAudio



			this.speechDetect();

				
		},
		togglepublisherVideo() {   // 토글 시도
			this.videoActive = !this.videoActive;
			this.publisher.publishVideo(this.videoActive);
			this.checkVideo = !this.checkVideo


		},

		checkSession(SessionId) {
			console.log(SessionId);
			let result = 0;

			instance({
				method: 'get',
				url: `${OPENVIDU_SERVER_URL}/openvidu/api/sessions`,
				headers: {
                            Authorization: 'Basic ' + btoa('OPENVIDUAPP:' + OPENVIDU_SERVER_SECRET),
                            'Content-Type': 'application/json',
                }
			})
			.then((res) => {
				let total = parseInt(JSON.stringify(res.data.numberOfElements));
				if(total!==0){
					for(let i=0; i<total; i++){
						if(JSON.stringify(res.data.content[i].id).replaceAll("\"", "")===SessionId){
							result = parseInt(JSON.stringify(res.data.content[i].connections.numberOfElements))+1;
							break;
						}
					}
				}else{
					result = 0;
				}

				if(result<=0){
					alert("잘못된 초대 URL이거나 종료된 면접연습방입니다.");
				}else if(result>4) {
					alert("면접관 인원이 꽉 찼어요!");
				}else{
					this.joinSession();
				}   
			})
			.catch(err => {
				console.log(err);
			})
		},

		joinSession () {
			// --- Get an OpenVidu object ---
			this.OV = new OpenVidu();

			// --- Init a session ---
			this.session = this.OV.initSession();

			// --- Specify the actions when events take place in the session ---

			// On every new Stream received...
			this.session.on('streamCreated', ({ stream }) => {
				const subscriber = this.session.subscribe(stream);
				this.subscribers.push(subscriber);
			});

				// On every Stream destroyed...
				this.session.on('streamDestroyed', ({ stream }) => {
					const index = this.subscribers.indexOf(stream.streamManager, 0);
					if (index >= 0) {
						this.subscribers.splice(index, 1);
					}
				});

				// On every asynchronous exception...
				this.session.on('exception', ({ exception }) => {
					console.warn(exception);
				});

				// --- Connect to the session with a valid user token ---

				// 'getToken' method is simulating what your server-side should do.
				// 'token' parameter should be retrieved and returned by your own backend
				this.getToken(this.mySessionId).then(token => {
					this.session.connect(token, { clientData: this.myUserName })
						.then(() => {

							// --- Get your own camera stream with the desired properties ---

							let publisher = this.OV.initPublisher(undefined, {
								audioSource: undefined, // The source of audio. If undefined default microphone
								videoSource: undefined, // The source of video. If undefined default webcam
								publishAudio: true,  	// Whether you want to start publishing with your audio unmuted or not
								publishVideo: true,  	// Whether you want to start publishing with your video enabled or not
								resolution: '640x480',  // The resolution of your video
								frameRate: 30,			// The frame rate of your video
								insertMode: 'APPEND',	// How the video is inserted in the target element 'video-container'
								mirror: false       	// Whether to mirror your local video or not
							});

							this.mainStreamManager = publisher;
							this.publisher = publisher;

							// --- Publish your stream ---

							this.session.publish(this.publisher);
						})
						.catch(error => {
							console.log('There was an error connecting to the session:', error.code, error.message);
						});
				});

				window.addEventListener('beforeunload', this.leaveSession)
		},

		leaveSession () {
			// --- Leave the session by calling 'disconnect' method over the Session object ---
			if (this.session) this.session.disconnect();

			this.session = undefined;
			this.mainStreamManager = undefined;
			this.publisher = undefined;
			this.subscribers = [];
			this.OV = undefined;

			window.removeEventListener('beforeunload', this.leaveSession);
		},

		updateMainVideoStreamManager (stream) {
			if (this.mainStreamManager === stream) return;
			this.mainStreamManager = stream;
		},

		/**
		 * --------------------------
		 * SERVER-SIDE RESPONSIBILITY
		 * --------------------------
		 * These methods retrieve the mandatory user token from OpenVidu Server.
		 * This behavior MUST BE IN YOUR SERVER-SIDE IN PRODUCTION (by using
		 * the API REST, openvidu-java-client or openvidu-node-client):
		 *   1) Initialize a Session in OpenVidu Server	(POST /openvidu/api/sessions)
		 *   2) Create a Connection in OpenVidu Server (POST /openvidu/api/sessions/<SESSION_ID>/connection)
		 *   3) The Connection.token must be consumed in Session.connect() method
		 */

		getToken (mySessionId) {
			return this.createSession(mySessionId).then(sessionId => this.createToken(sessionId));
		},

		// See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-openviduapisessions
		createSession (sessionId) {
			return new Promise((resolve, reject) => {
				axios
					.post(`${OPENVIDU_SERVER_URL}/openvidu/api/sessions`, JSON.stringify({
						customSessionId: sessionId,
					}), {
						auth: {
							username: 'OPENVIDUAPP',
							password: OPENVIDU_SERVER_SECRET,
						},
					})
					.then(response => response.data)
					.then(data => resolve(data.id))
					.catch(error => {
						if (error.response.status === 409) {
							resolve(sessionId);
						} else {
							console.warn(`No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}`);
							if (window.confirm(`No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}\n\nClick OK to navigate and accept it. If no certificate warning is shown, then check that your OpenVidu Server is up and running at "${OPENVIDU_SERVER_URL}"`)) {
								location.assign(`${OPENVIDU_SERVER_URL}/accept-certificate`);
							}
							reject(error.response);
						}
					});
			});
		},

		// See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-openviduapisessionsltsession_idgtconnection
		createToken (sessionId) {
			return new Promise((resolve, reject) => {
				axios
					.post(`${OPENVIDU_SERVER_URL}/openvidu/api/sessions/${sessionId}/connection`, {}, 
					{
						auth: {
							username: 'OPENVIDUAPP',
							password: OPENVIDU_SERVER_SECRET,
						},
					})
					.then(response => response.data)
					.then(data => resolve(data.token))
					.catch(error => reject(error.response));
			});
		},

		startWatch() {
			this.timer = setInterval(() => {
				this.elapsedTime += 1000;
			}, 1000);
		},
		// next() {
		//   this.questionStop.push(this.formattedElapsedTime);
				
		// },
		stopWatch() {
			clearInterval(this.timer);
		},
	},

	created() {
		let beforeUrl = window.location.pathname;
		let afterUrl = beforeUrl.split('/');
		this.mySessionId = afterUrl[4];
	},

	beforeUnmount() {
		this.leaveSession();
	},

}
</script>

<style scoped>
.btn_interview {
  font-weight: bolder;
  color: blueviolet;
  width: 50%;
  box-sizing: border-box;
  border: 3px solid slateblue;
}
.btn_interview:hover {
  font-weight: bolder;
  background-color: blueviolet;
  color: white;
}
.btn_home {
  font-weight: bolder;
  color: blueviolet;
  width: 10%;
  box-sizing: border-box;
  border: 3px solid slateblue;
}
.btn_home:hover {
  font-weight: bolder;
  background-color: blueviolet;
  color: white;
}
</style>
