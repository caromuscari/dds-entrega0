package ar.edu.utn.dds;

public abstract class Articulo {
    Double precio;

    public Articulo(Double precio) {
        this.precio = precio;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public abstract boolean habilitaARemito();
}
