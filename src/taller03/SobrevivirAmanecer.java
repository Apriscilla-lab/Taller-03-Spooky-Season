package taller03;

public class SobrevivirAmanecer implements Minijuego {

    @Override
    public String getNombre() {
        return "Sobrevivir al Amanecer";
    }

    @Override
    public String getDescripcion() {
        return "Aguanta en pie cuando salgan los primeros rayos de sol";
    }

    @Override
    public String getHabilidadPremiada() {
        return "Regenerable";
    }

    @Override
    public double calcularPotencial(Monstruo monstruo) {
        return monstruo.sobrevivirAmanecer();
    }
}
