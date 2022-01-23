<template>

    <div class="detail-container">
        <div class="all_question">
            <div class="question__box">
                <div class="real-question">
                    <textarea class="question" placeholder="질문을 입력하세요." style="overflow: auto; overflow-wrap: break-word; resize: none;"></textarea>
                    <hr>
                    <textarea class="answer" placeholder="답변을 입력하세요." style="overflow: auto; overflow-wrap: break-word; resize: none;"></textarea>
                </div>
            </div>
        </div>
        <div class="all_question">
            <div class="question__box">
                <div>
                    <textarea class="question" placeholder="질문을 입력하세요." style="overflow: auto; overflow-wrap: break-word; resize: none;"></textarea>
                    <hr>
                    <textarea class="answer" placeholder="답변을 입력하세요." style="overflow: auto; overflow-wrap: break-word; resize: none;"></textarea>

                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name : 'Detail',
    data() {
        return {
            text: ""
        }
    },
    props: {
        todos: Array,
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