<template>
  <button class="new-todo" @click="handleTodo">
    +
  </button>
</template>

<script>
export default {
  data() {
    return {
      text: "질문모음집"
    };
  },
  methods: {
    handleTodo() {
      const { text } = this;

      {
        this.$emit("insertTodo", text);
      }
    }
  }
};
</script>

<style>

</style>