import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;


public class GrauAcademico {
   private int codGrauAcademico;
   private String nomeGrauAcademico;
   
   //construtores
   public GrauAcademico(){
   
   }
   public GrauAcademico(int codGrauAcademico){
      this.codGrauAcademico = codGrauAcademico;
   }
   public GrauAcademico(int codGrauAcademico, String nomeGrauAcademico){
      this.codGrauAcademico = codGrauAcademico;
      this.nomeGrauAcademico = nomeGrauAcademico;
   }
   
   //codigo
   public int getCodGrauAcademico(){
      return codGrauAcademico;
   }
   public void setCodGrauAcademico(int codGrauAcademico){
      this.codGrauAcademico = codGrauAcademico;
   }
   
   //nome
   public String getNomeGrauAcademico(){
      return nomeGrauAcademico;
   }
   public void setNomeGrauAcademico(String nomeGrauAcademico){
      this.nomeGrauAcademico = nomeGrauAcademico;
   }
   
   //metodo to string
   
   @Override
   public String toString(){
      return "Grau academico [cod=" + codGrauAcademico + ", nome=" + nomeGrauAcademico + "]";
   }
   
   
   public void carregarGrauAcademico(Connection conn) {
      String sqlSelect = 
         "SELECT nome_grau_academico FROM grau_academico WHERE cod_grau_academico = ?";
   
      try (PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
         stm.setInt(1, getCodGrauAcademico());
         try (ResultSet rs = stm.executeQuery();) {
         
            if (rs.next()) {
               setNomeGrauAcademico(rs.getString("nome_grau_academico"));
            } else {
               setCodGrauAcademico(-1);
               setNomeGrauAcademico(null);
            }
 
         } catch (Exception e) {
            e.printStackTrace();
         }
      } catch (SQLException e1) {
         System.out.print(e1.getStackTrace());
      }
   }
   
    public String[] carregarGraisAcademicos(Connection conn) {
      String sqlSelect = 
         "SELECT nome_grau_academico FROM grau_academico";
      ArrayList<String> listaGraisAcademicos = new ArrayList<>();
      
      try (PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
         try (ResultSet rs = stm.executeQuery();) {
            int i = 0;
            
            while (rs.next()) {
            
              listaGraisAcademicos.add(rs.getString("nome_grau_academico"));
              i++;
            }
         } catch (Exception e) {
            e.printStackTrace();
         }
      } catch (SQLException e1) {
         System.out.print(e1.getStackTrace());
      }
      return listaGraisAcademicos.toArray(new String[listaGraisAcademicos.size()]);
      
   }
   
   public void carregarGrau(Connection conn) {
      String sqlSelect = 
         "SELECT cod_grau_academico FROM grau_academico WHERE nome_grau_academico = ?";
   
      try (PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
         stm.setString(1, getNomeGrauAcademico());
         try (ResultSet rs = stm.executeQuery();) {
         
            if (rs.next()) {
               setCodGrauAcademico(rs.getInt("cod_grau_academico"));
            } else {
               setCodGrauAcademico(-1);
               setNomeGrauAcademico(null);
            }
         } catch (Exception e) {
            e.printStackTrace();
         }
      } catch (SQLException e1) {
         System.out.print(e1.getStackTrace());
      }
   }
   
}