package Model;

public class CorreoElectronico implements IStrategyNotificador {

    private IAdapterJavaMail adapterJavaMail;

    public CorreoElectronico(IAdapterJavaMail adapterJavaMail) {
        this.adapterJavaMail = adapterJavaMail;
    }

    @Override
    public void enviarNotificacion(Notificacion notificacion) {
        adapterJavaMail.enviarNotificacion(notificacion);
    }
}
