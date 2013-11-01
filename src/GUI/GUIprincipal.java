/*
 * 6/10/2013
 * GUIprincipal es la clase encargada de mostrar la interfaz grafica del juego
 * 
 */
package GUI;

import org.lwjgl.LWJGLException;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Gero
 * @version 1.0
 */
public class GUIprincipal extends StateBasedGame {

    AppGameContainer ventana;
    Juego juego;

    /**
     *
     * @throws SlickException Parametriza el AppGameContainery asigna ancho,
     * alto e icono de la ventana
     */
    public GUIprincipal() throws SlickException, LWJGLException {
        super("El aviador");
        juego = new Juego();
        ventana = new AppGameContainer(this, 800, 600, false);
        ventana.setIcon("recursos/aeroplano/7.png");
        ventana.setShowFPS(false);
        ventana.start();
    }

    /**
     *
     * @param gc GameContainer del juego
     * @throws SlickException
     *
     * Adiere los estados que representan cada pantalla del juego
     */
    @Override
    public void initStatesList(GameContainer gc) throws SlickException {

        addState(new Inicio());
        addState(juego);
        addState(new Creditos());
        addState(new Configuracion(juego));
    }

    public static void main(String args[]) throws SlickException, LWJGLException {
        new GUIprincipal();
    }
}
