package Controller;

import Model.AbstractDeporte;
import Model.Jugador;

import java.util.ArrayList;
import java.util.List;

public class JugadorController {

    private List<Jugador> jugadores;
    private NotificacionController notificacionController;

    public JugadorController(NotificacionController notificacionController) {
        this.jugadores = new ArrayList<>();
        this.notificacionController = notificacionController;
    }

    public Jugador registrarJugador(String id, String nombre, String mail, String username,
                                    String password, AbstractDeporte deporteFavorito, String codigoPostal) {
        Jugador jugador;
        if (deporteFavorito != null) {
            jugador = new Jugador(id, nombre, mail, username, password, deporteFavorito, codigoPostal);
        } else {
            jugador = new Jugador(id, nombre, mail, username, password, codigoPostal);
        }
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
