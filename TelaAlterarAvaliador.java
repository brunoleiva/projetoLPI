import javax.swing.*;
import java.util.ArrayList;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

public class TelaAlterarAvaliador extends JFrame implements ActionListener{

   final int LARGURA_TELA = 520;
   final int ALTURA_TELA = 360;
   
   private JLabel lblCpf;
   private JLabel lblNome;
   private JLabel lblRg;
   private JLabel lblSexo;
   private JLabel lblDataNasci;
   private JLabel lblGrauAcademico;
   private JLabel lblInstituicao;
   private JLabel lblAreaConhecimento;
   private JLabel lblGrandeArea;
   private JLabel lblCodigoAvaliador;
   
   private JTextField txtCpf;
   private JTextField txtNome;
   private JTextField txtRg;
   private JTextField txtCodigoAvaliador;
   
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
   
   private String[] areasConhecimento;
   private JComboBox<String> cbAreasConhecimento;
   
   private String[] grandesAreas;
   private JComboBox<String> cbGrandesAreas;
   
   private JButton btnSalvar, btnVoltar;
   
   private Connection conn;
   
   private Container caixa;
   
   int codAvaliador;
   
   public TelaAlterarAvaliador(Connection conn, Avaliador avaliador){
      super("Alterar Avaliador");
      this.conn = conn;
      
      codAvaliador = avaliador.getCodAvaliador();
      avaliador.carregarAvaliador(conn);
      
      Instituicao i = new Instituicao();
      instituicoes = i.carregarInstituicoes(conn);
      
      GrauAcademico g = new GrauAcademico();
      graisAcademicos = g.carregarGraisAcademicos(conn);
      
      GrandeAreaConhecimento gac = new GrandeAreaConhecimento();
      grandesAreas = gac.carregarGrandesAreas(conn);
   
   
      lblCodigoAvaliador = new JLabel("Código do Avaliador");
      lblCpf = new JLabel(" CPF:");
      lblNome = new JLabel(" Nome:");
      lblRg = new JLabel(" RG:");
      lblSexo = new JLabel(" Sexo:");
      lblDataNasci = new JLabel(" Data de Nascimento:");
      lblGrauAcademico = new JLabel(" Grau Acadêmico:");
      lblInstituicao = new JLabel(" Instituição:");
      lblAreaConhecimento = new JLabel(" Área conhecimento:");
      lblGrandeArea = new JLabel(" Grande área:");
      
      cbSexo = new JComboBox(sexos);
      cbInstituicoes = new JComboBox(instituicoes);
      cbGraisAcademicos = new JComboBox(graisAcademicos);
      cbDias = new JComboBox(dias);
      cbMeses = new JComboBox(meses);
      cbAnos = new JComboBox(anos);
      cbGrandesAreas = new JComboBox(grandesAreas);
      
      
      gac.setNomeGAC(cbGrandesAreas.getSelectedItem().toString());
      
      int codGac = gac.carregarGrandeArea(conn);
      AreaConhecimento ac = new AreaConhecimento();
      areasConhecimento = ac.carregarAreasConhecimento(conn, codGac); 
      
      cbAreasConhecimento = new JComboBox(areasConhecimento);
      
      txtCodigoAvaliador = new JTextField(20);
      txtCpf = new JTextField(15);
      txtNome = new JTextField(22);
      txtRg = new JTextField(15);
            
      txtCodigoAvaliador.setText("" + avaliador.getCodAvaliador());
      txtCpf.setText(avaliador.getCpfAvaliador());
      txtNome.setText(avaliador.getNomeAvaliador());
      txtRg.setText(avaliador.getRgAvaliador());
      carregarComboBoxes(avaliador);
   
   
      btnSalvar = new JButton("Salvar");
      btnVoltar = new JButton("Voltar");
      
   
      caixa = getContentPane();
      caixa.setLayout(new BorderLayout());
     
      btnSalvar.addActionListener(this);
      btnVoltar.addActionListener(this);
      cbGrandesAreas.addActionListener(this);
      
      JPanel painelCentro = new JPanel(new GridLayout(10,2));
      JPanel painelSul = new JPanel (new FlowLayout());
      
      JPanel nascimento = new JPanel (new FlowLayout());
      
      nascimento.add(cbDias);
      nascimento.add(cbMeses);
      nascimento.add(cbAnos);
      
      painelCentro.add(lblCodigoAvaliador);
      painelCentro.add(txtCodigoAvaliador);
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
      painelCentro.add(lblGrandeArea);
      painelCentro.add(cbGrandesAreas);
      painelCentro.add(lblAreaConhecimento);
      painelCentro.add(cbAreasConhecimento);
      
      painelSul.add(btnSalvar);
      painelSul.add(btnVoltar);
   
      caixa.add(painelCentro, BorderLayout.CENTER);
      caixa.add(painelSul, BorderLayout.SOUTH); 
                  
      setSize(LARGURA_TELA, ALTURA_TELA);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setVisible(true);
     
      txtCodigoAvaliador.setEditable(false);
      
      setResizable(false);
   }
   
   
   @Override
   public void actionPerformed(ActionEvent e){
      if(e.getSource() == btnSalvar){
         ConexaoBD bd = new ConexaoBD();
         try{
            Connection conn = bd.conectar();
            
            int codAvaliador = Integer.parseInt(txtCodigoAvaliador.getText());
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
            
            AreaConhecimento area = new AreaConhecimento();
            String areaConhecimento = cbAreasConhecimento.getSelectedItem().toString();
            area.setNomeAC(areaConhecimento);
            area.carregarArea(conn);
            
            String data = dia + "/" + mes + "/" + ano;
            
            try{
               Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(data);  
               Avaliador avaliadorUpdate = new Avaliador(codAvaliador, cpf, rg, sexo, nome, date1, i, grau, area);
                  
               boolean retorno = avaliadorUpdate.atualizarAvaliador(conn);
               if(retorno){
                  JOptionPane.showMessageDialog(this, "Avaliador atualizado com sucesso!");
                  setVisible(false);
               
               }else{
                  JOptionPane.showMessageDialog(this, "Ocorreu um erro ao tentar atualizar o avaliador!");
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
      else if(e.getSource() == cbGrandesAreas){
         ConexaoBD bd = new ConexaoBD();
         try{
            Connection conn = bd.conectar();
            
            GrandeAreaConhecimento gac = new GrandeAreaConhecimento();
            grandesAreas = gac.carregarGrandesAreas(conn);
         
            gac.setNomeGAC(cbGrandesAreas.getSelectedItem().toString());
            int codGac = gac.carregarGrandeArea(conn);
            AreaConhecimento ac = new AreaConhecimento();
            areasConhecimento = ac.carregarAreasConhecimento(conn, codGac);
            
            cbAreasConhecimento.removeAllItems();
         
            for(int x=0; x < areasConhecimento.length; x++){
               cbAreasConhecimento.addItem(areasConhecimento[x]);
            } 
         
         } 
         catch (SQLException exception){
            exception.printStackTrace();
         }        
         
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
   
   public int indexInstituicao(Avaliador avaliador){
      String nomeInstituicao = avaliador.getInstituicao().getNomeInstituicao();  
      
      for(int contador = 0; contador < cbInstituicoes.getItemCount(); contador++){
         if(cbInstituicoes.getItemAt(contador).equals(nomeInstituicao)){   
            return contador;
         }
      }
      return -1;
   }
   
   public int indexGrauAcademico(Avaliador avaliador){
      String nomeGrauAcademico = avaliador.getGrauAcademico().getNomeGrauAcademico(); 
       
      for(int contador = 0; contador < cbGraisAcademicos.getItemCount(); contador++){
         if(cbGraisAcademicos.getItemAt(contador).equals(nomeGrauAcademico)){   
            return contador;
         }
      }
      return -1;
   }
   
   public int indexDia(Avaliador avaliador){
      String padrao = "" + avaliador.getDataNascAvaliador(); 
      String dia = padrao.substring(8,10);
       
      for(int contador = 0; contador < cbDias.getItemCount(); contador++){
         if(cbDias.getItemAt(contador).equals(dia)){   
            return contador;
         }
      }
      return -1;
   }
   
   public int indexMes(Avaliador avaliador){
      String padrao = "" + avaliador.getDataNascAvaliador(); 
      String mes = padrao.substring(5,7);
      
      for(int contador = 0; contador < cbMeses.getItemCount(); contador++){
         if(cbMeses.getItemAt(contador).equals(mes)){   
            return contador;
         }
      }
      return -1;
   }
   
   public int indexAno(Avaliador avaliador){
   
      String padrao = "" + avaliador.getDataNascAvaliador(); 
      int ano = Integer.parseInt(padrao.substring(0,4));
      
      for(int contador = 0; contador < cbAnos.getItemCount(); contador++){
         if(cbAnos.getItemAt(contador) == ano){   
            return contador;
         }
      }
      return -1;
   }
   
   public int indexGrandeArea(Avaliador avaliador){
   
      String grandeArea = avaliador.getAreaConhecimento().getGrandeAreaConhecimento().getNomeGAC(); 
      
      for(int contador = 0; contador < cbGrandesAreas.getItemCount(); contador++){
         if(cbGrandesAreas.getItemAt(contador).equals(grandeArea)){   
            return contador;
         }
      }
      return -1;
   }
   
   public int indexArea(Avaliador avaliador){
   
      String areaConhecimento = avaliador.getAreaConhecimento().getNomeAC(); 
      
      for(int contador = 0; contador < cbAreasConhecimento.getItemCount(); contador++){
         if(cbAreasConhecimento.getItemAt(contador).equals(areaConhecimento)){   
            return contador;
         }
      }
      return -1;
   }
   
   public void carregarComboBoxes(Avaliador avaliador){  
      ConexaoBD bd = new ConexaoBD();
      try{
         Connection conn = bd.conectar();
                  
         cbInstituicoes.setSelectedIndex(indexInstituicao(avaliador));
         cbGraisAcademicos.setSelectedIndex(indexGrauAcademico(avaliador));
      
         if(avaliador.getSexoAvaliador().equals("Masculino")){
            cbSexo.setSelectedIndex(1);
         }
         else if(avaliador.getSexoAvaliador().equals("Feminino")){
            cbSexo.setSelectedIndex(2);
         }
         
         cbDias.setSelectedIndex(indexDia(avaliador));
         cbMeses.setSelectedIndex(indexMes(avaliador));
         cbAnos.setSelectedIndex(indexAno(avaliador));
         cbGrandesAreas.setSelectedIndex(indexGrandeArea(avaliador));
      
         GrandeAreaConhecimento gac = new GrandeAreaConhecimento();
         grandesAreas = gac.carregarGrandesAreas(conn);
         
         gac.setNomeGAC(cbGrandesAreas.getSelectedItem().toString());
         int codGac = gac.carregarGrandeArea(conn);
         AreaConhecimento ac = new AreaConhecimento();
         areasConhecimento = ac.carregarAreasConhecimento(conn, codGac);
            
         cbAreasConhecimento.removeAllItems();
         
         for(int x=0; x < areasConhecimento.length; x++){
            cbAreasConhecimento.addItem(areasConhecimento[x]);
         }
         cbAreasConhecimento.setSelectedIndex(indexArea(avaliador));
         
      } 
      catch (SQLException exception){
         exception.printStackTrace();
      }
   }
   
}