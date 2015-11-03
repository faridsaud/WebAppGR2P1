package ec.edu.epn.model.servicio;

import java.util.Date;

import ec.edu.epn.model.dto.UsuarioDTO;

public class Prueba {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServiceCuenta sc= new ServiceCuenta();
		UsuarioDTO usrDTO=new UsuarioDTO();
		usrDTO.setAdmin(false);
		usrDTO.setApellido("Saud");
		usrDTO.setEmail("faridsaud@yahoo.com");
		usrDTO.setEstado(true);
		usrDTO.setFechaNacimiento(new Date());
		usrDTO.setNombre("farid");
		usrDTO.setPais("Ecuador");
		usrDTO.setPassword("farid");
		sc.registrarUsuario(usrDTO);

	}

}
