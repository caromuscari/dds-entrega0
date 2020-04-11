package ar.edu.utn.dds;

import ar.edu.utn.dds.Excepciones.NoSePuedeGenerarRemito;
import ar.edu.utn.dds.Excepciones.OperacionCerrada;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EgresoTest
{
    Egreso operacion;

    @Before
    public void init(){
        operacion = new Egreso();
        operacion.addItem(new Producto(100.0));
        operacion.addItem(new Producto(150.0));
        operacion.addItem(new Producto(50.0));
    }

    @Test
    public void testPrecioSinItems()
    {
        Egreso op2 = new Egreso();
        assertEquals(0.0,op2.getPrecio(),0);

        op2.addItem(new Servicio(2000.0));
        assertEquals(2000.0,op2.getPrecio(),0);
    }

    @Test
    public void testCalcularPrecioDeOperacion()
    {
        operacion.calcularValor();
        assertEquals(300.0,operacion.getPrecio(),0);
    }

    @Test
    public void testOperacionCerradaCambioDePrecio(){
        operacion.calcularValor();
        assertEquals(300.0,operacion.getPrecio(), 0);

        operacion.cerrarOperacion();
        operacion.getItems().get(0).setPrecio(500.0);
        assertEquals(300.0,operacion.getPrecio(),0);
    }

    @Test (expected = OperacionCerrada.class)
    public void testOperacionCerradaPrecio(){
        operacion.calcularValor();
        operacion.cerrarOperacion();
        operacion.getItems().get(0).setPrecio(500.0);
        operacion.calcularValor();
    }

    @Test (expected = NoSePuedeGenerarRemito.class)
    public void testTieneServicios(){
        operacion.addItem(new Servicio(2000.0));
        operacion.generarDocumento();
    }

    @Test
    public void testGeneraRemito(){
        operacion.generarDocumento();
        assertFalse(operacion.getDocumentoAsociado()==null);
    }

    @Test
    public void testCambioDePrecio(){
        operacion.calcularValor();
        assertEquals(300.0,operacion.getPrecio(), 0);

        operacion.getItems().get(0).setPrecio(500.0);
        operacion.calcularValor();
        assertEquals(700.0,operacion.getPrecio(),0);
    }

    @Test
    public void testNoCambiaPrecio(){
        operacion.calcularValor();
        operacion.getItems().get(0).setPrecio(500.0);
        assertNotEquals(700.0,operacion.getPrecio(),0);
    }
}
