package taller03;

public class HombreLobo extends Monstruo{

	private int ferocidad;
	private int faseLunar;
	
	public HombreLobo(int vida, int velocidad, int fuerza, int resistencia, int ferocidad, int faseLunar) {
		
		super(vida, velocidad, fuerza, resistencia);
		this.ferocidad = ferocidad;
		this.faseLunar = faseLunar;
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
