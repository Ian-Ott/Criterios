import java.util.ArrayList;

public class CriterioWald extends Criterio{
    public CriterioWald(Matriz matrizDatos) {
        super(matrizDatos);
    }

    @Override
    public int calcularCriterio() {
        //primero obtenemos cada fila
        ArrayList<Integer> listaMinimo = new ArrayList<>();
        ArrayList<Integer> listaFilaTemp = new ArrayList<>();
        int valorMinActual = 100000000;
        for (int i = 0; i < matrizDatos.getFilaTamanio(); i++) {
            listaFilaTemp = matrizDatos.obtenerFila(i);
            //luego buscamos el valor minimo de cada fila
            for (int j = 0; j < matrizDatos.getColumnaTamanio(); j++) {
                if (listaFilaTemp.get(j) < valorMinActual){
                    valorMinActual = listaFilaTemp.get(j);
                }
            }
            listaMinimo.add(valorMinActual);
            valorMinActual = 100000000;
        }
        //por ultimo, se busca el maximo de la lista generada
        int valorMaxActual = -100000000;
        for (int i = 0; i < listaMinimo.size(); i++) {
            if (listaMinimo.get(i) > valorMaxActual){
                valorMaxActual = listaMinimo.get(i);
                this.nroFilaResult = i;
            }
        }
        this.nombreFilaResult = matrizDatos.getNombreFila(this.nroFilaResult);
        return valorMaxActual;
    }
}
