<template>

	<div class="detail-container">
		<button  @click="InsertQuestion">
			+
		</button>
		<div class="all_question">
			<div class="question__box">
				<div class="real-question">
				
					<ul>
						<li v-for="id in this.questionanswer" :key="id">
							<textarea class="question" placeholder="질문을 입력하세요." style="overflow: auto; overflow-wrap: break-word; resize: none;">
								
							</textarea>
							<hr>
							<textarea class="answer" placeholder="답변을 입력하세요." style="overflow: auto; overflow-wrap: break-word; resize: none;">
							</textarea>
						</li>
					</ul>

				</div>
			</div>
		</div>
	</div>
</template>

<script>
export default {
    // name : 'Detail',
	data() {
		return {
      questionanswer: [
        {
          id: 1,
          text: "질문모음집",
        },
				{
					id: 2,
					text: "질문모음집"
				}
			]
		}
	},
	// props: {
	// 		todos: Array,
	// },
	methods:{
		insertQuestion() {
      this.todos = [
        ...this.todos,
          {
            id: new Date().getTime(), 
            text: '질문모음집', 
          }
        ];
      },
	}
}
</script>

<style scoped>

.all_question {
    display: flex;
    background-color: white;
    
}
.question__box {
  width: 700px;
  height: 300px;
  margin: 5px;
  border: 1px;
  position: absolute;
  background-color: white;

}

.question {
    border: none;
    width: 696px;
    min-height: 78px;
    height: 100px;
    display: flex;
    position: relative;
    background-color: white;
}
.answer {
    border: none;
    width: 696px;
    min-height: 78px;
    height: 200px;
    display: flex;
    position: relative;
    background-color: white;
}
.real-question {
    box-shadow: 0px 0px 5px #d8d8d8;
}
</style>