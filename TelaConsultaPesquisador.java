import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.event.*;
import java.util.ArrayList;

public class TelaConsultaPesquisador extends JFrame implements ActionListener, ListSelectionListener{
   private JButton btnAlterar, btnExcluir, btnFechar, btnAtualizarTabela;
   private JTable tabelaPesquisador;
   private JPanel pnlCentro;   
   private Container caixa;
   private JScrollPane rolagem;
   
   private Pesquisador pesquisador;

   private Connection conn;
   
   private String [] colunasPesquisador = { "Código", "CPF", "RG", "Nome", "Sexo","Data de Nascimento","Instituição", "Grau academico"};
   
   private Object [][] dadosPesquisador;   
   
   public TelaConsultaPesquisador(Connection conn){
      super("Tela de Consulta (Pesquisador)");
      this.conn = conn;
      
      btnAlterar = new JButton("Alterar");
      btnExcluir = new JButton("Excluir");
      btnFechar = new JButton("Fechar");
      btnAtualizarTabela = new JButton("Atualizar Tabela");
      
      Pesquisador pesquisador = new Pesquisador();
      ArrayList<Pesquisador> listaPesquisadores = new ArrayList<>();
      listaPesquisadores = pesquisador.carregarPesquisadores(conn);
      
      dadosPesquisador = new Object[listaPesquisadores.size()][8];
   
      int linha = 0;
      for (Pesquisador pesquisadores: listaPesquisadores) {
         dadosPesquisador[linha][0] = pesquisadores.getCodPesquisador();
         dadosPesquisador[linha][1] = pesquisadores.getCpfPesquisador();
         dadosPesquisador[linha][2] = pesquisadores.getRgPesquisador();
         dadosPesquisador[linha][3] = pesquisadores.getNomePesquisador();
         dadosPesquisador[linha][4] = pesquisadores.getSexoPesquisador();
         dadosPesquisador[linha][5] = pesquisadores.getDataNascPesquisador();
         dadosPesquisador[linha][6] = pesquisadores.getInstituicao().getNomeInstituicao();
         dadosPesquisador[linha][7] = pesquisadores.getGrauAcademico().getNomeGrauAcademico();
         linha++;
      }
      
      tabelaPesquisador = new JTable(dadosPesquisador, colunasPesquisador);
      rolagem = new JScrollPane(tabelaPesquisador);
      tabelaPesquisador.getSelectionModel().addListSelectionListener(this);
   
      
      caixa = new Container();
      
      caixa = getContentPane();
      caixa.setLayout(new BorderLayout());
      JPanel painelBotoes = new JPanel(new FlowLayout());
      
      painelBotoes.add(btnAlterar);
      painelBotoes.add(btnExcluir);
      painelBotoes.add(btnAtualizarTabela);
      painelBotoes.add(btnFechar);
      
      btnAlterar.addActionListener(this);
      btnExcluir.addActionListener(this);
      btnFechar.addActionListener(this);
      btnAtualizarTabela.addActionListener(this);
      
      caixa.add(rolagem, BorderLayout.CENTER);
      caixa.add(painelBotoes, BorderLayout.SOUTH);
      
      tabelaPesquisador.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
      tabelaPesquisador.getColumnModel().getColumn(0).setPreferredWidth(60);  
      tabelaPesquisador.getColumnModel().getColumn(1).setPreferredWidth(100);
      tabelaPesquisador.getColumnModel().getColumn(2).setPreferredWidth(100);
      tabelaPesquisador.getColumnModel().getColumn(3).setPreferredWidth(190);
      tabelaPesquisador.getColumnModel().getColumn(4).setPreferredWidth(90);
      tabelaPesquisador.getColumnModel().getColumn(5).setPreferredWidth(120);
      tabelaPesquisador.getColumnModel().getColumn(6).setPreferredWidth(100);
      tabelaPesquisador.getColumnModel().getColumn(7).setPreferredWidth(120);
      
      setSize(900, 500);
      setVisible(true);
      setResizable(false);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
   }
   
   @Override
   public void actionPerformed(ActionEvent e){
      if(e.getSource() == btnAlterar){
         if(pesquisador != null){
            ConexaoBD bd = new ConexaoBD();
            try{
               Connection conn = bd.conectar();
               new TelaAlterarPesquisador(conn, pesquisador);
            } 
            catch (SQLException exception){
               exception.printStackTrace();
            }
         }else{
            JOptionPane.showMessageDialog(this, "Selecione algum pesquisador da tabela para alterar!");
         }
      }       
      else if(e.getSource() == btnExcluir){
         if(pesquisador != null){
         ConexaoBD bd = new ConexaoBD();
         try{
            Connection conn = bd.conectar();
            new TelaExclusaoPesquisador(conn, pesquisador);
         } 
         catch (SQLException exception){
            exception.printStackTrace();
         }
         }else{
            JOptionPane.showMessageDialog(this, "Selecione algum pesquisador da tabela para excluir!");
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
            new TelaConsultaPesquisador(conn);
         } 
         catch (SQLException exception){
            exception.printStackTrace();
         }
      
      }
      
   }
   
   @Override
   public void valueChanged(ListSelectionEvent e){
      if(e.getValueIsAdjusting()){
         int cod = (Integer) tabelaPesquisador.getValueAt(tabelaPesquisador.getSelectedRow(),0);
         String nome = "" + tabelaPesquisador.getValueAt(tabelaPesquisador.getSelectedRow(),3);
         pesquisador = new Pesquisador(cod, nome);
      
      }
   
   }
}