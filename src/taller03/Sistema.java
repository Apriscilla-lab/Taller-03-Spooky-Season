package taller03;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Sistema {

    private ArrayList<Jugador> jugadores;
    private ArrayList<Monstruo> monstruos;
    private LinkedList<ResultadoMinijuego> historial;
    private ArrayList<Minijuego> minijuegos;
    private Jugador jugadorActual;
    private Scanner entrada;

    public Sistema() {
        jugadores = new ArrayList<Jugador>();
        monstruos = new ArrayList<Monstruo>();
        historial = new LinkedList<ResultadoMinijuego>();
        minijuegos = new ArrayList<Minijuego>();
        entrada = new Scanner(System.in);
    }

    public void cargarJugadores() {
        File archivo = new File("Registros.txt");
        if (!archivo.exists()) {
            System.out.println("Registros.txt no existe. Se comenzará sin registros.");
            return;
        }
        try {
            Scanner lector = new Scanner(archivo);
            int linea = 0;
            while (lector.hasNextLine()) {
                linea++;
                String[] datos = lector.nextLine().split(";", -1);
                try {
                    if (datos.length != 2 || datos[0].trim().isEmpty()) {
                        throw new IllegalArgumentException();
                    }
                    String nombre = datos[0].trim();
                    int puntaje = Integer.parseInt(datos[1].trim());
                    if (puntaje < 0 || buscarJugador(nombre) != null) {
                        throw new IllegalArgumentException();
                    }
                    jugadores.add(new Jugador(nombre, puntaje));
                } catch (IllegalArgumentException e) {
                    System.out.println("Registro inválido en línea " + linea + ". Se descartó.");
                }
            }
            lector.close();
            System.out.println(jugadores.size() + " jugadores cargados.");
        } catch (FileNotFoundException e) {
            System.out.println("No se pudo abrir Registros.txt.");
        }
    }

    public void cargarMonstruos() {
        File archivo = new File("Monstruos.txt");
        if (!archivo.exists()) {
            System.out.println("No existe Monstruos.txt.");
            return;
        }
        try {
            Scanner lector = new Scanner(archivo);
            int linea = 0;
            while (lector.hasNextLine()) {
                linea++;
                String[] datos = lector.nextLine().split(";", -1);
                try {
                    if (datos.length != 6 && datos.length != 7) {
                        throw new IllegalArgumentException();
                    }
                    int vida = Integer.parseInt(datos[1].trim());
                    int velocidad = Integer.parseInt(datos[2].trim());
                    int fuerza = Integer.parseInt(datos[3].trim());
                    int resistencia = Integer.parseInt(datos[4].trim());
                    if (!enRango(vida, 1, 200) || !enRango(velocidad, 1, 100)
                            || !enRango(fuerza, 1, 100) || !enRango(resistencia, 1, 100)) {
                        throw new IllegalArgumentException();
                    }
                    String tipo = datos[0].trim();
                    Monstruo monstruo = null;
                    if (tipo.equals("Zombi") && datos.length == 6) {
                        int mordida = Integer.parseInt(datos[5].trim());
                        if (!enRango(mordida, 1, 100)) throw new IllegalArgumentException();
                        monstruo = new Zombi(vida, velocidad, fuerza, resistencia, mordida);
                    } else if (tipo.equals("Vampiro") && datos.length == 7) {
                        int vuelo = Integer.parseInt(datos[5].trim());
                        int sol = Integer.parseInt(datos[6].trim());
                        if (!enRango(vuelo, 1, 100) || !enRango(sol, 1, 100)) throw new IllegalArgumentException();
                        monstruo = new Vampiro(vida, velocidad, fuerza, resistencia, vuelo, sol);
                    } else if (tipo.equals("Momia") && datos.length == 6) {
                        int vendajes = Integer.parseInt(datos[5].trim());
                        if (!enRango(vendajes, 1, 300)) throw new IllegalArgumentException();
                        monstruo = new Momia(vida, velocidad, fuerza, resistencia, vendajes);
                    } else if (tipo.equals("HombreLobo") && datos.length == 7) {
                        int ferocidad = Integer.parseInt(datos[5].trim());
                        int luna = Integer.parseInt(datos[6].trim());
                        if (!enRango(ferocidad, 1, 100) || !enRango(luna, 1, 4)) throw new IllegalArgumentException();
                        monstruo = new HombreLobo(vida, velocidad, fuerza, resistencia, ferocidad, luna);
                    }
                    if (monstruo == null) throw new IllegalArgumentException();
                    monstruos.add(monstruo);
                } catch (IllegalArgumentException e) {
                    System.out.println("Monstruo inválido en línea " + linea + ". Se descartó.");
                }
            }
            lector.close();
            System.out.println(monstruos.size() + " monstruos cargados.");
        } catch (FileNotFoundException e) {
            System.out.println("No se pudo abrir Monstruos.txt.");
        }
    }

    private boolean enRango(int numero, int minimo, int maximo) {
        return numero >= minimo && numero <= maximo;
    }

    public void inicializarMinijuegos() {
        minijuegos.clear();
        minijuegos.add(new CarreraCementerio());
        minijuegos.add(new CazaCalabazas());
        minijuegos.add(new LanzaLapidas());
        minijuegos.add(new SobrevivirAmanecer());
    }

    public void guardarJugadores() {
        try {
            BufferedWriter escritor = new BufferedWriter(new FileWriter("Registros.txt"));
            for (Jugador jugador : jugadores) {
                escritor.write(jugador.getNombre() + ";" + jugador.getPuntajeMaximo());
                escritor.newLine();
            }
            escritor.close();
            System.out.println("Registros guardados en Registros.txt.");
        } catch (IOException e) {
            System.out.println("No se pudieron guardar los registros.");
        }
    }

    public void registrarResultado(ResultadoMinijuego resultado) {
        historial.add(resultado);
    }

    public void mostrarHistorial() {
        System.out.println("\n------ HISTORIAL DE LA SESIÓN ------");
        if (historial.isEmpty()) System.out.println("Aún no se ha jugado ningún minijuego.");
        int numero = 1;
        for (ResultadoMinijuego resultado : historial) {
            System.out.println(numero + ". " + resultado.getMinijuego().getNombre()
                    + " | " + resultado.getMonstruo().getClass().getSimpleName()
                    + " | Habilidad: " + resultado.getHabilidadUtilizada()
                    + " | " + Math.round(resultado.getPuntajeObtenido()) + " pts");
            numero++;
        }
        System.out.println("Puntaje acumulado: " + Math.round(calcularPuntajeTotal()));
    }

    public double calcularPuntajeTotal() {
        double total = 0;
        for (ResultadoMinijuego resultado : historial) total += resultado.getPuntajeObtenido();
        return total;
    }

    public void actualizarRecord(Jugador jugador, int puntaje) {
        if (puntaje > jugador.getPuntajeMaximo()) jugador.setPuntajeMaximo(puntaje);
    }

    public void mostrarRanking() {
        ArrayList<Jugador> ordenados = new ArrayList<Jugador>(jugadores);
        for (int i = 0; i < ordenados.size(); i++) {
            for (int j = i + 1; j < ordenados.size(); j++) {
                Jugador primero = ordenados.get(i);
                Jugador segundo = ordenados.get(j);
                if (segundo.getPuntajeMaximo() > primero.getPuntajeMaximo()
                        || (segundo.getPuntajeMaximo() == primero.getPuntajeMaximo()
                        && segundo.getNombre().compareToIgnoreCase(primero.getNombre()) < 0)) {
                    ordenados.set(i, segundo);
                    ordenados.set(j, primero);
                }
            }
        }
        System.out.println("\n------ RANKING ------");
        if (ordenados.isEmpty()) System.out.println("No hay jugadores registrados.");
        for (int i = 0; i < ordenados.size(); i++) {
            Jugador jugador = ordenados.get(i);
            System.out.println((i + 1) + ". " + jugador.getNombre() + " - " + jugador.getPuntajeMaximo());
        }
    }

    public void iniciarSesion(Jugador jugador) {
        jugadorActual = jugador;
        historial.clear();
    }

    public boolean puedeJugar(Minijuego minijuego, Monstruo monstruo) {
        for (ResultadoMinijuego resultado : historial) {
            if (resultado.getMinijuego() == minijuego || resultado.getMonstruo() == monstruo) return false;
        }
        return true;
    }

    private boolean monstruoUsado(Monstruo monstruo) {
        for (ResultadoMinijuego resultado : historial) {
            if (resultado.getMonstruo() == monstruo) return true;
        }
        return false;
    }

    public void jugarMinijuego(Minijuego minijuego, Monstruo monstruo, String habilidad) {
        if (!puedeJugar(minijuego, monstruo)) {
            System.out.println("Ya utilizaste ese monstruo o jugaste ese minijuego.");
            return;
        }
        String mensaje = "";
        if (habilidad.equals("Mordedor") && monstruo instanceof Mordedor) {
            mensaje = ((Mordedor) monstruo).morder();
        } else if (habilidad.equals("Volador") && monstruo instanceof Volador) {
            mensaje = ((Volador) monstruo).volar();
        } else if (habilidad.equals("Regenerable") && monstruo instanceof Regenerable) {
            mensaje = ((Regenerable) monstruo).regenerarse();
        } else if (habilidad.equals("Furioso") && monstruo instanceof Furioso) {
            mensaje = ((Furioso) monstruo).enfurecerse();
        } else {
            habilidad = "Ninguna";
        }
        if (!mensaje.isEmpty()) System.out.println(mensaje);
        double multiplicador = 1.0;
        if (habilidad.equals(minijuego.getHabilidadPremiada())) {
            multiplicador = 1.5;
            System.out.println("¡La habilidad fue efectiva! Puntaje x1.5");
        } else if (!habilidad.equals("Ninguna")) {
            System.out.println("La habilidad no tuvo efecto en este minijuego.");
        }
        double potencial = minijuego.calcularPotencial(monstruo);
        long puntaje = Math.round(potencial * 10 * multiplicador);
        registrarResultado(new ResultadoMinijuego(minijuego, monstruo, habilidad, puntaje));
        System.out.println("Puntaje obtenido: " + puntaje + " puntos.");
    }

    public void finalizarSesion() {
        int puntaje = (int) Math.round(calcularPuntajeTotal());
        System.out.println("\nSesión terminada. Puntaje total: " + puntaje);
        if (historial.isEmpty()) {
            System.out.println("No jugaste ningún minijuego. No se agregó ningún récord.");
        } else {
            Jugador registrado = buscarJugador(jugadorActual.getNombre());
            if (registrado == null) {
                actualizarRecord(jugadorActual, puntaje);
                jugadores.add(jugadorActual);
                System.out.println("Nuevo jugador registrado con " + puntaje + " puntos.");
            } else if (puntaje > registrado.getPuntajeMaximo()) {
                actualizarRecord(registrado, puntaje);
                System.out.println("¡Nuevo récord personal: " + puntaje + " puntos!");
            } else {
                System.out.println("No superaste tu récord de " + registrado.getPuntajeMaximo() + " puntos.");
            }
        }
        guardarJugadores();
        jugadorActual = null;
    }

    private Jugador buscarJugador(String nombre) {
        for (Jugador jugador : jugadores) {
            if (jugador.getNombre().equalsIgnoreCase(nombre)) return jugador;
        }
        return null;
    }

    private int leerOpcion(int minimo, int maximo) {
        while (true) {
            System.out.print("> ");
            if (!entrada.hasNextLine()) return -1;
            String texto = entrada.nextLine().trim();
            try {
                int opcion = Integer.parseInt(texto);
                if (opcion >= minimo && opcion <= maximo) return opcion;
                System.out.println("Opción fuera de rango. Intenta nuevamente.");
            } catch (NumberFormatException e) {
                System.out.println("Debes ingresar un número válido.");
            }
        }
    }

    private void mostrarDatosMonstruo(Monstruo monstruo) {
        String datos = monstruo.getClass().getSimpleName()
                + " | Vida " + monstruo.getVida()
                + " | Vel " + monstruo.getVelocidad()
                + " | Fue " + monstruo.getFuerza()
                + " | Res " + monstruo.getResistencia();
        if (monstruo instanceof Zombi) {
            datos += " | Mordida " + ((Zombi) monstruo).getFuerzaDeMordida();
        } else if (monstruo instanceof Vampiro) {
            datos += " | Vuelo " + ((Vampiro) monstruo).getVelocidadDeVuelo()
                    + " | Sol " + ((Vampiro) monstruo).getResistenciaAlSol();
        } else if (monstruo instanceof Momia) {
            datos += " | Vendajes " + ((Momia) monstruo).getLongitudDeVendajes();
        } else if (monstruo instanceof HombreLobo) {
            datos += " | Ferocidad " + ((HombreLobo) monstruo).getFerocidad()
                    + " | Luna " + ((HombreLobo) monstruo).getFaseLunar();
        }
        String habilidades = "";
        if (monstruo instanceof Mordedor) habilidades += "Morder ";
        if (monstruo instanceof Volador) habilidades += "Volar ";
        if (monstruo instanceof Regenerable) habilidades += "Regenerarse ";
        if (monstruo instanceof Furioso) habilidades += "Enfurecerse ";
        System.out.println(datos + " | Habilidades: " + habilidades);
    }

    public void mostrarCatalogo() {
        System.out.println("\n------ CATÁLOGO DE MONSTRUOS ------");
        for (int i = 0; i < monstruos.size(); i++) {
            System.out.print((i + 1) + ") ");
            mostrarDatosMonstruo(monstruos.get(i));
            System.out.println(monstruoUsado(monstruos.get(i)) ? "   [USADO]" : "   [DISPONIBLE]");
        }
    }

    private String elegirHabilidad(Monstruo monstruo) {
        ArrayList<String> opciones = new ArrayList<String>();
        if (monstruo instanceof Mordedor) opciones.add("Mordedor");
        if (monstruo instanceof Volador) opciones.add("Volador");
        if (monstruo instanceof Regenerable) opciones.add("Regenerable");
        if (monstruo instanceof Furioso) opciones.add("Furioso");
        System.out.println("¿Quieres usar una habilidad?");
        for (int i = 0; i < opciones.size(); i++) {
            String habilidad = opciones.get(i);
            String nombre = habilidad.equals("Mordedor") ? "Morder"
                    : habilidad.equals("Volador") ? "Volar"
                    : habilidad.equals("Regenerable") ? "Regenerarse" : "Enfurecerse";
            System.out.println((i + 1) + ") " + nombre);
        }
        System.out.println("0) No usar habilidad");
        int opcion = leerOpcion(0, opciones.size());
        if (opcion <= 0) return "Ninguna";
        return opciones.get(opcion - 1);
    }

    private void seleccionarMinijuego() {
        System.out.println("\n------ MINIJUEGOS ------");
        for (int i = 0; i < minijuegos.size(); i++) {
            Minijuego minijuego = minijuegos.get(i);
            String estado = "PENDIENTE";
            for (ResultadoMinijuego resultado : historial) {
                if (resultado.getMinijuego() == minijuego) {
                    estado = "JUGADO - " + Math.round(resultado.getPuntajeObtenido()) + " pts";
                }
            }
            System.out.println((i + 1) + ") " + minijuego.getNombre() + " [" + estado + "]");
        }
        System.out.println("0) Volver");
        int opcionJuego = leerOpcion(0, minijuegos.size());
        if (opcionJuego <= 0) return;
        Minijuego minijuego = minijuegos.get(opcionJuego - 1);
        for (ResultadoMinijuego resultado : historial) {
            if (resultado.getMinijuego() == minijuego) {
                System.out.println("Ya jugaste ese minijuego en esta sesión.");
                return;
            }
        }
        ArrayList<Monstruo> disponibles = new ArrayList<Monstruo>();
        for (Monstruo monstruo : monstruos) {
            if (!monstruoUsado(monstruo)) disponibles.add(monstruo);
        }
        if (disponibles.isEmpty()) {
            System.out.println("No quedan monstruos disponibles.");
            return;
        }
        System.out.println("\n" + minijuego.getNombre() + ": " + minijuego.getDescripcion());
        System.out.println("Elige un monstruo:");
        for (int i = 0; i < disponibles.size(); i++) {
            System.out.print((i + 1) + ") ");
            mostrarDatosMonstruo(disponibles.get(i));
        }
        System.out.println("0) Volver");
        int opcionMonstruo = leerOpcion(0, disponibles.size());
        if (opcionMonstruo <= 0) return;
        Monstruo monstruo = disponibles.get(opcionMonstruo - 1);
        System.out.println("Te comes un dulce de dudosa procedencia...");
        System.out.println("Potencial para " + minijuego.getNombre() + ": "
                + String.format("%.2f", minijuego.calcularPotencial(monstruo)));
        String habilidad = elegirHabilidad(monstruo);
        jugarMinijuego(minijuego, monstruo, habilidad);
    }

    private void solicitarJugador() {
        String nombre = "";
        while (nombre.isEmpty() || nombre.contains(";")) {
            System.out.print("\nIngresa tu nombre de usuario: ");
            if (!entrada.hasNextLine()) return;
            nombre = entrada.nextLine().trim();
            if (nombre.isEmpty() || nombre.contains(";")) {
                System.out.println("Nombre inválido. No puede estar vacío ni contener ';'.");
            }
        }
        Jugador jugador = buscarJugador(nombre);
        if (jugador == null) {
            jugador = new Jugador(nombre, 0);
            System.out.println("¡Bienvenido, " + nombre + "! Es tu primera vez jugando.");
        } else {
            System.out.println("¡Bienvenido de vuelta, " + nombre + "! Tu récord es "
                    + jugador.getPuntajeMaximo() + ".");
        }
        iniciarSesion(jugador);
    }

    public void ejecutar() {
        System.out.println("Cargando archivos...");
        cargarMonstruos();
        cargarJugadores();
        inicializarMinijuegos();
        if (monstruos.isEmpty()) {
            System.out.println("No hay monstruos válidos. El programa terminará.");
            return;
        }
        boolean salir = false;
        while (!salir) {
            solicitarJugador();
            if (jugadorActual == null) return;
            boolean terminarSesion = false;
            while (!terminarSesion) {
                System.out.println("\n------ MENÚ PRINCIPAL (" + jugadorActual.getNombre() + ") ------");
                System.out.println("1) Jugar un minijuego");
                System.out.println("2) Ver catálogo de monstruos");
                System.out.println("3) Ver historial de la sesión");
                System.out.println("4) Ver ranking de jugadores");
                System.out.println("5) Terminar sesión");
                System.out.println("6) Salir");
                int opcion = leerOpcion(1, 6);
                switch (opcion) {
                    case 1: seleccionarMinijuego(); break;
                    case 2: mostrarCatalogo(); break;
                    case 3: mostrarHistorial(); break;
                    case 4: mostrarRanking(); break;
                    case 5:
                        finalizarSesion();
                        terminarSesion = true;
                        break;
                    case 6:
                        finalizarSesion();
                        terminarSesion = true;
                        salir = true;
                        break;
                    default:
                        finalizarSesion();
                        terminarSesion = true;
                        salir = true;
                        break;
                }
            }
        }
    }
}
