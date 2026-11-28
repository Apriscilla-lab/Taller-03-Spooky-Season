package taller03;

public class ResultadoMinijuego {

    private Minijuego minijuego;
    private Monstruo monstruo;
    private String habilidadUtilizada;
    private double puntajeObtenido;

    public ResultadoMinijuego(Minijuego minijuego, Monstruo monstruo,
                             String habilidadUtilizada, double puntajeObtenido) {
        this.minijuego = minijuego;
        this.monstruo = monstruo;
        this.habilidadUtilizada = habilidadUtilizada;
        this.puntajeObtenido = puntajeObtenido;
    }

    public Minijuego getMinijuego() {
        return minijuego;
    }

    public Monstruo getMonstruo() {
        return monstruo;
    }

    public String getHabilidadUtilizada() {
        return habilidadUtilizada;
    }

    public double getPuntajeObtenido() {
        return puntajeObtenido;
    }
}
