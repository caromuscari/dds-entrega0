package ar.edu.utn.dds;

import ar.edu.utn.dds.Excepciones.NoSePuedeGenerarRemito;
import ar.edu.utn.dds.Excepciones.OperacionCerrada;

import java.util.*;

public class Egreso
{
    private List<Articulo> items;
    private Boolean operacionAbierta;
    private Double precio;
    private Remito documentoAsociado;

    public Egreso(List<Articulo> items, Boolean estado) {
        this.items = items;
        this.operacionAbierta = estado;
        this.precio = 0.0;
    }

    public Egreso() {
        this.items = new ArrayList<Articulo>();
        this.operacionAbierta = true;
        this.precio = 0.0;
    }

    public List<Articulo> getItems() {
        return items;
    }

    public Boolean getOperacionAbierta() {
        return operacionAbierta;
    }

    public Remito getDocumentoAsociado() {
        return documentoAsociado;
    }

    public Double getPrecio() {
        if(precioVacio()) calcularValor();
        return precio;
    }

    public boolean precioVacio(){
        return precio <= 0.0;
    }

    public void addItem(Articulo item){
        items.add(item);
        calcularValor();
    }

    public void calcularValor(){
        if (operacionAbierta) {
            precio = items.stream().mapToDouble(x -> x.getPrecio()).sum();
        }else throw new OperacionCerrada();
    }

    public void cerrarOperacion(){
        calcularValor();
        operacionAbierta = false;
    }

    public void generarDocumento() {
        if (habilitaDocumento()) documentoAsociado = new Remito();
        else throw new NoSePuedeGenerarRemito();
    }

    private boolean habilitaDocumento() {
        return items.stream().allMatch(x -> x.habilitaARemito());
    }
}
