package test;
import java.time.LocalDate;

import negocio.PersonaABM;

/*
 * Test para agregar un nuevo cliente
 */
public class TestAgregarCliente {
	public static void main(String[] args) {
		PersonaABM abm = PersonaABM.getInstance();
		long idPersona = 1;
		try {
			idPersona = abm.agregar(idPersona);
			String apellido = "Downey Jr";
			String nombre = "Robert";
			int dni = 34000001;
			LocalDate fechaDeNacimiento = LocalDate.now();
			String email = "robert.downeyjr@gmail.com";
			int nroCliente = 1;

			System.out.println("Agregar un nuevo cliente \n\n");
			long ultimoIdCliente = abm.agregar(idPersona, apellido, nombre, dni, fechaDeNacimiento, email, nroCliente);
			System.out.println("Agregado satisfactoriamente. ID: " + Long.toString(ultimoIdCliente));
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
