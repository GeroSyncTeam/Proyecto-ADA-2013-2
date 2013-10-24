/*
 * 6/10/2013
 * Es donde se despliega el juego
 */
package GUI;

import clases.Aeropuerto;
import clases.Avion;
import clases.Sector;
import java.util.concurrent.CopyOnWriteArrayList;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Gero
 * @version 1.0
 */
public class Juego extends BasicGameState {
int x=280, y=80;
    public int minutos;
    public CopyOnWriteArrayList<Aeropuerto> aeropuertosMapa;
    Image barraIzquierda, marco;
    Sector mapa[][];
    Avion avion;
    Animation a1;

    public Juego() {
        minutos = 2;
        aeropuertosMapa = new CopyOnWriteArrayList<Aeropuerto>();
    }

    /**
     *
     * @return 2 es el id que lo identifica
     */
    @Override
    public int getID() {
        return 2;
    }

    /**
     *
     * @param container
     * @param sbg
     * @throws SlickException
     *
     * Se cargan todos los recursos que se usaran
     */
    public void init2(GameContainer container, StateBasedGame sbg) throws SlickException {
        mapa = new Sector[15][15];
        int x;
        int y = 60;
        int id = 0;
        for (int i = 0; i < mapa.length; i++) {
            x = 300;
            for (int j = 0; j < mapa.length; j++) {
                mapa[i][j] = new Sector(id, x, y);
                id++;
                x += 30;
            }
            y += 30;
        }

        barraIzquierda = new Image("recursos/fondos/barra izquierda2.jpg");
        avion = new Avion(2, 280, 70, 30, 30);
        a1 = new Animation(avion.rutasSpriteSheet, 180);
        a1.setPingPong(true);
//        marco = new Image("recursos/fondos/marco mapa.png");
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
        barraIzquierda.draw(0, 0, 250, 600);
        //------ Pinta aeropuertos del mapa 
        for (int i = 0; i < aeropuertosMapa.size(); i++) {
            int x = aeropuertosMapa.get(i).x;
            int y = aeropuertosMapa.get(i).y;
            aeropuertosMapa.get(i).getImagen().draw(x, y);
        }
        a1.draw(x, y);
        //----------------------------------
//        marco.draw(250, 0);
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
        System.out.println(""+i);
            
            x++;
        
        
    }

    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        init2(container, game);
    }

    @Override
    public void leave(GameContainer container, StateBasedGame game) throws SlickException {
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
    }
}
