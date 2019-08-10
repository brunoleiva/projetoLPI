import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

public class Pesquisador{
   //atributos
   private int codPesquisador;
   private String cpfPesquisador;
   private String nomePesquisador;
   private String rgPesquisador;
   private String sexoPesquisador;
   private Date dataNascPesquisador;
   private Instituicao instituicao;
   private GrauAcademico grauAcademico;
   //Construtores
   public Pesquisador(){
   }
   
   public Pesquisador(int codPesquisador){
      this.codPesquisador = codPesquisador;
   }
   
    public Pesquisador(int codPesquisador, String nomePesquisador) {
      this.codPesquisador = codPesquisador;
      this.nomePesquisador = nomePesquisador;
   }
   
   public Pesquisador(int codPesquisador, String cpf, String nomePesquisador, String rgPesquisador, String sexoPesquisador, Date dataNasc, Instituicao instituicao, GrauAcademico grauAcademico){
      this.codPesquisador = codPesquisador;
      this.cpfPesquisador = cpf;
      this.nomePesquisador = nomePesquisador;
      this.rgPesquisador = rgPesquisador;
      this.sexoPesquisador = sexoPesquisador;
      this.dataNascPesquisador = dataNasc;
      this.instituicao = instituicao;
      this.grauAcademico = grauAcademico;
   }
   
   //gets
   public int getCodPesquisador(){
      return this.codPesquisador;
   }
   
   public String getCpfPesquisador(){
      return this.cpfPesquisador;
   }
   
   public String getNomePesquisador(){
      return this.nomePesquisador;
   }
   
   public String getRgPesquisador(){
      return this.rgPesquisador;
   }
   
   public String getSexoPesquisador(){
      return this.sexoPesquisador;
   }
   
   public Date getDataNascPesquisador(){
      return this.dataNascPesquisador;
   }
   
   public Instituicao getInstituicao(){
      return this.instituicao;
   }
   
   public GrauAcademico getGrauAcademico(){
      return grauAcademico;
   }
   
   //sets
   public void setCodPesquisador(int codPesquisador){
      this.codPesquisador = codPesquisador;
   }
   
   public void setCpfPesquisador(String cpfPesquisador){
      this.cpfPesquisador = cpfPesquisador;
   }
   
   public void setNomePesquisador(String nomePesquisador){
      this.nomePesquisador = nomePesquisador;
   }
   
   public void setRgPesquisador(String rgPesquisador){
      this.rgPesquisador = rgPesquisador;
   }
   
   public void setSexoPesquisador(String sexoPesquisador){
      this.sexoPesquisador = sexoPesquisador;
   }
   
   public void setDataNascPesquisador(Date dataNascPesquisador){
      this.dataNascPesquisador = dataNascPesquisador;
   }
   
   public void setInstituicao(Instituicao instituicao){
      this.instituicao = instituicao;
   }
   
   public void setGrauAcademico(GrauAcademico grauAcademico){
      this.grauAcademico = grauAcademico;
   }
   
   //ToString
   @Override
   public String toString() {
      return "Pesquisador [Codigo=" + codPesquisador + ", Nome=" + nomePesquisador + ", CPF=" + cpfPesquisador + ", RG= "+rgPesquisador+", Sexo= "+ sexoPesquisador+
      ", Data de Nascimento= "+dataNascPesquisador+"]";
   }
   
