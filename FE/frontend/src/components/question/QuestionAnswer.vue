<template>
  <li class="d-flex align-items-center list-group-item">
    <div
      class="border-0 flex-grow-1 text-left  shadow-none"
      @click="$emit('moveQuestionAnswerList')"
      v-if="!isEditing"
    >
      <span>{{ qnasTitle }}</span>
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
    <button id="icon"
      @click="startEditing()"
      class=" btn-outline-primary border-0 ml-2"
    >
      <span class="fa fa-edit"></span>
    </button>
    <button id="icon" @click="$emit('removeQuestionList')" class=" btn-outline-danger border-0">
      <span class="fa fa-trash"></span> 
    </button>
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

</style>
