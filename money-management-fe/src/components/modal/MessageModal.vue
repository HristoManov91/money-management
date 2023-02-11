<template>
  <!-- TODO class -->
  <div :class="messageClass">
    <p>{{ this.message }}</p>
  </div>
</template>

<script src="../../scripts/modal/message.js"></script>

<style scoped>
@import url("../../assets/css/modal/message.css");
</style>