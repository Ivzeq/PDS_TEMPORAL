package service;

import Model.Jugador;
import Repository.IJugadorRepository;

public class JugadorAuthService {

    public Jugador autenticarJugador(IJugadorRepository repo, String username, String password) {
        if (username == null || password == null) return null;
        Jugador j = repo.findByUsername(username);
        if (j != null && password.equals(j.getPassword())) return j;
        return null;
    }
}
