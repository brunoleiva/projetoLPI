import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class TelaAvaliarProjeto extends JFrame implements ActionListener{
   
   private JLabel lblTituloProjeto, lblTituloProjetoBanco, lblCodigoProjeto, lblCodigoProjetoBanco, lblAvalicao;
   private JTextField txtAvaliacao;
   private JButton btnSalvar, btnVoltar;
   private Container caixa;
   
   public TelaAvaliarProjeto(){
      super("Alterando Projeto");
      
      caixa = new Container();
      
      lblTituloProjeto = new JLabel(" Titulo projeto : ");
      lblCodigoProjeto = new JLabel(" Código do Projeto :");
      lblAvalicao = new JLabel(" Avaliação:");
      lblTituloProjetoBanco = new JLabel("Aqui vai titulo do projeto");
      lblCodigoProjetoBanco = new JLabel("Aqui vai o codigo do projeto");
      
      txtAvaliacao = new JTextField(20);
      
      btnSalvar = new JButton("Salvar");
      btnVoltar = new JButton("Voltar");
      
      caixa = getContentPane();
      caixa.setLayout(new BorderLayout());
   
      btnSalvar.addActionListener(this);
      btnVoltar.addActionListener(this);

      JPanel painelCentro = new JPanel(new GridLayout(3,2));
      JPanel painelSul = new JPanel(new FlowLayout());
   
      painelCentro.add(lblTituloProjeto);
      painelCentro.add(lblTituloProjetoBanco);
      painelCentro.add(lblCodigoProjeto);
      painelCentro.add(lblCodigoProjetoBanco);
      painelCentro.add(lblAvalicao);
      painelCentro.add(txtAvaliacao);
      
      painelSul.add(btnSalvar);
      painelSul.add(btnVoltar);
            
      caixa.add(painelCentro, BorderLayout.CENTER);
      caixa.add(painelSul, BorderLayout.SOUTH);

      setSize(400, 150);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setVisible(true);
      setResizable(false);
      
   }
   @Override
   public void actionPerformed(ActionEvent e){
      if(e.getSource() == btnSalvar){
        
      }
      else if(e.getSource() == btnVoltar){
         //new TelaInicio();
         setVisible(false);
      }        
   }
   
   public static void main (String [] args){
      new TelaAvaliarProjeto();
   }
  
}


