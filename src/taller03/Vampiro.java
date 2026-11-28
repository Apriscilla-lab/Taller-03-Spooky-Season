
package taller03;

public class Vampiro extends Monstruo implements Mordedor, Volador {

    private int velocidadDeVuelo;
    private int resistenciaAlSol;

    public Vampiro(int vida, int velocidad, int fuerza, int resistencia, int velocidadDeVuelo, int resistenciaAlSol) {
        super(vida, velocidad, fuerza, resistencia);
        this.velocidadDeVuelo = velocidadDeVuelo;
        this.resistenciaAlSol = resistenciaAlSol;
    }

    public int getVelocidadDeVuelo() {
        return velocidadDeVuelo;
    }

    public int getResistenciaAlSol() {
        return resistenciaAlSol;
    }

    @Override
    public double carrera() {
        return (getVelocidad() + 2.0 * velocidadDeVuelo) / 2.0;
    }

    @Override
    public double cazaCalabaza() {
        return (getFuerza() + getVelocidad()) / 3.0;
    }

    @Override
    public double lanzamientoLapida() {
        return getFuerza() + velocidadDeVuelo / 4.0;
    }

    @Override
    public double sobrevivirAmanecer() {
        return getResistencia() * resistenciaAlSol / 100.0;
    }

    @Override
    public String morder() {
        return "El vampiro muerde a su objetivo";
    }

    @Override
    public String volar() {
        return "El vampiro vuela por el cielo";
    }
}
