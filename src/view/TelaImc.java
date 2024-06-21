package view;

import model.Imc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaImc {

    private JPanel painelTituto = new JPanel();
    private JLabel labelTitulo = new JLabel("Índice de Massa Corporal - IMC");
    private JLabel lblPeso = new JLabel("Peso:");
    private JTextField txtPeso = new JTextField();
    private JLabel lblTituloResultadoImc = new JLabel("Resultado do IMC");
    private JLabel lblResultadoImc = new JLabel("");
    private JLabel lblStatusImc = new JLabel("");
    private JButton btnCalcular = new JButton("Calcular");
    private JButton btnLimpar = new JButton("Limpar");
    private JLabel lblAltura = new JLabel("Altura:");
    private JTextField txtAltura = new JTextField();

    private String imagePath = "../images/";
    private Icon iconBtnCalcular = new ImageIcon(getClass().getResource(imagePath + "calc2.png"));
    private Icon iconBtnLimpar = new ImageIcon(getClass().getResource(imagePath + "restart24.png"));

    int peso = 0;
    double altura = 0.0;


    public TelaImc(){
        criarTela();
    }

    public void criarTela(){
        JFrame tela = new JFrame();
        tela.setTitle("Calculadora IMC");
        tela.setSize(500, 300);
        tela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        tela.setLayout(null);

        // DEFINIR O PAINEL DE TITULO
        painelTituto.setBounds(0, 0, 500, 50);
        painelTituto.setBackground(new Color(85, 163, 185));
        painelTituto.setLayout(null);

        labelTitulo.setBounds(10, 10, 200, 30);
        painelTituto.add(labelTitulo);

        // COMPONENTES TELA
        lblPeso.setBounds(10, 70, 150, 30);
        txtPeso.setBounds(10, 100, 150, 35);
        txtPeso.setFont(new Font("Arial", Font.BOLD, 24));

        lblAltura.setBounds(10, 140, 150, 30);
        txtAltura.setBounds(10, 170, 150, 35);
        txtAltura.setFont(new Font("Arial", Font.BOLD, 24));

        lblTituloResultadoImc.setBounds(225, 70, 250, 30);
        lblTituloResultadoImc.setForeground(Color.BLUE);
        lblTituloResultadoImc.setFont(new Font("Arial", Font.BOLD, 24));
        lblTituloResultadoImc.setHorizontalAlignment(JLabel.CENTER);

        lblResultadoImc.setBounds(225, 110, 250, 30);
        lblResultadoImc.setFont(new Font("Arial", Font.BOLD, 38));
        lblResultadoImc.setForeground(Color.RED);
        lblResultadoImc.setHorizontalAlignment(JLabel.CENTER);

        lblStatusImc.setBounds(225, 150, 250, 30);
        lblStatusImc.setFont(new Font("Arial", Font.BOLD, 24));
        lblStatusImc.setForeground(Color.GRAY);
        lblStatusImc.setHorizontalAlignment(JLabel.CENTER);

        btnCalcular.setBounds(190, 220, 130, 30);
        btnCalcular.setIcon(iconBtnCalcular);

        btnCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularImc();
            }
        });


        btnLimpar.setBounds(330, 220, 130, 30);
        btnLimpar.setIcon(iconBtnLimpar);

        btnLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparTela();
            }
        });

        tela.getContentPane().add(painelTituto);
        tela.getContentPane().add(lblPeso);
        tela.getContentPane().add(txtPeso);
        tela.getContentPane().add(lblAltura);
        tela.getContentPane().add(txtAltura);
        tela.getContentPane().add(lblTituloResultadoImc);
        tela.getContentPane().add(lblResultadoImc);
        tela.getContentPane().add(lblStatusImc);
        tela.getContentPane().add(btnCalcular);
        tela.getContentPane().add(btnLimpar);

        tela.setVisible(true);
    }



    private void limparTela(){
        txtAltura.setText("");
        txtPeso.setText("");
        lblStatusImc.setText("");
        lblResultadoImc.setText("");
        txtPeso.requestFocus();
    }

    Imc imc = new Imc();

    private void calcularImc () {

        if (validarDados()) {

            imc.setPeso(peso);
            imc.setAltura(altura);

            String resultImc = String.valueOf(imc.getImc());

            if (imc.getImc() <= 18.5 || imc.getImc() <= 25.0) {
                painelTituto.setBackground(Color.GREEN);
                lblResultadoImc.setForeground(Color.GREEN);
            } else {
                painelTituto.setBackground(Color.RED);
                lblResultadoImc.setForeground(Color.RED);
            }

          // lblResultadoImc.setText(resultImc);

            lblResultadoImc.setText(
               // String.format("%.2f", resultImc).replace(".", ",")
                    String.format("%.2f", imc.getImc()).replace(".", ",")
           );
            lblStatusImc.setText(imc.getStatus());

        }
    }

    private boolean validarDados() {
       try{
            peso = Integer.parseInt(txtPeso.getText().trim());
       } catch (NumberFormatException erro) {
           System.out.println(erro);
           JOptionPane.showMessageDialog(
                   null,
                   "O peso deve ser um valor númerico !!",
                   "Valor Inválido",
                   JOptionPane.ERROR_MESSAGE
           );
           return false;
       }

       try{
           altura = Double.parseDouble(txtAltura.getText().replace(",", ".").trim());
       } catch (NumberFormatException erro) {
           System.out.println(erro);
           JOptionPane.showMessageDialog(
                   null,
                   "A altura deve ser um valor númerico !!",
                   "Valor Inválido",
                   JOptionPane.ERROR_MESSAGE
           );
           return false;
       }

       return true;
    }

}
