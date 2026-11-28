package taller03;

public class HombreLobo extends Monstruo implements Mordedor, Furioso {

    private int ferocidad;
    private int faseLunar;

    public HombreLobo(int vida, int velocidad, int fuerza, int resistencia, int ferocidad, int faseLunar) {
        super(vida, velocidad, fuerza, resistencia);
        this.ferocidad = ferocidad;
        this.faseLunar = faseLunar;
    }

    public int getFerocidad() {
        return ferocidad;
    }

    public int getFaseLunar() {
        return faseLunar;
    }

    @Override
    public double carrera() {
        return (getVelocidad() + ferocidad / 5.0) * faseLunar / 4.0;
    }

    @Override
    public double cazaCalabaza() {
        return ferocidad * faseLunar / 4.0 + getVelocidad() / 4.0;
    }

    @Override
    public double lanzamientoLapida() {
        return getFuerza() + ferocidad * faseLunar / 8.0;
    }

    @Override
    public double sobrevivirAmanecer() {
        return (getResistencia() + getVida()) / 2.0 * (5.0 - faseLunar) / 4.0;
    }

    @Override
    public String morder() {
        return "El hombre lobo muerde con sus colmillos";
    }

    @Override
    public String enfurecerse() {
        return "El hombre lobo aulla y se enfurece";
    }
}
