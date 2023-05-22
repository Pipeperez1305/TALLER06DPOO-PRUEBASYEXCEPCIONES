package excepciones;

import java.util.Map;

public class ProductoRepetidoException extends HamburguesaException{
	
	private String productorepetido;
	
	public ProductoRepetidoException(String producto) throws Exception {
		this.productorepetido= producto;
		throw new Exception("El producto "+producto+" ya se encuentra dentro de la lista de productos del Menú");
		
	}

	
}
