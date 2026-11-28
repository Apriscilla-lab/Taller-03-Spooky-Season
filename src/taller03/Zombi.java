package taller03;

public class Zombi extends Monstruo implements Mordedor, Regenerable{

	private int fuerzaDeMordida;
	
	public Zombi(int vida, int velocidad, int fuerza, int resistencia, int fuerzaDeMordida) {
		
		super(vida, velocidad, fuerza, resistencia);
		this.fuerzaDeMordida = fuerzaDeMordida;
	}
	
	public int getFuerzaDeMordida() {
        return fuerzaDeMordida;
    }

    @Override
    public double carrera() {
        return getVelocidad() * getResistencia() / 100.0;
    }

    @Override
    public double cazaCalabaza() {
        return fuerzaDeMordida * getVida() / 100.0 + getResistencia() / 2.0;
    }

    @Override
    public double lanzamientoLapida() {
        return getFuerza() * getResistencia() / 100.0;
    }

    @Override
    public double sobrevivirAmanecer() {
        return getResistencia() * getVida() / 100.0;
    }

    @Override
    public String morder() {
        return "El zombi muerde a su objetivo";
    }

    @Override
    public String regenerarse() {
        return "El zombi se regenera";
    }
	
}
