//


import java.util.Date;

public class Projetos{

   private int codProjeto;
   private String tituloProjeto;
   private double orcamentoProjeto;
   private String duracaoProjeto;
   private Date dtResposta;
   private Date dtEnvio;
   private String respostaAvaliacao;
   private Instituicao instituicao;
   private Pesquisador pesquisador;
   private Avaliador avaliador;
   private AreaConhecimento areaConhecimento;
   
   //construtores
   public Projetos(){
   
   }
   public Projetos(int codProjeto){
      this.codProjeto = codProjeto;
   }
   public Projetos(int codProjeto, String tituloProjeto, double orcamentoProjeto, String duracaoProjeto, Date dtEnvio, Instituicao instituicao, Pesquisador pesquisador, Avaliador avaliador, AreaConhecimento areaConhecimento){
      this.codProjeto = codProjeto;
      this.tituloProjeto = tituloProjeto;
      this.orcamentoProjeto = orcamentoProjeto;
      this.duracaoProjeto = duracaoProjeto;
      this.dtEnvio = dtEnvio;
      this.instituicao = instituicao;
      this.pesquisador = pesquisador;
      this.avaliador = avaliador;
      this.areaConhecimento = areaConhecimento;
   }  
   
   //acesso
   public int getCodProjeto(){
      return codProjeto;
   }
   public String getTituloProjeto(){
      return tituloProjeto;
   }
   public double getOrcamentoProjeto(){
      return orcamentoProjeto;
   }
   public String getDuracaoProjeto(){
      return duracaoProjeto;
   }
   public Date getDtResposta(){
      return dtResposta;
   }
   public Date getDtEnvio(){
      return dtEnvio;
   }
   public String getRespostaAvaliacao(){
      return respostaAvaliacao;
   }
   public Instituicao getInstituicao(){
      return instituicao;
   }
   public Pesquisador getPesquisador(){
      return pesquisador;
   }
   public Avaliador getAvaliador(){
      return avaliador;
   }
   public AreaConhecimento getAreaConhecimento(){
      return areaConhecimento;
   }
   
   //metodos modificadores
   public void setCodProjeto(int codProjeto){
      this.codProjeto = codProjeto;
   }
   
   public void setTituloProjeto(String tituloProjeto){
      this.tituloProjeto = tituloProjeto;
   }
   
   public void setOrcamentoProjeto(double orcamentoProjeto){
      this.orcamentoProjeto = orcamentoProjeto;
   }
   
   public void setDuracaoProjeto(String duracaoProjeto){
      this.duracaoProjeto = duracaoProjeto;
   }
   
   public void setDtResposta(Date dtResposta){
      this.dtResposta = dtResposta;
   }
   
   public void setDtEnvio(Date dtEnvio){
      this.dtEnvio = dtEnvio;
   }
   
   public void setRespostaAvaliacao(String respostaAvaliacao){
      this.respostaAvaliacao = respostaAvaliacao;
   }
   
   public void setInstituicao(Instituicao instituicao){
      this.instituicao = instituicao;
   }
   
   public void setAvaliador(Avaliador avaliador){
      this.avaliador = avaliador;
   }
   
   public void setAreaConhecimento(AreaConhecimento areaConhecimento){
      this.areaConhecimento = areaConhecimento;
   }
   
   
}
//VER O NEGÓCIO DO BOOLEAN
/*
    public boolean cadastrarProjeto(Connection conn) {
      String sqlInsert = "INSERT INTO projetos(cod_projeto, titulo, orcamento, duracao, dt_envio, cod_instituicao, cod_pesquisador, cod_avaliador, cod_AC) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
   
      try (PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
         stm.setString(1, getCodProjeto());
         stm.setString(2, getTituloProjeto());
         stm.setString(3, getOrcamentoProjeto());
         stm.setString(4, getDuracaoProjeto());
         stm.setString(5, new java.sql.Date(getDtEnvio().getTime()));
         stm.setString(6, instituicao.getCodInstituicao());
         stm.setDate(7, pesquisador.getCodPesquisador()));
         stm.setInt(8, avaliador.getCodAvaliador());
         stm.setInt(9, AreaConhecimento.getCod_AC());
         stm.execute();
      	// pegar o id criado no banco
         String query = "SELECT LAST_INSERT_ID()";
         try (PreparedStatement stm2 = conn.prepareStatement(query);
         		ResultSet rs = stm2.executeQuery();) {
            if (rs.next()) {
               setCodPesquisador(rs.getInt(1));
            } else {
               throw new SQLException(
                  	"Erro para conseguir o Codigo do Projeto");
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
   }*/

