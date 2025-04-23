package ar.edu.unlu.edu.MSTD2025.Ventanas;

import ar.edu.unlu.edu.MSTD2025.Matriz.Matriz;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private int CantFilas;
    private int CantColumnas;
    Vector<Integer> MaxFilas = new Vector<>(0,2000);
    Vector<Integer> MaxColumnas = new Vector<>(0,2000);
    private DefaultTableCellRenderer renderCentralTabla;


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
            new VentanaNombreFilas(CantFilas,listaTabla);
            frame.dispose();
        }
    });
}


    public static void main(String[] args) {
        new App();
    }
}
