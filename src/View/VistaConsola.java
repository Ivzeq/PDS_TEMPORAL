package View;

import Controller.JugadorController;
import Controller.PartidoController;
import Model.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class VistaConsola {

    private Scanner scanner;
    private JugadorController jugadorController;
    private PartidoController partidoController;
    private Jugador jugadorLogeado;
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public VistaConsola(JugadorController jugadorController, PartidoController partidoController, Jugador jugadorLogeado) {
        this.scanner = new Scanner(System.in);
        this.jugadorController = jugadorController;
        this.partidoController = partidoController;
        this.jugadorLogeado = jugadorLogeado;
    }

    public void mostrarMenuPrincipal() {
        boolean salir = false;
        while (!salir) {
            partidoController.actualizarEstadosPorTiempo();

            System.out.println("\n========================================");
            System.out.println("   SISTEMA DE ENCUENTROS DEPORTIVOS");
            System.out.println("   Logeado como: " + jugadorLogeado.getUsername());
            System.out.println("========================================");
            System.out.println("1. Registrar jugador");
            System.out.println("2. Crear partido");
            System.out.println("3. Buscar partidos y unirse");
            System.out.println("4. Avanzar estado de partido");
            System.out.println("5. Ver jugadores registrados");
            System.out.println("6. Ver partidos");
            System.out.println("7. Modificar datos de jugador");
            System.out.println("8. Cambiar modo de busqueda de partidos");
            System.out.println("9. Confirmar asistencia a partido");
            System.out.println("0. Salir");
            System.out.println("========================================");
            System.out.print("Seleccione una opcion: ");

            String opcion = leerEntrada();
            try {
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
                        mostrarAvanzarEstado();
                        break;
                    case "5":
                        mostrarJugadores();
                        break;
                    case "6":
                        mostrarPartidos();
                        break;
                    case "7":
                        mostrarOpciones();
                        break;
                    case "8":
                        mostrarCambiarBusqueda();
                        break;
                    case "9":
                        mostrarConfirmarAsistencia();
                        break;
                    case "0":
                        salir = true;
                        mostrarMensaje("Hasta luego!");
                        break;
                    default:
                        mostrarMensaje("Opcion no valida.");
                }
            } catch (NumberFormatException e) {
                mostrarMensaje("Entrada invalida. Ingrese un numero valido.");
            } catch (Exception e) {
                mostrarMensaje("Ocurrio un error: " + e.getMessage());
            }
        }
    }

    public void mostrarRegistro() {
        System.out.println("\n--- Registro de Jugador ---");
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

        AbstractDeporte deporte = null;
        while (deporte == null) {
            System.out.println("Deporte favorito:");
            System.out.println("1. Futbol");
            System.out.println("2. Basquet");
            System.out.println("3. Voley");
            System.out.print("Seleccione: ");
            String deporteOpcion = leerEntrada();
            switch (deporteOpcion) {
                case "1":
                    deporte = new Futbol();
                    break;
                case "2":
                    deporte = new Basquet();
                    break;
                case "3":
                    deporte = new Voley();
                    break;
                default:
                    mostrarMensaje("Debe seleccionar un deporte.");
                    break;
            }
        }

        System.out.println("Medio de notificacion preferido:");
        System.out.println("1. Push (Firebase)");
        System.out.println("2. Correo electronico");
        System.out.print("Seleccione: ");
        String notiOpcion = leerEntrada();

        Jugador jugador = jugadorController.registrarJugador(nombre, mail, username, password, deporte, codigoPostal);

        IStrategyNotificador strategy = seleccionarStrategyNotificacion(notiOpcion);
        if (strategy != null) {
            jugadorController.setNotificadorJugador(jugador, strategy);
        }

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
            case "1":
                deporte = new Futbol();
                break;
            case "2":
                deporte = new Basquet();
                break;
            case "3":
                deporte = new Voley();
                break;
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

        System.out.print("Codigo postal: ");
        String codigoPostal = leerEntrada();

        System.out.print("Fecha y hora (dd/MM/yyyy HH:mm): ");
        String fechaStr = leerEntrada();
        LocalDateTime horario;
        try {
            horario = LocalDateTime.parse(fechaStr, dtf);
        } catch (Exception e) {
            mostrarMensaje("Formato de fecha invalido. Se usara la fecha y hora actual.");
            horario = LocalDateTime.now();
        }

        Partido partido = partidoController.crearPartido(deporte, nJugadores, null, duracion, ubicacion, codigoPostal,
                horario, organizador);
        mostrarMensaje("Partido creado! Deporte: " + partido.getDeporte()
                + " | Horario: " + partido.getHorario().format(dtf)
                + " | Estado: " + partido.getEstado());
    }

    public void mostrarBuscarPartidos() {
        System.out.println("\n--- Buscar Partidos (" + jugadorLogeado.getUsername() + ") ---");

        System.out.println("Buscar:");
        System.out.println("1. Solo partidos de " + jugadorLogeado.getDeporteFavorito() + " (mi deporte favorito)");
        System.out.println("2. Todos los deportes");
        System.out.print("Seleccione: ");
        String filtro = leerEntrada();

        String strategyActual = partidoController.getNombreStrategyBuscador();
        List<Partido> encontrados;
        if (filtro.equals("2")) {
            encontrados = partidoController.buscarTodosPartidos(jugadorLogeado);
            strategyActual = "Todos";
        } else {
            encontrados = partidoController.buscarPartidos(jugadorLogeado);
        }

        if (encontrados.isEmpty()) {
            mostrarMensaje("No se encontraron partidos disponibles (modo: " + strategyActual + ").");
        } else {
            mostrarMensaje("Se encontraron " + encontrados.size() + " partido(s) [modo: " + strategyActual + "]:");
            for (int i = 0; i < encontrados.size(); i++) {
                Partido p = encontrados.get(i);
                StringBuilder sb = new StringBuilder();
                sb.append("  ").append(i + 1).append(". ").append(p.getDeporte());
                sb.append(" en ").append(p.getUbicacion());
                String horarioStr = p.getHorario() != null ? p.getHorario().format(dtf) : "Sin definir";
                sb.append(" | ").append(horarioStr);
                sb.append(" (").append(p.getJugadores().size()).append("/").append(p.getNJugadores())
                        .append(" jugadores)");
                sb.append(" | Organizador: ").append(p.getOrganizador().getUsername());

                switch (strategyActual) {
                    case "PorUbicacion":
                        sb.append(" | CP: ").append(p.getCodigoPostal());
                        break;
                    case "PorHistorial":
                        sb.append(" | Deporte: ").append(p.getDeporte());
                        break;
                    case "PorNivel":
                        String nivelPartido = p.getNivelJugadores() != null
                                ? p.getNivelJugadores().toString()
                                : "Sin restriccion";
                        sb.append(" | Nivel requerido: ").append(nivelPartido);
                        break;
                }

                System.out.println(sb.toString());
            }

            System.out.print("\nDesea unirse a un partido? (s/n): ");
            String respuesta = leerEntrada();
            if (respuesta.equalsIgnoreCase("s")) {
                System.out.print("Seleccione numero de partido: ");
                int partidoIndex = Integer.parseInt(leerEntrada()) - 1;
                if (partidoIndex < 0 || partidoIndex >= encontrados.size()) {
                    mostrarMensaje("Seleccion invalida.");
                } else {
                    boolean ok = partidoController.agregarJugador(encontrados.get(partidoIndex), jugadorLogeado);
                    if (ok) {
                        mostrarMensaje("Te uniste al partido!");
                    } else {
                        mostrarMensaje("Ya estas inscripto en este partido.");
                    }
                }
            }
        }
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
        System.out.println("6. Cambiar medio de notificacion");
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
                    case "1":
                        nuevoDeporte = new Futbol();
                        break;
                    case "2":
                        nuevoDeporte = new Basquet();
                        break;
                    case "3":
                        nuevoDeporte = new Voley();
                        break;
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
            case "6":
                System.out.println("1. Push (Firebase)");
                System.out.println("2. Correo electronico");
                System.out.print("Seleccione: ");
                String notiOp = leerEntrada();
                IStrategyNotificador strategy = seleccionarStrategyNotificacion(notiOp);
                if (strategy != null) {
                    jugadorController.setNotificadorJugador(jugador, strategy);
                    mostrarMensaje("Medio de notificacion actualizado a: " + strategy.getClass().getSimpleName());
                } else {
                    mostrarMensaje("Opcion no valida.");
                }
                break;
            default:
                mostrarMensaje("Opcion no valida.");
        }
    }

    private void mostrarCambiarBusqueda() {
        System.out.println("\n--- Cambiar Modo de Busqueda de Partidos ---");
        System.out.println("1. Por Ubicacion");
        System.out.println("2. Por Historial");
        System.out.println("3. Por Nivel");
        System.out.print("Seleccione: ");
        String opcion = leerEntrada();

        IStrategyBuscadorPartidos strategy;
        switch (opcion) {
            case "1":
                strategy = new PorUbicacion();
                break;
            case "2":
                strategy = new PorHistorial();
                break;
            case "3":
                strategy = new PorNivel();
                break;
            default:
                mostrarMensaje("Opcion no valida.");
                return;
        }

        partidoController.cambiarStrategyBuscador(strategy);
        mostrarMensaje("Modo de busqueda cambiado a: " + strategy.getClass().getSimpleName());
    }

    private IStrategyNotificador seleccionarStrategyNotificacion(String opcion) {
        switch (opcion) {
            case "1":
                return new Push(new Firebase());
            case "2":
                return new CorreoElectronico(new JavaMail("smtp.gmail.com", 587, "", ""));
            default:
                return null;
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(">> " + mensaje);
    }

    public String leerEntrada() {
        return scanner.nextLine().trim();
    }

    private void mostrarAvanzarEstado() {
        System.out.println("\n--- Gestionar Estado de Partido ---");

        List<Partido> partidos = partidoController.getPartidos();
        if (partidos.isEmpty()) {
            mostrarMensaje("No hay partidos.");
            return;
        }

        System.out.println("Partidos:");
        for (int i = 0; i < partidos.size(); i++) {
            Partido p = partidos.get(i);
            String horario = p.getHorario() != null ? p.getHorario().format(dtf) : "Sin definir";
            System.out.println((i + 1) + ". " + p.getDeporte() + " en " + p.getUbicacion()
                    + " | " + horario
                    + " [" + p.getEstado().getClass().getSimpleName() + "]"
                    + " (" + p.getJugadores().size() + "/" + p.getNJugadores() + ")"
                    + " | Organizador: " + p.getOrganizador().getUsername());
        }
        System.out.print("Seleccione partido: ");
        int index = Integer.parseInt(leerEntrada()) - 1;
        if (index < 0 || index >= partidos.size()) {
            mostrarMensaje("Seleccion invalida.");
            return;
        }

        Partido partido = partidos.get(index);

        System.out.println("1. Avanzar estado");
        System.out.println("2. Cancelar partido (solo organizador)");
        System.out.print("Seleccione: ");
        String opcion = leerEntrada();

        switch (opcion) {
            case "1":
                String estadoAntes = partido.getEstado().getClass().getSimpleName();
                partidoController.avanzarEstado(partido);
                String estadoDespues = partido.getEstado().getClass().getSimpleName();
                if (estadoAntes.equals(estadoDespues)) {
                    mostrarMensaje("No se pudo avanzar el estado.");
                } else {
                    mostrarMensaje("Estado actualizado a: " + estadoDespues);
                }
                break;
            case "2":
                System.out.println("Quien cancela? Seleccione jugador:");
                List<Jugador> jugadores = jugadorController.getJugadores();
                for (int i = 0; i < jugadores.size(); i++) {
                    System.out.println((i + 1) + ". " + jugadores.get(i).getUsername());
                }
                System.out.print("Seleccione: ");
                int jIndex = Integer.parseInt(leerEntrada()) - 1;
                if (jIndex < 0 || jIndex >= jugadores.size()) {
                    mostrarMensaje("Seleccion invalida.");
                    return;
                }
                Jugador solicitante = jugadores.get(jIndex);
                if (solicitante.equals(partido.getOrganizador())) {
                    partidoController.cancelarPartido(partido);
                    mostrarMensaje("Partido cancelado.");
                } else {
                    mostrarMensaje("Solo el organizador (" + partido.getOrganizador().getUsername()
                            + ") puede cancelar el partido.");
                }
                break;
            default:
                mostrarMensaje("Opcion no valida.");
        }
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

    private void mostrarConfirmarAsistencia() {
        System.out.println("\n--- Confirmar Asistencia a Partido (" + jugadorLogeado.getUsername() + ") ---");

        List<Partido> partidosPendientes = new java.util.ArrayList<>();
        for (Partido p : partidoController.getPartidos()) {
            if (p.getEstado() instanceof PartidoArmado
                    && p.getJugadores().contains(jugadorLogeado)
                    && !p.isConfirmado(jugadorLogeado)) {
                partidosPendientes.add(p);
            }
        }

        if (partidosPendientes.isEmpty()) {
            mostrarMensaje("No tienes partidos pendientes de confirmacion.");
            return;
        }

        System.out.println("Partidos esperando tu confirmacion:");
        for (int i = 0; i < partidosPendientes.size(); i++) {
            Partido p = partidosPendientes.get(i);
            String horario = p.getHorario() != null ? p.getHorario().format(dtf) : "Sin definir";
            System.out.println("  " + (i + 1) + ". " + p.getDeporte()
                    + " en " + p.getUbicacion()
                    + " | " + horario
                    + " | Confirmados: " + p.getJugadoresConfirmados().size() + "/" + p.getNJugadores()
                    + " | Organizador: " + p.getOrganizador().getUsername());
        }
        System.out.print("Seleccione partido: ");
        int pIndex = Integer.parseInt(leerEntrada()) - 1;
        if (pIndex < 0 || pIndex >= partidosPendientes.size()) {
            mostrarMensaje("Seleccion invalida.");
            return;
        }

        Partido partido = partidosPendientes.get(pIndex);

        System.out.println("1. Confirmar asistencia");
        System.out.println("2. No puedo asistir (seras removido del partido)");
        System.out.print("Seleccione: ");
        String opcion = leerEntrada();

        switch (opcion) {
            case "1":
                boolean ok = partidoController.confirmarAsistencia(partido, jugadorLogeado);
                if (ok) {
                    mostrarMensaje("Asistencia confirmada!");
                    if (partido.todosConfirmados()) {
                        mostrarMensaje("Todos los jugadores confirmaron. El partido esta confirmado!");
                    } else {
                        mostrarMensaje("Confirmados: " + partido.getJugadoresConfirmados().size()
                                + "/" + partido.getNJugadores());
                    }
                } else {
                    mostrarMensaje("No se pudo confirmar.");
                }
                break;
            case "2":
                partidoController.rechazarAsistencia(partido, jugadorLogeado);
                mostrarMensaje("Fuiste removido del partido. El partido vuelve a buscar jugadores.");
                break;
            default:
                mostrarMensaje("Opcion no valida.");
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
            String horario = p.getHorario() != null ? p.getHorario().format(dtf) : "Sin definir";
            String confirmInfo = "";
            if (p.getEstado() instanceof PartidoArmado) {
                confirmInfo = " | Confirmados: " + p.getJugadoresConfirmados().size() + "/" + p.getNJugadores();
            }
            System.out.println("  " + (i + 1) + ". " + p.getDeporte()
                    + " | Ubicacion: " + p.getUbicacion()
                    + " | Horario: " + horario
                    + " | Duracion: " + p.getDuracion() + " min"
                    + " | Estado: " + p.getEstado().getClass().getSimpleName()
                    + " | Jugadores: " + p.getJugadores().size() + "/" + p.getNJugadores()
                    + confirmInfo
                    + " | Organizador: " + p.getOrganizador().getUsername());
        }

        System.out.print("\nVer jugadores de un partido? (s/n): ");
        String respuesta = leerEntrada();
        if (respuesta.equalsIgnoreCase("s")) {
            System.out.print("Seleccione numero de partido: ");
            int index = Integer.parseInt(leerEntrada()) - 1;
            if (index < 0 || index >= partidos.size()) {
                mostrarMensaje("Seleccion invalida.");
            } else {
                Partido p = partidos.get(index);
                System.out.println("\n--- Jugadores en " + p.getDeporte() + " - " + p.getUbicacion() + " ---");
                List<Jugador> jugadores = p.getJugadores();
                boolean mostrarConfirmacion = p.getEstado() instanceof PartidoArmado
                        || p.getEstado() instanceof Confirmado;
                for (int i = 0; i < jugadores.size(); i++) {
                    Jugador j = jugadores.get(i);
                    String rol = j.equals(p.getOrganizador()) ? " (organizador)" : "";
                    String conf = mostrarConfirmacion
                            ? (p.isConfirmado(j) ? " [Confirmado]" : " [Pendiente]")
                            : "";
                    System.out.println("  " + (i + 1) + ". " + j.getUsername()
                            + " - " + j.getNombre() + rol + conf);
                }
            }
        }
    }
}
