package ar.edu.unlu.edu.MSTD2025.Ventanas;

import ar.edu.unlu.edu.MSTD2025.Modelo.Matriz;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class VentanaMatrizBeneficios {
    private Matriz listaTabla;
    private boolean error = false;
    private JButton BotonCalcular;
    private JFrame frameResultado;

    public VentanaMatrizBeneficios(Matriz tablaL){
        this.listaTabla = tablaL;
        iniciarVentana();

    }

    private void iniciarVentana() {
        JFrame frameTabla = new JFrame();
        frameTabla.setTitle("Matriz De Beneficios");
        frameTabla.setLayout(new BorderLayout());
        frameTabla.setBackground(Color.WHITE);
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
                new App();
            }
        });
        JPanel panelM = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelM.setBackground(Color.WHITE);
        JTextArea txtM = new JTextArea("MATRIZ DE BENEFICIOS");
        txtM.setBackground(new Color(229, 63, 57));
        txtM.setDisabledTextColor(Color.BLACK);
        txtM.setFont(new Font("Calibri", Font.BOLD, 24));
        txtM.setBorder(new LineBorder(Color.BLACK));
        txtM.setEditable(false);
        txtM.setEnabled(false);
        panelM.add(txtM);
        //se suma una fila para los encabezados
        JTable tabla = new JTable(new DefaultTableModel(listaTabla.getNombreColumnas() ,listaTabla.getFilaTamanio()));
        tabla.setBackground(new Color(255, 255, 255));
        tabla.getTableHeader().setBackground(new Color(220, 87, 83));
        tabla.getTableHeader().setForeground(Color.BLACK);
        tabla.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 16));
        tabla.getTableHeader().setOpaque(false);
        tabla.getTableHeader().setReorderingAllowed(false);
        //tabla.getTableHeader().setResizingAllowed(false);
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tabla.getTableHeader().setBorder(new LineBorder(Color.BLACK));
        //tabla.setPreferredScrollableViewportSize(new Dimension(800, 400));


        JScrollPane scrollTabla = new JScrollPane(tabla);
        scrollTabla.setBackground(Color.WHITE);
        scrollTabla.setBorder(new LineBorder(Color.BLACK));
        scrollTabla.getHorizontalScrollBar().setBackground(Color.WHITE);
        scrollTabla.getVerticalScrollBar().setBackground(Color.WHITE);


        scrollTabla.getViewport().setFont(new Font("Calibri", Font.BOLD, 12));
        scrollTabla.getViewport().setBackground(Color.WHITE);
        scrollTabla.getViewport().setForeground(Color.BLACK);//parece que no cambia
        scrollTabla.setViewportBorder(new LineBorder(Color.BLACK));

        JList<String> encabezadoF = new JList<>(listaTabla.getNombreFilas());
        //encabezadoF.setEnabled(false);
        encabezadoF.setBackground(new Color(220, 87, 83));
        encabezadoF.setFont(new Font("Calibri", Font.BOLD, 16));
        encabezadoF.setForeground(Color.BLACK);
        encabezadoF.setBorder(new LineBorder(Color.BLACK));


        FontMetrics tamanioFuente = encabezadoF.getFontMetrics(encabezadoF.getFont());
        //se establece el ancho de el encabezado de fila segun el ancho del string mas largo
        encabezadoF.setFixedCellWidth(encabezadoSizeMax(listaTabla.getNombreFilas(),tamanioFuente) + 10);
        scrollTabla.setRowHeaderView(encabezadoF);

        FontMetrics tamanioFC = tabla.getTableHeader().getFontMetrics(tabla.getTableHeader().getFont());
        setEncabezadoSizeMax(tabla,listaTabla.getNombreColumnas(),tamanioFC);

        scrollTabla.setSize(1000,500);
        JScrollBar scrollLateral = new JScrollBar(JScrollBar.HORIZONTAL);
        scrollTabla.setHorizontalScrollBar(scrollLateral);
        scrollTabla.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollTabla.getHorizontalScrollBar().setBackground(Color.WHITE);

        //tabla.setSize(1100,500);
        tabla.setPreferredScrollableViewportSize(new Dimension(300, 200));
        //tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        frameTabla.add(scrollTabla, BorderLayout.CENTER);
        tabla.setVisible(true);
        tabla.setEnabled(true);
        JTextArea textP = new JTextArea("Coeficiente de Optimismo = ");
        textP.setBorder(new LineBorder(Color.BLACK));
        textP.setFont(new Font("Calibri", Font.BOLD, 16));
        textP.setDisabledTextColor(Color.BLACK);
        textP.setEditable(false);
        textP.setEnabled(false);

        JPanel panelOpt = new JPanel(new FlowLayout());
        panelOpt.setBackground(Color.WHITE);
        panelOpt.add(textP);

        JTextField textOptimismo = new JTextField("0.7");
        textOptimismo.setFont(new Font("Calibri", Font.BOLD, 16));
        textOptimismo.setBackground(Color.WHITE);
        textOptimismo.setBorder(new LineBorder(Color.BLACK));
        panelOpt.add(textOptimismo);
        JPanel panelN = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelN.setBackground(Color.WHITE);
        panelN.add(panelM);
        panelN.add(panelOpt);
        frameTabla.add(panelN, BorderLayout.NORTH);

        JPanel panelS = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelS.setBackground(Color.WHITE);
        BotonCalcular = new JButton("Calcular");
        BotonCalcular.setBackground(new Color(220, 87, 83));
        BotonCalcular.setFont(new Font("Calibri", Font.BOLD, 24));
        BotonCalcular.setBorder(new LineBorder(Color.BLACK));
        panelS.add(BotonCalcular);
        frameTabla.add(panelS, BorderLayout.SOUTH);
        BotonCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Double optimismo = 0.7;
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
                        VentanaResultadoCriterio result = new VentanaResultadoCriterio(listaTabla);
                        frameResultado = result.iniciarVentana(BotonCalcular,optimismo);
                    }else {error = false;
                        BotonCalcular.setEnabled(true);
                    }
                }else {error = false;
                    BotonCalcular.setEnabled(true);
                }
            }
        });
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
    private void setEncabezadoSizeMax(JTable tabla, String[] nombreCol, FontMetrics tamanioFuente) {
        int maxLenght = 0;
        int actual;
        for (int i = 0; i < nombreCol.length; i++) {
            actual = tamanioFuente.stringWidth(nombreCol[i]);
            tabla.getColumnModel().getColumn(i).setPreferredWidth(actual + 20);
        }
    }
    private void mostrarError(String textError) {
        JOptionPane.showMessageDialog(null,textError,"ERROR",JOptionPane.ERROR_MESSAGE);//agregar icono?
    }
}
