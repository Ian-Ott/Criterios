import java.util.ArrayList;

public class CriterioHurwicz extends Criterio{
    double coeficienteOptimismo;
    public CriterioHurwicz(Matriz matrizDatos) {
        super(matrizDatos);
        coeficienteOptimismo = 0.7;
    }

    @Override
    public Double calcularCriterio() {
        //primero obtenemos cada fila
        ArrayList<Double> listaMaximo = new ArrayList<>();
        ArrayList<Double> listaMinimo = new ArrayList<>();
        ArrayList<Double> listaFilaTemp;
        Double valorMaxActual = (double) -100000000;
        Double valorMinActual = 100000000.0;
        for (int i = 0; i < matrizDatos.getFilaTamanio(); i++) {
            listaFilaTemp = matrizDatos.obtenerFila(i);
            //luego buscamos el valor maximo y minimo de cada fila
            for (int j = 0; j < matrizDatos.getColumnaTamanio(); j++) {
                if (listaFilaTemp.get(j) > valorMaxActual){
                    valorMaxActual = listaFilaTemp.get(j);
                }
                if (listaFilaTemp.get(j) < valorMinActual){
                    valorMinActual = listaFilaTemp.get(j);
                }
            }
            listaMaximo.add(valorMaxActual);
            listaMinimo.add(valorMinActual);
            valorMaxActual =  -100000000.0;
            valorMinActual = 100000000.0;
        }
        //despues generamos una lista con los calculos necesarios (nueva columna H)
        ArrayList<Double> listaH = new ArrayList<>();
        double calculoActual = 0;
        for (int i = 0; i < matrizDatos.getFilaTamanio(); i++) {
            calculoActual = coeficienteOptimismo * listaMaximo.get(i) + (1- coeficienteOptimismo) * listaMinimo.get(i);
            listaH.add(calculoActual);
        }
        //por ultimo, selecciono el maximo de esos valores
        for (int i = 0; i < listaH.size(); i++) {
            if (listaH.get(i) > valorMaxActual){
                valorMaxActual = listaH.get(i);
                this.nroFilaResult = i;
            }
        }
        this.nombreFilaResult = matrizDatos.getNombreFila(this.nroFilaResult);
        return valorMaxActual;
    }

}
