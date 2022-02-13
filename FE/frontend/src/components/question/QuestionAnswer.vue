<template>
  <li class="d-flex align-items-center list-group-item">
    <button
      class="btn border-0 flex-grow-1 text-left shadow-none"
       v-if="!isEditing"
    >
      <span>{{ qnasTitle }}</span>
    </button>
    <form v-else class="flex-grow-1" @submit.prevent="finishEditing()">
      <input
        type="text"
        class="form-control"
        v-model="newQuestion"
        @blur="finishEditing()"
        ref="newQues"
      />
    </form>
    <button
      @click="startEditing()"
      class="btn btn-outline-primary border-0 ml-2"
    >
      <span class="fa fa-edit"></span>
    </button>
    <button @click="$emit('removeQuestionList')" class="btn btn-outline-danger border-0">
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
        this.$nextTick(() => this.$refs.newTodo.focus());
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
