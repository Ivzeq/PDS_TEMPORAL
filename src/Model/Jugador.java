package Model;

public class Jugador {

    private static int nextId = 1;

    private String id;
    private String nombre;
    private String mail;
    private String username;
    private String password;
    private AbstractDeporte deporteFavorito;
    private AbstractNivelDeporte nivelDeporteFavorito;
    private String codigoPostal;
    private int nPartidos;
    private Notificador notificador;

    public Jugador(String nombre, String mail, String username, String password,
            AbstractDeporte deporteFavorito, String codigoPostal) {
        this.id = String.valueOf(nextId++);
        this.nombre = nombre;
        this.mail = mail;
        this.username = username;
        this.password = password;
        this.deporteFavorito = deporteFavorito;
        this.codigoPostal = codigoPostal;
        this.nPartidos = 0;
        this.nivelDeporteFavorito = new Principiante(this);
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

    public Notificador getNotificador() {
        return notificador;
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

    public void setNotificador(Notificador notificador) {
        this.notificador = notificador;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", nombre='" + nombre + '\'' +
                ", deporteFavorito=" + deporteFavorito +
                ", nivel=" + nivelDeporteFavorito +
                ", nPartidos=" + nPartidos +
                ", notificacion=" + (notificador != null ? notificador.getStrategy().getClass().getSimpleName() : "N/A")
                +
                '}';
    }
}
