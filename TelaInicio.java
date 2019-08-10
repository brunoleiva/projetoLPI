import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.Dimension;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TelaInicio extends JFrame implements ActionListener{

   final int LARGURA_TELA = 300;
   final int ALTURA_TELA = 250;

   private JLabel lblInicio;
   private JMenuBar jmbBarra;
   private JMenu jmAvaliador, jmPesquisador, jmProjetos;
   private JMenuItem jmiInserirPesquisador, jmiConsultarTabelaPesquisador;      
   private JMenuItem jmiInserirAvaliador, jmiConsultarTabelaAvaliador;
   private JMenuItem jmiInserirProjeto, jmiConsultarTabelaProjeto;
   private JButton btnSair;
   
   private Connection conn;
   
   private Container caixa;
   
   public TelaInicio(Connection conn){
      super("Menu Inicial");
               
      jmbBarra = new JMenuBar();
      jmAvaliador = new JMenu("Avaliador");
      jmPesquisador = new JMenu("Pesquisador");
      jmProjetos = new JMenu("Projeto");
      btnSair = new JButton("Sair");
      
      jmiInserirPesquisador = new JMenuItem ("Inserir");
      jmiConsultarTabelaPesquisador = new JMenuItem("Consultar");
      jmiInserirAvaliador = new JMenuItem ("Inserir");
      jmiConsultarTabelaAvaliador = new JMenuItem("Consultar");
      jmiInserirProjeto = new JMenuItem ("Inserir");
      jmiConsultarTabelaProjeto = new JMenuItem("Consultar");
            
      lblInicio = new JLabel("Bem-Vindo");
      
      this.conn = conn;
   
      caixa = getContentPane();
      caixa.setLayout(new BorderLayout());
      
      JPanel painelNorte = new JPanel(new FlowLayout());
      JPanel painelSul = new JPanel(new FlowLayout());
      
      jmiInserirAvaliador.addActionListener(this);
      jmiInserirPesquisador.addActionListener(this);
      jmiConsultarTabelaPesquisador.addActionListener(this);
      jmiConsultarTabelaAvaliador.addActionListener(this);
      jmiInserirProjeto.addActionListener(this);
      jmiConsultarTabelaProjeto.addActionListener(this);
      btnSair.addActionListener(this);
      
      painelNorte.add(lblInicio);
      
      painelSul.add(btnSair);
      
      jmPesquisador.add(jmiInserirPesquisador);
      jmPesquisador.add(jmiConsultarTabelaPesquisador);
      jmAvaliador.add(jmiInserirAvaliador);
      jmAvaliador.add(jmiConsultarTabelaAvaliador);
      jmProjetos.add(jmiInserirProjeto);
      jmProjetos.add(jmiConsultarTabelaProjeto);
      
      jmbBarra.add(jmAvaliador);
      jmbBarra.add(jmPesquisador);
      jmbBarra.add(jmProjetos);
            
      caixa.add(jmbBarra);
      
      caixa.add(painelNorte,BorderLayout.NORTH);
      caixa.add(painelSul, BorderLayout.SOUTH);
            
      setJMenuBar(jmbBarra); 
      setSize(LARGURA_TELA, ALTURA_TELA);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setResizable(false);
      setVisible(true);
      
   }
   
   
   @Override  
    public void actionPerformed(ActionEvent e){
      if(e.getSource() == jmiInserirAvaliador){
         ConexaoBD bd = new ConexaoBD();
         try{
            Connection conn = bd.conectar();
            new TelaAvaliadorRegistro(conn);
         } 
         catch (SQLException exception){
            exception.printStackTrace();
         }
      
      } 
      
      else if(e.getSource() == jmiInserirPesquisador){
         ConexaoBD bd = new ConexaoBD();
         try{
            Connection conn = bd.conectar();
         new TelaPesquisadorRegistro(conn);
         } 
         catch (SQLException exception){
            exception.printStackTrace();
         }
      }
      else if(e.getSource() == jmiConsultarTabelaPesquisador){
         ConexaoBD bd = new ConexaoBD();
         try{
            Connection conn = bd.conectar();
            new TelaConsultaPesquisador(conn);
         } 
         catch (SQLException exception){
            exception.printStackTrace();
         }
      }  
      else if(e.getSource() == jmiConsultarTabelaAvaliador){
         ConexaoBD bd = new ConexaoBD();
         try{
            Connection conn = bd.conectar();
            new TelaConsultaAvaliador(conn);
         } 
         catch (SQLException exception){
            exception.printStackTrace();
         }       
      } 
      else if(e.getSource() == btnSair){
         System.exit(0);
      }   
      else if(e.getSource() == jmiInserirProjeto){
         new TelaProjetoRegistro();
      } 
      else if(e.getSource() == jmiConsultarTabelaProjeto){
         
      } 
   }
   
   public static void main(String [] args){
   
      ConexaoBD bd = new ConexaoBD();
      try{
         Connection conn = bd.conectar();
         new TelaInicio(conn);
      } 
      catch (SQLException e){
         e.printStackTrace();
      }
   }
   
}