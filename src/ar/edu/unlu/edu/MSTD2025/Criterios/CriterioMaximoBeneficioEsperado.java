package ar.edu.unlu.edu.MSTD2025.Criterios;
import ar.edu.unlu.edu.MSTD2025.Modelo.Matriz;

import java.util.ArrayList;

public class CriterioMaximoBeneficioEsperado extends Criterio {
    private Double BEIP;
    private Double VEIP;
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
                valorFilaActual = valorFilaActual + (matrizDatos.getValorListaProb(j) * listaFilaTemp.get(j));
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
        BEIP = calcularBEIP();
        VEIP = calcularVEIP(BEIP, valorMaxActual);
        return valorMaxActual;
    }

    private Double calcularVEIP(Double beip, Double valorMaxActual) {
        return beip - valorMaxActual;
    }

    private Double calcularBEIP() {
        Double beip = 0.0;
        //primero obtenemos cada columna
        ArrayList<Double> listaMaximo = new ArrayList<>();
        ArrayList<Double> listaColTemp;
        Double valorMaxActual = -100000000.0;
        for (int i = 0; i < matrizDatos.getColumnaTamanio(); i++) {
            listaColTemp = matrizDatos.obtenerColumna(i);
            //luego buscamos el valor maximo de cada columna
            for (int j = 0; j < matrizDatos.getFilaTamanio(); j++) {
                if (listaColTemp.get(j) > valorMaxActual){
                    valorMaxActual = listaColTemp.get(j);
                }
            }
            listaMaximo.add(valorMaxActual);
            valorMaxActual = -100000000.0;
        }
        //por ultimo calculamos el beip
        for (int i = 0; i < listaMaximo.size(); i++) {
            beip = beip + (matrizDatos.getValorListaProb(i) * listaMaximo.get(i));
        }
        return beip;
    }

    public Double getBEIP() {
        return BEIP;
    }

    public Double getVEIP() {
        return VEIP;
    }
}
