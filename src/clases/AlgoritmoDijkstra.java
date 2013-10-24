/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.Vector;

/**
 *
 * @author Gero
 */
public class AlgoritmoDijkstra {

    public int tiposDeSector[];
    int[] nodos;  // Letras de identificación de nodo
    int[][] grafo;  // Matriz de distancias entre nodos
    String rutaMasCorta;                           // distancia más corta
    int longitudMasCorta = Integer.MAX_VALUE;   // ruta más corta
    List<Nodo> listos = null;                        // nodos revisados Dijkstra

    // construye el grafo con la serie de identificadores de nodo en una cadena
    public AlgoritmoDijkstra(int[] nodos) {
        this.nodos = nodos;
        grafo = new int[this.nodos.length][this.nodos.length];
        enlazarGrafo();
    }

    public AlgoritmoDijkstra(int[] nodos, int[] tiposDeSector) {
        this.nodos = nodos;
        grafo = new int[this.nodos.length][this.nodos.length];
        this.tiposDeSector = tiposDeSector;
        enlazarGrafo();
    }
//tiposDeSector[i + 1] 

    private void enlazarGrafo() {

        for (int nodo = 0; nodo < 225; nodo++) {
            //agrega nodo a la izquierda
            try {
                if(tiposDeSector[nodo]!= 0 ){
                    //aca va la logica para reasignar valores de distancia
                }
                agregarRuta(nodo, nodo - 1, 1);
                agregarRuta(nodo - 1, nodo, 1);
                
            } catch (Exception e) {
            }
            //agrega nodo a la derecha
            try {
                agregarRuta(nodo, nodo + 1, 1);
                agregarRuta(nodo + 1, nodo, 1);
            } catch (Exception e) {
            }
            //agrega nodo arriba
            try {
                agregarRuta(nodo, nodo - 15, 1);
                agregarRuta(nodo - 15, nodo, 1);
            } catch (Exception e) {
            }
            //agrega nodo abajo
            try {
                agregarRuta(nodo, nodo + 15, 1);
                agregarRuta(nodo + 15, nodo, 1);
            } catch (Exception e) {
            }
            //agrega nodo izquierda-arriba
            try {
                agregarRuta(nodo, nodo - 16, 1);
                agregarRuta(nodo - 16, nodo, 1);
            } catch (Exception e) {
            }
            //agrega nodo derecha-arriba
            try {
                agregarRuta(nodo, nodo - 14, 1);
                agregarRuta(nodo - 14, nodo, 1);
            } catch (Exception e) {
            }
            //agrega nodo izquierda-abajo
            try {
                agregarRuta(nodo, nodo + 14, 1);
                agregarRuta(nodo + 14, nodo, 1);
            } catch (Exception e) {
            }
            //agrega nodo derecha-abajo
            try {
                agregarRuta(nodo, nodo + 16, 1);
                agregarRuta(nodo + 16, nodo, 1);
            } catch (Exception e) {
            }
        }
    }
/**
 * 
 * @param nodo 
 * Modifica el valor de los caminos que se unen con el parametro nodo
 *  
 */
    public void modificarDistancias(int nodo) {
        
        //agrega nodo a la izquierda
        try {
//            agregarRuta(nodo, nodo - 1, 9);
            agregarRuta(nodo - 1, nodo, Integer.MAX_VALUE);
        } catch (Exception e) {
        }
        //agrega nodo a la derecha
        try {
//            agregarRuta(nodo, nodo + 1, 9);
            agregarRuta(nodo + 1, nodo, Integer.MAX_VALUE);
        } catch (Exception e) {
        }
        //agrega nodo arriba
        try {
//            agregarRuta(nodo, nodo - 15, 9);
            agregarRuta(nodo - 15, nodo, Integer.MAX_VALUE);
        } catch (Exception e) {
        }
        //agrega nodo abajo
        try {
//            agregarRuta(nodo, nodo + 15, 9);
            agregarRuta(nodo + 15, nodo, Integer.MAX_VALUE);
        } catch (Exception e) {
        }
        //agrega nodo izquierda-arriba
        try {
//            agregarRuta(nodo, nodo - 16, 9);
            agregarRuta(nodo - 16, nodo, Integer.MAX_VALUE);
        } catch (Exception e) {
        }
        //agrega nodo derecha-arriba
        try {
//            agregarRuta(nodo, nodo - 14, 9);
            agregarRuta(nodo - 14, nodo, Integer.MAX_VALUE);
        } catch (Exception e) {
        }
        //agrega nodo izquierda-abajo
        try {
//            agregarRuta(nodo, nodo + 14, 9);
            agregarRuta(nodo + 14, nodo, Integer.MAX_VALUE);
        } catch (Exception e) {
        }
        //agrega nodo derecha-abajo
        try {
//            agregarRuta(nodo, nodo + 16, 9);
            agregarRuta(nodo + 16, nodo, Integer.MAX_VALUE);
        } catch (Exception e) {
        }

    }
    // asigna el tamaño de la arista entre dos nodos

    public void agregarRuta(int origen, int destino, int distancia) {
        grafo[origen][destino] = distancia;
        grafo[destino][origen] = distancia;
    }

