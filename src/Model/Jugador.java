package Model;

import java.util.ArrayList;
import java.util.List;

public class Jugador {

    private String id;
    private String nombre;
    private String mail;
    private String username;
    private String password;
    private AbstractDeporte deporteFavorito;
    private IEstadoNivelDeporte nivelDeporteFavorito;
    private String codigoPostal;
    private int nPartidos;
    private List<Partido> partidosCompletados;

    public Jugador(String id, String nombre, String mail, String username, String password,
                   AbstractDeporte deporteFavorito, IEstadoNivelDeporte nivelDeporte, String codigoPostal) {
        this.id = id;
        this.nombre = nombre;
        this.mail = mail;
        this.username = username;
        this.password = password;
        this.deporteFavorito = deporteFavorito;
        this.codigoPostal = codigoPostal;
        this.nPartidos = 0;
        this.nivelDeporteFavorito = nivelDeporte;
        this.partidosCompletados = new ArrayList<>();
    }

    public Jugador(String id, String nombre, String mail, String username, String password,
                   String codigoPostal) {
        this(id, nombre, mail, username, password, null, null,  codigoPostal);
    }

    public void modificarMail(String mail) {
        this.mail = mail;
    }

    public void modificarPassword(String password) {
        this.password = password;
    }

    public void confirmarPartido() {
        this.nPartidos++;
    }

    public void addPartidoCompletado(Partido partido) {
        partidosCompletados.add(partido);
    }

    public void subirNivel() {
        nivelDeporteFavorito.subirNivelJugador(this);
    }

    public void bajarNivel() {
        nivelDeporteFavorito.bajarNivelJugador(this);
    }

    // Getters

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getMail() {
        return mail;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public AbstractDeporte getDeporteFavorito() {
        return deporteFavorito;
    }

    public IEstadoNivelDeporte getNivelDeporteFavorito() {
        return nivelDeporteFavorito;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public int getNPartidos() {
        return nPartidos;
    }

    public List<Partido> getPartidosCompletados() {
        return partidosCompletados;
    }

    // Setters

    public void setDeporteFavorito(AbstractDeporte deporteFavorito) {
        this.deporteFavorito = deporteFavorito;
    }

    public void setNivelDeporteFavorito(IEstadoNivelDeporte nivelDeporteFavorito) {
        this.nivelDeporteFavorito = nivelDeporteFavorito;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "username='" + username + '\'' +
                ", nombre='" + nombre + '\'' +
                ", deporteFavorito=" + (deporteFavorito != null ? deporteFavorito : "N/A") +
                ", nivel=" + nivelDeporteFavorito +
                ", nPartidos=" + nPartidos +
                '}';
    }
}
