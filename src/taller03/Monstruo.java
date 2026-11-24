package taller03;

public abstract class Monstruo {

	private int vida;
	private int velocidad;
	private int fuerza; 
	private int resistencia;
	
	
	public Monstruo(int vida, int velocidad, int fuerza, int resistencia) {
		super();
		this.vida = vida;
		this.velocidad = velocidad;
		this.fuerza = fuerza;
		this.resistencia = resistencia;
	}
	
	public int getVelocidad() {
		return velocidad;
	}
	
	public int getResistencia() {
		return resistencia;
	}
	
	public int getVida() {
		return vida;
	}

	public int getFuerza() {
		return fuerza;
	}

	public abstract double carrera();
	public abstract double cazaCalabaza();
	public abstract double lanzamientoLapida();
	public abstract double sobrevivirAmanecer();
	
	
	
	
	
	
	
	
	
	
	
}
