/*
 * Muestra los creditos del programa
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
public class Creditos extends BasicGameState {

    Rectangle rAtras;
    Image fondo, atras1, atras2;
    boolean dibujarSobreAtras;
    int XATRAS = 10;
    int YATRAS = 540;

    /**
     *
     * @return 3 es el id que lo identifica
     */
    @Override
    public int getID() {
        return 1;
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
        cargarAtrasRender(container);
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
