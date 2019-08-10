import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.*;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TelaProjetoRegistro extends JFrame implements ActionListener{

   final int LARGURA_TELA = 530;
   final int ALTURA_TELA = 260;
   
   private JLabel lblTituloProjeto, lblOrcamento, lblDuracao, lblCodigoPesquisador, lblCodigoAvaliador, lblInstituicao, lblAreaPesquisa, lblGrandeAreaConhecimento;
   private JTextField txtTituloProjeto, txtOrcamento, txtDuracao, txtCodigoPesquisador, txtCodigoAvaliador;
   
   private String[] instituicao = {"Escolha uma Instituição", "USJT", "USP", "PUC"};
   private JComboBox<String> cbInstituicao;
   
   private String[] areaConhecimento = {"Escolha uma área de conhecimento","Probabilidade e Estatística", "Ciência da Computação", "Engenharia de Minas", "Engenharia de Minasasdasd", "Engenharia de Minassdvfsv"};
   private JComboBox<String> cbAreaConhecimento;
   
   private String[] grandeAreaConhecimento = {"Escolha uma grande área de conhecimento","Ciências Exatas e da Terra", "Ciências Biológicas", "Engenharias", "Ciências da Saúde", "Ciências Agrárias", "Ciências Sociais Aplicadas", "Ciências Humanas"};
   private JComboBox<String> cbGrandeAreaConhecimento;
   
   private JButton btnIncluir, btnVoltar, btnLimpar;
   
   private Connection conn;
   
   private Container caixa;
   
   public TelaProjetoRegistro(Connection conn){
      super("Inclusão Projeto");
      this.conn = conn;
      
      lblTituloProjeto = new JLabel(" Titulo projeto: ");
      lblOrcamento = new JLabel(" Orcamento: ");
      lblDuracao = new JLabel(" Duração: ");
      lblCodigoPesquisador =  new JLabel(" Código Pesquisador: "); 
      lblCodigoAvaliador = new JLabel(" Código Avaliador: ");
      lblInstituicao = new JLabel(" Instituição: ");
      lblAreaPesquisa = new JLabel(" Area de conhecimento: ");
      lblGrandeAreaConhecimento = new JLabel(" Grande Area de Conhecimento: ");
      
      
      cbInstituicao = new JComboBox(instituicao);
      cbAreaConhecimento = new JComboBox(areaConhecimento);
      cbGrandeAreaConhecimento = new JComboBox(grandeAreaConhecimento);
      
      
      txtTituloProjeto = new JTextField(25);
      txtOrcamento = new JTextField(5);
      txtDuracao = new JTextField(10);
      txtCodigoPesquisador = new JTextField(3);
      txtCodigoAvaliador = new JTextField(3);

      
      btnIncluir = new JButton("Incluir");
      btnVoltar = new JButton("Voltar");
      btnLimpar = new JButton("Limpar");
      
     

      caixa = getContentPane();
      caixa.setLayout(new BorderLayout());
     
      btnIncluir.addActionListener(this);
      btnVoltar.addActionListener(this);
      btnLimpar.addActionListener(this);
      
      JPanel painelNorte = new JPanel(new GridLayout(5,2));
      JPanel painelCombo = new JPanel(new GridLayout(3,2));
      JPanel painelSul = new JPanel(new FlowLayout());
      
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
      painelCombo.add(lblAreaPesquisa);
      painelCombo.add(cbAreaConhecimento);
      painelCombo.add(lblGrandeAreaConhecimento);
      painelCombo.add(cbGrandeAreaConhecimento);
      
      caixa.add(painelNorte, BorderLayout.NORTH);
      caixa.add(painelCombo, BorderLayout.CENTER);
      caixa.add(painelSul, BorderLayout.SOUTH); 

      painelSul.add(btnIncluir);
      painelSul.add(btnLimpar);
      painelSul.add(btnVoltar);
                  
      setSize(LARGURA_TELA, ALTURA_TELA);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setVisible(true);
      setResizable(true);
     
   }
   
   @Override
   public void actionPerformed(ActionEvent e){
      if(e.getSource() == btnIncluir){
        
      }
      else if(e.getSource() == btnVoltar){
         //new TelaInicio();
         setVisible(false);
      } 
      else if(e.getSource() == btnLimpar){
       
      }
         

   }
  
}