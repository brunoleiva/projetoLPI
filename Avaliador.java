import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;


public class Avaliador{
   private int codAvaliador;
   private String cpfAvaliador;
   private String rgAvaliador;
   private String sexoAvaliador;
   private String nomeAvaliador;
   private Date dataNascAvaliador;
   private Instituicao instituicao;
   private GrauAcademico grauAcademico;
   private AreaConhecimento areaConhecimento;
   private ArrayList<Projetos> projetos;
   

   public Avaliador() {
      projetos = new ArrayList<>();
   }

   public Avaliador(int codAvaliador) {
      this.codAvaliador = codAvaliador;
      projetos = new ArrayList<>();
   }

   public Avaliador(int codAvaliador, String nomeAvaliador) {
      this.codAvaliador = codAvaliador;
      this.nomeAvaliador = nomeAvaliador;
      projetos = new ArrayList<>();
   }
   
   public Avaliador(int codAvaliador, String cpfAvaliador, String rgAvaliador, String sexoAvaliador, String nomeAvaliador, Date dataNascAvaliador, Instituicao instituicao, GrauAcademico grauAcademico, AreaConhecimento areaConhecimento) {
      this.codAvaliador = codAvaliador;
      this.cpfAvaliador = cpfAvaliador;
      this.rgAvaliador = rgAvaliador;
      this.sexoAvaliador = sexoAvaliador;
      this.nomeAvaliador = nomeAvaliador;
      this.dataNascAvaliador = dataNascAvaliador;
      this.instituicao = instituicao;
      this.grauAcademico = grauAcademico;
      this.areaConhecimento = areaConhecimento;
      projetos = new ArrayList<>();
   }
//gets sets codAvaliador
   public int getCodAvaliador() {
      return codAvaliador;
   }

   public void setCodAvaliador(int codAvaliador) {
      this.codAvaliador = codAvaliador;
   }
//gets sets cpfAvaliador
   public String getCpfAvaliador() {
      return cpfAvaliador;
   }

   public void setCpfAvaliador(String cpfAvaliador) {
      this.cpfAvaliador = cpfAvaliador;
   }
//gets sets rgAvaliador
   public String getRgAvaliador(){
      return rgAvaliador;
   }
   
   public void setRgAvaliador(String rgAvaliador){
      this.rgAvaliador = rgAvaliador;   
   }
//gets sets sexoAaliador
   public String getSexoAvaliador(){
      return sexoAvaliador;
   }
   
   public void setSexoAvaliador(String sexoAvaliador){
      this.sexoAvaliador = sexoAvaliador;
   }

//gets sets nomeAvaliador
   public String getNomeAvaliador(){
      return nomeAvaliador;
   }
   
   public void setNomeAvaliador(String nomeAvaliador){
      this.nomeAvaliador = nomeAvaliador;
   }

//gets sets dataNascAvaliador
   public Date getDataNascAvaliador(){
      return dataNascAvaliador;
   }
   
   public void setDataNascAvaliador (Date dataNascAvaliador){
      this.dataNascAvaliador = dataNascAvaliador;
   }

//gets sets instituicao
   public Instituicao getInstituicao(){
      return instituicao;
   }
   
   public void setInstituicao(Instituicao instituicao){
      this.instituicao = instituicao;
   }

//gets sets grauAcademico
   public GrauAcademico getGrauAcademico(){
      return grauAcademico;
   }
   
   public void setGrauAcademico(GrauAcademico grauAcademico){
      this.grauAcademico = grauAcademico;
   }

//gets sets areaConhecimento
   public AreaConhecimento getAreaConhecimento(){
      return areaConhecimento;
   }
   
   public void setAreaConhecimento (AreaConhecimento areaConhecimento){
      this.areaConhecimento = areaConhecimento;
   }
   
   //gets sets Array Projetos
   public ArrayList<Projetos> getProjetos() {
      return projetos;
   }

   public void setProjetos(ArrayList<Projetos> projetos) {
      this.projetos = projetos;
   }
	
