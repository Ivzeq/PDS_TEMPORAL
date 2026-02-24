import Controller.JugadorController;
import Controller.NotificacionController;
import Controller.PartidoController;
import Model.*;
import View.VistaConsola;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

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
        Jugador juan = jugadorController.registrarJugador("Juan Perez", "juan@mail.com", "juanp", "pass123",
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
        jugadorController.setNotificadorJugador(juan, strategyPush);
        jugadorController.setNotificadorJugador(maria, strategyPush);
        jugadorController.setNotificadorJugador(carlos, strategyPush);
        jugadorController.setNotificadorJugador(lucia, strategyPush);
        jugadorController.setNotificadorJugador(gianluca, new CorreoElectronico(new JavaMail(
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
        Calendar cal = Calendar.getInstance();

        cal.add(Calendar.HOUR_OF_DAY, 2);
        Date enDosHoras = cal.getTime();

        cal = Calendar.getInstance();
        cal.add(Calendar.HOUR_OF_DAY, 5);
        Date enCincoHoras = cal.getTime();

        cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 18);
        cal.set(Calendar.MINUTE, 0);
        Date maniana18 = cal.getTime();

        cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 20);
        cal.set(Calendar.MINUTE, 30);
        Date maniana2030 = cal.getTime();

        cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 2);
        cal.set(Calendar.HOUR_OF_DAY, 10);
        cal.set(Calendar.MINUTE, 0);
        Date pasadoManiana10 = cal.getTime();

        cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 2);
        cal.set(Calendar.HOUR_OF_DAY, 16);
        cal.set(Calendar.MINUTE, 0);
        Date pasadoManiana16 = cal.getTime();

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

        System.out.println("=== Datos iniciales cargados ===");
        System.out.println("Jugadores: " + jugadorController.getJugadores().size());
        System.out.println("Partidos: " + partidoController.getPartidos().size());
        System.out.println();

        VistaConsola vista = new VistaConsola(jugadorController, partidoController);
        vista.mostrarMenuPrincipal();
    }
}
