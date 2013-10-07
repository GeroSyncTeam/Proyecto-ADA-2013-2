/*
 * 6/10/2013
 * Muestra las opciones play y menú, es la primer pantalla que muestra el juego
 */
package GUI;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author @version 1.0
 */
public class Inicio extends BasicGameState {

    /**
     * 
     * @return 0 es el id que la identifica
     */
    @Override
    public int getID() {
        return 0;
    }
 /**
     * 
     * @param container
     * @param sbg
     * @throws SlickException 
     * 
     * Se cargan todos los recursos que se usaran
     */
    @Override
    public void init(GameContainer container, StateBasedGame sbg) throws SlickException {
    }

    /**
     * 
     * @param container
     * @param game
     * @param g
     * @throws SlickException 
     * 
     * Se encarga de realizar el dibujado de los graficos
     */
    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        container.getGraphics().drawString("inicio", 100, 167);
    }
/**
 * 
 * @param container
 * @param game
 * @param i
 * @throws SlickException 
 * 
 * Registra los cambios de valores en el juego y los actualiza
 */
    @Override
    public void update(GameContainer container, StateBasedGame game, int i) throws SlickException {
        if(container.getInput().isKeyPressed(Input.KEY_ENTER)){
            game.enterState(1);
        }
    }
}