   public void adicionarProjetos(Projetos projeto){
      projetos.add(projeto);
   }
   
   //ToString
   @Override
   public String toString() {
      return "Avaliador [Codigo=" + codAvaliador + ", Nome=" + nomeAvaliador + ", CPF=" + cpfAvaliador + ", RG= "+rgAvaliador+", Sexo= "+ sexoAvaliador+
         ", Data de Nascimento= "+dataNascAvaliador+"]";
   }
   
   public boolean cadastrarAvaliador(Connection conn) {
      String sqlInsert = "INSERT INTO avaliador(cpf_avaliador, rg_avaliador, sexo_avaliador, nome_avaliador, dt_nasci_avaliador, cod_instituicao, cod_grau_academico, cod_AC) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
   
      try (PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
         stm.setString(1, getCpfAvaliador());
         stm.setString(2, getRgAvaliador());
         stm.setString(3, getSexoAvaliador());
         stm.setString(4, getNomeAvaliador());
         stm.setDate(5, new java.sql.Date(getDataNascAvaliador().getTime()));
         stm.setInt(6, instituicao.getCodInstituicao());
         stm.setInt(7, grauAcademico.getCodGrauAcademico());
         stm.setInt(8, areaConhecimento.getCodAC());
         stm.execute();
      	// pegar o id criado no banco
         String query = "SELECT LAST_INSERT_ID()";
         try (PreparedStatement stm2 = conn.prepareStatement(query);
         		ResultSet rs = stm2.executeQuery();) {
            if (rs.next()) {
               setCodAvaliador(rs.getInt(1));
            } else {
               throw new SQLException(
                  	"Erro para conseguir o id do avaliador");
            }
         } 
         catch (Exception e) {
            e.printStackTrace();
            try {
               conn.rollback();
            } catch (SQLException e1) {
               System.out.print(e1.getStackTrace());
            }
         }
      
         return true;
      } catch (Exception e) {
         e.printStackTrace();
         try {
            conn.rollback();
         } catch (SQLException e1) {
            System.out.print(e1.getStackTrace());
         }
      }
      return false;
   }
   
    
   public boolean excluirAvaliador(Connection conn) {
      String sqlDelete = "DELETE FROM avaliador WHERE cod_avaliador = ?";
      try (PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
      	// depois excluir o cliente
         stm.setInt(1, getCodAvaliador());
         stm.execute();
      	// anular os projetos e os atributos
         setCodAvaliador(-1);
         setCpfAvaliador(null);
         setRgAvaliador(null);
         setSexoAvaliador(null);
         setNomeAvaliador(null);
         setDataNascAvaliador(null);
         setInstituicao(null);
         setGrauAcademico(null);
         setAreaConhecimento(null);
         projetos = new ArrayList<>();
         
         return true;
      } catch (Exception e) {
         e.printStackTrace();
         try {
            conn.rollback();
         } catch (SQLException e1) {
            System.out.print(e1.getStackTrace());
         }
      }
      return false;
   }
   

   public boolean atualizarAvaliador(Connection conn){
      String sqlUpdate = 
         "UPDATE avaliador SET cpf_avaliador = ?, rg_avaliador = ?, sexo_avaliador = ?, nome_avaliador = ?, dt_nasci_avaliador = ?, cod_instituicao = ?, cod_grau_academico = ? , cod_ac = ? WHERE cod_avaliador = ?";
   
      try (PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
         stm.setString(1, getCpfAvaliador());
         stm.setString(2, getRgAvaliador());
         stm.setString(3, getSexoAvaliador());
         stm.setString(4, getNomeAvaliador());
         stm.setDate(5, new java.sql.Date(getDataNascAvaliador().getTime()));
         stm.setInt(6, instituicao.getCodInstituicao());
         stm.setInt(7, grauAcademico.getCodGrauAcademico());
         stm.setInt(8, areaConhecimento.getCodAC());
         stm.setInt(9, getCodAvaliador());

         stm.execute();  
         return true;
      } catch (Exception e) {
         e.printStackTrace();
         try {
            conn.rollback();
         } catch (SQLException e1) {
            System.out.print(e1.getStackTrace());
         }
      }
      return false;
   }
   
