package taller03;

public class Momia extends Monstruo{

	private int longitudDeVendajes;
	
	public Momia(int vida, int velocidad, int fuerza, int resistencia, int longitudDeVendajes) {
		
		super(vida, velocidad,fuerza,resistencia);
		this.longitudDeVendajes = longitudDeVendajes;
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
