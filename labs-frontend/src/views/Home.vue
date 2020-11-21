<template>
  <div class="container-fluid mt-2">
    <h1>Bienvenido al dummy</h1>
    <div class="row">
      <router-view></router-view>
    </div>
  </div>
</template>

<script>

  export default{
    name: "Home",
    beforeCreate( ){
      if( !localStorage.getItem( 'token' ) ){
        this.$router.push( {name: 'login'} )
      }
    }
  }
</script>

<style>

</style>
