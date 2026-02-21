package Model;

import java.io.*;
import java.util.*;
import IStrategyNotiifcador ;

/**
 * 
 */
public class Notificador implements IObserverPartido {

    /**
     * Default constructor
     */
    public Notificador() {
    }

    /**
     * 
     */
    private IStrategyNotificador strategy;







    /**
     * @param notificacion
     * @return
     */
    public void enviarNotificacion(Notificacion notificacion) {
        // TODO implement here
    }

    /**
     * @param strategy
     * @return
     */
    public void cambiarStrategy(IStrategyNotificador strategy) {
        // TODO implement here
    }

    /**
     * @param partido
     * @return
     */
    public void update(Partido partido) {
        // TODO implement IObserverPartido.update() here
    }

}