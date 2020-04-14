package datos;

public class PedidoStock {
	private long idPedido;
	private Producto producto;
	private int cantidad;
	private Empleado solicitante;
	private boolean aceptado;
	private Empleado oferente;
	private Chango chango;

	public PedidoStock(long idPedido, Producto producto, int cantidad, Empleado solicitante, boolean aceptado, Empleado oferente) {
		this.idPedido = idPedido;
		this.producto = producto;
		this.cantidad = cantidad;
		this.solicitante = solicitante;
		this.aceptado = aceptado;
		this.oferente = oferente;
	}

	//Getters y Setters
	public long getIdPedido() {
		return idPedido;
	}

	protected void setIdPedido(long idPedido) {
		this.idPedido = idPedido;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Empleado getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(Empleado solicitante) {
		this.solicitante = solicitante;
	}

	public boolean isAceptado() {
		return aceptado;
	}

	public void setAceptado(boolean aceptado) {
		this.aceptado = aceptado;
	}

	public Empleado getOferente() {
		return oferente;
	}

	public void setOferente(Empleado oferente) {
		this.oferente = oferente;
	}

	public Chango getChango() {
		return chango;
	}

	public void setChango(Chango chango) {
		this.chango = chango;
	}

	@Override
	public String toString() {
		return "PedidoStock [idPedido=" + idPedido + ", producto=" + producto + ", cantidad=" + cantidad
				+ ", solicitante=" + solicitante + ", aceptado=" + aceptado + ", oferente=" + oferente + "]";
	}
}
