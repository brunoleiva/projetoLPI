import javax.swing.*;
import java.util.ArrayList;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;


public class TelaAlterarProjeto extends JFrame implements ActionListener{

   final int LARGURA_TELA = 530;
   final int ALTURA_TELA = 260;
   
   private JLabel lblTituloProjeto, lblOrcamento, lblDuracao, lblCodigoPesquisador, lblCodigoAvaliador, lblInstituicao, lblAreaPesquisa, lblGrandeAreaConhecimento, lblCodigoProjeto;
   private JTextField txtTituloProjeto, txtOrcamento, txtDuracao, txtCodigoPesquisador, txtCodigoAvaliador, txtCodigoProjeto;
   
   private String[] instituicao = {"Escolha uma Institui��o", "USJT", "USP", "PUC"};
   private JComboBox<String> cbInstituicao;
   
   private String[] areaConhecimento = {"Escolha uma �rea de conhecimento","Probabilidade e Estat�stica", "Ci�ncia da Computa��o", "Engenharia de Minas", "Engenharia de Minasasdasd", "Engenharia de Minassdvfsv"};
   private JComboBox<String> cbAreaConhecimento;
   
   private String[] grandeAreaConhecimento = {"Escolha uma grande �rea de conhecimento","Ci�ncias Exatas e da Terra", "Ci�ncias Biol�gicas", "Engenharias", "Ci�ncias da Sa�de", "Ci�ncias Agr�rias", "Ci�ncias Sociais Aplicadas", "Ci�ncias Humanas"};
   private JComboBox<String> cbGrandeAreaConhecimento;

   
   private JButton btnAlterar, btnVoltar;
   
   //private Connection conn;
   
   private Container caixa;
   
   public TelaAlterarProjeto(){
      super("Alterar Projeto");
      
      lblCodigoProjeto = new JLabel(" C�digo do Projeto: ");
      lblTituloProjeto = new JLabel(" Titulo projeto: ");
      lblOrcamento = new JLabel(" Orcamento: ");
      lblDuracao = new JLabel(" Dura��o: ");
      lblCodigoPesquisador =  new JLabel(" C�digo Pesquisador: "); 
      lblCodigoAvaliador = new JLabel(" C�digo Avaliador: ");
      lblInstituicao = new JLabel(" Institui��o: ");
      lblAreaPesquisa = new JLabel(" Area de conhecimento: ");
      lblGrandeAreaConhecimento = new JLabel(" Grande Area de Conhecimento: ");
      
      txtCodigoProjeto = new JTextField(20);
      txtTituloProjeto = new JTextField(25);
      txtOrcamento = new JTextField(5);
      txtDuracao = new JTextField(10);
      txtCodigoPesquisador = new JTextField(20);
      txtCodigoAvaliador = new JTextField(20);
      
      btnAlterar = new JButton("Alterar");
      btnVoltar = new JButton("Voltar");
      
      cbInstituicao = new JComboBox(instituicao);
      cbAreaConhecimento = new JComboBox(areaConhecimento);
      cbGrandeAreaConhecimento = new JComboBox(grandeAreaConhecimento);
      
     // this.conn = conn;

      caixa = getContentPane();
      caixa.setLayout(new BorderLayout());
     
      btnAlterar.addActionListener(this);
      btnVoltar.addActionListener(this);
      
      JPanel painelNorte = new JPanel(new GridLayout(6,2));
      JPanel painelCombo = new JPanel(new GridLayout(3,2));
      JPanel painelSul = new JPanel(new FlowLayout());
      
      
      painelNorte.add(lblCodigoProjeto);
      painelNorte.add(txtCodigoProjeto);
      painelNorte.add(lblTituloProjeto);
      painelNorte.add(txtTituloProjeto);
      painelNorte.add(lblOrcamento);
      painelNorte.add(txtOrcamento);
      painelNorte.add(lblDuracao);
      painelNorte.add(txtDuracao); 
      painelNorte.add(lblCodigoPesquisador);
      painelNorte.add(txtCodigoPesquisador);
      painelNorte.add(lblCodigoAvaliador);
      painelNorte.add(txtCodigoAvaliador);
            
      painelCombo.add(lblInstituicao);
      painelCombo.add(cbInstituicao);
      painelCombo.add(lblGrandeAreaConhecimento);
      painelCombo.add(cbGrandeAreaConhecimento);
      painelCombo.add(lblAreaPesquisa);
      painelCombo.add(cbAreaConhecimento);
      
      caixa.add(painelNorte, BorderLayout.NORTH);
      caixa.add(painelCombo, BorderLayout.CENTER);
      caixa.add(painelSul, BorderLayout.SOUTH); 

      painelSul.add(btnAlterar);
      painelSul.add(btnVoltar);
      
      txtCodigoProjeto.setEditable(false);
                        
      setSize(LARGURA_TELA, ALTURA_TELA);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setVisible(true);
      setResizable(false);
     
   }
   
   @Override
   public void actionPerformed(ActionEvent e){
      if(e.getSource() == btnAlterar){
        
      }
      else if(e.getSource() == btnVoltar){
         //new TelaInicio();
         setVisible(false);
      }        
   }
   
   public static void main (String [] args){
      new TelaAlterarProjeto();
   }
  
}