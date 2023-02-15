<template>
  <div id="app">
    <header>
      <NavigationComponent/>
    </header>
    <main>
      <transition name="fade" mode="out-in">
        <router-view/>
      </transition>
    </main>
    <FooterComponent />
  </div>
</template>

<script>

import NavigationComponent from "@/components/NavigationComponent";
import FooterComponent from "@/components/FooterComponent";

export default {
  name: 'App',
  components: {
    NavigationComponent,
    FooterComponent
  }
}
</script>

<style>
@import url("assets/css/basic/main.css");

</style>
