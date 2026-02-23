package ejecucionNotificador;
import Model.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ejecucionNotificador {
    public static void ejecutarNotificador () {
        Properties config = new Properties();
        try (FileInputStream fis = new FileInputStream("config.properties")) {
            config.load(fis);
        } catch (IOException e) {
            System.err.println("No se encontro config.properties. Los emails no se enviaran.");
        }

        System.out.println("=== 1. Registro de Jugadores ===\n");

        Jugador juan = new Jugador("1", "Juan Perez", "juan@mail.com", "juanp", "pass123",
                new Futbol(), new Principiante(),"1000");
        Jugador maria = new Jugador("2", "Maria Lopez", "maria@mail.com", "marial", "pass456",
                new Basquet(), new Intermedio(), "2000");
        Jugador carlos = new Jugador("3", "Carlos Gomez", "carlos@mail.com", "carlosg", "pass789",
                new Voley(), new Avanzado(), "3000");
        Jugador lucia = new Jugador("4", "Lucia Fernandez", "lucia@mail.com", "luciaf", "pass000",
                "4000");

        System.out.println(juan);
        System.out.println(maria);
        System.out.println(carlos);
        System.out.println(lucia);

        System.out.println("\n=== 2. DeporteFavorito ===\n");

        System.out.println("Deporte favorito de Juan: " + juan.getDeporteFavorito());
        System.out.println("Deporte favorito de Maria: " + maria.getDeporteFavorito());
        System.out.println("Deporte favorito de Carlos: " + carlos.getDeporteFavorito());
        System.out.println("Deporte favorito de Lucia (sin asignar): " + lucia.getDeporteFavorito());

        lucia.setDeporteFavorito(new Futbol());
        System.out.println("Lucia elige Futbol: " + lucia.getDeporteFavorito());

        carlos.setDeporteFavorito(new Basquet());
        System.out.println("Carlos cambia a Basquet: " + carlos.getDeporteFavorito());

        System.out.println("\nDatos del deporte Futbol:");
        AbstractDeporte futbol = juan.getDeporteFavorito();
        System.out.println("  Duracion partido: " + futbol.getDuracionPartido() + " min");
        System.out.println("  Cantidad jugadores: " + futbol.getCantidadJugadores());

        System.out.println("\n=== 3. State Pattern: Transiciones de NivelDeporte ===\n");

        System.out.println("--- Juan: Principiante -> Intermedio -> Avanzado ---");
        System.out.println("Nivel inicial: " + juan.getNivelDeporteFavorito());

        juan.subirNivel();
        System.out.println("subirNivel() -> " + juan.getNivelDeporteFavorito());

        juan.subirNivel();
        System.out.println("subirNivel() -> " + juan.getNivelDeporteFavorito());

        juan.subirNivel();
        System.out.println("subirNivel() (ya Avanzado, no-op) -> " + juan.getNivelDeporteFavorito());

        System.out.println("\n--- Juan: Avanzado -> Intermedio -> Principiante ---");

        juan.bajarNivel();
        System.out.println("bajarNivel() -> " + juan.getNivelDeporteFavorito());

        juan.bajarNivel();
        System.out.println("bajarNivel() -> " + juan.getNivelDeporteFavorito());

        juan.bajarNivel();
        System.out.println("bajarNivel() (ya Principiante, no-op) -> " + juan.getNivelDeporteFavorito());

        System.out.println("\n--- Maria: subir un nivel ---");
        System.out.println("Nivel inicial: " + maria.getNivelDeporteFavorito());
        maria.subirNivel();
        System.out.println("subirNivel() -> " + maria.getNivelDeporteFavorito());

        System.out.println("\n=== 4. confirmarPartido() ===\n");

        System.out.println("Juan - partidos jugados: " + juan.getNPartidos());
        juan.confirmarPartido();
        juan.confirmarPartido();
        juan.confirmarPartido();
        System.out.println("Juan confirma 3 partidos: " + juan.getNPartidos());

        System.out.println("Maria - partidos jugados: " + maria.getNPartidos());
        maria.confirmarPartido();
        System.out.println("Maria confirma 1 partido: " + maria.getNPartidos());

        System.out.println("\n=== 5. Modificar datos del Jugador ===\n");

        System.out.println("Mail de Juan antes: " + juan.getMail());
        juan.modificarMail("juannuevo@mail.com");
        System.out.println("Mail de Juan despues: " + juan.getMail());

        System.out.println("Password de Maria antes: " + maria.getPassword());
        maria.modificarPassword("nuevaPass789");
        System.out.println("Password de Maria despues: " + maria.getPassword());

        System.out.println("\n=== 6. Strategy + Adapter: Notificaciones Push (Firebase) ===\n");

        IAdapterFirebase adapterFirebase = new Firebase();
        IStrategyNotificador strategyPush = new Push(adapterFirebase);
        Notificador notificadorPush = new Notificador(strategyPush);

        Notificacion noti1 = new Notificacion("Nuevo partido de Futbol en tu zona!", juan);
        Notificacion noti2 = new Notificacion("Nuevo partido de Basquet en tu zona!", maria);
        Notificacion noti3 = new Notificacion("Tu partido paso a estado: Partido armado", carlos);

        notificadorPush.enviarNotificacion(noti1);
        notificadorPush.enviarNotificacion(noti2);
        notificadorPush.enviarNotificacion(noti3);

        System.out.println("\n=== 7. Strategy + Adapter: Notificaciones Email (JavaMail) ===\n");

        String smtpHost = config.getProperty("mail.smtp.host", "smtp.gmail.com");
        int smtpPort = Integer.parseInt(config.getProperty("mail.smtp.port", "587"));
        String remitente = config.getProperty("mail.remitente", "");
        String mailPassword = config.getProperty("mail.password", "");

        IAdapterJavaMail adapterJavaMail = new JavaMail(
                smtpHost, smtpPort, remitente, mailPassword);
        IStrategyNotificador strategyMail = new CorreoElectronico(adapterJavaMail);
        Notificador notificadorMail = new Notificador(strategyMail);

        Notificacion noti4 = new Notificacion("Nuevo partido de Futbol en tu zona!", juan);
        Notificacion noti5 = new Notificacion("Tu partido fue confirmado!", maria);

        System.out.println("(Email requiere credenciales SMTP reales para enviar)");
        notificadorMail.enviarNotificacion(noti4);
        notificadorMail.enviarNotificacion(noti5);

        System.out.println("\n=== 8. Cambio dinamico de Strategy en runtime ===\n");

        System.out.println("Notificador usa Push:");
        Notificador notificador = new Notificador(strategyPush);
        Notificacion noti6 = new Notificacion("Partido confirmado!", lucia);
        notificador.enviarNotificacion(noti6);

        System.out.println("\nCambiando strategy a Email:");
        notificador.cambiarStrategy(strategyMail);
        Notificacion noti7 = new Notificacion("Partido confirmado!", lucia);
        notificador.enviarNotificacion(noti7);

        System.out.println("\nCambiando strategy de vuelta a Push:");
        notificador.cambiarStrategy(strategyPush);
        Notificacion noti8 = new Notificacion("Partido en juego!", lucia);
        notificador.enviarNotificacion(noti8);

        System.out.println("\n=== 9. Multiples Notificadores (Observer) ===\n");

        Notificador observerPush = new Notificador(strategyPush);
        Notificador observerMail = new Notificador(strategyMail);

        System.out.println("Enviando a Juan por Push Y Email simultaneamente:");
        Notificacion notiPush = new Notificacion("Partido cancelado", juan);
        Notificacion notiMail = new Notificacion("Partido cancelado", juan);
        observerPush.enviarNotificacion(notiPush);
        observerMail.enviarNotificacion(notiMail);

        System.out.println("\n=== Resumen Final ===\n");
        System.out.println(juan);
        System.out.println(maria);
        System.out.println(carlos);
        System.out.println(lucia);
    }
}
