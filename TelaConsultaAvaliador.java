import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.event.*;
import java.util.ArrayList;

public class TelaConsultaAvaliador extends JFrame implements ActionListener, ListSelectionListener{
   private JButton btnAlterar, btnExcluir, btnFechar, btnAtualizarTabela;
   private JTable tabelaAvaliador;
   private JPanel pnlCentro;   
   private Container caixa;
   private JScrollPane rolagem;
   private Avaliador avaliador;
   
   private String [] colunasAvaliadores = { "Código", "CPF", "RG", "Nome", "Sexo","Data de Nascimento","Instituição", "Grau academico"};// "Área de Pesquisa"}; 
   
   private Object [][] dadosAvaliadores;
   
   private Connection conn;
   
   public TelaConsultaAvaliador(Connection conn){
      super("Tela de Consulta (Avaliadores)");
      this.conn = conn;
      
      btnAlterar = new JButton("Alterar");
      btnExcluir = new JButton("Excluir");
      btnAtualizarTabela = new JButton("Atualizar Tabela");
      btnFechar = new JButton("Fechar");
      
      Avaliador avaliador = new Avaliador();
      ArrayList<Avaliador> listaAvaliadores = new ArrayList<>();
      listaAvaliadores = avaliador.carregarAvaliadores(conn);
      
      dadosAvaliadores = new Object[listaAvaliadores.size()][8];
   
      int linha = 0;
      for (Avaliador avaliadores: listaAvaliadores) {
         dadosAvaliadores[linha][0] = avaliadores.getCodAvaliador();
         dadosAvaliadores[linha][1] = avaliadores.getCpfAvaliador();
         dadosAvaliadores[linha][2] = avaliadores.getRgAvaliador();
         dadosAvaliadores[linha][3] = avaliadores.getNomeAvaliador();
         dadosAvaliadores[linha][4] = avaliadores.getSexoAvaliador();
         dadosAvaliadores[linha][5] = avaliadores.getDataNascAvaliador();
         dadosAvaliadores[linha][6] = avaliadores.getInstituicao().getNomeInstituicao();
         dadosAvaliadores[linha][7] = avaliadores.getGrauAcademico().getNomeGrauAcademico();
         linha++;
      }
      
      tabelaAvaliador = new JTable(dadosAvaliadores, colunasAvaliadores);
      rolagem = new JScrollPane(tabelaAvaliador);
      tabelaAvaliador.getSelectionModel().addListSelectionListener(this);
   
      
      caixa = new Container();
      
      caixa = getContentPane();
      caixa.setLayout(new BorderLayout());
      JPanel painelBotoes = new JPanel(new FlowLayout());
      
      caixa.add(rolagem, BorderLayout.CENTER);
      painelBotoes.add(btnAlterar);
      painelBotoes.add(btnExcluir);
      painelBotoes.add(btnAtualizarTabela);
      painelBotoes.add(btnFechar);
      
      btnAlterar.addActionListener(this);
      btnExcluir.addActionListener(this);
      btnFechar.addActionListener(this);
      btnAtualizarTabela.addActionListener(this);
      
      tabelaAvaliador.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
      tabelaAvaliador.getColumnModel().getColumn(0).setPreferredWidth(60);  
      tabelaAvaliador.getColumnModel().getColumn(1).setPreferredWidth(100);
      tabelaAvaliador.getColumnModel().getColumn(2).setPreferredWidth(100);
      tabelaAvaliador.getColumnModel().getColumn(3).setPreferredWidth(190);
      tabelaAvaliador.getColumnModel().getColumn(4).setPreferredWidth(90);
      tabelaAvaliador.getColumnModel().getColumn(5).setPreferredWidth(120);
      tabelaAvaliador.getColumnModel().getColumn(6).setPreferredWidth(100);
      tabelaAvaliador.getColumnModel().getColumn(7).setPreferredWidth(110);
      caixa.add(painelBotoes, BorderLayout.SOUTH);
      
      setSize(890, 500);
      setVisible(true);
      setResizable(false);
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setLocationRelativeTo(null);
   }
   
   
   
   @Override
   public void actionPerformed(ActionEvent e){
      if(e.getSource() == btnAlterar){
         if(avaliador != null){
            ConexaoBD bd = new ConexaoBD();
            try{
               Connection conn = bd.conectar();
               new TelaAlterarAvaliador(conn, avaliador);
            } 
            catch (SQLException exception){
               exception.printStackTrace();
            }
         }else{
            JOptionPane.showMessageDialog(this, "Selecione algum avaliador da tabela para alterar!");
         }
         
      }       
      else if(e.getSource() == btnExcluir){
         if(avaliador != null){
         ConexaoBD bd = new ConexaoBD();
         try{
            Connection conn = bd.conectar();
            new TelaExclusaoAvaliador(conn, avaliador);
         } 
         catch (SQLException exception){
            exception.printStackTrace();
         }
         }else{
            JOptionPane.showMessageDialog(this, "Selecione algum avaliador da tabela para excluir!");
         }
         
      }
      else if(e.getSource() == btnFechar){
      
         setVisible(false);
         
      }
      else if(e.getSource() == btnAtualizarTabela){
      
         setVisible(false);
         ConexaoBD bd = new ConexaoBD();
         try{
            Connection conn = bd.conectar();
            new TelaConsultaAvaliador(conn);
         } 
         catch (SQLException exception){
            exception.printStackTrace();
         }
      
      }
   }
   
   @Override
   public void valueChanged(ListSelectionEvent e){
      if(e.getValueIsAdjusting()){
         int cod = (Integer) tabelaAvaliador.getValueAt(tabelaAvaliador.getSelectedRow(),0);
         String nome = "" + tabelaAvaliador.getValueAt(tabelaAvaliador.getSelectedRow(),3);
         avaliador = new Avaliador(cod, nome);
      
      }
   
   }   
}