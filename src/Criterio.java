public class Criterio {
    protected Matriz matrizDatos;
    protected int nroFilaResult;
    protected String nombreFilaResult;
    public Criterio(Matriz matrizDatos){
        this.matrizDatos = matrizDatos;
        nroFilaResult = 0;
        nombreFilaResult = " ";
    }
    public Double calcularCriterio(){
        return 0.0;
    }

    public int getNroFilaResult() {
        return nroFilaResult;
    }

    public String getNombreFilaResult() {
        return nombreFilaResult;
    }
}
