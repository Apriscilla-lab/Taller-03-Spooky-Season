package taller03;

public class CarreraCementerio implements Minijuego {

    @Override
    public String getNombre() {
        return "Carrera del Cementerio";
    }

    @Override
    public String getDescripcion() {
        return "Recorre el cementerio antes de que el sepulturero te vea";
    }

    @Override
    public String getHabilidadPremiada() {
        return "Volador";
    }

    @Override
    public double calcularPotencial(Monstruo monstruo) {
        return monstruo.carrera();
    }
}
