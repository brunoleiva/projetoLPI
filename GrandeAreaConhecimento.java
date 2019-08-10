import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class GrandeAreaConhecimento{
   private int codGAC;
   private String nomeGAC;

   public GrandeAreaConhecimento() {
   }

   public GrandeAreaConhecimento(int codGAC) {
      this.codGAC = codGAC;
   }

   public GrandeAreaConhecimento(int codGAC, String nomeGAC) {
      this.codGAC = codGAC;
      this.nomeGAC = nomeGAC;
   }
//gets sets codGAC
   public int getCodGAC() {
      return codGAC;
   }

   public void setCodGAC(int codGAC) {
      this.codGAC = codGAC;
   }
//gets sets nomeGAC
   public String getNomeGAC() {
      return nomeGAC;
   }

   public void setNomeGAC(String nomeGAC) {
      this.nomeGAC = nomeGAC;
   }

   @Override
   public String toString() {
      return "Grande Area de Conhecimento [Codigo=" + codGAC + ", Nome=" + nomeGAC + "]";
   }
   
   public String[] carregarGrandesAreas(Connection conn) {
      String sqlSelect = 
         "SELECT nome_gac FROM grande_area_conhecimento";
      ArrayList<String> listaGrandesAreas = new ArrayList<>();
      
      try (PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
         try (ResultSet rs = stm.executeQuery();) {
            
            while (rs.next()) {
            
              listaGrandesAreas.add(rs.getString("nome_gac"));

            }
         } catch (Exception e) {
            e.printStackTrace();
         }
      } catch (SQLException e1) {
         System.out.print(e1.getStackTrace());
      }
      return listaGrandesAreas.toArray(new String[listaGrandesAreas.size()]);
      
   }
   
    public int carregarGrandeArea(Connection conn) {
      String sqlSelect = 
         "SELECT cod_gac FROM grande_area_conhecimento WHERE nome_gac = ?";
   
      try (PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
         stm.setString(1, getNomeGAC());
         try (ResultSet rs = stm.executeQuery();) {
         
            if (rs.next()) {
               setCodGAC(rs.getInt("cod_gac"));
            } else {
               setCodGAC(-1);
               setNomeGAC(null);
            }
            return getCodGAC();
         } catch (Exception e) {
            e.printStackTrace();
         }
      } catch (SQLException e1) {
         System.out.print(e1.getStackTrace());
      }
      return getCodGAC();
   }
   

}