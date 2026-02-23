package View;

import Controller.JugadorController;
import Controller.NotificacionController;
import Controller.PartidoController;
import Model.*;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class VistaConsola {

    private Scanner scanner;
    private JugadorController jugadorController;
    private PartidoController partidoController;

    public VistaConsola(JugadorController jugadorController, PartidoController partidoController) {
        this.scanner = new Scanner(System.in);
        this.jugadorController = jugadorController;
        this.partidoController = partidoController;
    }

    public void mostrarMenuPrincipal() {
        boolean salir = false;
        while (!salir) {
            System.out.println("\n========================================");
            System.out.println("   SISTEMA DE ENCUENTROS DEPORTIVOS");
            System.out.println("========================================");
            System.out.println("1. Registrar jugador");
            System.out.println("2. Crear partido");
            System.out.println("3. Buscar partidos");
            System.out.println("4. Inscribirse a un partido");
            System.out.println("5. Avanzar estado de partido");
            System.out.println("6. Ver jugadores registrados");
            System.out.println("7. Ver partidos");
            System.out.println("8. Modificar datos de jugador");
            System.out.println("9. Salir");
            System.out.println("========================================");
            System.out.print("Seleccione una opcion: ");

            String opcion = leerEntrada();
            switch (opcion) {
                case "1":
                    mostrarRegistro();
                    break;
                case "2":
                    mostrarCrearPartido();
                    break;
                case "3":
                    mostrarBuscarPartidos();
                    break;
                case "4":
                    mostrarInscripcion();
                    break;
                case "5":
                    mostrarAvanzarEstado();
                    break;
                case "6":
                    mostrarJugadores();
                    break;
                case "7":
                    mostrarPartidos();
                    break;
                case "8":
                    mostrarOpciones();
                    break;
                case "9":
                    salir = true;
                    mostrarMensaje("Hasta luego!");
                    break;
                default:
                    mostrarMensaje("Opcion no valida.");
            }
        }
    }

    public void mostrarRegistro() {
        System.out.println("\n--- Registro de Jugador ---");
        System.out.print("ID: ");
        String id = leerEntrada();
        System.out.print("Nombre: ");
        String nombre = leerEntrada();
        System.out.print("Email: ");
        String mail = leerEntrada();
        System.out.print("Username: ");
        String username = leerEntrada();
        System.out.print("Password: ");
        String password = leerEntrada();
        System.out.print("Codigo postal: ");
        String codigoPostal = leerEntrada();

        System.out.println("Deporte favorito:");
        System.out.println("1. Futbol");
        System.out.println("2. Basquet");
        System.out.println("3. Voley");
        System.out.println("4. Ninguno");
        System.out.print("Seleccione: ");
        String deporteOpcion = leerEntrada();

        AbstractDeporte deporte = null;
        switch (deporteOpcion) {
            case "1": deporte = new Futbol(); break;
            case "2": deporte = new Basquet(); break;
            case "3": deporte = new Voley(); break;
        }

        Jugador jugador = jugadorController.registrarJugador(id, nombre, mail, username, password, deporte, codigoPostal);
        mostrarMensaje("Jugador registrado: " + jugador);
    }

    public void mostrarCrearPartido() {
        System.out.println("\n--- Crear Partido ---");

        List<Jugador> jugadores = jugadorController.getJugadores();
        if (jugadores.isEmpty()) {
            mostrarMensaje("No hay jugadores registrados. Registre uno primero.");
            return;
        }

        System.out.println("Jugadores disponibles:");
        for (int i = 0; i < jugadores.size(); i++) {
            System.out.println((i + 1) + ". " + jugadores.get(i).getUsername());
        }
        System.out.print("Seleccione organizador: ");
        int orgIndex = Integer.parseInt(leerEntrada()) - 1;
        if (orgIndex < 0 || orgIndex >= jugadores.size()) {
            mostrarMensaje("Seleccion invalida.");
            return;
        }
        Jugador organizador = jugadores.get(orgIndex);

        System.out.println("Deporte:");
        System.out.println("1. Futbol");
        System.out.println("2. Basquet");
        System.out.println("3. Voley");
        System.out.print("Seleccione: ");
        String deporteOpcion = leerEntrada();

        AbstractDeporte deporte;
        switch (deporteOpcion) {
            case "1": deporte = new Futbol(); break;
            case "2": deporte = new Basquet(); break;
            case "3": deporte = new Voley(); break;
            default:
                mostrarMensaje("Opcion invalida.");
                return;
        }

        System.out.print("Cantidad de jugadores requeridos: ");
        int nJugadores = Integer.parseInt(leerEntrada());

        System.out.print("Duracion (minutos): ");
        int duracion = Integer.parseInt(leerEntrada());

        System.out.print("Ubicacion: ");
        String ubicacion = leerEntrada();

        Partido partido = partidoController.crearPartido(deporte, nJugadores, null, duracion, ubicacion, new Date(), organizador);
        mostrarMensaje("Partido creado! Deporte: " + partido.getDeporte() + " | Estado: " + partido.getEstado());
    }

    public void mostrarBuscarPartidos() {
        System.out.println("\n--- Buscar Partidos ---");

        Jugador jugadorBusqueda = new Jugador("1", "Juan Perez", "juan1@mail.com", "juanp1", "pass123", new Futbol(), "1000");
        List<Partido> encontrado = partidoController.buscarPartido(jugadorBusqueda);
        if (encontrado.getFirst() != null) {
            mostrarMensaje("Partido encontrado: " + encontrado.getFirst().getDeporte() + " en " + encontrado.getFirst().getUbicacion());
        } else {
            mostrarMensaje("No se encontraron partidos disponibles.");
        }
    }

    public void mostrarInscripcion() {
        System.out.println("\n--- Inscripcion a Partido ---");

        List<Partido> partidos = partidoController.getPartidos();
        if (partidos.isEmpty()) {
            mostrarMensaje("No hay partidos disponibles.");
            return;
        }

        System.out.println("Partidos disponibles:");
        for (int i = 0; i < partidos.size(); i++) {
            Partido p = partidos.get(i);
            System.out.println((i + 1) + ". " + p.getDeporte() + " en " + p.getUbicacion()
                    + " [" + p.getEstado().getClass().getSimpleName() + "]"
                    + " (" + p.getJugadores().size() + "/" + p.getNJugadores() + " jugadores)");
        }
        System.out.print("Seleccione partido: ");
        int partidoIndex = Integer.parseInt(leerEntrada()) - 1;
        if (partidoIndex < 0 || partidoIndex >= partidos.size()) {
            mostrarMensaje("Seleccion invalida.");
            return;
        }

        List<Jugador> jugadores = jugadorController.getJugadores();
        System.out.println("Jugadores disponibles:");
        for (int i = 0; i < jugadores.size(); i++) {
            System.out.println((i + 1) + ". " + jugadores.get(i).getUsername());
        }
        System.out.print("Seleccione jugador: ");
        int jugadorIndex = Integer.parseInt(leerEntrada()) - 1;
        if (jugadorIndex < 0 || jugadorIndex >= jugadores.size()) {
            mostrarMensaje("Seleccion invalida.");
            return;
        }

        partidoController.agregarJugador(partidos.get(partidoIndex), jugadores.get(jugadorIndex));
        mostrarMensaje("Jugador inscripto al partido.");
    }

    public void mostrarOpciones() {
        System.out.println("\n--- Modificar Datos de Jugador ---");

        List<Jugador> jugadores = jugadorController.getJugadores();
        if (jugadores.isEmpty()) {
            mostrarMensaje("No hay jugadores registrados.");
            return;
        }

        System.out.println("Jugadores:");
        for (int i = 0; i < jugadores.size(); i++) {
            System.out.println((i + 1) + ". " + jugadores.get(i).getUsername());
        }
        System.out.print("Seleccione jugador: ");
        int index = Integer.parseInt(leerEntrada()) - 1;
        if (index < 0 || index >= jugadores.size()) {
            mostrarMensaje("Seleccion invalida.");
            return;
        }
        Jugador jugador = jugadores.get(index);

        System.out.println("1. Modificar email");
        System.out.println("2. Modificar password");
        System.out.println("3. Cambiar deporte favorito");
        System.out.println("4. Subir nivel");
        System.out.println("5. Bajar nivel");
        System.out.print("Seleccione: ");
        String opcion = leerEntrada();

        switch (opcion) {
            case "1":
                System.out.print("Nuevo email: ");
                jugadorController.modificarMail(jugador, leerEntrada());
                mostrarMensaje("Email actualizado.");
                break;
            case "2":
                System.out.print("Nuevo password: ");
                jugadorController.modificarPassword(jugador, leerEntrada());
                mostrarMensaje("Password actualizado.");
                break;
            case "3":
                System.out.println("1. Futbol  2. Basquet  3. Voley");
                System.out.print("Seleccione: ");
                String dep = leerEntrada();
                AbstractDeporte nuevoDeporte = null;
                switch (dep) {
                    case "1": nuevoDeporte = new Futbol(); break;
                    case "2": nuevoDeporte = new Basquet(); break;
                    case "3": nuevoDeporte = new Voley(); break;
                }
                if (nuevoDeporte != null) {
                    jugadorController.setDeporteFavoritoJugador(jugador, nuevoDeporte);
                    mostrarMensaje("Deporte favorito actualizado.");
                }
                break;
            case "4":
                jugadorController.subirNivel(jugador);
                mostrarMensaje("Nivel actual: " + jugador.getNivelDeporteFavorito());
                break;
            case "5":
                jugadorController.bajarNivel(jugador);
                mostrarMensaje("Nivel actual: " + jugador.getNivelDeporteFavorito());
                break;
            default:
                mostrarMensaje("Opcion no valida.");
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(">> " + mensaje);
    }

    public String leerEntrada() {
        return scanner.nextLine().trim();
    }

    private void mostrarAvanzarEstado() {
        System.out.println("\n--- Avanzar Estado de Partido ---");

        List<Partido> partidos = partidoController.getPartidos();
        if (partidos.isEmpty()) {
            mostrarMensaje("No hay partidos.");
            return;
        }

        System.out.println("Partidos:");
        for (int i = 0; i < partidos.size(); i++) {
            Partido p = partidos.get(i);
            System.out.println((i + 1) + ". " + p.getDeporte() + " en " + p.getUbicacion()
                    + " [" + p.getEstado().getClass().getSimpleName() + "]");
        }
        System.out.print("Seleccione partido: ");
        int index = Integer.parseInt(leerEntrada()) - 1;
        if (index < 0 || index >= partidos.size()) {
            mostrarMensaje("Seleccion invalida.");
            return;
        }

        partidoController.avanzarEstado(partidos.get(index));
        mostrarMensaje("Estado actualizado a: " + partidos.get(index).getEstado().getClass().getSimpleName());
    }

    private void mostrarJugadores() {
        System.out.println("\n--- Jugadores Registrados ---");
        List<Jugador> jugadores = jugadorController.getJugadores();
        if (jugadores.isEmpty()) {
            mostrarMensaje("No hay jugadores registrados.");
            return;
        }
        for (Jugador j : jugadores) {
            System.out.println("  " + j);
        }
    }

    private void mostrarPartidos() {
        System.out.println("\n--- Partidos ---");
        List<Partido> partidos = partidoController.getPartidos();
        if (partidos.isEmpty()) {
            mostrarMensaje("No hay partidos creados.");
            return;
        }
        for (int i = 0; i < partidos.size(); i++) {
            Partido p = partidos.get(i);
            System.out.println("  " + (i + 1) + ". " + p.getDeporte()
                    + " | Ubicacion: " + p.getUbicacion()
                    + " | Estado: " + p.getEstado().getClass().getSimpleName()
                    + " | Jugadores: " + p.getJugadores().size() + "/" + p.getNJugadores()
                    + " | Organizador: " + p.getOrganizador().getUsername());
        }
    }

    public static void main(String[] args) {
        NotificacionController notificacionController = new NotificacionController();

        IAdapterFirebase adapterFirebase = new Firebase();
        IStrategyNotificador strategyPush = new Push(adapterFirebase);
        Notificador notificadorPush = new Notificador(strategyPush);
        notificacionController.agregarNotificador(notificadorPush);

        JugadorController jugadorController = new JugadorController(notificacionController);
        BuscadorPartidos buscadorPartidos = new BuscadorPartidos();
        PartidoController partidoController = new PartidoController(buscadorPartidos, notificacionController);

        VistaConsola vista = new VistaConsola(jugadorController, partidoController);
        vista.mostrarMenuPrincipal();
    }
}