   public boolean cadastrarPesquisador(Connection conn) {
      String sqlInsert = "INSERT INTO pesquisador(cpf_pesquisador, nome_pesquisador, rg_pesquisador, sexo_pesquisador, dt_nasci_pesquisador, cod_instituicao, cod_grau_academico) VALUES (?, ?, ?, ?, ?, ?, ?)";
   
      try (PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
         stm.setString(1, getCpfPesquisador());
         stm.setString(2, getNomePesquisador());
         stm.setString(3, getRgPesquisador());
         stm.setString(4, getSexoPesquisador());
         stm.setDate(5, new java.sql.Date(getDataNascPesquisador().getTime()));
         stm.setInt(6, instituicao.getCodInstituicao());
         stm.setInt(7, grauAcademico.getCodGrauAcademico());
         stm.execute();
      	// pegar o id criado no banco
         String query = "SELECT LAST_INSERT_ID()";
         try (PreparedStatement stm2 = conn.prepareStatement(query);
         		ResultSet rs = stm2.executeQuery();) {
            if (rs.next()) {
               setCodPesquisador(rs.getInt(1));
            } else {
               throw new SQLException(
                  	"Erro para conseguir o id do Pesquisador");
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
   
    
   public boolean excluirPesquisador(Connection conn) {
      String sqlDelete = "DELETE FROM Pesquisador WHERE cod_Pesquisador = ?";
      try (PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
      	// depois excluir o cliente
         stm.setInt(1, getCodPesquisador());
         stm.execute();
      	// anular os projetos e os atributos
         setCodPesquisador(-1);
         setCpfPesquisador(null);
         setRgPesquisador(null);
         setSexoPesquisador(null);
         setNomePesquisador(null);
         setDataNascPesquisador(null);
         setInstituicao(null);
         setGrauAcademico(null);
         
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
   
   public boolean atualizarPesquisador(Connection conn){
      String sqlUpdate = 
         "UPDATE pesquisador SET cpf_pesquisador = ?, rg_pesquisador = ?, sexo_pesquisador = ?, nome_pesquisador = ?, dt_nasci_pesquisador = ?, cod_instituicao = ?, cod_grau_academico = ? WHERE cod_pesquisador = ?";
   
      try (PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
          stm.setString(1, getCpfPesquisador());
         stm.setString(2, getRgPesquisador());
         stm.setString(3, getSexoPesquisador());
         stm.setString(4, getNomePesquisador());
         stm.setDate(5, new java.sql.Date(getDataNascPesquisador().getTime()));
         stm.setInt(6, instituicao.getCodInstituicao());
         stm.setInt(7, grauAcademico.getCodGrauAcademico());
         stm.setInt(8, getCodPesquisador());
         
        /* System.out.println(getCpfPesquisador() + getRgPesquisador() + getSexoPesquisador() +
         getNomePesquisador() + getDataNascPesquisador() + instituicao.getCodInstituicao() +
         grauAcademico.getCodGrauAcademico() + getCodPesquisador());*/
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
   
   public void carregarPesquisador(Connection conn) {
      String sqlSelect = 
         "SELECT cpf_Pesquisador, rg_Pesquisador, sexo_Pesquisador, nome_Pesquisador, dt_nasci_Pesquisador, cod_instituicao, cod_grau_academico FROM Pesquisador WHERE cod_Pesquisador = ?";
   
      try (PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
         stm.setInt(1, getCodPesquisador());
         try (ResultSet rs = stm.executeQuery();) {
            if (rs.next()) {
               setCpfPesquisador(rs.getString("cpf_Pesquisador"));
               setRgPesquisador(rs.getString("rg_Pesquisador"));
               setSexoPesquisador(rs.getString("sexo_Pesquisador"));
               setNomePesquisador(rs.getString("nome_Pesquisador"));
               setDataNascPesquisador(rs.getDate("dt_nasci_Pesquisador"));
               
               instituicao = new Instituicao();
               instituicao.setCodInstituicao(rs.getInt("cod_instituicao"));
               instituicao.carregarInstituicao(conn);
               setInstituicao(instituicao);
               
               grauAcademico = new GrauAcademico();
               grauAcademico.setCodGrauAcademico(rs.getInt("cod_grau_academico"));
               grauAcademico.carregarGrauAcademico(conn);
               setGrauAcademico(grauAcademico);

               
              
            } else {
               setCodPesquisador(-1);
               setCpfPesquisador(null);
               setRgPesquisador(null);
               setSexoPesquisador(null);
               setNomePesquisador(null);
               setDataNascPesquisador(null);
               setInstituicao(null);
               setGrauAcademico(null);
            }       
         } catch (Exception e) {
            e.printStackTrace();
         }
      } catch (SQLException e1) {
         System.out.print(e1.getStackTrace());
      }
   }
   
   public ArrayList<Pesquisador> carregarPesquisadores(Connection conn) {
      String sqlSelect = 
         "SELECT * FROM pesquisador ORDER BY cod_pesquisador ASC";
      ArrayList<Pesquisador> listaPesquisadores = new ArrayList<>();
   
      try (PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
         try (ResultSet rs = stm.executeQuery();) {
            while (rs.next()) {
               Pesquisador pesquisador = new Pesquisador();
               pesquisador.setNomePesquisador(rs.getString("nome_pesquisador"));
               pesquisador.setCodPesquisador(rs.getInt("cod_pesquisador"));
               pesquisador.setCpfPesquisador(rs.getString("cpf_pesquisador"));
               pesquisador.setRgPesquisador(rs.getString("rg_pesquisador"));
               pesquisador.setSexoPesquisador(rs.getString("sexo_pesquisador"));
               pesquisador.setDataNascPesquisador(rs.getDate("dt_nasci_pesquisador"));
               
               instituicao = new Instituicao();
               instituicao.setCodInstituicao(rs.getInt("cod_instituicao"));
               instituicao.carregarInstituicao(conn);
               pesquisador.setInstituicao(instituicao);
               
               grauAcademico = new GrauAcademico();
               grauAcademico.setCodGrauAcademico(rs.getInt("cod_grau_academico"));
               grauAcademico.carregarGrauAcademico(conn);
               pesquisador.setGrauAcademico(grauAcademico);

               listaPesquisadores.add(pesquisador);
            }
         } catch (Exception e) {
            e.printStackTrace();
         }
      } catch (SQLException e1) {
         System.out.print(e1.getStackTrace());
      }
      return listaPesquisadores;
   }
   
}