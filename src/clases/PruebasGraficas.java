/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Gero
 */
public class PruebasGraficas extends BasicGame {

    static Image im;
    static Helicoptero s;
    AppGameContainer formulario;

    public PruebasGraficas() {
        super("Pruebas");
        s = new Helicoptero(100, 100, 100, 100);
        try {
            formulario = new AppGameContainer(this);
            formulario.setDisplayMode(500, 500, false);
            formulario.setShowFPS(false);
//            formulario.setIcon("recursos/icono.jpg");
            formulario.start();
        } catch (SlickException ex) {
        }
    }

    public static void main(String args[]) {

//        new PruebasGraficas();

        AlgoritmoDijkstra mapa = new AlgoritmoDijkstra();
        System.out.println(mapa.encontrarRutaMinimaDijkstra(5, 30));
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        try {
            im = new Image(s.rutasSpriteSheet[9]);
        } catch (SlickException ex) {
            Logger.getLogger(PruebasGraficas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException {
    }

    @Override
    public void render(GameContainer gc, Graphics grphcs) throws SlickException {
        im.draw(s.x, s.y);
    }
}
