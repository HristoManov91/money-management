<template>
  <div>
    <nav>
      <ul>
        <router-link tag="li" :to="{name: 'Home'}">HOME</router-link>
        <router-link v-if="currentUser" class="router" :to="{name: 'AddExpense'}">ADD EXPENSE</router-link>
      </ul>
      <ul>
        <p class="welcomeMessage" v-if="currentUser">Welcome: {{ currentUser.email }}</p>
        <router-link v-if="!currentUser" tag="li" :to="{name: 'Login'}">LOGIN</router-link>
        <router-link v-if="!currentUser" tag="li" :to="{name: 'Register'}">REGISTER</router-link>
        <li v-if="currentUser" @click="logOut">LOGOUT</li>
      </ul>
    </nav>
  </div>
</template>

<script src="../scripts/navigation.js"></script>

<style scoped>
  @import url("../assets/css/navigation.css");
</style>