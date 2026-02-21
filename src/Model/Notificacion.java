package Model;

public class Notificacion {

    private String mensaje;
    private Jugador destinatario;

    public Notificacion(String mensaje, Jugador destinatario) {
        this.mensaje = mensaje;
        this.destinatario = destinatario;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Jugador getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Jugador destinatario) {
        this.destinatario = destinatario;
    }

    @Override
    public String toString() {
        return "Notificacion{" +
                "destinatario=" + destinatario.getUsername() +
                ", mensaje='" + mensaje + '\'' +
                '}';
    }
}
