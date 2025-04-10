import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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




public App(){
    frame = new JFrame();
    frame.setSize(1100, 500);
    frame.setResizable(false);
    frame.setVisible(true);
    frame.setTitle("Programa Criterios - Modelos, Simulacion y Teoria de la decision 2025");
    frame.add(panelPrincipal);
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
                    NombresFila[i] = (String) tabla.getModel().getValueAt(i,0);
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
                    NombresColumna[i] = (String) tabla.getModel().getValueAt(i,0);
                    //controlar que sea string el valor?
                }
                listaTabla.setNombreColumnas(NombresColumna);
                mostrarMatrizBeneficios();
                frameTabla.dispose();
            }
        });

    }


    private void mostrarMatrizBeneficios(){
        //listaTabla.setLista(listaTablaTemp);
        JFrame frameTabla = new JFrame();
        frameTabla.setTitle("Matriz De Beneficios");
        frameTabla.setLayout(new BorderLayout());
        //se suma una fila para los encabezados
        JTable tabla = new JTable(new DefaultTableModel(listaTabla.getNombreColumnas() , CantFilas));
        tabla.getTableHeader().setBackground(new Color(147,147,150));
        tabla.getTableHeader().setOpaque(false);
        tabla.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollTabla = new JScrollPane(tabla);
        JTable encabezadoFila = new JTable(new DefaultTableModel(CantFilas, 1));
        encabezadoFila.getColumnModel().getColumn(0).setCellRenderer(renderCentralTabla);
        encabezadoFila.setEnabled(false);
        encabezadoFila.setBackground(new Color(147,147,150));
        for (int i = 0; i < listaTabla.getNombreFilas().length; i++) {
            encabezadoFila.setValueAt(listaTabla.getNombreFilas()[i],i,0);
        }
        scrollTabla.setRowHeaderView(encabezadoFila);
        JScrollBar scrollLateral = new JScrollBar(JScrollBar.HORIZONTAL);
        scrollTabla.setHorizontalScrollBar(scrollLateral);
        scrollTabla.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        frameTabla.setSize(1100,500);
        //tabla.setSize(1100,500);
        tabla.setPreferredScrollableViewportSize(new Dimension(300, 200));
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        frameTabla.add(scrollTabla, BorderLayout.CENTER);
        frameTabla.setVisible(true);
        tabla.setVisible(true);
        frameTabla.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        tabla.setEnabled(true);
        JButton BotonCalcular = new JButton("Calcular");
        frameTabla.add(BotonCalcular,BorderLayout.SOUTH);
        BotonCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MostrarResultadoCriterios();
            }
        });
    }

    private void MostrarResultadoCriterios() {
        JFrame frameResultado = new JFrame();
        frameResultado.setTitle("Matriz De Beneficios");
        frameResultado.setSize(1100,500);
        frameResultado.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JTextArea textC1 = new JTextArea("Resultado para el criterio 1:" + calcularCriterio1());
        JTextArea textC2 = new JTextArea("Resultado para el criterio 2:" + calcularCriterio2());
        JTextArea textC3 = new JTextArea("Resultado para el criterio 3:" + calcularCriterio3());
        JTextArea textC4 = new JTextArea("Resultado para el criterio 4:" + calcularCriterio4());
        JTextArea textC5 = new JTextArea("Resultado para el criterio 5:" + calcularCriterio5());
        JTextArea textC6 = new JTextArea("Resultado para el criterio 6:" + calcularCriterio6());
        frameResultado.add(textC1);
        frameResultado.add(textC2);
        frameResultado.add(textC3);
        frameResultado.add(textC4);
        frameResultado.add(textC5);
        frameResultado.add(textC6);

    }

    private int calcularCriterio1() {
        return 0;
    }

    private int calcularCriterio2() {
        return 0;
    }

    private int calcularCriterio3() {
        return 0;
    }

    private int calcularCriterio4() {
        return 0;
    }

    private int calcularCriterio5() {
        return 0;
    }

    private int calcularCriterio6() {
        return 0;
    }


    public static void main(String[] args) {
        new App();
    }
}
