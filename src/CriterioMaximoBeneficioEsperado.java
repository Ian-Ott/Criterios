import java.util.ArrayList;

public class CriterioMaximoBeneficioEsperado extends Criterio{
    public CriterioMaximoBeneficioEsperado(Matriz matrizDatos) {
        super(matrizDatos);
    }

    @Override
    public Double calcularCriterio() {
        //primero obtenemos cada fila
        ArrayList<Double> listaMBE = new ArrayList<>();
        ArrayList<Double> listaFilaTemp;
        Double valorFilaActual = 0.0;
        for (int i = 0; i < matrizDatos.getFilaTamanio(); i++) {
            listaFilaTemp = matrizDatos.obtenerFila(i);
            //luego generamos el MBE por cada fila
            for (int j = 0; j < matrizDatos.getColumnaTamanio(); j++) {
                valorFilaActual = valorFilaActual + (matrizDatos.getValorListaProb(j) * matrizDatos.getValueAt(i,j));
            }
            listaMBE.add(valorFilaActual);
            valorFilaActual = 0.0;
        }
        //por ultimo, se busca el maximo de la lista generada
        Double valorMaxActual = -100000000.0;
        for (int i = 0; i < listaMBE.size(); i++) {
            if (listaMBE.get(i) > valorMaxActual){
                valorMaxActual = listaMBE.get(i);
                this.nroFilaResult = i;
            }
        }
        this.nombreFilaResult = matrizDatos.getNombreFila(this.nroFilaResult);
        return valorMaxActual;
    }
}
