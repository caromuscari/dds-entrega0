package ar.edu.utn.dds;

public class Servicio extends Articulo {

    public Servicio(double precio){
        super(precio);
    }

    @Override
    public boolean habilitaARemito() {
        return false;
    }
}
