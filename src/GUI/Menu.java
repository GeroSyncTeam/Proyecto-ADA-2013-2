/*
 * 6/10/2013
 * La clase Menu es el estado en el cual se muestra la GUI del menú
 */
package GUI;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Gero
 * @version 1.0
 */
public class Menu extends BasicGameState {

    /**
     * 
     * @return 1 es el id que lo identifica
     */
    @Override
    public int getID() {
        return 1;
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
        container.getGraphics().drawString("menú", 100, 167);
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
    }
}
