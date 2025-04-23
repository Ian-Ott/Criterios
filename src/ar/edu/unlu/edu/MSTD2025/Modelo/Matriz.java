package ar.edu.unlu.edu.MSTD2025.Modelo;

import java.util.ArrayList;

public class Matriz {
    private String[] NombreColumnas;
    private String[] NombreFilas;
    private ArrayList<Double> lista = new ArrayList<>();
    private int CantColumnas;
    private int CantFilas;
    private ArrayList<Double> listaProb = new ArrayList<>();

    public Matriz(int CantColumnas,int CantFilas) {
        NombreColumnas = new String[CantColumnas];
        NombreFilas = new String[CantFilas];
        this.CantColumnas = CantColumnas;
        this.CantFilas = CantFilas;
    }

    public void setLista(ArrayList<Double> lista){
        this.lista = lista;
    }

    public int getFilaTamanio() {
        return  NombreFilas.length;
    }


    public int getColumnaTamanio() {
        return NombreColumnas.length;
    }

    public String[] getNombreColumnas() {
        return NombreColumnas;
    }

    public String[] getNombreFilas(){return NombreFilas;}

    public void setNombreColumnas(String[] nombreColumnas) {
        NombreColumnas = nombreColumnas;
    }

    public void setNombreFilas(String[] nombreFilas) {
        NombreFilas = nombreFilas;
    }

    public ArrayList<Double> obtenerFila(int numFila){
        ArrayList<Double> listaTemp = new ArrayList<>();
        for (int i = 0; i < CantColumnas; i++) {
            listaTemp.add(i,this.getValueAt(numFila,i));
        }
        return listaTemp;
    }

    public ArrayList<Double> obtenerColumna(int numCol){
        ArrayList<Double> listaTemp = new ArrayList<>();
        for (int i = 0; i < CantFilas; i++) {
            listaTemp.add(i,this.getValueAt(i,numCol));
        }
        return listaTemp;
    }

    public String getNombreFila(int numFila){
        return NombreFilas[numFila];
    }

    public void setValueAt(int rowIndex, int columnIndex, Double valor){
        int posicion = (rowIndex * CantColumnas) + columnIndex;
        lista.add(posicion,valor);
    }
    public Double getValueAt(int rowIndex, int columnIndex) {
        int posicion = (rowIndex * CantColumnas) + columnIndex;
        if (lista.get(posicion) != null){
            return lista.get(posicion);
        }
        return null;
    }

    public void setListaProb(ArrayList<Double> listaProb) {
        this.listaProb = listaProb;
    }

    public ArrayList<Double> getListaProb() {
        return listaProb;
    }
    public Double getValorListaProb(int numCol) {
        return listaProb.get(numCol);
    }
}
