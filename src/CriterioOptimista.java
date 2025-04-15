import java.util.ArrayList;

public class CriterioOptimista extends Criterio{
    public CriterioOptimista(Matriz matrizDatos) {
        super(matrizDatos);
    }

    @Override
    public int calcularCriterio() {
        //primero obtenemos cada fila
        ArrayList<Integer> listaMaximo = new ArrayList<>();
        ArrayList<Integer> listaFilaTemp;
        int valorMaxActual = -100000000;
        for (int i = 0; i < matrizDatos.getFilaTamanio(); i++) {
            listaFilaTemp = matrizDatos.obtenerFila(i);
            //luego buscamos el valor maximo de cada fila
            for (int j = 0; j < matrizDatos.getColumnaTamanio(); j++) {
                if (listaFilaTemp.get(j) > valorMaxActual){
                    valorMaxActual = listaFilaTemp.get(j);
                }
            }
            listaMaximo.add(valorMaxActual);
            valorMaxActual = -100000000;
        }
        //por ultimo, se busca el maximo de la lista generada
        for (int i = 0; i < listaMaximo.size(); i++) {
            if (listaMaximo.get(i) > valorMaxActual){
                valorMaxActual = listaMaximo.get(i);
                this.nroFilaResult = i;
            }
        }
        this.nombreFilaResult = matrizDatos.getNombreFila(this.nroFilaResult);
        return valorMaxActual;
    }
}
