package Model;

public class Push implements IStrategyNotificador {

    private IAdapterFirebase adapterFirebase;

    public Push(IAdapterFirebase adapterFirebase) {
        this.adapterFirebase = adapterFirebase;
    }

    @Override
    public void enviarNotificacion(Notificacion notificacion) {
        adapterFirebase.enviarNotificacion(notificacion);
    }
}
