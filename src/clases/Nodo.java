/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

/**
 *
 * @author Gero
 */
public class Nodo implements Comparable<Nodo> {

    public int id;
    int distancia = Integer.MAX_VALUE;
    Nodo procedencia = null;

    public Nodo() {
    }

    Nodo(int x, int d, Nodo p) {
        id = x;
        distancia = d;
        procedencia = p;
    }

    Nodo(int x) {
        this(x, 0, null);
    }

    @Override
    public int compareTo(Nodo tmp) {
        return this.distancia - tmp.distancia;
    }

    public boolean equals(Object o) {
        Nodo tmp = (Nodo) o;
        if (tmp.id == this.id) {
            return true;
        }
        return false;
    }
}
