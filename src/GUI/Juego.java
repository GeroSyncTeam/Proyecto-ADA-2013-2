/*
 * 6/10/2013
 * Es donde se despliega el juego
 */
package GUI;

import clases.Aeropuerto;
import clases.AlgoritmoDijkstra;
import clases.Avion;
import clases.Sector;
import java.awt.Point;
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
    
    public int minutos;
    public CopyOnWriteArrayList<Aeropuerto> aeropuertosMapa;
    CopyOnWriteArrayList<Avion> aviones;
    CopyOnWriteArrayList<Point> nodosAviones;
    Image barraIzquierda, marco;
    Sector mapa[];
    AlgoritmoDijkstra grafo;
    Avion avion;
//    Animation a1;

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
        // inicializa los sectores-------------------
        mapa = new Sector[225];
        int x;
        int y = 60;
        int id = 0;
        for (int i = 0; i < 15; i++) {
            x = 300;
            for (int j = 0; j < 15; j++) {
                mapa[id] = new Sector(id, x, y);
                
                id++;
                x += 30;
            }
            
            y += 30;
        }
        //-------------------------------------------
        
        aviones = new CopyOnWriteArrayList<Avion>();
        nodosAviones = new CopyOnWriteArrayList<Point>();
        grafo = new AlgoritmoDijkstra();
        
        barraIzquierda = new Image("recursos/fondos/barra izquierda2.jpg");
        avion = new Avion(3,200,90, 30,30);
        aviones.add(avion);
//        a1 = new Animation(avion.rutasSpriteSheet, 180);
//        a1.setPingPong(true);
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
        int x1 = 300, y1 = 60, aumento = 0;
        for (int i = 0; i <= 15; i++) {
            g.drawLine(x1 + aumento, y1, x1 + aumento, 510);
            g.drawLine(x1, y1 + aumento, 750, y1 + aumento);
            aumento += 30;
        }
        //------ Pinta aeropuertos del mapa 
        for (int i = 0; i < aeropuertosMapa.size(); i++) {
            int x = aeropuertosMapa.get(i).x;
            int y = aeropuertosMapa.get(i).y;
            aeropuertosMapa.get(i).getImagen().draw(x, y);
        }
        //----------------------------------
        g.drawImage(avion.rutasSpriteSheet[5], avion.x, avion.y);

//        marco.draw(250, 0);
    }

    /**
     *
     * @param container
     * @param game
     * @param delta
     * @throws SlickException
     *
     * Registra los cambios de valores en el juego y los actualiza
     */
    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        if (container.getInput().isKeyPressed(Input.KEY_RIGHT)) {
            avion.x += 4;
            System.out.println(" x "+avion.x);
        }
        if (container.getInput().isKeyPressed(Input.KEY_DOWN)) {
            avion.y += 4;
            System.out.println(" y "+avion.y);
        }
        if (container.getInput().isKeyPressed(Input.KEY_UP)) {
            avion.y -= 4;
            System.out.println(" y "+avion.y);
        }
        if (container.getInput().isKeyPressed(Input.KEY_LEFT)) {
            avion.x -= 4;
            System.out.println(" x "+avion.x);
        }
        if (container.getInput().isKeyPressed(Input.KEY_ENTER)) {
            
            for (int i = 0; i < 225; i++) {   
                
                    if(i%15==0 ){
                        System.out.println("");

                    }    
                    System.out.print("  "+grafo.tiposDeSector[i]);
            }
            System.out.println("");
        }
        //Reasignar posición a un avion en el mapa -----
        for (int i = 0; i < aviones.size(); i++) {
            buscarAviones(mapa, aviones.get(i), minutos);
        }
        grafo.reiniciarTiposSector();
        for (int j = 0; j < nodosAviones.size(); j++) {
            int id = nodosAviones.get(j).y;
            int posicion=nodosAviones.get(j).x;
            grafo.tiposDeSector[posicion] = id;
            
            
        }
        grafo.enlazarGrafo();
        //llamo el metode la la clase AlgoritmoDijkstra que actualiza los enlaces
        //----------------------------------------------

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

    /**
     *
     * @param sectores
     * @param avion
     * @param encontrados
     *
     * Compara las posiciones de los aviones con las posiciones de casa sector
     * del mapa con el fin de encontrar en que posicion esxacta se encuentra el
     * avion y las guarda en un objeto de tipo Point
     * x= posición del avión en el mapa
     * y= id del avión
     */
    public void buscarAviones(Sector[] sectores, Avion avion, int encontrados) {
        nodosAviones.clear();
        int inicio = 0;
        int fin = sectores.length - 1;
        int medio = (inicio + fin) / 2;
        
        while (medio < sectores.length && encontrados < 4) {
            if (inicio < (fin / 2) && sectores[inicio].intersects(avion)) {
                encontrados++;
                nodosAviones.add(new Point(sectores[inicio].getId(),avion.tipo));
            }
            if (sectores[medio].intersects(avion)) {
                encontrados++;
                nodosAviones.add(new Point(sectores[medio].getId(),avion.tipo));
            }
            inicio++;
            medio++;
        }
    }
}
