import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.*;
import java.awt.event.*;


//para tratar eventos de selecao na tabela
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class TelaExclusaoAvaliador extends JFrame implements ActionListener{

   final int LARGURA_TELA = 300;
   final int ALTURA_TELA = 150;
   
   private JLabel lblNomeAvaliador, lblConfirmacao, lblCodigoAvaliador;
   private JButton btnConfirmar, btnCancelar;
   
   private Container caixa;
   
   int codAvaliador;
   
   public TelaExclusaoAvaliador(Connection conn, Avaliador avaliador){
      super("Exclusão de Avaliador");
      
      codAvaliador = avaliador.getCodAvaliador();
      
      lblConfirmacao = new JLabel("Deseja mesmo excluir o avaliador : ");
      lblCodigoAvaliador = new JLabel("" + avaliador.getCodAvaliador());//(conexão pegando o código do Avaliador)
      lblNomeAvaliador = new JLabel("" + avaliador.getNomeAvaliador());//(conexão pegando nome do Avaliador)
               
      btnConfirmar = new JButton("Confirmar");
      btnCancelar = new JButton("Cancelar");
    
   
      caixa = getContentPane();
      caixa.setLayout(new BorderLayout());
      
      JPanel painelCentro = new JPanel(new GridLayout(3,1));
      JPanel painelSul = new JPanel(new FlowLayout());
      
      painelCentro.add(lblConfirmacao);
      painelCentro.add(lblCodigoAvaliador);
      painelCentro.add(lblNomeAvaliador);
      
      painelSul.add(btnConfirmar);
      painelSul.add(btnCancelar);
      
      caixa.add(painelCentro, BorderLayout.CENTER);
      caixa.add(painelSul, BorderLayout.SOUTH);
      
      btnConfirmar.addActionListener(this);
      btnCancelar.addActionListener(this);
            
      lblConfirmacao.setHorizontalAlignment(SwingConstants.CENTER);
      lblCodigoAvaliador.setHorizontalAlignment(SwingConstants.CENTER);
      lblNomeAvaliador.setHorizontalAlignment(SwingConstants.CENTER);
      
      setSize(LARGURA_TELA, ALTURA_TELA);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setResizable(true);
      setVisible(true);
   
   }

   @Override
    public void actionPerformed(ActionEvent e){
      if(e.getSource() == btnConfirmar){
         ConexaoBD bd = new ConexaoBD();
         try{
            Connection conn = bd.conectar();
            Avaliador avaliador = new Avaliador(codAvaliador);
            boolean retorno = avaliador.excluirAvaliador(conn);
            if(retorno){
               JOptionPane.showMessageDialog(this, "Avaliador excluído com sucesso!");
            }else{
               JOptionPane.showMessageDialog(this, "Ocorreu um erro ao tentar excluír o avaliador!");
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