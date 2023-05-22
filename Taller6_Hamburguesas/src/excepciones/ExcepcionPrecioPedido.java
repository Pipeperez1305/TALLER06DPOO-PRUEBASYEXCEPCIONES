package excepciones;

public class ExcepcionPrecioPedido extends Exception {
	
	private String precio;
	
	public ExcepcionPrecioPedido(String precio, String producto) throws Exception {
		this.precio= precio;
		throw new Exception("Al agregar el producto "+producto+ " el pedido en curso sumaría un valor de $"+precio+", que supera el tope máximo de $150.000, por lo que no es posible agregarlo.");
		
	}
}
