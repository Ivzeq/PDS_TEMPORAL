package Model;

public class Firebase implements IAdapterFirebase {

    @Override
    public void enviarNotificacion(Notificacion notificacion) {
        System.out.println("[Firebase Push] Para: " + notificacion.getDestinatario().getUsername()
                + " | Mensaje: " + notificacion.getMensaje());
    }
}
