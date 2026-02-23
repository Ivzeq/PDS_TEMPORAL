package Model;

public class EnJuego implements IEstadoPartido {

    @Override
    public void avanzarEstado(Partido partido) {
        partido.setEstado(new Finalizado());
        for (Jugador jugador : partido.getJugadores()) {
            jugador.addPartidoCompletado(partido);
        }
    }

    @Override
    public void cancelarPartido(Partido partido) {
        partido.setEstado(new Cancelado());
    }

    @Override
    public String toString() {
        return "En Juego";
    }
}
