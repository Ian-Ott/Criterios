import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Vector;

public class App {
    private JFrame frame;
    private JPanel panelPrincipal;
    private JTextArea ingreseLaCantidadDeTextArea;
    private JButton SiguienteButton;
    private JComboBox<Integer> ColComboBox;
    private JTextArea ingreseLaCantidadDeTextArea1;
    private JComboBox<Integer> FilaComboBox;
    private Matriz listaTabla;
    private ArrayList<String> listaTablaTemp;
    private int CantFilas;
    private int CantColumnas;
    Vector<Integer> MaxFilas = new Vector<>(0,2000);
    Vector<Integer> MaxColumnas = new Vector<>(0,2000);
    private String[] NombresFila;
    private String[] NombresColumna;
    private DefaultTableCellRenderer renderCentralTabla;
    private JButton BotonCalcular;
    private JFrame frameResultado;
    private JTextField textOptimismo = new JTextField("0.7");
    private Double optimismo;
    private boolean error = false;




public App(){
    inciarApp();
}
private void inciarApp(){
    frame = new JFrame();
    frame.setSize(1100, 500);
    frame.setResizable(false);
    frame.setVisible(true);
    frame.setTitle("Programa Criterios - Modelos, Simulacion y Teoria de la decision 2025");
    frame.add(panelPrincipal);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    for (int i = 0; i < 2000; i++) {
        MaxFilas.add(i,i + 1);
        MaxColumnas.add(i ,i + 1);
    }
    renderCentralTabla = new DefaultTableCellRenderer();
    renderCentralTabla.setHorizontalAlignment(JLabel.CENTER);
    FilaComboBox.setModel(new DefaultComboBoxModel<>(MaxFilas));
    ColComboBox.setModel(new DefaultComboBoxModel<>(MaxColumnas));
    SiguienteButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            CantFilas = (int) FilaComboBox.getSelectedItem();
            CantColumnas = (int) ColComboBox.getSelectedItem();
            listaTabla = new Matriz(CantColumnas, CantFilas);
            IniciarVentanaNombresFilas();
            frame.dispose();
        }
    });
}

    private void IniciarVentanaNombresFilas() {
        JFrame frameTabla = new JFrame();
        frameTabla.setTitle("Lista de Acciones");
        frameTabla.setLayout(new BorderLayout());
         NombresFila = new String[CantFilas];
         Object[] encabezado = {"Nombres de las acciones"};
        //se suma una fila para los encabezados
        JTable tabla = new JTable(new DefaultTableModel(encabezado,CantFilas));
        tabla.getTableHeader().setReorderingAllowed(false);
        /*for (int i = 0; i < CantFilas; i++) {
            tabla.setValueAt(" ",i,0);
        }*/
        JScrollPane scrollTabla = new JScrollPane(tabla);
        frameTabla.setSize(1100,500);
        tabla.setSize(1100,500);
        frameTabla.add(scrollTabla, BorderLayout.CENTER);
        frameTabla.setVisible(true);
        tabla.setVisible(true);
        frameTabla.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        tabla.setEnabled(true);
        JButton BotonContinuar = new JButton("Continuar");
        frameTabla.add(BotonContinuar, BorderLayout.SOUTH);

        BotonContinuar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tabla.isEditing()){
                    tabla.getCellEditor().stopCellEditing();
                }
                for (int i = 0; i < CantFilas; i++) {
                    if (tabla.getModel().getValueAt(i, 0) == null){
                        NombresFila[i] = "Accion " + (i + 1);
                    }else {
                        NombresFila[i] = (String) tabla.getModel().getValueAt(i, 0);
                    }
                    //controlar que sea string el valor?
                }
                listaTabla.setNombreFilas(NombresFila);
                IniciarVentanaNombresColumnas();
                frameTabla.dispose();
            }
        });
    }

    private void IniciarVentanaNombresColumnas() {
        JFrame frameTabla = new JFrame();
        frameTabla.setTitle("Lista de Estados de Naturaleza");
        frameTabla.setLayout(new BorderLayout());
        Object[] encabezado = {"Nombres de los estados de la naturaleza"};
        NombresColumna = new String[CantColumnas];
        //se suma una fila para los encabezados
        JTable tabla = new JTable(new DefaultTableModel(encabezado,CantColumnas));
        JScrollPane scrollTabla = new JScrollPane(tabla);
        tabla.getTableHeader().setReorderingAllowed(false);
        frameTabla.setSize(1100,500);
        tabla.setSize(1100,500);
        frameTabla.add(scrollTabla,BorderLayout.CENTER);
        frameTabla.setVisible(true);
        tabla.setVisible(true);
        frameTabla.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        tabla.setEnabled(true);
        JButton BotonContinuar = new JButton("Continuar");
        frameTabla.add(BotonContinuar,BorderLayout.SOUTH);
        BotonContinuar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tabla.isEditing()){
                    tabla.getCellEditor().stopCellEditing();
                }
                for (int i = 0; i < CantColumnas; i++) {
                    if (tabla.getModel().getValueAt(i, 0) == null){
                        NombresColumna[i] = "Estado " + (i + 1);
                    }else {
                        NombresColumna[i] = (String) tabla.getModel().getValueAt(i, 0);
                    }
                    //controlar que sea string el valor?
                }
                listaTabla.setNombreColumnas(NombresColumna);
                IniciarVentanaProbabilidadesCol();
                frameTabla.dispose();
            }
        });

    }

    private void IniciarVentanaProbabilidadesCol() {
        JFrame frameTabla = new JFrame();
        frameTabla.setTitle("Probabilidades");
        frameTabla.setLayout(new BorderLayout());
        JTextArea text = new JTextArea("Inserte las probabilidades de cada columna.");
        frameTabla.add(text,BorderLayout.NORTH);
        text.setEditable(false);
        Object[] encabezado = listaTabla.getNombreColumnas();
        //se suma una fila para los encabezados
        JTable tabla = new JTable(new DefaultTableModel(encabezado,1));
        JScrollPane scrollTabla = new JScrollPane(tabla);
        tabla.getTableHeader().setReorderingAllowed(false);
        frameTabla.setSize(1100,500);
        tabla.setSize(1100,500);
        frameTabla.add(scrollTabla,BorderLayout.CENTER);
        frameTabla.setVisible(true);
        tabla.setVisible(true);
        frameTabla.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        tabla.setEnabled(true);
        JButton BotonContinuar = new JButton("Continuar");
        frameTabla.add(BotonContinuar,BorderLayout.SOUTH);
        BotonContinuar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tabla.isEditing()){
                    tabla.getCellEditor().stopCellEditing();
                }
                ArrayList<Double> listaTemp = new ArrayList<>();
                String actual;
                Double sumaP = 0.0;
                for (int i = 0; i < CantColumnas; i++) {
                    if (!error) {
                        actual = (String) tabla.getModel().getValueAt(0, i);
                        if (actual != null) {
                            try {
                                listaTemp.add(Double.valueOf(actual));
                                sumaP = sumaP + listaTemp.get(i);
                            } catch (NumberFormatException ex) {
                                error = true;
                                mostrarError("El valor ingresado no es valido.");
                            }
                        } else {
                            error = true;
                            mostrarError("Ningun campo de probabilidad puede estar vacio.");
                        }
                    }
                }
                if (!error) {
                    if (sumaP != 1) {
                        mostrarError("La suma de las probabilidades no son iguales a 1.");
                    } else {
                        listaTabla.setListaProb(listaTemp); //comprobar que la suma de probabilidades sea igual a 1
                        listaTabla.setNombreColumnas(NombresColumna);
                        mostrarMatrizBeneficios();
                        frameTabla.dispose();
                    }
                }else {
                    error = false;
                }
            }
        });

    }

    private void mostrarError(String textError) {
        JOptionPane.showMessageDialog(null,textError,"ERROR",JOptionPane.ERROR_MESSAGE);//agregar icono?
    }

    private void mostrarMatrizBeneficios(){
        //listaTabla.setLista(listaTablaTemp);
        JFrame frameTabla = new JFrame();
        frameTabla.setTitle("Matriz De Beneficios");
        frameTabla.setLayout(new BorderLayout());
        frameTabla.setSize(1100,500);
        frameTabla.setVisible(true);
        frameTabla.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameTabla.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosed(e);
                if (frameResultado != null) {
                    frameResultado.dispose();
                }
                inciarApp();
            }
        });
        //se suma una fila para los encabezados
        JTable tabla = new JTable(new DefaultTableModel(listaTabla.getNombreColumnas() , CantFilas));
        tabla.getTableHeader().setBackground(new Color(147,147,150));
        tabla.getTableHeader().setForeground(new Color(0,0,0));
        tabla.getTableHeader().setOpaque(false);
        tabla.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollTabla = new JScrollPane(tabla);
        JList<String> encabezadoF = new JList<>(listaTabla.getNombreFilas());
        //encabezadoF.setEnabled(false);
        encabezadoF.setBackground(new Color(147,147,150));
        encabezadoF.setForeground(new Color(0,0,0));


        FontMetrics tamanioFuente = encabezadoF.getFontMetrics(encabezadoF.getFont());
        //se establece el ancho de el encabezado de fila segun el ancho del string mas largo
        encabezadoF.setFixedCellWidth(encabezadoSizeMax(listaTabla.getNombreFilas(),tamanioFuente) + 10);
        scrollTabla.setRowHeaderView(encabezadoF);

        scrollTabla.setSize(1000,500);
        JScrollBar scrollLateral = new JScrollBar(JScrollBar.HORIZONTAL);
        scrollTabla.setHorizontalScrollBar(scrollLateral);
        scrollTabla.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        //tabla.setSize(1100,500);
        tabla.setPreferredScrollableViewportSize(new Dimension(300, 200));
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        frameTabla.add(scrollTabla, BorderLayout.CENTER);
        tabla.setVisible(true);
        tabla.setEnabled(true);
        JTextArea textP = new JTextArea("Coeficiente de Optimismo = ");
        textP.setEditable(false);
        JPanel panelOpt = new JPanel(new FlowLayout());
        panelOpt.add(textP);
        panelOpt.add(textOptimismo);
        frameTabla.add(panelOpt, BorderLayout.NORTH);
        BotonCalcular = new JButton("Calcular");
        BotonCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BotonCalcular.setEnabled(false);
                if(tabla.isEditing()){
                    tabla.getCellEditor().stopCellEditing();
                }
                if (textOptimismo.getText() != null) {
                    try {
                        optimismo = Double.valueOf(textOptimismo.getText());
                        if (optimismo > 1.0 || optimismo < 0.0) {
                            error = true;
                            mostrarError("El Coeficiente de Optimismo debe ser entre 0 y 1.");
                        }
                    } catch (NumberFormatException ex) {
                        error = true;
                        mostrarError("El Coeficiente de Optimismo no es valido.");
                    }
                } else{
                    error = true;
                    mostrarError("El Coeficiente de Optimismo no puede estar vacio.");
                }
                if (!error) {
                    String vActual;
                    listaTabla.setLista(new ArrayList<>());
                    for (int i = 0; i < tabla.getRowCount(); i++) {
                        for (int j = 0; j < tabla.getColumnCount(); j++) {
                            if(!error) {
                                vActual = (String) tabla.getValueAt(i, j);
                                Double vNumActual = 0.0;
                                if (vActual != null) {
                                    try {
                                        vNumActual = Double.valueOf(vActual);
                                    } catch (NumberFormatException ex) {
                                        error = true;
                                        mostrarError("El valor ingresado en la matriz de beneficios no es valido");
                                    }
                                    listaTabla.setValueAt(i, j, vNumActual); //falta verificacion de tipo
                                } else {
                                    error = true;
                                    mostrarError("La matriz de beneficios no puede tener celdas vacias.");
                                }
                            }
                        }
                    }
                    if (!error) {
                        MostrarResultadoCriterios();
                    }else {error = false;
                        BotonCalcular.setEnabled(true);
                    }
                }else {error = false;
                    BotonCalcular.setEnabled(true);
                }
            }
        });
        frameTabla.add(BotonCalcular,BorderLayout.SOUTH);
    }

    private int encabezadoSizeMax(String[] nombreFila, FontMetrics tamanioFuente) {
        int maxLenght = 0;
        int actual;
        for (int i = 0; i < nombreFila.length; i++) {
            actual = tamanioFuente.stringWidth(nombreFila[i]);
            if (nombreFila[i].length() > maxLenght){
                maxLenght = actual;
            }
        }
        return maxLenght;
    }

    private void MostrarResultadoCriterios() {
        frameResultado = new JFrame();
        frameResultado.setTitle("Resultado segun los criterios");
        frameResultado.setVisible(true);
        
        frameResultado.getContentPane().setLayout(new BoxLayout(frameResultado.getContentPane(),BoxLayout.Y_AXIS));
        frameResultado.setSize(1100,500);
        frameResultado.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frameResultado.setTitle("Matriz De Beneficios");
        frameResultado.setSize(1100,500);
        frameResultado.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameResultado.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
                BotonCalcular.setEnabled(true);
            }
        });
        CriterioWald criterioW = new CriterioWald(listaTabla);
        Double valorW = criterioW.calcularCriterio();
        CriterioOptimista criterioO = new CriterioOptimista(listaTabla);
        Double valorO = criterioO.calcularCriterio();
        CriterioHurwicz criterioH = new CriterioHurwicz(listaTabla, optimismo);
        Double valorH = criterioH.calcularCriterio();
        CriterioSavage criterioS = new CriterioSavage(listaTabla);
        Double valorS = criterioS.calcularCriterio();
        CriterioMaximoBeneficioEsperado criterioMBE = new CriterioMaximoBeneficioEsperado(listaTabla);
        Double valorMBE = criterioMBE.calcularCriterio();
        JTextArea textC1 = new JTextArea("Resultado para el criterio Wald: " + criterioW.getNombreFilaResult() + " " + String.format("%.4s",valorW));
        JTextArea textC2 = new JTextArea("Resultado para el criterio Optimista: " + criterioO.getNombreFilaResult() + " " + String.format("%.4s",valorO));
        JTextArea textC3 = new JTextArea("Resultado para el criterio Hurwicz: " + criterioH.getNombreFilaResult() + " " + String.format("%.4s",valorH));
        JTextArea textC4 = new JTextArea("Resultado para el criterio Savage: " + criterioS.getNombreFilaResult() + " " + String.format("%.4s",valorS));
        JTextArea textC5 = new JTextArea("Resultado para el criterio de Riesgo: " + criterioMBE.getNombreFilaResult() + " " + String.format("%.4s",valorMBE));
        JTextArea textC6 = new JTextArea("Resultado para el BEIP:" + String.format("%.4s",criterioMBE.getBEIP()));
        JTextArea textC7 = new JTextArea("Resultado para el VEIP:" + String.format("%.4s",criterioMBE.getVEIP()));
        textC1.setEditable(false);
        textC2.setEditable(false);
        textC3.setEditable(false);
        textC4.setEditable(false);
        textC5.setEditable(false);
        textC6.setEditable(false);
        textC7.setEditable(false);

        frameResultado.add(textC1);
        frameResultado.add(textC2);
        frameResultado.add(textC3);
        frameResultado.add(textC4);
        frameResultado.add(textC5);
        frameResultado.add(textC6);
        frameResultado.add(textC7);
        textC1.setAlignmentX(Container.CENTER_ALIGNMENT);
        textC1.setMaximumSize(new Dimension(400, 60));
        textC2.setAlignmentX(Container.CENTER_ALIGNMENT);
        textC2.setMaximumSize(new Dimension(400, 60));
        textC3.setAlignmentX(Container.CENTER_ALIGNMENT);
        textC3.setMaximumSize(new Dimension(400, 60));
        textC4.setAlignmentX(Container.CENTER_ALIGNMENT);
        textC4.setMaximumSize(new Dimension(400, 60));
        textC5.setAlignmentX(Container.CENTER_ALIGNMENT);
        textC5.setMaximumSize(new Dimension(400, 60));
        textC6.setAlignmentX(Container.CENTER_ALIGNMENT);
        textC6.setMaximumSize(new Dimension(400, 60));
        textC7.setAlignmentX(Container.CENTER_ALIGNMENT);
        textC7.setMaximumSize(new Dimension(400, 60));

    }

    public static void main(String[] args) {
        new App();
    }
}