    // retorna la posición en el arreglo de un nodo específico
    private int posicionNodo(int nodo) {
        for (int i = 0; i < nodos.length; i++) {
            if (nodos[i] == nodo) {
                return i;
            }
        }
        return -1;
    }

    // encuentra la ruta más corta desde un nodo origen a un nodo destino
    public String encontrarRutaMinimaDijkstra(int inicio, int fin) {
        // calcula la ruta más corta del inicio a los demás
        encontrarRutaMinimaDijkstra(inicio);
        // recupera el nodo final de la lista de terminados
        Nodo tmp = new Nodo(fin);
        if (!listos.contains(tmp)) {
            System.out.println("Error, nodo no alcanzable");
            return "Bye";
        }
        tmp = listos.get(listos.indexOf(tmp));
        // crea una pila para almacenar la ruta desde el nodo final al origen
        Stack<Nodo> pila = new Stack<Nodo>();
        while (tmp != null) {
            pila.add(tmp);
            tmp = tmp.procedencia;
        }
        String ruta = "";
        // recorre la pila para armar la ruta en el orden correcto
        while (!pila.isEmpty()) {
            ruta += (pila.pop().id + " ");
        }
        //distancia + ": " +
        return ruta;
    }

    // encuentra la ruta más corta desde el nodo inicial a todos los demás
    public void encontrarRutaMinimaDijkstra(int inicio) {
        Queue<Nodo> cola = new PriorityQueue<Nodo>(); // cola de prioridad
        Nodo ni = new Nodo(inicio);          // nodo inicial

        listos = new LinkedList<Nodo>();// lista de nodos ya revisados
        cola.add(ni);                   // Agregar nodo inicial a la cola de prioridad
        while (!cola.isEmpty()) {        // mientras que la cola no esta vacia
            Nodo tmp = cola.poll();     // saca el primer elemento
            listos.add(tmp);            // lo manda a la lista de terminados
            int p = posicionNodo(tmp.id);
            for (int j = 0; j < grafo[p].length; j++) {  // revisa los nodos hijos del nodo tmp
                if (grafo[p][j] == 0) {
                    continue;        // si no hay conexión no lo evalua
                }
                if (estaTerminado(j)) {
                    continue;      // si ya fue agregado a la lista de terminados
                }
                Nodo nod = new Nodo(nodos[j], tmp.distancia + grafo[p][j], tmp);
                // si no está en la cola de prioridad, lo agrega
                if (!cola.contains(nod)) {
                    cola.add(nod);
                    continue;
                }
                // si ya está en la cola de prioridad actualiza la distancia menor
                for (Nodo x : cola) {
                    // si la distancia en la cola es mayor que la distancia calculada
                    if (x.id == nod.id && x.distancia > nod.distancia) {
                        cola.remove(x); // remueve el nodo de la cola
                        cola.add(nod);  // agrega el nodo con la nueva distancia
                        break;          // no sigue revisando
                    }
                }
            }
        }
    }

    // verifica si un nodo ya está en lista de terminados
    public boolean estaTerminado(int j) {
        Nodo tmp = new Nodo(nodos[j]);
        return listos.contains(tmp);
    }

    // encontrar la ruta mínima por fuerza bruta
    public void encontrarRutaMinimaFuerzaBruta(int inicio, int fin) {
        int p1 = posicionNodo(inicio);
        int p2 = posicionNodo(fin);
        // cola para almacenar cada ruta que está siendo evaluada
        Stack<Integer> resultado = new Stack<Integer>();
        resultado.push(p1);
        recorrerRutas(p1, p2, resultado);
    }

    // recorre recursivamente las rutas entre un nodo inicial y un nodo final
    // almacenando en una cola cada nodo visitado
    private void recorrerRutas(int nodoI, int nodoF, Stack<Integer> resultado) {
        // si el nodo inicial es igual al final se evalúa la ruta en revisión
        if (nodoI == nodoF) {
            int respuesta = evaluar(resultado);
            if (respuesta < longitudMasCorta) {
                longitudMasCorta = respuesta;
                rutaMasCorta = "";
                for (int x : resultado) {
                    rutaMasCorta += (nodos[x] + " ");
                }
            }
            return;
        }
        // Si el nodoInicial no es igual al final se crea una lista con todos los nodos
        // adyacentes al nodo inicial que no estén en la ruta en evaluación
        List<Integer> lista = new Vector<Integer>();
        for (int i = 0; i < grafo.length; i++) {
            if (grafo[nodoI][i] != 0 && !resultado.contains(i)) {
                lista.add(i);
            }
        }
        // se recorren todas las rutas formadas con los nodos adyacentes al inicial
        for (int nodo : lista) {
            resultado.push(nodo);
            recorrerRutas(nodo, nodoF, resultado);
            resultado.pop();
        }
    }

    // evaluar la longitud de una ruta
    public int evaluar(Stack<Integer> resultado) {
        int resp = 0;
        int[] r = new int[resultado.size()];
        int i = 0;
        for (int x : resultado) {
            r[i++] = x;
        }
        for (i = 1; i < r.length; i++) {
            resp += grafo[r[i]][r[i - 1]];
        }
        return resp;
    }

    public void agregarRutas2() {
    }
}
