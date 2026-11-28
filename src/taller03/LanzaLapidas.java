package taller03;

public class LanzaLapidas implements Minijuego {

    @Override
    public String getNombre() {
        return "Lanzamiento de Lápidas";
    }

    @Override
    public String getDescripcion() {
        return "Lanza una lápida lo más lejos posible";
    }

    @Override
    public String getHabilidadPremiada() {
        return "Furioso";
    }

    @Override
    public double calcularPotencial(Monstruo monstruo) {
        return monstruo.lanzamientoLapida();
    }
}
