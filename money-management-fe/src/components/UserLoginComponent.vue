<template>
  <div>
    <div class="overlay"></div>
    <div class="userLoginComponent">
      <p class="closeButton" @click="closeLoginForm">X</p>
      <p class="loginFormTitle">Login Form</p>
      <form @submit.prevent="login" class="loginForm">
        <label class="loginLabel" for="loginEmail">Email:</label>
        <input v-model.trim="$v.user.email.$model"
               class="loginInput" type="text" name="loginEmail" id="loginEmail"
               :class="{invalidFiled: $v.user.email.$error}">
        <div class="loginError">
        <span v-if="$v.user.email.$dirty && !$v.user.email.required" class="errorMessage">
          Имейла е задължителен!
        </span>
          <span v-if="!$v.user.email.email" class="errorMessage">
          Невалидан имейл!
        </span>
          <span v-else-if="!$v.user.email.minLength || !$v.user.email.maxLength" class="errorMessage">
          Дължината може да бъде между 6 и 50 символа!
        </span>
        </div>
        <label class="loginLabel" for="loginPassword">Password:</label>
        <input v-model.trim="$v.user.password.$model"
               class="loginInput" type="password" name="loginPassword" id="loginPassword"
               :class="{invalidFiled: $v.user.password.$error}">
        <div class="loginError">
        <span v-if="$v.user.password.$dirty && !$v.user.password.required" class="errorMessage">
        Паролата е задължителна!
        </span>
          <span v-else-if="!$v.user.password.minLength || !$v.user.password.maxLength" class="errorMessage">
          Дължината може да е междъ 6 и 20 символа!
        </span>
        </div>
        <button class="loginButton">LOGIN</button>
        <p class="smallText">If you don't have account
          <router-link tag="span" :to="{name: 'Register'}">click here</router-link>
        </p>
      </form>
    </div>
    <modal name="messageModal" :shiftX="1" :shiftY="0" :height="0" :width="0">
      <MessageModal :message="this.modal.message" :messageClass="this.modal.messageClass"/>
    </modal>
  </div>
</template>

<script src="../scripts/user-login.js"></script>

<!-- TODO style -->
<style scoped>
@import url("../assets/css/user-login.css");
</style>