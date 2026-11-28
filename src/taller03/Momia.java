package taller03;

public class Momia extends Monstruo implements Regenerable, Furioso {

    private int longitudDeVendajes;

    public Momia(int vida, int velocidad, int fuerza, int resistencia, int longitudDeVendajes) {
        super(vida, velocidad, fuerza, resistencia);
        this.longitudDeVendajes = longitudDeVendajes;
    }

    public int getLongitudDeVendajes() {
        return longitudDeVendajes;
    }

    @Override
    public double carrera() {
        return getVelocidad() * 100.0 / (100.0 + longitudDeVendajes);
    }

    @Override
    public double cazaCalabaza() {
        return longitudDeVendajes * getFuerza() / 200.0;
    }

    @Override
    public double lanzamientoLapida() {
        return getFuerza() + longitudDeVendajes / 10.0;
    }

    @Override
    public double sobrevivirAmanecer() {
        return (getResistencia() + getVida()) / 2.0 * (1.0 + longitudDeVendajes / 300.0);
    }

    @Override
    public String regenerarse() {
        return "La momia utiliza sus vendajes para regenerarse";
    }

    @Override
    public String enfurecerse() {
        return "La momia se enfurece y agita sus vendajes";
    }
}
