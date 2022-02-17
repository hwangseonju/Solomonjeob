<template>
	<div class="container">
		<div class="align-items-center pt-3 pb-2 mb-3 ">
			<div id="session" v-if="session && !selected">
				<div id="session-header " class="d-flex justify-content-center m-4">
					<h2 id="session-title" class="d-flex justify-content-center">
						<img class="setimg" src="@/assets/gear.png"> &nbsp;
						<div>SETTING</div>
					</h2>
				</div>
				<div class="container d-flex justify-content-center">
					<div >
						<user-video  :stream-manager="publisher" @click="updateMainVideoStreamManager(publisher)"/>
					</div>
					<div class="col-3">
						<my-question-list
							:formattedElapsedTime="formattedElapsedTime"
							@changeForm="changeForm"
							@stopWatch="stopWatch"
							@startWatch="startWatch"
							>
						</my-question-list>
					</div>
				</div>

				<div class="mt-5 d-flex justify-content-center">
					<div class="setbtn m-4" @click="this.togglepublisherVideo">
						<span v-if="this.checkVideo"><img class="soundimg" src="@/assets/novideo.png"></span>
						<span v-else><img class="soundimg" src="@/assets/video.png"></span>
					</div>
					<div class="setbtn m-4" @click="this.togglepublisherAudio">
						<span v-if="this.audioActive && !this.audioDetect"><img class="soundimg" src="@/assets/audio.png"></span>
						<span v-if="!this.audioActive"><img src="@/assets/noaudio.png"></span>
						<span v-if="this.audioDetect && this.audioDetect"><img class="soundimg" src="@/assets/audio.gif"></span>
					</div>
					<div class="leavebtn m-4"  @click="leaveSession">
						<i class="fas fa-times fa-lg"></i>
					</div>
				

					
				</div>

			</div>
			<div v-if="selected" class="flex-container">
				<br>
				<div class="row text-center">
					<h1>TEST</h1>
				</div>
				<div class="row text-center">
					<div>
						<!--초대 URL <input type="text" v-model="copyUrl" style="width:300px;" readonly/>-->
						<button class="btn-two blue rounded" type="button" @click="docopy"><i class="fas fa-clone"></i> 초대 URL 복사</button>
					</div>
				</div>
				<br>
				<div class="row">
					<div class="col-md-8 text-center ">
						<div id="video-container" class="container">
							<div class="row">
								<!--<div v-if="this.subscribers.length!==0" class="flex-container mx-auto card text-center" style="width: 310px;height: 340px;padding:5px;">
										<user-video class="flex-container card-img-top" v-for="sub in subscribers" :key="sub.stream.connection.connectionId" :stream-manager="sub" @click="updateMainVideoStreamManager(sub)" />
										<div class="card-text text-center">면접관</div>
								</div>-->
								<div v-if="this.subscribers.length!==0">
									<div id="my-comp-template" >
										<user-video class="card-img-top" style="display:inline;" v-for="sub in subscribers" :key="sub.stream.connection.connectionId" :stream-manager="sub" @click="updateMainVideoStreamManager(sub)" />
									</div>
								</div>
								<div v-if="this.subscribers.length===0">
									<img class="col-md-8" src="@/assets/director.png" alt="가상 면접관">
								</div>
								<div style="font-size:30px;padding:20px;">
									<img v-if="elapsedTime!==0" src="@/assets/movetime.gif" style="width:50px;height:50px;"/>
									<img v-if="elapsedTime===0" src="@/assets/stoptime.png" style="width:50px;height:50px;"/>
									{{formattedElapsedTime}}
								</div>
								<div>
									<div class="mx-auto card text-center" style="width: 310px;height: 340px;padding:5px;">
										<user-video class="card-img-top" :stream-manager="publisher" @click="updateMainVideoStreamManager(publisher)" />
										<div class="card-text text-center">{{this.nickname}}</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<my-question-list
						style="float: right;"
						:formattedElapsedTime="formattedElapsedTime"
						@changeForm="changeForm"
						stopWatch="stopWatch"
						@startWatch="startWatch"
					>
					</my-question-list>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
import myQuestionList from '@/components/interview/myQuestionList.vue';
import axios from 'axios';
// import stopWatch from '@/components/interview/stopWatch.vue';
import { OpenVidu } from 'openvidu-browser';
import UserVideo from '@/components/interview/UserVideo.vue';
import {  mapMutations, mapState } from 'vuex';

