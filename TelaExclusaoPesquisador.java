import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.*;
import java.awt.event.*;

public class TelaExclusaoPesquisador extends JFrame implements ActionListener{

   final int LARGURA_TELA = 310;
   final int ALTURA_TELA = 150;
   
   private JLabel lblNomePesquisador, lblConfirmacao, lblCodigoPesquisador;
   private JButton btnConfirmar, btnCancelar;
   
   private Connection conn;
   
   private Container caixa;
   
   int codPesquisador;
   
   public TelaExclusaoPesquisador(Connection conn, Pesquisador pesquisador){
      super("Exclusão de Pesquisador");
      this.conn = conn;
      
      codPesquisador = pesquisador.getCodPesquisador();

      lblConfirmacao = new JLabel("Deseja mesmo excluir o pesquisador : ");
      lblCodigoPesquisador = new JLabel("" + pesquisador.getCodPesquisador());//(conexão pegando o código do pesquisador)
      lblNomePesquisador = new JLabel("" + pesquisador.getNomePesquisador());//(conexão pegando nome do pesquisador)
               
      btnConfirmar = new JButton("Confirmar");
      btnCancelar = new JButton("Cancelar");
    
     // this.conn = conn;

      caixa = getContentPane();
      caixa.setLayout(new BorderLayout());
      
      JPanel painelCentro = new JPanel(new GridLayout(3,1));
      JPanel painelSul = new JPanel(new FlowLayout());
      
      painelCentro.add(lblConfirmacao);
      painelCentro.add(lblCodigoPesquisador);
      painelCentro.add(lblNomePesquisador);
      
      painelSul.add(btnConfirmar);
      painelSul.add(btnCancelar);
      
      caixa.add(painelCentro, BorderLayout.CENTER);
      caixa.add(painelSul, BorderLayout.SOUTH);
      
      btnConfirmar.addActionListener(this);
      btnCancelar.addActionListener(this);
            
      lblConfirmacao.setHorizontalAlignment(SwingConstants.CENTER);
      lblCodigoPesquisador.setHorizontalAlignment(SwingConstants.CENTER);
      lblNomePesquisador.setHorizontalAlignment(SwingConstants.CENTER);
      
      setSize(LARGURA_TELA, ALTURA_TELA);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setResizable(false);
      setVisible(true);

}
   @Override
   
    public void actionPerformed(ActionEvent e){
      if(e.getSource() == btnConfirmar){
         ConexaoBD bd = new ConexaoBD();
         try{
            Connection conn = bd.conectar();
            Pesquisador pesquisador = new Pesquisador(codPesquisador);
            boolean retorno = pesquisador.excluirPesquisador(conn);
            if(retorno){
               JOptionPane.showMessageDialog(this, "Pesquisador excluído com sucesso!");
            }else{
               JOptionPane.showMessageDialog(this, "Ocorreu um erro ao tentar excluír o pesquisador!");
            }    
         } 
         catch (SQLException exception){
            exception.printStackTrace();
         }
         
         setVisible(false);
      } 
      
      else if(e.getSource() == btnCancelar){
         setVisible(false);
      } 
    }

}