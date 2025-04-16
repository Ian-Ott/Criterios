import java.util.ArrayList;

public class CriterioSavage extends Criterio{
    Matriz matrizArrepentimiento;
    public CriterioSavage(Matriz matrizDatos) {
        super(matrizDatos);
    }
    @Override
    public Double calcularCriterio() {
        //primero obtenemos cada fila
        ArrayList<Double> listaMaximo = new ArrayList<>();
        ArrayList<Double> listaFilaTemp;
        Double valorMaxActual = -100000000.0;
        for (int i = 0; i < matrizDatos.getFilaTamanio(); i++) {
            listaFilaTemp = matrizDatos.obtenerFila(i);
            //luego buscamos el valor maximo de cada fila
            for (int j = 0; j < matrizDatos.getColumnaTamanio(); j++) {
                if (listaFilaTemp.get(j) > valorMaxActual){
                    valorMaxActual = listaFilaTemp.get(j);
                }
            }
            listaMaximo.add(valorMaxActual);
            valorMaxActual = -100000000.0;
        }
        //luego, se genera la matriz de arrepentimientos
        matrizArrepentimiento = new Matriz(matrizDatos.getColumnaTamanio(),matrizDatos.getFilaTamanio());
        matrizArrepentimiento.setNombreColumnas(matrizDatos.getNombreColumnas());
        matrizArrepentimiento.setNombreFilas(matrizDatos.getNombreFilas());
        Double valorArrepentimientoActual;
        for (int i = 0; i < matrizDatos.getFilaTamanio(); i++) {
            listaFilaTemp = matrizDatos.obtenerFila(i);
            for (int j = 0; j < matrizDatos.getColumnaTamanio(); j++) {
                valorArrepentimientoActual = listaMaximo.get(i) - listaFilaTemp.get(j);
                matrizArrepentimiento.setValueAt(i,j,valorArrepentimientoActual);
            }
        }
        //ahora saco el maximo arrepentimiento de cada fila
        listaMaximo.clear();
        valorMaxActual = -100000000.0;
        for (int i = 0; i < matrizArrepentimiento.getFilaTamanio(); i++) {
            listaFilaTemp = matrizArrepentimiento.obtenerFila(i);
            //luego buscamos el valor maximo de cada fila
            for (int j = 0; j < matrizArrepentimiento.getColumnaTamanio(); j++) {
                if (listaFilaTemp.get(j) > valorMaxActual){
                    valorMaxActual = listaFilaTemp.get(j);
                }
            }
            listaMaximo.add(valorMaxActual);
            valorMaxActual = -100000000.0;
        }
        //por ultimo, buscamos el valor minimo de arrepentimiento de la lista de maximos
        Double valorMinActual = 100000000.0;
        for (int i = 0; i < listaMaximo.size(); i++) {
            if (listaMaximo.get(i) < valorMinActual){
                valorMinActual = listaMaximo.get(i);
                this.nroFilaResult = i;
            }
        }
        this.nombreFilaResult = matrizArrepentimiento.getNombreFila(this.nroFilaResult);
        return valorMinActual;
    }
}
