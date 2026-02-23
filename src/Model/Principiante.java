package Model;

public class Principiante implements IEstadoNivelDeporte {

    private int maxPartidos;

    public Principiante() {
        this.maxPartidos = 10;
    }

    @Override
    public void subirNivelJugador(Jugador jugador) {
        jugador.setNivelDeporteFavorito(new Avanzado());
    }

    @Override
    public void bajarNivelJugador(Jugador jugador) {
        System.out.println("Jugador en nivel minimo");
    }

    @Override
    public void subirNivelPartido(Partido partido) {
        partido.setNivelPartido(new Avanzado());
    }

    @Override
    public void bajarNivelPartido(Partido partido) {
        System.out.println("Partido en nivel minimo");
    }

    public int getMaxPartidos() {
        return maxPartidos;
    }

    public void setMaxPartidos(int maxPartidos) {
        this.maxPartidos = maxPartidos;
    }
}
