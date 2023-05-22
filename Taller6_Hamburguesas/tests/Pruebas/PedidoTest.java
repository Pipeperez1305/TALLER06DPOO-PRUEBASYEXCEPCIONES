package Pruebas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.Combo;
import modelo.ProductoMenu;

public class PedidoTest {

	private modelo.Pedido Pedido;
	private ProductoMenu productoMenu;
	private Combo combo;
	private ProductoMenu productoMenu2;
	private ProductoMenu productoMenu3;
	private ProductoMenu productoMenu4;
	private ProductoMenu productoMenu5;
	
	@BeforeEach
	public void setUp() {
		Pedido= new modelo.Pedido("Juan", "Calle 5", 1);
		productoMenu = new ProductoMenu("ProductoPrueba",12000.0,9);
		combo = new Combo("ComboPrueba",0.19,9);
		productoMenu2 = new ProductoMenu("ProductoPrueba2",9999.9,10);
		productoMenu3 = new ProductoMenu("ProductoPrueba3",10000.0,11);
		productoMenu4 = new ProductoMenu("ProductoPrueba4",123000.0,12);
		productoMenu5 = new ProductoMenu("ProductoPrueba5",30000.0,13);
	}
	
	@Test
	public void testAgregarProducto () throws Exception {
		combo.agregarItemACombo(productoMenu2);
		combo.agregarItemACombo(productoMenu3);
		Pedido.agregarProducto(combo);
		Pedido.agregarProducto(productoMenu);
		
		double calculo= ((productoMenu2.getPrecio()*(1 - 0.19))+(productoMenu3.getPrecio()*(1 - 0.19))+productoMenu.getPrecio())*(1+0.19);
		
		assertEquals( calculo, Pedido.getPrecioTotalPedido(), "Falló el metodo agregarProducto() y consecuentemente el método getPrecioNetoPedido(), getPrecioIVAPedido() y setPrecioTotalPedido()");
	}
	@Test
	public void testGenerarTextoFactura() throws Exception {
		combo.agregarItemACombo(productoMenu2);
		combo.agregarItemACombo(productoMenu3);
		Pedido.agregarProducto(combo);
		Pedido.agregarProducto(productoMenu);
		double calculoNeto=(productoMenu2.getPrecio()*(1 - 0.19))+(productoMenu3.getPrecio()*(1 - 0.19))+productoMenu.getPrecio();
		double calculoIVA= ((productoMenu2.getPrecio()*(1 - 0.19))+(productoMenu3.getPrecio()*(1 - 0.19))+productoMenu.getPrecio())*(0.19);
		double calculocombo= (productoMenu2.getPrecio()*(1 - 0.19))+(productoMenu3.getPrecio()*(1 - 0.19));
		double calculototal= ((productoMenu2.getPrecio()*(1 - 0.19))+(productoMenu3.getPrecio()*(1 - 0.19))+productoMenu.getPrecio())*(1+0.19);
		
		assertEquals(""+"RESTAURANTE DE HAMBURGUESAS DPOO\n--------------------------------\n\nID pedido: 2\nNombre cliente: Juan\nDirección cliente: Calle 5\n\nArtículos comprados\n--------------------------------\n9. ComboPrueba -> $"+String.valueOf(calculocombo)+"\n  -ProductoPrueba2\n  -ProductoPrueba3\n9. ProductoPrueba -> $12000.0\n\n--------------------------------\nPrecio neto: $"+String.valueOf(calculoNeto)+"\nIVA: $"+String.valueOf(calculoIVA)+"\nPrecio total: $" + String.valueOf(calculototal) + "\n--------------------------------\n\n¡Gracias por su compra!", Pedido.generarTextoFactura(), "Generar texto factura NO funciona");
	}
	
	@Test
	public void testGuardarFactura() throws Exception {
		
		String ruta = "pedidos/2.txt";
		File file = new File(ruta);
		Pedido.guardarFactura(file);
		assertEquals(true, file.exists(), "Guardar Factura no funciona");
		file.delete();
	}
	
	@Test
	public void testExcepcionPrecioPedido() throws Exception {
		
		Exception excepcion=assertThrows(Exception.class, ()->{
		
		Pedido.agregarProducto(productoMenu4);
		Pedido.agregarProducto(productoMenu5);});
		String producto= productoMenu5.getNombre();
		double calculo=(productoMenu4.getPrecio()+productoMenu5.getPrecio())*(1+0.19);
		assertEquals("Al agregar el producto "+producto+ " el pedido en curso sumaría un valor de $"+String.valueOf(calculo)+", que supera el tope máximo de $150.000, por lo que no es posible agregarlo.",excepcion.getMessage(), "No se está generando la excepción cuando el pedido supera un valor total de $150000");
		
		
	}
}



