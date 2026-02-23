package ejecucionBuscador;

import Model.*;

import java.util.Date;

public class ejecucionBuscador {
    public static void ejecutarBuscador () {
        // Instanciar jugadores de futbol
        Jugador j1 = new Jugador("1", "Juan Perez", "juan1@mail.com", "juanp1", "pass123", new Futbol(), new Principiante(), "1000");
        Jugador j2 = new Jugador("2", "Diego Martinez", "diego2@mail.com", "diegom2", "pass123",new Futbol(), new Principiante(), "1000");
        Jugador j3 = new Jugador("3", "Lucas Ramirez", "lucas3@mail.com", "lucasr3", "pass123", new Futbol(), new Principiante(), "1000");
        Jugador j4 = new Jugador("4", "Santiago Lopez", "santi4@mail.com", "santil4", "pass123", new Futbol(), new Principiante(), "1000");
        Jugador j5 = new Jugador("5", "Matias Gomez", "matias5@mail.com", "matiasg5", "pass123", new Futbol(), new Principiante(), "1000");

        Jugador j6 = new Jugador("6", "Tomas Silva", "tomas6@mail.com", "tomass6", "pass123", new Futbol(), new Principiante(), "2000");
        Jugador j7 = new Jugador("7", "Franco Diaz", "franco7@mail.com", "francod7", "pass123", new Futbol(), new Principiante(), "2000");
        Jugador j8 = new Jugador("8", "Nicolas Torres", "nico8@mail.com", "nicot8", "pass123", new Futbol(), new Principiante(), "2000");
        Jugador j9 = new Jugador("9", "Agustin Herrera", "agus9@mail.com", "agush9", "pass123", new Futbol(), new Principiante(), "2000");
        Jugador j10 = new Jugador("10", "Bruno Castro", "bruno10@mail.com", "brunoc10", "pass123", new Futbol(), new Principiante(), "2000");

        Jugador j11 = new Jugador("11", "Alan Romero", "alan11@mail.com", "alanr11", "pass123", new Futbol(), new Principiante(), "3000");
        Jugador j12 = new Jugador("12", "Joaquin Vega", "joaquin12@mail.com", "joaquinv12", "pass123", new Futbol(), new Principiante(), "3000");
        Jugador j13 = new Jugador("13", "Facundo Luna", "facu13@mail.com", "facul13", "pass123", new Futbol(), new Principiante(), "3000");
        Jugador j14 = new Jugador("14", "Maximiliano Ruiz", "maxi14@mail.com", "maxir14", "pass123", new Futbol(), new Principiante(), "3000");
        Jugador j15 = new Jugador("15", "Cristian Molina", "cristian15@mail.com", "crism15", "pass123", new Futbol(), new Principiante(), "3000");

        Jugador j16 = new Jugador("16", "Sebastian Acosta", "seba16@mail.com", "sebaa16", "pass123", new Futbol(), new Principiante(), "4000");
        Jugador j17 = new Jugador("17", "Gabriel Ortiz", "gabi17@mail.com", "gabio17", "pass123", new Futbol(), new Principiante(), "4000");
        Jugador j18 = new Jugador("18", "Emiliano Suarez", "emi18@mail.com", "emis18", "pass123", new Futbol(), new Principiante(), "4000");
        Jugador j19 = new Jugador("19", "Leandro Rojas", "lean19@mail.com", "leanr19", "pass123", new Futbol(), new Principiante(), "4000");
        Jugador j20 = new Jugador("20", "Martin Herrera", "martin20@mail.com", "martinh20", "pass123", new Futbol(), new Principiante(), "4000");

        // Instanciar jugadores de basquet

        Jugador j21 = new Jugador("21", "Maria Lopez", "maria21@mail.com", "marial21", "pass456", new Basquet(), new Principiante(), "1000");
        Jugador j22 = new Jugador("22", "Valentina Cruz", "vale22@mail.com", "valec22", "pass456", new Basquet(), new Principiante(), "1000");
        Jugador j23 = new Jugador("23", "Camila Ortiz", "camila23@mail.com", "camilao23", "pass456", new Basquet(), new Principiante(), "1000");
        Jugador j24 = new Jugador("24", "Sofia Mendez", "sofia24@mail.com", "sofiam24", "pass456", new Basquet(), new Principiante(), "1000");
        Jugador j25 = new Jugador("25", "Martina Rojas", "martina25@mail.com", "martinar25", "pass456", new Basquet(), new Principiante(),"1000");

        Jugador j26 = new Jugador("26", "Julieta Navarro", "juli26@mail.com", "julian26", "pass456", new Basquet(), new Principiante(), "2000");
        Jugador j27 = new Jugador("27", "Paula Vargas", "paula27@mail.com", "paulav27", "pass456", new Basquet(), new Principiante(), "2000");
        Jugador j28 = new Jugador("28", "Luciana Perez", "luci28@mail.com", "lucianap28", "pass456", new Basquet(), new Principiante(), "2000");
        Jugador j29 = new Jugador("29", "Florencia Diaz", "flor29@mail.com", "flord29", "pass456", new Basquet(), new Principiante(),"2000");
        Jugador j30 = new Jugador("30", "Carla Molina", "carla30@mail.com", "carlam30", "pass456", new Basquet(), new Principiante(), "2000");

        Jugador j31 = new Jugador("31", "Daniela Gomez", "dani31@mail.com", "danig31", "pass456", new Basquet(), new Principiante(), "3000");
        Jugador j32 = new Jugador("32", "Agustina Silva", "agus32@mail.com", "aguss32", "pass456", new Basquet(), new Principiante(), "3000");
        Jugador j33 = new Jugador("33", "Melina Torres", "meli33@mail.com", "melit33", "pass456", new Basquet(), new Principiante(), "3000");
        Jugador j34 = new Jugador("34", "Rocio Luna", "rocio34@mail.com", "rociol34", "pass456", new Basquet(), new Principiante(), "3000");
        Jugador j35 = new Jugador("35", "Antonella Castro", "anto35@mail.com", "antoc35", "pass456", new Basquet(), new Principiante(), "3000");

        Jugador j36 = new Jugador("36", "Brenda Herrera", "brenda36@mail.com", "brendah36", "pass456", new Basquet(), new Principiante(), "4000");
        Jugador j37 = new Jugador("37", "Noelia Ruiz", "noe37@mail.com", "noer37", "pass456", new Basquet(), new Principiante(), "4000");
        Jugador j38 = new Jugador("38", "Cecilia Acosta", "ceci38@mail.com", "cecia38", "pass456", new Basquet(), new Principiante(), "4000");
        Jugador j39 = new Jugador("39", "Karen Molina", "karen39@mail.com", "karenm39", "pass456", new Basquet(), new Principiante(), "4000");
        Jugador j40 = new Jugador("40", "Natalia Vega", "nata40@mail.com", "natav40", "pass456", new Basquet(), new Principiante(), "4000");

        // Instanciar jugadores de tenis
        Jugador j41 = new Jugador("41", "Carlos Gomez", "carlos41@mail.com", "carlosg41", "pass789", new Voley(), new Principiante(), "1000");
        Jugador j42 = new Jugador("42", "Federico Ruiz", "fede42@mail.com", "feder42", "pass789", new Voley(), new Principiante(), "1000");
        Jugador j43 = new Jugador("43", "Ignacio Morales", "ignacio43@mail.com", "ignaciom43", "pass789", new Voley(), new Principiante(), "1000");
        Jugador j44 = new Jugador("44", "Pablo Herrera", "pablo44@mail.com", "pabloh44", "pass789", new Voley(), new Principiante(), "1000");
        Jugador j45 = new Jugador("45", "Martin Acosta", "martin45@mail.com", "martina45", "pass789", new Voley(), new Principiante(), "1000");

        Jugador j46 = new Jugador("46", "Gonzalo Vega", "gonza46@mail.com", "gonzav46", "pass789", new Voley(), new Principiante(), "2000");
        Jugador j47 = new Jugador("47", "Ezequiel Romero", "eze47@mail.com", "ezer47", "pass789", new Voley(), new Principiante(), "2000");
        Jugador j48 = new Jugador("48", "Leandro Suarez", "lean48@mail.com", "leans48", "pass789", new Voley(), new Principiante(), "2000");
        Jugador j49 = new Jugador("49", "Ramiro Luna", "ramiro49@mail.com", "ramirol49", "pass789", new Voley(), new Principiante(), "2000");
        Jugador j50 = new Jugador("50", "Damian Castro", "damian50@mail.com", "damianc50", "pass789", new Voley(), new Principiante(), "2000");

        Jugador j51 = new Jugador("51", "Ivan Torres", "ivan51@mail.com", "ivant51", "pass789", new Voley(), new Principiante(), "3000");
        Jugador j52 = new Jugador("52", "Marcos Diaz", "marcos52@mail.com", "marcosd52", "pass789", new Voley(), new Principiante(), "3000");
        Jugador j53 = new Jugador("53", "Luciano Perez", "luciano53@mail.com", "lucianop53", "pass789", new Voley(), new Principiante(), "3000");
        Jugador j54 = new Jugador("54", "Sergio Molina", "sergio54@mail.com", "sergiom54", "pass789", new Voley(), new Principiante(), "3000");
        Jugador j55 = new Jugador("55", "Andres Rojas", "andres55@mail.com", "andresr55", "pass789", new Voley(), new Principiante(), "3000");

        Jugador j56 = new Jugador("56", "Javier Silva", "javier56@mail.com", "javiers56", "pass789", new Voley(), new Principiante(), "4000");
        Jugador j57 = new Jugador("57", "Matias Vega", "matias57@mail.com", "matiasv57", "pass789", new Voley(), new Principiante(), "4000");
        Jugador j58 = new Jugador("58", "Nicolas Luna", "nico58@mail.com", "nicol58", "pass789", new Voley(), new Principiante(), "4000");
        Jugador j59 = new Jugador("59", "Alan Herrera", "alan59@mail.com", "alanh59", "pass789", new Voley(), new Principiante(), "4000");
        Jugador j60 = new Jugador("60", "Kevin Acosta", "kevin60@mail.com", "kevina60", "pass789", new Voley(), new Principiante(), "4000");

        // Instanciar partidos

        Partido pFutbol1 = new Partido(new Futbol(), new Principiante(), "1000", new Date(), j1);
        Partido pFutbol2 = new Partido(new Futbol(), new Intermedio(), "2000", new Date(), j1);
        Partido pFutbol3 = new Partido(new Futbol(), new Avanzado(), "3000", new Date(), j1);

        Partido pBasquet1 = new Partido(new Basquet(), new Principiante(), "1000", new Date(), j1);
        Partido pBasquet2 = new Partido(new Basquet(), new Intermedio(), "2000", new Date(), j1);
        Partido pBasquet3 = new Partido(new Basquet(), new Avanzado(), "3000", new Date(), j1);

        Partido pVoley1 = new Partido(new Voley(), new Principiante(), "1000", new Date(), j1);
        Partido pVoley2 = new Partido(new Voley(), new Intermedio(), "2000", new Date(), j1);
        Partido pVoley3 = new Partido(new Voley(), new Avanzado(), "3000", new Date(), j1);

        System.out.println("Prueba funcionamiento");
    }
}
