package ar.edu.unlu.edu.MSTD2025.Ventanas;

import ar.edu.unlu.edu.MSTD2025.Modelo.Matriz;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class VentanaNombreFilas {
    private int CantFilas;
    private Matriz listaTabla;
    private String[] NombresFila;


    public VentanaNombreFilas( int cantFilas, Matriz tablaL) {
        this.CantFilas = cantFilas;
        this.listaTabla = tablaL;
        iniciarVentana();
    }

    private void iniciarVentana() {
        JFrame frameTabla = new JFrame();
        frameTabla.setTitle("Lista de Acciones");
        frameTabla.setLayout(new BorderLayout());
        frameTabla.setBackground(Color.WHITE);
        frameTabla.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosed(e);
                new App();
            }
        });

        this.NombresFila = new String[this.CantFilas];
        JPanel panelA = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelA.setBackground(Color.WHITE);
        JTextArea txtA = new JTextArea("ACCIONES");
        txtA.setBackground(new Color(255, 251, 55));
        txtA.setDisabledTextColor(Color.BLACK);
        txtA.setFont(new Font("Calibri", Font.BOLD, 24));
        txtA.setBorder(new LineBorder(Color.BLACK));
        txtA.setEditable(false);
        txtA.setEnabled(false);
        panelA.add(txtA);
        frameTabla.add(panelA, BorderLayout.NORTH);
        Object[] encabezado = {"Nombres de las acciones"};
        //se suma una fila para los encabezados
        JTable tabla = new JTable(new DefaultTableModel(encabezado, this.CantFilas));

        //configuraciones visuales
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 16));
        tabla.getTableHeader().setForeground(Color.BLACK);
        tabla.getTableHeader().setBackground(Color.WHITE);
        tabla.getTableHeader().setBorder(new LineBorder(Color.BLACK));
        tabla.setPreferredScrollableViewportSize(new Dimension(500, 300));

        JPanel panelT = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelT.setBackground(Color.WHITE);
        JScrollPane scrollTabla = new JScrollPane(tabla);
        scrollTabla.setBackground(Color.WHITE);
        scrollTabla.setBorder(new LineBorder(Color.BLACK));
        scrollTabla.getVerticalScrollBar().setBackground(Color.WHITE);
        scrollTabla.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);


        scrollTabla.getViewport().setFont(new Font("Calibri", Font.BOLD, 12));
        scrollTabla.getViewport().setBackground(Color.WHITE);
        scrollTabla.getViewport().setForeground(Color.BLACK);//parece que no cambia
        scrollTabla.setViewportBorder(new LineBorder(Color.BLACK));

        frameTabla.setSize(1100, 500);
        tabla.setSize(1100, 400);
        panelT.add(scrollTabla);
        frameTabla.add(panelT, BorderLayout.CENTER);
        frameTabla.setVisible(true);
        tabla.setVisible(true);
        frameTabla.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        tabla.setEnabled(true);
        JPanel panelS = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelS.setBackground(Color.WHITE);
        JButton BotonContinuar = new JButton("Continuar");
        BotonContinuar.setBackground(new Color(255, 251, 55));
        BotonContinuar.setFont(new Font("Calibri", Font.BOLD, 24));
        BotonContinuar.setBorder(new LineBorder(Color.BLACK));
        panelS.add(BotonContinuar);
        frameTabla.add(panelS, BorderLayout.SOUTH);

        BotonContinuar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tabla.isEditing()) {
                    tabla.getCellEditor().stopCellEditing();
                }
                for (int i = 0; i < CantFilas; i++) {
                    if (tabla.getModel().getValueAt(i, 0) == null) {
                        NombresFila[i] = "Accion " + (i + 1);
                    } else {
                        NombresFila[i] = (String) tabla.getModel().getValueAt(i, 0);
                    }
                    //controlar que sea string el valor?
                }
                listaTabla.setNombreFilas(NombresFila);
                new VentanaNombreColumnas(listaTabla.getColumnaTamanio(),listaTabla);
                frameTabla.dispose();
            }
        });
    }
}
