import java.util.ArrayList;

public class Matriz {
    private String[] NombreColumnas;
    private String[] NombreFilas;
    private ArrayList<Double> lista = new ArrayList<>();
    private int CantColumnas;

    public Matriz(int CantColumnas,int CantFilas) {
        NombreColumnas = new String[CantColumnas];
        NombreFilas = new String[CantFilas];
        this.CantColumnas = CantColumnas;
    }

    public void setLista(ArrayList<Double> lista){
        this.lista = lista;
    }

    public int getFilaTamanio() {
        return lista.size()/ NombreColumnas.length;
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
        int posFila = numFila * CantColumnas;
        for (int i = 0; i < CantColumnas; i++) {
            listaTemp.add(i,this.getValueAt(posFila,i));
        }
        return listaTemp;
    }

    public String getNombreFila(int numFila){
        return NombreFilas[numFila];
    }

    public void setValueAt(int rowIndex, int columnIndex, Double valor){
        int posicion = (rowIndex * CantColumnas) + columnIndex;
        lista.set(posicion,valor);
    }
    public Double getValueAt(int rowIndex, int columnIndex) {
        int posicion = (rowIndex * CantColumnas) + columnIndex;
        if (lista.get(posicion) != null){
            return lista.get(posicion);
        }
        return null;
    }
}
