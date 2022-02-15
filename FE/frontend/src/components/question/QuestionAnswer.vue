<template>
  <li class="d-flex align-items-center list-group-item li_style">
    <div
      class="border-0 flex-grow-1 text-left  shadow-none"
      @click="$emit('moveQuestionAnswerList')"
      v-if="!isEditing"
    >
      <span class="span_style">{{ qnasTitle }}</span>
    </div>
    <form v-else class="flex-grow-1" @submit.prevent="finishEditing()">
      <input
        type="text"
        class="form-control"
        v-model="newQuestion"
        @blur="finishEditing()"
        ref="newQues"
      />
    </form>
    <div id="icon" class="edit_style"
      @click="startEditing()"
    >
      <i class="far fa-edit e_style"></i>
    </div>
    
    <div id="icon" @click="$emit('removeQuestionList')" class="remove_style">
      <i class="fas fa-times r_style"></i>
      <!-- <span class="fa fa-trash"></span>  -->
    </div>
  </li>
</template>

<script>
export default {
  data() {
    return {
      isEditing: false,
      newQuestion: ""
    };
  },
  props: {
    qnasTitle: String,
  },
  methods: {
    startEditing() {
      if (this.isEditing) {
        this.finishEditing();
      } else {
        this.newQuestion = this.qnasTitle;
        this.isEditing = true;
        this.$nextTick(() => this.$refs.newQues.focus());
      }
    },
    finishEditing() {
      this.isEditing = false;
      this.$emit("editQuestion", this.newQuestion);
    }
  }
};
</script>

<style scoped>
.li_style {
  width: 110%;
}
.span_style {
  padding-right: 10%;
  text-align: left;
  width: 40%;
}
.edit_style {
  padding: 2%;
}
.remove_style {
  padding: 2%;
}
.e_style:hover {
  color: black;
  font-size: 120%;
}
.r_style:hover {
  color: red;
  font-size: 120%;
}
</style>
