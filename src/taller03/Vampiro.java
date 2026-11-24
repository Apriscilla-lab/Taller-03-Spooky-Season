package taller03;

public class Vampiro extends Monstruo {

	private int velocidadDeVuelo;
	private int resistenciaAlSol;
	
	public Vampiro(int vida, int velocidad, int fuerza, int resistencia, int velocidadDeVuelo, int resistenciaAlSol) {
		
		super(vida, velocidad,fuerza,resistencia);
		this.velocidadDeVuelo = velocidadDeVuelo;
		this.resistenciaAlSol = resistenciaAlSol;
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
