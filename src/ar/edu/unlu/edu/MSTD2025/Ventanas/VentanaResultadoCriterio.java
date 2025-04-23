package ar.edu.unlu.edu.MSTD2025.Ventanas;

import ar.edu.unlu.edu.MSTD2025.Criterios.*;
import ar.edu.unlu.edu.MSTD2025.Modelo.Matriz;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VentanaResultadoCriterio {
    private JFrame frameResultado;
    private Matriz listaTabla;
    public VentanaResultadoCriterio(Matriz tablaL){
        this.listaTabla = tablaL;

    }
    public JFrame iniciarVentana(JButton BotonCalcular,Double optimismo){
        frameResultado = new JFrame();
        frameResultado.setTitle("Resultado segun los criterios");
        frameResultado.setVisible(true);
        frameResultado.setBackground(Color.WHITE);

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
        JPanel panelM = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelM.setBackground(Color.WHITE);
        JTextArea txtM = new JTextArea("MATRIZ DE BENEFICIOS");
        txtM.setBackground(new Color(229, 63, 57));
        txtM.setDisabledTextColor(Color.BLACK);
        txtM.setFont(new Font("Calibri", Font.BOLD, 24));
        txtM.setBorder(new LineBorder(Color.BLACK));
        txtM.setEditable(false);
        txtM.setEnabled(false);
        JPanel panelR = new JPanel(new FlowLayout());
        panelR.setBackground(Color.WHITE);
        panelM.setBackground(Color.WHITE);
        JTextArea txtR = new JTextArea("Resultados");
        txtR.setBackground(new Color(255, 255, 255));
        txtR.setDisabledTextColor(Color.BLACK);
        txtR.setFont(new Font("Calibri", Font.BOLD, 20));
        txtR.setBorder(new LineBorder(Color.BLACK));
        txtR.setEditable(false);
        txtR.setEnabled(false);
        panelM.add(txtM);
        panelR.add(txtR);
        frameResultado.add(panelM, BorderLayout.NORTH);
        frameResultado.add(panelR, BorderLayout.NORTH);


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

        JPanel panelC1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelC1.setBackground(Color.WHITE);

        JPanel panel1 = new JPanel(new FlowLayout());
        panel1.setBackground(Color.WHITE);
        JTextArea textC1 = new JTextArea("Resultado para el criterio Wald ");
        textC1.setBackground(new Color(220,87,83));
        textC1.setBorder(new LineBorder(Color.BLACK));
        textC1.setFont(new Font("Calibri", Font.BOLD, 16));
        textC1.setDisabledTextColor(Color.BLACK);
        textC1.setEditable(false);
        textC1.setEnabled(false);
        JTextArea textCO1 = new JTextArea(criterioW.getNombreFilaResult());
        textCO1.setBackground(new Color(255, 255, 255));
        textCO1.setBorder(new LineBorder(Color.BLACK));
        textCO1.setFont(new Font("Calibri", Font.BOLD, 16));
        textCO1.setDisabledTextColor(Color.BLACK);
        textCO1.setEditable(false);
        textCO1.setEnabled(false);
        JTextArea textCOR1 = new JTextArea(String.format("%.4s",valorW));
        textCOR1.setBackground(new Color(255, 255, 255));
        textCOR1.setBorder(new LineBorder(Color.BLACK));
        textCOR1.setFont(new Font("Calibri", Font.BOLD, 16));
        textCOR1.setDisabledTextColor(Color.BLACK);
        textCOR1.setEditable(false);
        textCOR1.setEnabled(false);
        panel1.add(textC1);
        panel1.add(textCO1);
        panel1.add(textCOR1);
        panelC1.add(panel1);

        JPanel panelC2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelC2.setBackground(Color.WHITE);

        JPanel panel2 = new JPanel(new FlowLayout());
        panel2.setBackground(Color.WHITE);
        JTextArea textC2 = new JTextArea("Resultado para el criterio Optimista ");
        textC2.setBackground(new Color(220,87,83));
        textC2.setBorder(new LineBorder(Color.BLACK));
        textC2.setFont(new Font("Calibri", Font.BOLD, 16));
        textC2.setDisabledTextColor(Color.BLACK);
        textC2.setEditable(false);
        textC2.setEnabled(false);
        JTextArea textCO2 = new JTextArea(criterioO.getNombreFilaResult());
        textCO2.setBackground(new Color(255, 255, 255));
        textCO2.setBorder(new LineBorder(Color.BLACK));
        textCO2.setFont(new Font("Calibri", Font.BOLD, 16));
        textCO2.setDisabledTextColor(Color.BLACK);
        textCO2.setEditable(false);
        textCO2.setEnabled(false);
        JTextArea textCOR2 = new JTextArea(String.format("%.4s",valorO));
        textCOR2.setBackground(new Color(255, 255, 255));
        textCOR2.setBorder(new LineBorder(Color.BLACK));
        textCOR2.setFont(new Font("Calibri", Font.BOLD, 16));
        textCOR2.setDisabledTextColor(Color.BLACK);
        textCOR2.setEditable(false);
        textCOR2.setEnabled(false);
        panel2.add(textC2);
        panel2.add(textCO2);
        panel2.add(textCOR2);
        panelC2.add(panel2);

        JPanel panelC3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelC3.setBackground(Color.WHITE);
        JPanel panel3 = new JPanel(new FlowLayout());
        panel3.setBackground(Color.WHITE);
        JTextArea textC3 = new JTextArea("Resultado para el criterio Hurwicz ");
        textC3.setBackground(new Color(220,87,83));
        textC3.setBorder(new LineBorder(Color.BLACK));
        textC3.setFont(new Font("Calibri", Font.BOLD, 16));
        textC3.setDisabledTextColor(Color.BLACK);
        textC3.setEditable(false);
        textC3.setEnabled(false);
        JTextArea textCO3 = new JTextArea(criterioH.getNombreFilaResult());
        textCO3.setBackground(new Color(255, 255, 255));
        textCO3.setBorder(new LineBorder(Color.BLACK));
        textCO3.setFont(new Font("Calibri", Font.BOLD, 16));
        textCO3.setDisabledTextColor(Color.BLACK);
        textCO3.setEditable(false);
        textCO3.setEnabled(false);
        JTextArea textCOR3 = new JTextArea(String.format("%.4s",valorH));
        textCOR3.setBackground(new Color(255, 255, 255));
        textCOR3.setBorder(new LineBorder(Color.BLACK));
        textCOR3.setFont(new Font("Calibri", Font.BOLD, 16));
        textCOR3.setDisabledTextColor(Color.BLACK);
        textCOR3.setEditable(false);
        textCOR3.setEnabled(false);
        panel3.add(textC3);
        panel3.add(textCO3);
        panel3.add(textCOR3);
        panelC3.add(panel3);

        JPanel panelC4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelC4.setBackground(Color.WHITE);

        JPanel panel4 = new JPanel(new FlowLayout());
        panel4.setBackground(Color.WHITE);
        JTextArea textC4 = new JTextArea("Resultado para el criterio Savage ");
        textC4.setBackground(new Color(220,87,83));
        textC4.setBorder(new LineBorder(Color.BLACK));
        textC4.setFont(new Font("Calibri", Font.BOLD, 16));
        textC4.setDisabledTextColor(Color.BLACK);
        textC4.setEditable(false);
        textC4.setEnabled(false);
        JTextArea textCO4 = new JTextArea(criterioS.getNombreFilaResult());
        textCO4.setBackground(new Color(255, 255, 255));
        textCO4.setBorder(new LineBorder(Color.BLACK));
        textCO4.setFont(new Font("Calibri", Font.BOLD, 16));
        textCO4.setDisabledTextColor(Color.BLACK);
        textCO4.setEditable(false);
        textCO4.setEnabled(false);
        JTextArea textCOR4 = new JTextArea(String.format("%.4s",valorS));
        textCOR4.setBackground(new Color(255, 255, 255));
        textCOR4.setBorder(new LineBorder(Color.BLACK));
        textCOR4.setFont(new Font("Calibri", Font.BOLD, 16));
        textCOR4.setDisabledTextColor(Color.BLACK);
        textCOR4.setEditable(false);
        textCOR4.setEnabled(false);
        panel4.add(textC4);
        panel4.add(textCO4);
        panel4.add(textCOR4);
        panelC4.add(panel4);

        JPanel panelC5 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelC5.setBackground(Color.WHITE);
        JPanel panel5 = new JPanel(new FlowLayout());
        panel5.setBackground(Color.WHITE);
        JTextArea textC5 = new JTextArea("Resultado para el criterio de Riesgo ");
        textC5.setBackground(new Color(220,87,83));
        textC5.setBorder(new LineBorder(Color.BLACK));
        textC5.setFont(new Font("Calibri", Font.BOLD, 16));
        textC5.setDisabledTextColor(Color.BLACK);
        textC5.setEditable(false);
        textC5.setEnabled(false);
        JTextArea textCO5 = new JTextArea(criterioMBE.getNombreFilaResult());
        textCO5.setBackground(new Color(255, 255, 255));
        textCO5.setBorder(new LineBorder(Color.BLACK));
        textCO5.setFont(new Font("Calibri", Font.BOLD, 16));
        textCO5.setDisabledTextColor(Color.BLACK);
        textCO5.setEditable(false);
        textCO5.setEnabled(false);
        JTextArea textCOR5 = new JTextArea(String.format("%.4s",valorMBE));
        textCOR5.setBackground(new Color(255, 255, 255));
        textCOR5.setBorder(new LineBorder(Color.BLACK));
        textCOR5.setFont(new Font("Calibri", Font.BOLD, 16));
        textCOR5.setDisabledTextColor(Color.BLACK);
        textCOR5.setEditable(false);
        textCOR5.setEnabled(false);
        panel5.add(textC5);
        panel5.add(textCO5);
        panel5.add(textCOR5);
        panelC5.add(panel5);

        JPanel panelC6 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelC6.setBackground(Color.WHITE);
        JPanel panel6 = new JPanel(new FlowLayout());
        panel6.setBackground(Color.WHITE);
        JTextArea textB6 = new JTextArea("Resultado para el BEIP ");
        textB6.setBackground(new Color(220,87,83));
        textB6.setBorder(new LineBorder(Color.BLACK));
        textB6.setFont(new Font("Calibri", Font.BOLD, 16));
        textB6.setDisabledTextColor(Color.BLACK);
        textB6.setEditable(false);
        textB6.setEnabled(false);
        JTextArea textBR6 = new JTextArea(String.format("%.4s",criterioMBE.getBEIP()));
        textBR6.setBackground(new Color(255, 255, 255));
        textBR6.setBorder(new LineBorder(Color.BLACK));
        textBR6.setFont(new Font("Calibri", Font.BOLD, 16));
        textBR6.setDisabledTextColor(Color.BLACK);
        textBR6.setEditable(false);
        textBR6.setEnabled(false);
        panel6.add(textB6);
        panel6.add(textBR6);
        panelC6.add(panel6);

        JPanel panelC7 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelC7.setBackground(Color.WHITE);
        JPanel panel7 = new JPanel(new FlowLayout());
        panel7.setBackground(Color.WHITE);
        JTextArea textV7 = new JTextArea("Resultado para el VEIP ");
        textV7.setBackground(new Color(220,87,83));
        textV7.setBorder(new LineBorder(Color.BLACK));
        textV7.setFont(new Font("Calibri", Font.BOLD, 16));
        textV7.setDisabledTextColor(Color.BLACK);
        textV7.setEditable(false);
        textV7.setEnabled(false);
        JTextArea textVR7 = new JTextArea(String.format("%.4s",criterioMBE.getVEIP()));
        textVR7.setBackground(new Color(255, 255, 255));
        textVR7.setBorder(new LineBorder(Color.BLACK));
        textVR7.setFont(new Font("Calibri", Font.BOLD, 16));
        textVR7.setDisabledTextColor(Color.BLACK);
        textVR7.setEditable(false);
        textVR7.setEnabled(false);
        panel7.add(textV7);
        panel7.add(textVR7);
        panelC7.add(panel7);


        frameResultado.add(panelC1,BorderLayout.CENTER);
        frameResultado.add(panelC2,BorderLayout.CENTER);
        frameResultado.add(panelC3,BorderLayout.CENTER);
        frameResultado.add(panelC4,BorderLayout.CENTER);
        frameResultado.add(panelC5,BorderLayout.CENTER);
        frameResultado.add(panelC6,BorderLayout.CENTER);
        frameResultado.add(panelC7,BorderLayout.CENTER);

        panel1.setAlignmentY(Container.CENTER_ALIGNMENT);
        panel2.setAlignmentY(Component.CENTER_ALIGNMENT);
        panel3.setAlignmentY(Component.CENTER_ALIGNMENT);
        panel4.setAlignmentY(Component.CENTER_ALIGNMENT);
        panel5.setAlignmentY(Component.CENTER_ALIGNMENT);
        panel6.setAlignmentY(Component.CENTER_ALIGNMENT);
        panel7.setAlignmentY(Component.CENTER_ALIGNMENT);
        return frameResultado;
    }
}
