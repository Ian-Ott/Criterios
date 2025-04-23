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

public class VentanaProbColumn {
    private Matriz listaTabla;
    private boolean error = false;
    public VentanaProbColumn(Matriz tablaL){
        this.listaTabla = tablaL;
        iniciarVentana();

    }

    private void iniciarVentana() {
        JFrame frameTabla = new JFrame();
        frameTabla.setTitle("Probabilidades");
        frameTabla.setLayout(new BorderLayout());
        frameTabla.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosed(e);
                new App();
            }
        });

        JPanel panelP = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelP.setBackground(Color.WHITE);
        JTextArea txtP = new JTextArea("INGRESE LA PROBABILIDAD DE CADA ESTADO");
        txtP.setBackground(new Color(236, 136, 2));
        txtP.setDisabledTextColor(Color.BLACK);
        txtP.setFont(new Font("Calibri", Font.BOLD, 24));
        txtP.setBorder(new LineBorder(Color.BLACK));
        txtP.setEditable(false);
        txtP.setEnabled(false);
        panelP.add(txtP);
        frameTabla.add(panelP, BorderLayout.NORTH);

        //se suma una fila para los encabezados
        JTable tabla = new JTable(new DefaultTableModel(listaTabla.getNombreColumnas(),1));

        JPanel panelT = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelT.setBackground(Color.WHITE);
        JScrollPane scrollTabla = new JScrollPane(tabla);
        scrollTabla.setBackground(Color.WHITE);
        scrollTabla.setBorder(new LineBorder(Color.BLACK));
        scrollTabla.getHorizontalScrollBar().setBackground(Color.WHITE);
        scrollTabla.getVerticalScrollBar().setBackground(Color.WHITE);

        scrollTabla.getViewport().setFont(new Font("Calibri", Font.BOLD, 12));
        scrollTabla.getViewport().setBackground(Color.WHITE);
        scrollTabla.getViewport().setForeground(Color.BLACK);//parece que no cambia
        scrollTabla.setViewportBorder(new LineBorder(Color.BLACK));

        JScrollBar scrollLateral = new JScrollBar(JScrollBar.HORIZONTAL);
        scrollTabla.setHorizontalScrollBar(scrollLateral);
        scrollTabla.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        tabla.setPreferredScrollableViewportSize(new Dimension(800, 50));
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);


        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 16));
        tabla.getTableHeader().setForeground(Color.BLACK);
        tabla.getTableHeader().setBackground(Color.WHITE);
        tabla.getTableHeader().setBorder(new LineBorder(Color.BLACK));

        FontMetrics tamanioFuente = tabla.getTableHeader().getFontMetrics(tabla.getTableHeader().getFont());
        setEncabezadoSizeMax(tabla,listaTabla.getNombreColumnas(),tamanioFuente);


        frameTabla.setSize(1100,500);
        tabla.setSize(1100,500);
        panelT.add(scrollTabla);
        frameTabla.add(panelT, BorderLayout.CENTER);
        frameTabla.setVisible(true);
        tabla.setVisible(true);
        frameTabla.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        tabla.setEnabled(true);
        JPanel panelS = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelS.setBackground(Color.WHITE);
        JButton BotonContinuar = new JButton("Continuar");
        BotonContinuar.setBackground(new Color(236, 136, 2));
        BotonContinuar.setFont(new Font("Calibri", Font.BOLD, 24));
        BotonContinuar.setBorder(new LineBorder(Color.BLACK));
        panelS.add(BotonContinuar);
        frameTabla.add(panelS, BorderLayout.SOUTH);
        BotonContinuar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tabla.isEditing()){
                    tabla.getCellEditor().stopCellEditing();
                }
                ArrayList<Double> listaTemp = new ArrayList<>();
                String actual;
                Double sumaP = 0.0;
                for (int i = 0; i < listaTabla.getColumnaTamanio(); i++) {
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

                        new VentanaMatrizBeneficios(listaTabla);
                        frameTabla.dispose();
                    }
                }else {
                    error = false;
                }
            }
        });
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