   public void carregarAvaliador(Connection conn) {
      String sqlSelect = 
         "SELECT cpf_avaliador, rg_avaliador, sexo_avaliador, nome_avaliador, dt_nasci_avaliador, cod_instituicao, cod_grau_academico, cod_AC FROM avaliador WHERE cod_avaliador = ?";
   
      try (PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
         stm.setInt(1, getCodAvaliador());
         try (ResultSet rs = stm.executeQuery();) {
            if (rs.next()) {
               setCpfAvaliador(rs.getString("cpf_avaliador"));
               setRgAvaliador(rs.getString("rg_avaliador"));
               setSexoAvaliador(rs.getString("sexo_avaliador"));
               setNomeAvaliador(rs.getString("nome_avaliador"));
               setDataNascAvaliador(rs.getDate("dt_nasci_avaliador"));
               
               instituicao = new Instituicao();
               instituicao.setCodInstituicao(rs.getInt("cod_instituicao"));
               instituicao.carregarInstituicao(conn);
               setInstituicao(instituicao);
               
               grauAcademico = new GrauAcademico();
               grauAcademico.setCodGrauAcademico(rs.getInt("cod_grau_academico"));
               grauAcademico.carregarGrauAcademico(conn);
               setGrauAcademico(grauAcademico);
               
               areaConhecimento = new AreaConhecimento();
               areaConhecimento.setCodAC(rs.getInt("cod_AC"));
               areaConhecimento.carregarAreaConhecimento(conn);
               setAreaConhecimento(areaConhecimento);
               
              
            } else {
               setCodAvaliador(-1);
               setCpfAvaliador(null);
               setRgAvaliador(null);
               setSexoAvaliador(null);
               setNomeAvaliador(null);
               setDataNascAvaliador(null);
               setInstituicao(null);
               setGrauAcademico(null);
               setAreaConhecimento(null);
               projetos = new ArrayList<>();
            }       
         } catch (Exception e) {
            e.printStackTrace();
         }
      } catch (SQLException e1) {
         System.out.print(e1.getStackTrace());
      }
   }
   
   public ArrayList<Avaliador> carregarAvaliadores(Connection conn) {
      String sqlSelect = 
         "SELECT * FROM avaliador ORDER BY cod_avaliador ASC";
      ArrayList<Avaliador> listaAvaliadores = new ArrayList<>();
   
      try (PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
         try (ResultSet rs = stm.executeQuery();) {
            while (rs.next()) {
               Avaliador avaliador = new Avaliador();
               avaliador.setNomeAvaliador(rs.getString("nome_avaliador"));
               avaliador.setCodAvaliador(rs.getInt("cod_avaliador"));
               avaliador.setCpfAvaliador(rs.getString("cpf_avaliador"));
               avaliador.setRgAvaliador(rs.getString("rg_avaliador"));
               avaliador.setSexoAvaliador(rs.getString("sexo_avaliador"));
               avaliador.setDataNascAvaliador(rs.getDate("dt_nasci_avaliador"));
               
               instituicao = new Instituicao(rs.getInt("cod_instituicao"));
               instituicao.carregarInstituicao(conn);
               avaliador.setInstituicao(instituicao);
               //System.out.println(instituicao);
               //System.out.println(avaliador.getInstituicao());
            
               grauAcademico = new GrauAcademico();
               grauAcademico.setCodGrauAcademico(rs.getInt("cod_grau_academico"));
               grauAcademico.carregarGrauAcademico(conn);
               avaliador.setGrauAcademico(grauAcademico);
            
               listaAvaliadores.add(avaliador);
            }
         } catch (Exception e) {
            e.printStackTrace();
         }
      } catch (SQLException e1) {
         System.out.print(e1.getStackTrace());
      }
      return listaAvaliadores;
   }

}