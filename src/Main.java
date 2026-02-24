import Controller.JugadorController;
import Controller.NotificacionController;
import Controller.PartidoController;
import Model.*;
import View.VistaConsola;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Properties config = new Properties();
        String[] configPaths = { "config.properties", "PDS_TEMPORAL/config.properties" };
        boolean configLoaded = false;
        for (String path : configPaths) {
            try (FileInputStream fis = new FileInputStream(path)) {
                config.load(fis);
                configLoaded = true;
                break;
            } catch (IOException ignored) {
            }
        }
        if (!configLoaded) {
            System.err.println("No se encontro config.properties. Los emails no se enviaran.");
        }

        IAdapterFirebase adapterFirebase = new Firebase();
        IStrategyNotificador strategyPush = new Push(adapterFirebase);

        JugadorController jugadorController = new JugadorController();
        BuscadorPartidos buscadorPartidos = new BuscadorPartidos();
        NotificacionController notificacionController = new NotificacionController();
        PartidoController partidoController = new PartidoController(buscadorPartidos, notificacionController);

        // --- Jugadores ---
        Jugador juan = jugadorController.registrarJugador("Juan Perez", "shinfin12@gmail.com", "juanp", "pass123",
                new Futbol(), "1824");
        Jugador maria = jugadorController.registrarJugador("Maria Lopez", "maria@mail.com", "marial", "pass456",
                new Basquet(), "1830");
        Jugador carlos = jugadorController.registrarJugador("Carlos Gomez", "carlos@mail.com", "carlosg", "pass789",
                new Voley(), "1830");
        Jugador lucia = jugadorController.registrarJugador("Lucia Fernandez", "lucia@mail.com", "luciaf", "pass000",
                new Futbol(), "1824");
        Jugador gianluca = jugadorController.registrarJugador("Gianluca Calabria", "shinfin12@gmail.com", "gianlucac",
                "pass555", new Futbol(), "1824");
        Jugador pablo = jugadorController.registrarJugador("Pablo Diaz", "pablo@mail.com", "pablod", "pass601",
                new Futbol(), "1824");
        Jugador sofia = jugadorController.registrarJugador("Sofia Martinez", "sofia@mail.com", "sofiam", "pass602",
                new Basquet(), "1830");
        Jugador andres = jugadorController.registrarJugador("Andres Ruiz", "andres@mail.com", "andresr", "pass603",
                new Voley(), "1830");
        Jugador valentina = jugadorController.registrarJugador("Valentina Torres", "vale@mail.com", "valet", "pass604",
                new Futbol(), "1830");
        Jugador martin = jugadorController.registrarJugador("Martin Sosa", "martin@mail.com", "martins", "pass605",
                new Basquet(), "1824");
        Jugador camila = jugadorController.registrarJugador("Camila Herrera", "camila@mail.com", "camilah", "pass606",
                new Voley(), "1824");
        Jugador diego = jugadorController.registrarJugador("Diego Morales", "diego@mail.com", "diegom", "pass607",
                new Futbol(), "1824");

        // Notificadores
        jugadorController.setNotificadorJugador(gianluca, strategyPush);
        jugadorController.setNotificadorJugador(maria, strategyPush);
        jugadorController.setNotificadorJugador(carlos, strategyPush);
        jugadorController.setNotificadorJugador(lucia, strategyPush);
        jugadorController.setNotificadorJugador(juan, new CorreoElectronico(new JavaMail(
                "smtp.gmail.com", 465, "shinfin12@gmail.com", "ruih pjwx wgob lbye")));
        jugadorController.setNotificadorJugador(pablo, strategyPush);
        jugadorController.setNotificadorJugador(sofia, strategyPush);
        jugadorController.setNotificadorJugador(andres, strategyPush);
        jugadorController.setNotificadorJugador(valentina, strategyPush);
        jugadorController.setNotificadorJugador(martin, strategyPush);
        jugadorController.setNotificadorJugador(camila, strategyPush);
        jugadorController.setNotificadorJugador(diego, strategyPush);

        // Subir niveles a algunos jugadores
        jugadorController.subirNivel(juan); // Intermedio
        jugadorController.subirNivel(maria); // Intermedio
        jugadorController.subirNivel(maria); // Avanzado
        jugadorController.subirNivel(gianluca); // Intermedio
        jugadorController.subirNivel(pablo); // Intermedio
        jugadorController.subirNivel(pablo); // Avanzado

        // --- Partidos (con fechas futuras) ---
        LocalDateTime ahora = LocalDateTime.now();
        LocalDateTime enDosHoras = ahora.plusHours(2);
        LocalDateTime enCincoHoras = ahora.plusHours(5);
        LocalDateTime maniana18 = ahora.plusDays(1).withHour(18).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime maniana2030 = ahora.plusDays(1).withHour(20).withMinute(30).withSecond(0).withNano(0);
        LocalDateTime pasadoManiana10 = ahora.plusDays(2).withHour(10).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime pasadoManiana16 = ahora.plusDays(2).withHour(16).withMinute(0).withSecond(0).withNano(0);

        Partido futbol5Zona1 = partidoController.crearPartido(new Futbol(), 10, null, 90,
                "Cancha Central", "1824", enDosHoras, juan);
        partidoController.agregarJugador(futbol5Zona1, pablo);
        partidoController.agregarJugador(futbol5Zona1, diego);

        partidoController.crearPartido(new Futbol(), 6, null, 60,
                "Cancha Los Amigos", "1830", maniana18, valentina);

        Partido basquetZona2 = partidoController.crearPartido(new Basquet(), 6, null, 40,
                "Club Deportivo Norte", "1830", enCincoHoras, maria);
        partidoController.agregarJugador(basquetZona2, sofia);

        partidoController.crearPartido(new Basquet(), 4, null, 30,
                "Polideportivo Sur", "1824", maniana2030, martin);

        Partido voleyZona2 = partidoController.crearPartido(new Voley(), 8, null, 50,
                "Playa Deportiva", "1830", pasadoManiana10, carlos);
        partidoController.agregarJugador(voleyZona2, andres);

        partidoController.crearPartido(new Voley(), 6, null, 45,
                "Club El Sol", "1824", pasadoManiana16, camila);

        // Partido para hoy + 1 minuto
        LocalDateTime hoySumaUnMinuto = ahora.plusMinutes(1);
        partidoController.crearPartido(new Futbol(), 8, null, 60,
                "Cancha Rapida", "1824", hoySumaUnMinuto, lucia);

        System.out.println("=== Datos iniciales cargados ===");
        System.out.println("Jugadores: " + jugadorController.getJugadores().size());
        System.out.println("Partidos: " + partidoController.getPartidos().size());
        System.out.println();

        // Menu inicial
        Jugador jugadorLogeado = null;
        boolean salir = false;
        
        Scanner scanner = new Scanner(System.in);
        try {
            while (!salir) {
                System.out.println("\n========================================");
                System.out.println("   SISTEMA DE ENCUENTROS DEPORTIVOS");
                System.out.println("========================================");
                System.out.println("1. Login");
                System.out.println("2. Registrarse");
                System.out.println("0. Salir");
                System.out.print("Seleccione opcion: ");
                
                String opcion = scanner.nextLine().trim();
                
                switch (opcion) {
                    case "1":
                        jugadorLogeado = mostrarLogin(jugadorController, scanner);
                        if (jugadorLogeado != null) {
                            salir = true;
                            VistaConsola vista = new VistaConsola(jugadorController, partidoController, jugadorLogeado);
                            vista.mostrarMenuPrincipal();
                        }
                        break;
                    case "2":
                        mostrarRegistroNuevo(jugadorController, strategyPush, scanner);
                        break;
                    case "0":
                        System.out.println("Hasta luego!");
                        salir = true;
                        break;
                    default:
                        System.out.println("Opcion invalida.");
                }
            }
        } finally {
            scanner.close();
        }
    }

    private static Jugador mostrarLogin(JugadorController jugadorController, Scanner scanner) {
        int intentos = 3;
        
        while (intentos > 0) {
            System.out.println("\n========================================");
            System.out.println("   LOGIN - SISTEMA DE ENCUENTROS");
            System.out.println("========================================");
            System.out.print("Username: ");
            String username = scanner.nextLine().trim();
            System.out.print("Password: ");
            String password = scanner.nextLine().trim();
            
            Jugador jugador = jugadorController.autenticarJugador(username, password);
            if (jugador != null) {
                System.out.println("\nBienvenido, " + jugador.getNombre() + "!");
                return jugador;
            } else {
                intentos--;
                if (intentos > 0) {
                    System.out.println("Usuario o contraseña incorrectos. Intentos restantes: " + intentos);
                } else {
                    System.out.println("Demasiados intentos fallidos. Acceso denegado.");
                }
            }
        }
        return null;
    }

    private static void mostrarRegistroNuevo(JugadorController jugadorController, IStrategyNotificador strategyPush, Scanner scanner) {
        
        System.out.println("\n========================================");
        System.out.println("   REGISTRARSE - SISTEMA DE ENCUENTROS");
        System.out.println("========================================");
        System.out.print("Nombre completo: ");
        String nombre = scanner.nextLine().trim();
        
        System.out.print("Email: ");
        String email = scanner.nextLine().trim();
        
        System.out.print("Username: ");
        String username = scanner.nextLine().trim();
        
        // Verificar que el username no exista
        if (jugadorController.buscarJugadorPorUsername(username) != null) {
            System.out.println("El username ya existe. Intente otro.");
            return;
        }
        
        System.out.print("Password: ");
        String password = scanner.nextLine().trim();
        
        System.out.print("Codigo postal: ");
        String codigoPostal = scanner.nextLine().trim();
        
        System.out.println("Deporte favorito:");
        System.out.println("1. Futbol");
        System.out.println("2. Basquet");
        System.out.println("3. Voley");
        System.out.print("Seleccione: ");
        String deporteOpcion = scanner.nextLine().trim();
        
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
                System.out.println("Deporte invalido. Se asignara Futbol por defecto.");
                deporte = new Futbol();
        }
        
        Jugador nuevoJugador = jugadorController.registrarJugador(nombre, email, username, password, deporte, codigoPostal);
        jugadorController.setNotificadorJugador(nuevoJugador, strategyPush);
        
        System.out.println("\n¡Jugador registrado exitosamente!");
        System.out.println("Datos: " + nuevoJugador);
    }
}