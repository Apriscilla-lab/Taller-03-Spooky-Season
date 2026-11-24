package taller03;

public class Zombi extends Monstruo{

	private int fuerzaDeMordida;
	
	public Zombi(int fuerza, int vida, int resistencia, int velocidad, int fuerzaDeMordida) {
		
		super(vida, velocidad, fuerza, resistencia);
		this.fuerzaDeMordida = fuerzaDeMordida;
	}
	
	@Override
	public double carrera() {
		return 0;
	}
	
	@Override
	public double cazaCalabaza() {
		return 0;
	}
	
	@Override
	public double lanzamientoLapida() {
		return 0;
	}
	
	@Override
	public double sobrevivirAmanecer() {
		return 0;
	}
}
