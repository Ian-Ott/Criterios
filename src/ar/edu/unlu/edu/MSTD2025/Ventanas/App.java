package ar.edu.unlu.edu.MSTD2025.Ventanas;

import ar.edu.unlu.edu.MSTD2025.Criterios.*;
import ar.edu.unlu.edu.MSTD2025.Modelo.Matriz;

import javax.swing.*;
import javax.swing.border.LineBorder;
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
    private JTextArea laCantidadDeEstadosTextArea;
    private JButton SiguienteButton;
    private JComboBox<Integer> ColComboBox;
    private JTextArea laCantidadDeAccionesTextArea;
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
            //IniciarVentanaNombresFilas();
            new VentanaNombreFilas(CantFilas,listaTabla);
            frame.dispose();
        }
    });
}


    public static void main(String[] args) {
        new App();
    }
}
