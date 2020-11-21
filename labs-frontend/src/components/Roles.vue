<template>
  <div class="col-12 col-sm-10 col-md-8 offset-sm-1 offset-md-2">
    <div class="mt-5">
      <div class="border border-primary rounded">
        <h2>Listado de roles</h2>

        <ul>
            <li v-for="role in roles" :key="role.id">{{ role.id }} - {{ role.roleName }}</li>
        </ul>

      </div>
    </div>
  </div>
</template>

<script>
  import axios from 'axios';

  export default {
    name: "Roles",
    data() {
        return {
            roles: []
        }
    },
    beforeMount( ){
      axios
        .get( this.$store.state.backURL + "/roles/mine?access_token=" + localStorage.getItem('token'))
        .then( response => {
          if( response.status !== 200 ){
            alert( "Error en la petición. Intente nuevamente" )
          }else{
            this.roles = response.data;
          }
        }).catch( response => {
          alert( "No es posible conectar con el backend en este momento" );
        });
    }
  }
</script>

<style scoped>
  .form-inline .form-group{
    margin-bottom: 1rem;
  }
</style>