axios.defaults.headers.post['Content-Type'] = 'application/json';
const OPENVIDU_SERVER_URL = "https://i6c207.p.ssafy.io";
const OPENVIDU_SERVER_SECRET = "Ss2o0l7o";
// const OPENVIDU_SERVER_URL = "https://" + location.hostname + ":4443";
// const OPENVIDU_SERVER_SECRET = "MY_SECRET";
export default {

	components: {
		UserVideo,
		myQuestionList,
		// stopWatch
	},
	data () {
		return {
			questionStop:[],
			OV: undefined,
			session: undefined,
			mainStreamManager: undefined,
			publisher: undefined,
			subscribers: [],
			// mySessionId: 'SessionA',
			mySessionId: '',
			// myUserName: 'Participant' + Math.floor(Math.random() * 100),
			myUserName: undefined,
			checkVideo: true,
			checkAudio: false,
			audioDetect: false,
			audioActive: false,
			elapsedTime: 0,
			timer: undefined,
			copyUrl : ''
		}
	},
	computed: {
		...mapState(["isLogin", "signinIdx", "vidieoActive",  "session", "nickname", "selected"]),
		formattedElapsedTime() {
      const date = new Date(null);
      date.setSeconds(this.elapsedTime / 1000);
      const utc = date.toUTCString();
      return utc.substr(utc.indexOf(":") - 2, 8);
    }
	},

	methods: {
		...mapMutations(["SET_VIDEO", "SET_AUDIO", "toggleVideo", "toggleAudio", "SET_AUDIO_DETECT", "SET_SESSION", "SET_SELECTED","SET_SELECTED_LIST"]),
		changeForm(){
			this.$store.commit("SET_SELECTED", true);
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
							publishAudio: false,  	// Whether you want to start publishing with your audio unmuted or not
							publishVideo: false,  	// Whether you want to start publishing with your video enabled or not
							resolution: '650x400',  // The resolution of your video 640x480
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
			this.$store.commit("SET_SELECTED", false)
			this.$store.commit("SET_SELECTED_LIST", [])

     		this.$router.push('Home') 
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
					.post(`${OPENVIDU_SERVER_URL}/openvidu/api/sessions/${sessionId}/connection`, {}, {
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

		docopy(){
			const currenturl = "https://i6c207.p.ssafy.io/solomonjeob/interview/invite/"+this.mySessionId;
			navigator.clipboard.writeText(currenturl);
		}
	},

	created() {
		this.mySessionId = "Session"+this.signinIdx
		this.myUserName = this.nickname
		// this.mySessionId = this.myUserName
		this.joinSession()
		this.copyUrl = "https://i6c207.p.ssafy.io/solomonjeob/interview/invite/"+this.mySessionId
	},

	beforeUnmount() { 
		this.leaveSession(); 
	},

}
</script>

<style scoped>
.leavebtn{
	border-radius: 50%;
	background: #e85848;
	width: 50px;
	height: 50px;
	text-align: center;
	line-height: 50px;
}
.leavebtn:hover{
	background: rgb(221, 34, 34);
}
.setbtn{
	border-radius: 50%;
	background: #68c433;
	width: 50px;
	height: 50px;
	text-align: center;
	line-height: 50px;
}
.setbtn:hover{
	background: green;
	
}
.soundimg {
	width: 27px;
	height: 27px;
}
.setimg{
	width: 35px;
	height: 35px;
}
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
.btn-two {
	color: white;	
	padding: 10px 25px;
	display: inline-block;
	border: none;
	border-bottom-color: rgba(0,0,0,0.34);
	text-shadow:0 1px 0 rgba(0,0,0,0.15);
	box-shadow: 0 1px 0 rgba(255,255,255,0.34) inset, 
				      0 2px 0 -1px rgba(0,0,0,0.13), 
				      0 3px 0 -1px rgba(0,0,0,0.08), 
				      0 3px 13px -1px rgba(0,0,0,0.21);
}
.btn-two:active {
	top: 1px;
	box-shadow: 0 1px 0 rgba(255,255,255,0.89),0 1px rgba(0,0,0,0.05) inset;
	position: relative;
}
.blue   {background: #6698cb;}
</style>

