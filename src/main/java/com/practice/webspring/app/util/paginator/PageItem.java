package com.practice.webspring.app.util.paginator;

public class PageItem {

    private int numero;
    private boolean actual;

    public PageItem(int numero, boolean actual){
        this.numero = numero;
        this.actual = actual;
    }

/*   Getters and Setters  ******************************************************************************************/

    public int getNumero() {
        return numero;
    }

    public boolean isActual() {
        return actual;
    }
}
