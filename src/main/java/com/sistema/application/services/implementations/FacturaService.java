package com.sistema.application.services.implementations;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.sistema.application.services.IFacturaService;
import com.sistema.application.repositories.IFacturaRepository;
import com.sistema.application.converters.ChangoConverter;
import com.sistema.application.converters.FacturaConverter;
import com.sistema.application.converters.LocalConverter;
import com.sistema.application.dto.ProductoRankingDto;
import com.sistema.application.entities.Factura;
import com.sistema.application.models.ChangoModel;
import com.sistema.application.models.FacturaModel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service("facturaService")
public class FacturaService implements IFacturaService {

	// Atributos
	@Autowired
	@Qualifier("facturaRepository")
	private IFacturaRepository facturaRepository;

	@Autowired
	@Qualifier("facturaConverter")
	private FacturaConverter facturaConverter;

	@Autowired
	@Qualifier("changoConverter")
	private ChangoConverter changoConverter;

	@Autowired
	@Qualifier("localConverter")
	private LocalConverter localConverter;

	// Métodos
	@Override
	public FacturaModel findByIdFactura(long idFactura) {
		Factura factura = facturaRepository.findByIdFactura(idFactura);
		if(factura == null) {
			return null;
		}
		return facturaConverter.entityToModel(factura);
	}

	@Override
	public List<Factura> getAll() {
		return facturaRepository.findAll();
	}

	@Override
	public List<FacturaModel> getAllModel() {
		List<FacturaModel> facturas = new ArrayList<FacturaModel>();
		for (Factura f : facturaRepository.findAll()) {
			facturas.add(facturaConverter.entityToModel(f));
		}
		return facturas;
	}

	@Override
	public FacturaModel insertOrUpdate(FacturaModel facturaModel) {
		Factura factura = facturaRepository.save(facturaConverter.modelToEntity(facturaModel));
		return facturaConverter.entityToModel(factura);
	}

	@Override
	public boolean remove(long id) {
		try {
			facturaRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<FacturaModel> findByFechaFacturaBetweenAndIdLocal(LocalDate fecha1, LocalDate fecha2, long idLocal) {
		List<FacturaModel> lista = new ArrayList<FacturaModel>();// creo una lista de facturas
		for (Factura fa : facturaRepository.findByFechaFacturaBetweenAndIdLocal(fecha1, fecha2, idLocal)) {// traigo
																						// la
																						// lista
																						// de
																						// facturas
																						// entre
																						// fechas
																						// d eun
																						// local
			FacturaModel factura = facturaConverter.entityToModel(fa);
			lista.add(factura);// las agrego a la lista model
		}
		return lista;
	}

	/*
	 * @Override public Set<FacturaModel> findByFechaFacturaBetween(LocalDate
	 * fecha1, LocalDate fecha2){ Set<FacturaModel> lista = null;// crei una lista
	 * de facturas for (Factura fa :
	 * facturaRepository.findByFechaFacturaBetween(fecha1, fecha2 )) {//traigo la
	 * lista de facturas entre fechas d eun local
	 * lista.add(facturaConverter.entityToModel(fa));// las agrego a la lista model
	 * } return lista; }
	 */
	@Override
	public List<Factura> findByFechaFacturaBetween(LocalDate fecha1, LocalDate fecha2) {
		List<Factura> lista = new ArrayList<Factura>();// crei una lista de facturas
		for (Factura fa : facturaRepository.findByFechaFacturaBetween(fecha1, fecha2)) {// traigo la lista de facturas
																		// entre fechas d eun local
			lista.add(fa);
		}
		return lista;
	}

	@Override
	public FacturaModel findByChango(ChangoModel chango) {
		try {
			Factura factura = facturaRepository.findByChango(changoConverter.modelToEntity(chango));
			return facturaConverter.entityToModel(factura);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<FacturaModel> findByIdLocal(long idLocal) {
		List <FacturaModel> facturas = new ArrayList <FacturaModel> ();
		for(Factura factura: facturaRepository.findByIdLocal(idLocal)) {
			facturas.add(facturaConverter.entityToModel(factura));
		}
		return facturas;
	}

	@Override
	public List<FacturaModel> findByIdLocalAndByLegajoEmpleado(long idLocal, long legajo) {
		List <FacturaModel> facturas = new ArrayList <FacturaModel> ();
		for(Factura factura: facturaRepository.findByIdLocalAndByLegajoEmpleado(idLocal, legajo)) {
			facturas.add( facturaConverter.entityToModel(factura));
		}
		return facturas;
	}
}
