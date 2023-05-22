package excepciones;

import java.util.Map;

import modelo.Ingrediente;
import modelo.Producto;

public class IngredienteRepetidoException extends HamburguesaException{

	private String Ingredienterepetido;
	
	public IngredienteRepetidoException(String ingrediente) throws Exception {
		this.Ingredienterepetido= ingrediente;
		throw new Exception("El ingrediente "+ingrediente+" ya se encuentra dentro de la lista de ingredientes");
		
	}
}

	

	