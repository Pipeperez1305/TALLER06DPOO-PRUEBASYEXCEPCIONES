package Pruebas;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.Combo;
import modelo.ProductoMenu;

public class ComboTest {
	
	private Combo combo;
	private ProductoMenu productoMenu;
	private ProductoMenu productoMenu2;
	
	
	@BeforeEach
	public void setUp() {
		
		
		combo = new Combo("ComboPrueba",0.19,9);
		productoMenu = new ProductoMenu("ProductoPrueba",9999.9,9);
		productoMenu2 = new ProductoMenu("ProductoPrueba2",10000.0,10);
		
	}
	@Test
	public void testAgregarProducto () {
		combo.agregarItemACombo(productoMenu);
		combo.agregarItemACombo(productoMenu2);
		double calculo= (productoMenu.getPrecio()*(1 - 0.19))+(productoMenu2.getPrecio()*(1 - 0.19));
		
		assertEquals( calculo, combo.getPrecio(), "Falló el metodo agregarItemACombo() y consecuentemente el método getPrecio()");
	}
	@Test
	public void testGenerarTextoFactura() {
		combo.agregarItemACombo(productoMenu);
		combo.agregarItemACombo(productoMenu2);
		double calculo= (productoMenu.getPrecio()*(1 - 0.19))+(productoMenu2.getPrecio()*(1 - 0.19));
		assertEquals("9. ComboPrueba -> $"+String.valueOf(calculo)+"\n  -ProductoPrueba\n  -ProductoPrueba2\n", combo.generarTextoFactura(), "Generar texto factura NO funciona");
	}
	
	@Test
	public void testGetNo() {
		assertEquals(9, combo.getNo(), "Get list number NO funciona");
	}
	

}
