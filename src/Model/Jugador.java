package Model;

public class Jugador {

    private String id;
    private String nombre;
    private String mail;
    private String username;
    private String password;
    private AbstractDeporte deporteFavorito;
    private AbstractNivelDeporte nivelDeporteFavorito;
    private String codigoPostal;
    private int nPartidos;

    public Jugador(String id, String nombre, String mail, String username, String password,
                   AbstractDeporte deporteFavorito, String codigoPostal) {
        this.id = id;
        this.nombre = nombre;
        this.mail = mail;
        this.username = username;
        this.password = password;
        this.deporteFavorito = deporteFavorito;
        this.codigoPostal = codigoPostal;
        this.nPartidos = 0;
        this.nivelDeporteFavorito = new Principiante(this);
    }

    public Jugador(String id, String nombre, String mail, String username, String password,
                   String codigoPostal) {
        this(id, nombre, mail, username, password, null, codigoPostal);
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

    public AbstractNivelDeporte getNivelDeporteFavorito() {
        return nivelDeporteFavorito;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public int getNPartidos() {
        return nPartidos;
    }

    // Setters

    public void setDeporteFavorito(AbstractDeporte deporteFavorito) {
        this.deporteFavorito = deporteFavorito;
    }

    public void setNivelDeporteFavorito(AbstractNivelDeporte nivelDeporteFavorito) {
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
