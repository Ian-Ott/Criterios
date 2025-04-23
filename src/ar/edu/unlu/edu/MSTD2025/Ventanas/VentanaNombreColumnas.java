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

public class VentanaNombreColumnas {
    private int CantColumnas;
    private Matriz listaTabla;
    private String[] NombresCol;

    public VentanaNombreColumnas( int cantColumnas, Matriz tablaL) {
        this.CantColumnas = cantColumnas;
        this.listaTabla = tablaL;
        iniciarVentana();

    }

    private void iniciarVentana() {
        JFrame frameTabla = new JFrame();
        frameTabla.setTitle("Lista de Estados de Naturaleza");
        frameTabla.setLayout(new BorderLayout());
        frameTabla.setBackground(Color.WHITE);
        frameTabla.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosed(e);
                new App();
            }
        });

        this.NombresCol = new String[this.CantColumnas];
        JPanel panelE = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelE.setBackground(Color.WHITE);
        JTextArea txtE = new JTextArea("ESTADOS");
        txtE.setBackground(new Color(53, 206, 36));
        txtE.setDisabledTextColor(Color.BLACK);
        txtE.setFont(new Font("Calibri", Font.BOLD, 24));
        txtE.setBorder(new LineBorder(Color.BLACK));
        txtE.setEditable(false);
        txtE.setEnabled(false);
        panelE.add(txtE);
        frameTabla.add(panelE, BorderLayout.NORTH);
        Object[] encabezado = {"Nombres de los estados de la naturaleza"};
        //se suma una fila para los encabezados
        JTable tabla = new JTable(new DefaultTableModel(encabezado, this.CantColumnas));

        //configuraciones visuales
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 16));
        tabla.getTableHeader().setForeground(Color.BLACK);
        tabla.getTableHeader().setBackground(Color.WHITE);
        tabla.getTableHeader().setBorder(new LineBorder(Color.BLACK));
        /*for (int i = 0; i < CantFilas; i++) {
            tabla.setValueAt(" ",i,0);
        }*/
        JPanel panelT = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelT.setBackground(Color.WHITE);
        JScrollPane scrollTabla = new JScrollPane(tabla);
        scrollTabla.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollTabla.setBackground(Color.WHITE);
        scrollTabla.setBorder(new LineBorder(Color.BLACK));
        scrollTabla.getVerticalScrollBar().setBackground(Color.WHITE);


        scrollTabla.getViewport().setFont(new Font("Calibri", Font.BOLD, 12));
        scrollTabla.getViewport().setBackground(Color.WHITE);
        scrollTabla.getViewport().setForeground(Color.BLACK);//parece que no cambia
        scrollTabla.setViewportBorder(new LineBorder(Color.BLACK));


        frameTabla.setSize(1100, 500);
        tabla.setPreferredScrollableViewportSize(new Dimension(500, 300));
        panelT.add(scrollTabla);
        frameTabla.add(panelT, BorderLayout.CENTER);
        frameTabla.setVisible(true);
        tabla.setVisible(true);
        frameTabla.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        tabla.setEnabled(true);
        JPanel panelS = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelS.setBackground(Color.WHITE);
        JButton BotonContinuar = new JButton("Continuar");
        BotonContinuar.setBackground(new Color(53, 206, 36));
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
                for (int i = 0; i < CantColumnas; i++) {
                    if (tabla.getModel().getValueAt(i, 0) == null) {
                        NombresCol[i] = "Estado " + (i + 1);
                    } else {
                        NombresCol[i] = (String) tabla.getModel().getValueAt(i, 0);
                    }
                    //controlar que sea string el valor?
                }
                listaTabla.setNombreColumnas(NombresCol);
                new VentanaProbColumn(listaTabla);
                frameTabla.dispose();
            }
        });
    }
}
