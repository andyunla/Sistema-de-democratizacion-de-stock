package datos;

import java.util.*;

public class Chango {
	private int idChango;
	private List<Item> listaItems;
	private PedidoStock pedidostock;

	public Chango(int idChango, PedidoStock pedidostock) {
		super();
		this.idChango = idChango;
		this.listaItems = new ArrayList<Item>();
		this.pedidostock = pedidostock;
	}

	//Getters y Setters
	public int getIdChango() {
		return idChango;
	}

	public void setIdChango(int idChango) {
		this.idChango = idChango;
	}

	public List<Item> getListaItems() {
		return listaItems;
	}

	public PedidoStock getPedidostock() {
		return pedidostock;
	}

	public void setPedidostock(PedidoStock pedidostock) {
		this.pedidostock = pedidostock;
	}

	@Override
	public String toString() {
		return "\n\nCHANGO: " + idChango + listaItems.toString();
		//return "Chango [idChango=" + idChango + ", pedidostock=" + pedidostock + "]";
	}

	public boolean equals (Chango chango){
		return chango.getIdChango()==this.getIdChango();
	}
	
	/*****************************************************************************************************************/
	//Metodos
	public List<Item> traerItem() {
		return listaItems;
	}	
	public Item traerItem(Producto producto){
		Item obj = null;		
		int i = 0;
		while(obj == null && i<listaItems.size() ){			
			if(listaItems.get(i).getProducto().equals(producto) ) obj = listaItems.get(i);						
			i++;
		}	
		return obj;
	}

	public Item traerItem( int idItem){
		Item obj = null;		
		int i = 0;
		while(obj == null && i<listaItems.size() ){			
			if(listaItems.get(i).getIdItem() == idItem ) obj = listaItems.get(i);						
			i++;
		}	
		return obj;
	}

	public boolean crearItem (int cantidad, Producto producto) {		
		if(traerItem(producto) != null) {
			traerItem(producto).setCantidad(traerItem(producto).getCantidad() + cantidad);			
			}
			else{
				int idItem = 1;
				if(!this.listaItems.isEmpty() ) idItem = listaItems.get(listaItems.size()-1).getIdItem()+1;		
				listaItems.add(new Item(idItem, cantidad, producto) );
		}
		return true;
	}

	public boolean eliminarItem (int cantidad, Producto producto) throws Exception{
		if(traerItem(producto) == null) throw new Exception ("El producto " + producto.getNombre() + " no existe");		
		if(traerItem(producto).getCantidad()-cantidad <= 0) listaItems.remove(traerItem(producto) );
		else traerItem(producto).setCantidad(traerItem(producto).getCantidad()-cantidad);		
		return true;
	}
	
	/*****************************************************************************************************************/
	public double calcularTotalChango() {		
		double total = 0;
		for(Item it: listaItems) {
			total = total + (it.getProducto().getPrecio()*it.getCantidad() );
		}		
		return total;
	}
}