/*
   public boolean excluirProjeto(Connection conn) {
      String sqlDelete = "DELETE FROM projeto WHERE cod_projeto = ?";
      try (PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
      	// depois excluir o cliente
         stm.setInt(1, getCodProjeto());
         stm.execute();
      	// anular os projetos e os atributos
         setCodProjeto(-1);
         setTituloProjeto(null);
         setOrcamentoProjeto(null);
         setDuracaoProjeto(null);
         setDtEnvio(null);
         setCodInsituicao(null);
         setCodPesquisador(null);
         setCodAvaliador(null);
         setCodAC(null);         
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
   }*/
   /*
    ????????????????????????????
   public boolean atualizarProjeto(Connection conn){
      Cliente c = new Cliente(this.getIdCliente());
      c.carregar(conn);
      if(c.getNome() == null){
         return false;
      }
      String sqlUpdate = 
         "UPDATE cliente SET nome=?, fone=? WHERE id = ?";
   
      try (PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
         stm.setString(1, getNome());
         stm.setString(2, getFone());
         stm.setInt(3, getIdCliente());
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
   }*/
   
   //cod_projeto, titulo, orcamento, duracao, dt_envio, cod_instituicao, cod_pesquisador, cod_avaliador, cod_AC
/*
   public void carregarProjeto(Connection conn) {
      String sqlSelect = 
         "SELECT cod_projeto, titulo, orcamento, duracao, dt_envio, cod_instituicao, cod_pesquisador, cod_avaliador, cod_AC FROM Pesquisador WHERE cod_Projeto = ?";
   
      try (PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
         stm.setInt(1, getCodProjeto());
         try (ResultSet rs = stm.executeQuery();) {
            if (rs.next()) {
               setCodProjeto(rs.getString("cod_projeto"));
               setTituloProjeto(rs.getString("titulo"));
               setOrcamentoProjeto(rs.getString("orcamento"));
               setDuracaoProjeto(rs.getString("duracao"));
               setDtEnvio(rs.getDate("dt_envio"));
               setCodInstituicao(rs.getDate("cod_instituicao"));
               setCodPesquisador(rs.getDate("cod_pesquisador"));
               setCodAvaliador(rs.getDate("cod_avaliador"));
               setCodAC(rs.getDate("cod_AC"));
               
               instituicao = new Instituicao();
               instituicao.setCodInstituicao(rs.getInt("cod_instituicao"));
               instituicao.carregarInstituicao(conn);
               setInstituicao(instituicao);
               
               pesquisador = new Pesquisador();
               pesquisador.setCodPesquisador(rs.getInt("cod_pesquisador"));
               pesquisador.carregarPesquisador(conn);
               setPesquisador(pesquisador);

               avaliador = new Avaliador();
               avaliador.setCodAvaliador(rs.getInt("cod_avaliador"));
               avaliador.carregarAvaliador(conn);
               setAvaliador(avaliador);
                              
               areaConhecimento = new AreaConhecimento();
               areaConhecimento.setCodAC(rs.getInt("cod_AC"));
               areaConhecimento.carregarAreaConhecimento(conn);
               setAreaConhecimento(areaConhecimento);
               
//cod_projeto, titulo, orcamento, duracao, dt_envio, cod_instituicao, cod_pesquisador, cod_avaliador, cod_AC              
            } else {
               setCodProjeto(-1);
               setTituloProjeto(null);
               setOrcamento(null);
               setDuracao(null);
               setDtEnvio(null);
               setCodInstituicao(null);
               setCodPesquisador(null);
               setCodAvaliador(null);
               setCodAC(null);
               
            }       
         } catch (Exception e) {
            e.printStackTrace();
         }
      } catch (SQLException e1) {
         System.out.print(e1.getStackTrace());
      }
   }*/
   /*
    public ArrayList<Projeto> carregarProjetos(Connection conn, int codGAC) {
      String sqlSelect = 
         "SELECT projeto. FROM Projetos JOIN  ON area_conhecimento.cod_GAC = grande_area_conhecimento.cod_GAC WHERE grande_area_conhecimento.cod_GAC = ?";
      ArrayList<Projeto> listaProjeto = new ArrayList<>();
   
      try (PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
         stm.setInt(1, codGAC);
         try (ResultSet rs = stm.executeQuery();) {
            while (rs.next()) {
               Projeto area = new Projeto();
               area.setNomeAC(rs.getString("nome_area_conhecimento"));
               listaProjeto.add(area);
            }
         } catch (Exception e) {
            e.printStackTrace();
         }
      } catch (SQLException e1) {
         System.out.print(e1.getStackTrace());
      }
      return listaProjeto;
   }
   
   public void carregarProjeto(Connection conn) {
      String sqlSelect = 
         "SELECT cod_AC, nome_AC FROM area_conhecimento WHERE cod_AC = ?";
   
      try (PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
         stm.setInt(1, getCodAC());
         try (ResultSet rs = stm.executeQuery();) {
         
            if (rs.next()) {
               setCodAC(rs.getInt("cod_area_conhecimento"));
               setNomeAC(rs.getString("nome_AC"));
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
   */
