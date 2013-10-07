/*
 * Muestra los creditos del programa
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
 */
public class Creditos extends BasicGameState{

      /**
     * 
     * @return 3 es el id que lo identifica
     */
    @Override
    public int getID() {
        return 3;
    }

    /**
     * 
     * @param gc
     * @param sbg
     * @throws SlickException 
     * 
     * Se cargan todos los recursos que se usaran
     */
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
    }

    /**
     * 
     * @param gc
     * @param game
     * @param g
     * @throws SlickException 
     * 
     * Se encarga de realizar el dibujado de los graficos
     */
    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
        gc.getGraphics().drawString("creditos", 100, 167);
    }
/**
 * 
 * @param gc
 * @param game
 * @param i
 * @throws SlickException 
 * 
 * Registra los cambios de valores en el juego y los actualiza
 */
    @Override
    public void update(GameContainer gc, StateBasedGame game, int i) throws SlickException {
    }
    
}
