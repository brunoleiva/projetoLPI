import javax.swing.*;
import java.util.ArrayList;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

public class TelaAlterarPesquisador extends JFrame implements ActionListener{

   final int LARGURA_TELA = 520;
   final int ALTURA_TELA = 290;//280
   
   private JLabel lblCpf, lblNome, lblRg, lblSexo, lblDataNasci, lblGrauAcademico, lblInstituicao, lblCodigoPesquisador;
   
   private JTextField txtCpf,txtNome,txtRg, txtCodigoPesquisador;
   
   private String[] sexos = {"Escolha", "Masculino", "Feminino"};
   private JComboBox<String> cbSexo;
   
   private String[] instituicoes;
   private JComboBox<String> cbInstituicoes;
   
   private String[] graisAcademicos;
   private JComboBox<String> cbGraisAcademicos;
   
   private String[] dias = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
   private JComboBox<String> cbDias;
   
   private String[] meses = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12",};
   private JComboBox<String> cbMeses;
   
   private Integer[] anos = anos();
   private JComboBox<Integer> cbAnos;
   
   private JButton btnAlterar, btnVoltar;
   
   private Connection conn;
   private Pesquisador pesquisador;
   
   private Container caixa;
   
   int codPesquisador;
   
   public TelaAlterarPesquisador(Connection conn, Pesquisador pesquisador){
      super("Alterar Pesquisador");
      this.conn = conn;
      
      codPesquisador = pesquisador.getCodPesquisador();
      pesquisador.carregarPesquisador(conn);
      
      Instituicao i = new Instituicao();
      instituicoes = i.carregarInstituicoes(conn);
      
      GrauAcademico g = new GrauAcademico();
      graisAcademicos = g.carregarGraisAcademicos(conn);
      
      lblCodigoPesquisador = new JLabel("Código do Pesquisador");
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
      
      txtCodigoPesquisador = new JTextField(20);
      txtCpf = new JTextField(15);
      txtNome = new JTextField(22);
      txtRg = new JTextField(15);
      
      txtCodigoPesquisador.setText("" + pesquisador.getCodPesquisador());
      txtCpf.setText(pesquisador.getCpfPesquisador());
      txtNome.setText(pesquisador.getNomePesquisador());
      txtRg.setText(pesquisador.getRgPesquisador());
      carregarComboBoxes(pesquisador);
   
      
      btnAlterar = new JButton("Salvar");
      btnVoltar = new JButton("Voltar");
   
   
      caixa = getContentPane();
      caixa.setLayout(new BorderLayout());
     
      btnAlterar.addActionListener(this);
      btnVoltar.addActionListener(this);
      
      JPanel painelCentro = new JPanel(new GridLayout(8,2));
      JPanel painelSul = new JPanel(new FlowLayout());
      JPanel nascimento = new JPanel(new FlowLayout());
      
      nascimento.add(cbDias);
      nascimento.add(cbMeses);
      nascimento.add(cbAnos);
      
      
      painelCentro.add(lblCodigoPesquisador);
      painelCentro.add(txtCodigoPesquisador);
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
      
      painelSul.add(btnAlterar);
      painelSul.add(btnVoltar);
   
      caixa.add(painelCentro, BorderLayout.CENTER);
      caixa.add(painelSul, BorderLayout.SOUTH); 
                  
      setSize(LARGURA_TELA, ALTURA_TELA);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setVisible(true);
      
      txtCodigoPesquisador.setEditable(false);
   
      //Travar o FlowLayout
      setResizable(true);
     
   }
   
   @Override
   public void actionPerformed(ActionEvent e){
      if(e.getSource() == btnAlterar){
        ConexaoBD bd = new ConexaoBD();
         try{
            Connection conn = bd.conectar();
            
            int codPesquisador = Integer.parseInt(txtCodigoPesquisador.getText());
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
               Pesquisador pesquisadorUpdate = new Pesquisador(codPesquisador, cpf, nome, rg, sexo, date1, i, grau);
                  
               boolean retorno = pesquisadorUpdate.atualizarPesquisador(conn);
               if(retorno){
                  JOptionPane.showMessageDialog(this, "Pesquisador atualizado com sucesso!");
                  setVisible(false);
               
               }else{
                  JOptionPane.showMessageDialog(this, "Ocorreu um erro ao tentar atualizar o pesquisador!");
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
      else if(e.getSource() == btnVoltar){
         setVisible(false);
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
   
   public int indexInstituicao(Pesquisador pesquisador){
      String nomeInstituicao = pesquisador.getInstituicao().getNomeInstituicao();  
      
      for(int contador = 0; contador < cbInstituicoes.getItemCount(); contador++){
         if(cbInstituicoes.getItemAt(contador).equals(nomeInstituicao)){   
            return contador;
         }
      }
      return -1;
   }
   
   public int indexGrauAcademico(Pesquisador pesquisador){
      String nomeGrauAcademico = pesquisador.getGrauAcademico().getNomeGrauAcademico(); 
       
      for(int contador = 0; contador < cbGraisAcademicos.getItemCount(); contador++){
         if(cbGraisAcademicos.getItemAt(contador).equals(nomeGrauAcademico)){   
            return contador;
         }
      }
      return -1;
   }
   
   public int indexDia(Pesquisador pesquisador){
      String padrao = "" + pesquisador.getDataNascPesquisador(); 
      String dia = padrao.substring(8,10);
       
      for(int contador = 0; contador < cbDias.getItemCount(); contador++){
         if(cbDias.getItemAt(contador).equals(dia)){   
            return contador;
         }
      }
      return -1;
   }
   
   public int indexMes(Pesquisador pesquisador){
      String padrao = "" + pesquisador.getDataNascPesquisador(); 
      String mes = padrao.substring(5,7);
      
      for(int contador = 0; contador < cbMeses.getItemCount(); contador++){
         if(cbMeses.getItemAt(contador).equals(mes)){   
            return contador;
         }
      }
      return -1;
   }
   
   public int indexAno(Pesquisador pesquisador){
   
      String padrao = "" + pesquisador.getDataNascPesquisador(); 
      int ano = Integer.parseInt(padrao.substring(0,4));
      
      for(int contador = 0; contador < cbAnos.getItemCount(); contador++){
         if(cbAnos.getItemAt(contador) == ano){   
            return contador;
         }
      }
      return -1;
   }
   
   public void carregarComboBoxes(Pesquisador pesquisador){  
   
      cbInstituicoes.setSelectedIndex(indexInstituicao(pesquisador));
      cbGraisAcademicos.setSelectedIndex(indexGrauAcademico(pesquisador));
      
      if(pesquisador.getSexoPesquisador().equals("Masculino")){
         cbSexo.setSelectedIndex(1);
      }
      else if(pesquisador.getSexoPesquisador().equals("Feminino")){
         cbSexo.setSelectedIndex(2);
      }
         
      cbDias.setSelectedIndex(indexDia(pesquisador));
      cbMeses.setSelectedIndex(indexMes(pesquisador));
      cbAnos.setSelectedIndex(indexAno(pesquisador));
   
   }
   
}