<template>
  <div class="col-12 col-sm-10 col-md-8 offset-sm-1 offset-md-2">
    <div class="mt-5">
      <form class="border border-primary rounded form-inline" @submit="create">

        <h2 class="col-12 text-center text-primary mt-3 mb-5">Crear curso</h2>

        <div class="form-group col-12">
          <label for="courseName" class="custom-label col-md-3">Nombre del curso</label>
          <input id="courseName" class="form-control col-12 col-sm-10 col-md-7 offset-sm-1" type="text"
                 placeholder="Nombre del curso" v-model="course_name" required/>
        </div>

        <div class="form-group col-12">
          <label for="durationHours" class="custom-label col-md-3">Duración en horas del curso</label>
          <input id="durationHours" class="form-control col-12 col-sm-10 col-md-7 offset-sm-1" type="number"
                 placeholder="Duración en horas del curso" v-model="duration_hours" required/>
        </div>

        <div class="col-12 mb-3">
          <button class="col-sm-6 col-md-4 offset-sm-5 offset-md-7 btn btn-primary" type="submit">
            Crear curso
          </button>
        </div>

      </form>
    </div>
  </div>
</template>

<script>
  import axios from 'axios';

  export default {
    name: "AddRole",
    data( ){
      return {
        course_name: '',
        duration_hours: 0,
      }
    },
    methods: {
      create( event ){
        axios
          .post( this.$store.state.backURL + "/profesor/crear-curso?access_token=" + localStorage.getItem('token'), {
              "courseName": this.course_name,
              "durationHours": this.duration_hours
          })
          .then( response => {
            if( response.status !== 201 ){
              alert( "Error en la petición. Intente nuevamente" );
            }else{
              alert( "Se ha creado exitosamente el curso" );
            }
          }).catch( response => {
            if( response.response.status === 401 ){
              alert( "¡Ups! Al parecer tu contraseña es incorrecta o la sesión ha finalizado" );
            }else if ( response.response.status === 403 ){
              alert( "¿Estás seguro de que aún no tienes ese rol asignado?" );
            }else{
              alert( "No es posible conectar con el backend en este momento" );
            }
          });
        event.preventDefault( );
      },
    }

  }
</script>

<style scoped>
  .form-inline .form-group{
    margin-bottom: 1rem;
  }
</style>
