package modelo;

import java.util.ArrayList;

import excepciones.ExcepcionPrecioPedido;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Pedido {
	
	private int numeroPedidos;
	private int idPedido;
	private String nombreCliente;
	private String direccionCliente;
	private ArrayList<Producto> pedido;
	private double preciototal;
	
	public Pedido(String nombreCliente, String direccionCliente, int numeroPedidos) {
		this.nombreCliente = nombreCliente;
		this.direccionCliente = direccionCliente;
		this.numeroPedidos = numeroPedidos;
		this.idPedido = this.numeroPedidos + 1;
		this.numeroPedidos++;
		this.pedido = new ArrayList<Producto>();
		this.preciototal=0;
		}
	
	public int getIdPedido() {
		return idPedido;
	}
	
	public void agregarProducto(Producto nuevoItem) throws Exception {
		pedido.add(nuevoItem);
		setPrecioTotalPedido();
		Double precio= getPrecioTotalPedido();
		if (precio>=150000.0) {
			
			new ExcepcionPrecioPedido(String.valueOf(precio), nuevoItem.getNombre());
		}
		
	}
	
	private double getPrecioNetoPedido() {
		double p = 0;
		for (Producto producto: pedido) {
			p += producto.getPrecio();
		}
		return p;
	}
	
	private double getPrecioIVAPedido() {
		double precioBase = getPrecioNetoPedido();
		double IVA = precioBase*0.19;
		return IVA;
	}
	
	private void setPrecioTotalPedido() {
		double precioBase = getPrecioNetoPedido();
		double IVA = getPrecioIVAPedido();
		double precioTotal = precioBase + IVA;
		this.preciototal= precioTotal;
	}
	
	public double getPrecioTotalPedido() {
		return preciototal;
	}
	public String generarTextoFactura() {
		String mssg = "";
		mssg += "RESTAURANTE DE HAMBURGUESAS DPOO\n";
		mssg += "--------------------------------\n\n";
		mssg += "ID pedido: " + idPedido + "\n";
		mssg += "Nombre cliente: " + nombreCliente + "\n";
		mssg += "Dirección cliente: " + direccionCliente + "\n\n";
		mssg += "Artículos comprados\n--------------------------------\n";
		for (Producto producto: pedido) {
			mssg += producto.generarTextoFactura();
		}
		mssg += "\n--------------------------------\nPrecio neto: $" + getPrecioNetoPedido() + "\n";
		mssg += "IVA: $" + getPrecioIVAPedido() + "\n";
		mssg += "Precio total: $" + getPrecioTotalPedido() + "\n--------------------------------\n";
		mssg += "\n¡Gracias por su compra!";
		return mssg;
	}
	
	public void guardarFactura(File archivo) throws IOException {
        FileWriter fw = new FileWriter(archivo);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter wr = new PrintWriter(bw);  
        String factura = this.generarTextoFactura();
        wr.write(factura);
        wr.close();
        bw.close();
	}
	
}