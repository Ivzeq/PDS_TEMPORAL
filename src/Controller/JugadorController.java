package Controller;

import Model.AbstractDeporte;
import Model.IStrategyNotificador;
import Model.Jugador;
import Model.Notificador;
import Repository.InMemoryJugadorRepository;
import Repository.IJugadorRepository;
import service.JugadorAuthService;

import java.util.List;

public class JugadorController {

    private IJugadorRepository jugadorRepository;
    private JugadorAuthService authService;

    public JugadorController() {
        this(new InMemoryJugadorRepository());
    }

    public JugadorController(IJugadorRepository repository) {
        this.jugadorRepository = repository;
        this.authService = new JugadorAuthService();
    }

    public Jugador registrarJugador(String nombre, String mail, String username,
                                    String password, AbstractDeporte deporteFavorito, String codigoPostal) {
        Jugador jugador = new Jugador(nombre, mail, username, password, deporteFavorito, codigoPostal);
        jugadorRepository.save(jugador);
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
        return jugadorRepository.findByUsername(username);
    }

    public List<Jugador> getJugadores() {
        return jugadorRepository.findAll();
    }

    public Jugador autenticarJugador(String username, String password) {
        return authService.autenticarJugador(jugadorRepository, username, password);
    }
}
