
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

public class AreaConhecimento{
   private int codAC;
   private String nomeAC;
   private GrandeAreaConhecimento grandeAreaConhecimento;
   
   public AreaConhecimento() {
   }

   public AreaConhecimento(int codAC) {
      this.codAC = codAC;
   }

   public AreaConhecimento(int codGAC, String nomeGAC) {
      this.codAC = codAC;
      this.nomeAC = nomeAC;
      this.grandeAreaConhecimento = grandeAreaConhecimento;
   }
//gets sets codAC
   public int getCodAC() {
      return codAC;
   }

   public void setCodAC(int codAC) {
      this.codAC = codAC;
   }
//gets sets nomeAC
   public String getNomeAC() {
      return nomeAC;
   }

   public void setNomeAC(String nomeAC) {
      this.nomeAC = nomeAC;
   }
//gets sets GrandeAreaConhecimento
   public GrandeAreaConhecimento getGrandeAreaConhecimento(){
      return grandeAreaConhecimento;
   }
   
   public void setGrandeAreaConhecimento(GrandeAreaConhecimento grandeAreaConhecimento){
      this.grandeAreaConhecimento = grandeAreaConhecimento;
   }
   

   @Override
   public String toString() {
      return "Area de Conhecimento [CodigoAC=" + codAC + ", Nome=" + nomeAC + "]";
   }
   
   public String[] carregarAreasConhecimento(Connection conn, int codGac) {
      String sqlSelect = 
         "SELECT area_conhecimento.nome_ac FROM area_conhecimento join grande_area_conhecimento ON area_conhecimento.cod_gac = grande_area_conhecimento.cod_gac WHERE grande_area_conhecimento.cod_gac = ?";
      ArrayList<String> listaAreaConhecimento = new ArrayList<>();
   
      try (PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
         stm.setInt(1, codGac);
         try (ResultSet rs = stm.executeQuery();) {
            while (rs.next()) {
               listaAreaConhecimento.add(rs.getString("nome_ac"));

            }
         } catch (Exception e) {
            e.printStackTrace();
         }
      } catch (SQLException e1) {
         System.out.print(e1.getStackTrace());
      }
      return listaAreaConhecimento.toArray(new String[listaAreaConhecimento.size()]);

   }
   
   
   public void carregarAreaConhecimento(Connection conn) {
      String sqlSelect = 
         "SELECT area_conhecimento.cod_AC, area_conhecimento.nome_AC, grande_area_conhecimento.cod_gac, grande_area_conhecimento.nome_gac FROM area_conhecimento join grande_area_conhecimento on area_conhecimento.cod_gac = grande_area_conhecimento.cod_gac WHERE cod_ac = ?";
   
      try (PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
         stm.setInt(1, getCodAC());
         try (ResultSet rs = stm.executeQuery();) {
         
            if (rs.next()) {
               setCodAC(rs.getInt("cod_AC"));
               setNomeAC(rs.getString("nome_AC"));
               
               GrandeAreaConhecimento gac = new GrandeAreaConhecimento(rs.getInt("cod_gac"), rs.getString("nome_gac"));
               setGrandeAreaConhecimento(gac);
            } else {
               setGrandeAreaConhecimento(null);
               setCodAC(-1);
               setNomeAC(null);
            }
         
         } catch (Exception e) {
            e.printStackTrace();
         }
      } catch (SQLException e1) {
         System.out.print(e1.getStackTrace());
      }
   }
   
   public void carregarArea(Connection conn) {
      String sqlSelect = 
         "SELECT cod_ac FROM area_conhecimento WHERE nome_ac = ?";
   
      try (PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
         stm.setString(1, getNomeAC());
         try (ResultSet rs = stm.executeQuery();) {
         
            if (rs.next()) {
               setCodAC(rs.getInt("cod_ac"));
            } else {
               setCodAC(-1);
               setNomeAC(null);
            }
         } catch (Exception e) {
            e.printStackTrace();
         }
      } catch (SQLException e1) {
         System.out.print(e1.getStackTrace());
      }
   }
   
   
   
}