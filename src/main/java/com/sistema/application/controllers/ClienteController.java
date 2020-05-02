package com.sistema.application.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sistema.application.helpers.ViewRouteHelper;
import com.sistema.application.models.ClienteModel;
import com.sistema.application.services.IClienteService;

@Controller
@RequestMapping("cliente")
public class ClienteController {

	@Autowired
	@Qualifier("clienteService")
	private IClienteService clienteService;

	//Lista de clientes que simula los datos en la base de datos
	private List <ClienteModel> clientes = new ArrayList <ClienteModel>( Arrays.asList(
			new ClienteModel("Pepe", "Gonzales", LocalDate.of(2000, 1, 10), "pepe@mail.com", 1),
			new ClienteModel("Juan", "Gomez", LocalDate.of(2001, 2, 20), "juan@mail.com", 2),
			new ClienteModel("Juan", "Gomez", LocalDate.of(2001, 2, 20), "juan@mail.com", 3),
			new ClienteModel("Juan", "Gomez", LocalDate.of(2001, 2, 20), "juan@mail.com", 4))
			);
	private int ultimoNroCliente = 2;
	
	@GetMapping("")
	public String clientes(Model modelo) {
		modelo.addAttribute("clientes", clienteService.getAll());
		modelo.addAttribute("cliente", new ClienteModel());
		return ViewRouteHelper.CLIENTE_ABM;
	}
	
	@PostMapping("agregar")
	public String agregar(@ModelAttribute("cliente") ClienteModel nuevoCliente) {
		ultimoNroCliente++;
		nuevoCliente.setNroCliente(ultimoNroCliente);
		clientes.add(nuevoCliente);
        return "redirect:/cliente";
	}
	
	@PostMapping("modificar")
	public String modificar(@ModelAttribute("cliente") ClienteModel clienteModificado) {
		System.out.println("hola");
		int i=0;
		boolean encontrado = false;
		while(i < clientes.size() && !encontrado) {
			encontrado = clientes.get(i).getNroCliente() == clienteModificado.getNroCliente();
			i++;
		}
		if(encontrado) {
			clientes.set(i-1, clienteModificado);
		}	// En caso de no encontrarlo implementar otra cosa
        return "redirect:/cliente";
	}
	
	@PostMapping("eliminar/{nroCliente}")
	public String eliminar(@PathVariable("nroCliente") int nroCliente) {
		int i=0;
		boolean encontrado = false;
		while(i < clientes.size() && !encontrado) {
			encontrado = clientes.get(i).getNroCliente() == nroCliente;
			i++;
		}
		if(encontrado) {
			clientes.remove(i-1);
		}	// En caso de no poder implementar otra cosa
        return "redirect:/cliente";
	}
}
