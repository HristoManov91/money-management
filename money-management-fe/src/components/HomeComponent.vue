<template>
  <div>
    <transition name="fade" mode="out-in">
      <router-view/>
    </transition>
    This is home page
  </div>
</template>

<script src="../scripts/home.js"></script>

<style scoped>
@import url("../assets/css/home.css");
</style>