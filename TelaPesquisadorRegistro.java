import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
import java.awt.*;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TelaPesquisadorRegistro extends JFrame implements ActionListener{

   final int LARGURA_TELA = 520;
   final int ALTURA_TELA = 280;
   
   private JLabel lblCpf, lblNome, lblRg, lblSexo, lblDataNasci, lblGrauAcademico, lblInstituicao;
   
   private JTextField txtCpf,txtNome,txtRg;
   
   private String[] sexos = {"Escolha", "Masculino", "Feminino"};
   private JComboBox<String> cbSexo;
   
   private String[] instituicoes;
   private JComboBox<String> cbInstituicoes;
   
   private String[] graisAcademicos;
   private JComboBox<String> cbGraisAcademicos;
   
   private String[] dias = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
   private JComboBox<String> cbDias;
   
   private String[] meses = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
   private JComboBox<String> cbMeses;
   
   private Integer[] anos = anos();
   private JComboBox<String> cbAnos;
   
   private JButton btnCadastrar, btnVoltar, btnLimpar;
   
   private Connection conn;
   
   private Container caixa;
   
   public TelaPesquisadorRegistro(Connection conn){
      super("Cadastro Pesquisador");
      
      this.conn = conn;
      
      Instituicao i = new Instituicao();
      instituicoes = i.carregarInstituicoes(conn);
      
      GrauAcademico g = new GrauAcademico();
      graisAcademicos = g.carregarGraisAcademicos(conn);
   
      
      lblCpf = new JLabel(" CPF:");
      lblNome = new JLabel(" Nome:");
      lblRg = new JLabel(" RG:");
      lblSexo = new JLabel(" Sexo:");
      lblDataNasci = new JLabel(" Data de Nascimento:");
      lblGrauAcademico = new JLabel(" Grau Acadêmico:");
      lblInstituicao = new JLabel(" Instituição:");
      
      cbSexo = new JComboBox(sexos);
      cbInstituicoes = new JComboBox(instituicoes);
      cbGraisAcademicos = new JComboBox(graisAcademicos);
      cbDias = new JComboBox(dias);
      cbMeses = new JComboBox(meses);
      cbAnos = new JComboBox(anos);
      
      txtCpf = new JTextField(15);
      txtNome = new JTextField(22);
      txtRg = new JTextField(15);
   
      
      btnCadastrar = new JButton("Cadastrar");
      btnVoltar = new JButton("Voltar");
      btnLimpar = new JButton("Limpar");
      
     // this.conn = conn;
   
      caixa = getContentPane();
      caixa.setLayout(new BorderLayout());
     
      btnCadastrar.addActionListener(this);
      btnVoltar.addActionListener(this);
      btnLimpar.addActionListener(this);
      
      JPanel painelCentro = new JPanel(new GridLayout(7,2));
      JPanel painelSul = new JPanel(new FlowLayout());
      JPanel nascimento = new JPanel(new FlowLayout());
      
      nascimento.add(cbDias);
      nascimento.add(cbMeses);
      nascimento.add(cbAnos);
   
      painelCentro.add(lblCpf);
      painelCentro.add(txtCpf);
      painelCentro.add(lblNome);
      painelCentro.add(txtNome);
      painelCentro.add(lblRg);
      painelCentro.add(txtRg);
      painelCentro.add(lblDataNasci);
      painelCentro.add(nascimento);
      painelCentro.add(lblSexo);
      painelCentro.add(cbSexo);
      painelCentro.add(lblInstituicao);
      painelCentro.add(cbInstituicoes);
      painelCentro.add(lblGrauAcademico);
      painelCentro.add(cbGraisAcademicos);
      
      painelSul.add(btnCadastrar);
      painelSul.add(btnLimpar);
      painelSul.add(btnVoltar);
   
      caixa.add(painelCentro, BorderLayout.CENTER);
      caixa.add(painelSul, BorderLayout.SOUTH); 
                  
      setSize(LARGURA_TELA, ALTURA_TELA);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setVisible(true);
      
      //Travar o FlowLayout
      setResizable(true);
     
   }
   
   @Override
   public void actionPerformed(ActionEvent e){
      if(e.getSource() == btnCadastrar){
         if (txtNome.getText().equals("") || txtCpf.getText().equals("") || txtRg.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Por favor complete todos os campos!");
         }else{
         
            ConexaoBD bd = new ConexaoBD();
            try{
               Connection conn = bd.conectar();
               
               String cpf = txtCpf.getText();
               String nome = txtNome.getText();
               String rg = txtRg.getText();
               String dia = cbDias.getSelectedItem().toString();
               String mes = cbMeses.getSelectedItem().toString();
               int ano = Integer.parseInt(cbAnos.getSelectedItem().toString());
               String sexo = cbSexo.getSelectedItem().toString();
            
               Instituicao i = new Instituicao();
               String instituicao = cbInstituicoes.getSelectedItem().toString();
               i.setNomeInstituicao(instituicao);
               i.carregarInsti(conn);
            
               GrauAcademico grau = new GrauAcademico();
               String grauAcademico = cbGraisAcademicos.getSelectedItem().toString();
               grau.setNomeGrauAcademico(grauAcademico);
               grau.carregarGrau(conn);

            
               String data = dia + "/" + mes + "/" + ano;
            
               try{
                  Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(data);  
                  Pesquisador pesquisadorInsert = new Pesquisador(0, cpf, nome, rg, sexo, date1, i, grau);
                  boolean retorno = pesquisadorInsert.cadastrarPesquisador(conn);
                  if(retorno){
                     JOptionPane.showMessageDialog(this, "Pesquisador cadastrado com Sucesso!");
                     txtCpf.setText("");
                     txtNome.setText("");
                     txtRg.setText("");
                     cbDias.setSelectedIndex(0); 
                     cbMeses.setSelectedIndex(0); 
                     cbAnos.setSelectedIndex(0); 
                     cbInstituicoes.setSelectedIndex(0); 
                     cbGraisAcademicos.setSelectedIndex(0); 
                     cbSexo.setSelectedIndex(0); 
                  }else{
                     JOptionPane.showMessageDialog(this, "Erro ao tentar cadastrar pesquisador!");
                  }
               
               }
               catch(ParseException ex){
                  ex.printStackTrace();
               }         
            } 
            catch (SQLException exception){
               exception.printStackTrace();
            } 
         }
        
      }
      else if(e.getSource() == btnVoltar){
         setVisible(false);
      } 
      else if(e.getSource() == btnLimpar){
         txtCpf.setText("");
         txtNome.setText("");
         txtRg.setText("");
         cbDias.setSelectedIndex(0); 
         cbMeses.setSelectedIndex(0); 
         cbAnos.setSelectedIndex(0); 
         cbInstituicoes.setSelectedIndex(0); 
         cbSexo.setSelectedIndex(0); 
         cbGraisAcademicos.setSelectedIndex(0); 
      }
   
   
   }
   
   public Integer[] anos(){
      Integer[] ano = new Integer[70];
      int a = 0;
      for(int i= 1950; i<=2019; i++){
         ano[a] = i;
         a++;
      }
      return ano;
   }
   
}