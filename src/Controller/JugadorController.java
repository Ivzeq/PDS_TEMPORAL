package Controller;

import Model.AbstractDeporte;
import Model.IStrategyNotificador;
import Model.Jugador;
import Model.Notificador;

import java.util.ArrayList;
import java.util.List;

public class JugadorController {

    private List<Jugador> jugadores;

    public JugadorController() {
        this.jugadores = new ArrayList<>();
    }

    public Jugador registrarJugador(String nombre, String mail, String username,
                                    String password, AbstractDeporte deporteFavorito, String codigoPostal) {
        Jugador jugador = new Jugador(nombre, mail, username, password, deporteFavorito, codigoPostal);
        jugadores.add(jugador);
        return jugador;
    }

    public void modificarMail(Jugador jugador, String mail) {
        jugador.modificarMail(mail);
    }

    public void modificarPassword(Jugador jugador, String password) {
        jugador.modificarPassword(password);
    }

    public void setDeporteFavoritoJugador(Jugador jugador, AbstractDeporte deporte) {
        jugador.setDeporteFavorito(deporte);
    }

    public void setNotificadorJugador(Jugador jugador, IStrategyNotificador strategy) {
        jugador.setNotificador(new Notificador(strategy));
    }

    public void subirNivel(Jugador jugador) {
        jugador.getNivelDeporteFavorito().subirNivel();
    }

    public void bajarNivel(Jugador jugador) {
        jugador.getNivelDeporteFavorito().bajarNivel();
    }

    public Jugador buscarJugadorPorUsername(String username) {
        for (Jugador jugador : jugadores) {
            if (jugador.getUsername().equals(username)) {
                return jugador;
            }
        }
        return null;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }
}
