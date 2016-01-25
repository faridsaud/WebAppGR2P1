app.controller('registrarUsuario',['$scope','$http','toastr',function($scope,$http,toastr){
  $scope.usuario=new Object();
  $scope.registrar=function(){
    $http({
      method:'POST',
      url:"http://localhost:8080/ConCritic/REST/Usuario/",
      data:{
        email:$scope.usuario.email,
        password:$scope.usuario.password,
        nombre:$scope.usuario.nombre,
        apellido:$scope.usuario.apellido,
        fechaNacimiento:$scope.usuario.fechaNacimiento,
        pais:$scope.usuario.pais,
        admin:false,
        estado:true
      }
    }).then(function success(response){
      toastr.success("Usuario creado con exito", "Exito");
    },
    function error(response){
      toastr.error("Error al crear el usuario", "Error");

    });
  };
}]);
