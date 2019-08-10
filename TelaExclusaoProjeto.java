import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.*;
import java.awt.event.*;

public class TelaExclusaoProjeto extends JFrame implements ActionListener{

   final int LARGURA_TELA = 300;
   final int ALTURA_TELA = 150;
   
   private JLabel lblTituloProjeto, lblConfirmacao, lblCodigoProjeto;
   private JButton btnConfirmar, btnCancelar;
   
   private Container caixa;
   
   public TelaExclusaoProjeto(Connection conn, ){
      super("Exclusão de Projeto");
      
      lblConfirmacao = new JLabel("Deseja mesmo excluir o projeto : ");
      lblCodigoProjeto = new JLabel(avaliador.getCodAvaliador());//(conexão pegando o código do projeto)
      lblTituloProjeto = new JLabel("kk");//(conexão pegando titulo do projeto)
               
      btnConfirmar = new JButton("Confirmar");
      btnCancelar = new JButton("Cancelar");


      caixa = getContentPane();
      caixa.setLayout(new BorderLayout());
      
      JPanel painelCentro = new JPanel(new GridLayout(3,1));
      JPanel painelSul = new JPanel(new FlowLayout());
      
      painelCentro.add(lblConfirmacao);
      painelCentro.add(lblCodigoProjeto);
      painelCentro.add(lblTituloProjeto);
      
      painelSul.add(btnConfirmar);
      painelSul.add(btnCancelar);
      
      caixa.add(painelCentro, BorderLayout.CENTER);
      caixa.add(painelSul, BorderLayout.SOUTH);
      
      btnConfirmar.addActionListener(this);
      btnCancelar.addActionListener(this);
            
      lblConfirmacao.setHorizontalAlignment(SwingConstants.CENTER);
      lblCodigoProjeto.setHorizontalAlignment(SwingConstants.CENTER);
      lblTituloProjeto.setHorizontalAlignment(SwingConstants.CENTER);
      
      setSize(LARGURA_TELA, ALTURA_TELA);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setResizable(false);
      setVisible(true);

}
   @Override
   
    public void actionPerformed(ActionEvent e){
      if(e.getSource() == btnConfirmar){
         new TelaAvaliadorRegistro();
         setVisible(false);
      } 
      
      else if(e.getSource() == btnCancelar){
         new TelaPesquisadorRegistro();
         setVisible(false);
      } 
    }
   public static void main(String [] args){
      new TelaExclusaoProjeto();
   }
}