<template>
  <div>
    <div class="overlay"></div>
    <div class="userRegisterComponent">
      <p class="closeButton" @click="closeRegisterForm">X</p>
      <p class="registerFormTitle">Register Form</p>
      <form @submit.prevent="register" class="registerForm">
        <label class="registerLabel" for="registerEmail">Email:</label>
        <input v-model.trim="$v.user.email.$model"
               class="registerInput" type="text" name="registerEmail" id="registerEmail"
               :class="{invalidFiled: $v.user.email.$error}">
        <div class="registerError">
         <span v-if="$v.user.email.$dirty && !$v.user.email.required" class="regErrorMessage">
          Имейла е задължителен!
         </span>
          <span v-if="!$v.user.email.email" class="regErrorMessage">
          Невалидан имейл!
          </span>
          <span v-else-if="!$v.user.email.minLength || !$v.user.email.maxLength" class="regErrorMessage">
          Дължината може да бъде между 6 и 50 символа!
          </span>
        </div>
        <label class="registerLabel" for="registerPassword">Password:</label>
        <input v-model.trim="$v.user.password.$model"
               class="registerInput" type="password" name="registerPassword" id="registerPassword"
               :class="{invalidFiled: $v.user.password.$error}">
        <div class="registerError">
          <span v-if="$v.user.password.$dirty && !$v.user.password.required" class="regErrorMessage">
           Паролата е задължителна!
          </span>
          <span v-else-if="!$v.user.password.minLength || !$v.user.password.maxLength" class="regErrorMessage">
             Дължината може да е между 6 и 20 символа!
          </span>
        </div>
        <label class="registerLabel" for="registerFullName">FullName:</label>
        <input v-model.trim="$v.user.fullName.$model"
               class="registerInput" type="text" name="registerFullName" id="registerFullName"
               :class="{invalidFiled: $v.user.fullName.$error}">
        <div class="registerError">
          <span v-if="$v.user.fullName.$dirty && !$v.user.fullName.required" class="regErrorMessage">
           Името е задължително!
          </span>
          <span v-else-if="!$v.user.fullName.minLength || !$v.user.fullName.maxLength" class="regErrorMessage">
             Дължината може да е между 4 и 100 символа!
          </span>
        </div>
        <label class="registerLabel" for="registerDateOfBirth">Date of Birth</label>
        <input v-model="$v.user.dateOfBirth.$model"
               class="registerInput" type="date" name="registerDateOfBirth" id="registerDateOfBirth"
               :class="{invalidFiled: $v.user.dateOfBirth.$error}">
        <div class="registerError">
          <span v-if="$v.user.dateOfBirth.$dirty && !$v.user.dateOfBirth.required" class="regErrorMessage">
           Рожденната дата е задължителна!
          </span>
        </div>
        <label class="registerLabel" for="registerGender">Gender</label>
        <select v-model="$v.user.gender.$model"
                id="registerGender" class="registerInput" name="registerGender"
                :class="{invalidFiled: $v.user.gender.$error}">
          <option value="MALE">MALE</option>
          <option value="FEMALE">FEMALE</option>
        </select>
        <div class="registerError">
          <span v-if="$v.user.gender.$dirty && !$v.user.gender.required" class="regErrorMessage">
           Пол е задължителен!
          </span>
        </div>
        <button class="registerButton">Register</button>
        <p class="smallText">If you have account
          <router-link tag="span" :to="{name: 'Login'}">click here</router-link>
        </p>
      </form>
    </div>
    <modal name="messageModal" :shiftX="1" :shiftY="0" :height="0" :width="0">
      <MessageModal :message="this.modal.message" :messageClass="this.modal.messageClass"/>
    </modal>
  </div>
</template>

<script src="../scripts/user-register.js"></script>

<style scoped>
@import url("../assets/css/user-register.css");
</style>