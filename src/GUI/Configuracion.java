/*
 * 6/10/2013
 * Se configuran el mapa, el tiempo entre oleada de aviones y el tipo de 
 * aeropuertos
 */
package GUI;

import java.awt.Rectangle;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Gero
 */
public class Configuracion extends BasicGameState {
Rectangle rAtras;
    Image fondo, atras1, atras2;
    boolean dibujarSobreAtras;
    int XATRAS = 10;
    int YATRAS = 540;
    
    /**
     *
     * @return 4 es el id que lo identifica
     */
    @Override
    public int getID() {
        return 3;
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
   atras1 = new Image("recursos/fondos/atras1.png");
        atras2 = new Image("recursos/fondos/atras2.png");
        rAtras = new Rectangle(XATRAS, YATRAS, 190, 50);
        dibujarSobreAtras = false;
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
        container.getGraphics().drawString("configuración", 100, 167);
   cargarAtrasRender(container);
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
  cargarAtrasUpdate(container, game);
    }
    
     private void cargarAtrasUpdate(GameContainer container, StateBasedGame game) {
        int x = container.getInput().getMouseX();
        int y = container.getInput().getMouseY();
        Rectangle pulsacion = new Rectangle(x, y, 2, 2);

        if (pulsacion.intersects(rAtras)) {
            dibujarSobreAtras = true;
            if (container.getInput().isMousePressed(container.getInput().MOUSE_LEFT_BUTTON)) {
                game.enterState(0);
            }
        } else {
            dibujarSobreAtras = false;
        }
    }

    private void cargarAtrasRender(GameContainer container) {
        container.getGraphics().drawString("creditos", 100, 167);
        if (dibujarSobreAtras) {
            atras2.draw(XATRAS, YATRAS);
        } else {
            atras1.draw(XATRAS, YATRAS);
        }
    }
}
