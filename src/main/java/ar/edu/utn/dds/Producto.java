package ar.edu.utn.dds;

public class Producto extends Articulo {

    public Producto(Double precio) {
        super(precio);
    }

    @Override
    public boolean habilitaARemito() {
        return true;
    }
}
