import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;


public class Instituicao{
   private int codInstituicao;
   private String nomeInstituicao;
   
   
   //construtores
   public Instituicao(){
   }
   public Instituicao(int codInstituicao){
      this.codInstituicao = codInstituicao;
   }
   public Instituicao(int codInstituicao, String nomeInstituicao){
      this.codInstituicao = codInstituicao;
      this.nomeInstituicao = nomeInstituicao;
   }
   
   //codigo
   public int getCodInstituicao(){
      return codInstituicao;
   }
   public void setCodInstituicao(int codInstituicao){
      this.codInstituicao = codInstituicao;
   }
   
   //nome
   public String getNomeInstituicao(){
      return nomeInstituicao;
   }
   public void setNomeInstituicao(String nomeInstituicao){
      this.nomeInstituicao = nomeInstituicao;
   }
   
   //método to String
   
   @Override
   public String toString(){
      return "Codigo da Instituicao [cod=" + codInstituicao + " , nome=" + nomeInstituicao + "]";
   }
   
   public void carregarInstituicao(Connection conn) {
      String sqlSelect = 
         "SELECT nome_instituicao FROM instituicao WHERE cod_instituicao = ?";
   
      try (PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
         stm.setInt(1, getCodInstituicao());
         try (ResultSet rs = stm.executeQuery();) {
         
            if (rs.next()) {
               setNomeInstituicao(rs.getString("nome_instituicao"));
            } else {
               setCodInstituicao(-1);
               setNomeInstituicao(null);
            }
 
         } catch (Exception e) {
            e.printStackTrace();
         }
      } catch (SQLException e1) {
         System.out.print(e1.getStackTrace());
      }
   }
   
   
   public String[] carregarInstituicoes(Connection conn) {
      String sqlSelect = 
         "SELECT instituicao.nome_instituicao FROM instituicao";
      ArrayList<String> listaInstituicao = new ArrayList<>();
      
      try (PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
         try (ResultSet rs = stm.executeQuery();) {
            int i = 0;
            
            while (rs.next()) {
            
              listaInstituicao.add(rs.getString("nome_instituicao"));
              i++;
            }
         } catch (Exception e) {
            e.printStackTrace();
         }
      } catch (SQLException e1) {
         System.out.print(e1.getStackTrace());
      }
      return listaInstituicao.toArray(new String[listaInstituicao.size()]);
      
   }

   public void carregarInsti(Connection conn) {
      String sqlSelect = 
         "SELECT cod_instituicao FROM instituicao WHERE nome_instituicao = ?";
   
      try (PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
         stm.setString(1, getNomeInstituicao());
         try (ResultSet rs = stm.executeQuery();) {
         
            if (rs.next()) {
               setCodInstituicao(rs.getInt("cod_instituicao"));
            } else {
               setCodInstituicao(-1);
               setNomeInstituicao(null);
            }
         } catch (Exception e) {
            e.printStackTrace();
         }
      } catch (SQLException e1) {
         System.out.print(e1.getStackTrace());
      }
   }


}