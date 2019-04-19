
//Projetos

public class Projetos{
   
   private int codProjeto;
   private double orcamento;
   private String duracao;
   private String titulo;
   private GrandeAreaConhecimento GrandeAreaConhecimento;
   
   
   public Projetos(int codProjeto, double orcamento, String duracao, String titulo){
      this.orcamento = orcamento;
      this.codProjeto = codProjeto;
      this.duracao = duracao;
      this.titulo = titulo;
   }
      //metodos de acesso
      
      public double getOrcamento(){
         return this.orcamento;
      }
      public int getCodProjeto (){
         return this.codProjeto;
      }
      public String getDuracao (){
         return this.duracao;
      }
      public String getTitulo (){
         return this.titulo;
      }
      public GrandeAreaConhecimento getGrandeAreaConhecimento (){
         return this.GrandeAreaConhecimento;
      }
   
      //metodos modificadores
      
      public void setOrcamento(double orcamento){
         this.orcamento = orcamento;
      }
      public void setCodProjeto(int codProjeto){
         this.codProjeto = codProjeto;
      }
      public void setDuracao(String duracao){
         this.duracao = duracao;
      }
      public void setTitulo(String titulo){
         this.titulo = titulo;
      }
      public void setGrandeAreaConhecimento (GrandeAreaConhecimento GrandeAreaConhecimento){
         this.GrandeAreaConhecimento = GrandeAreaConhecimento;
      }
      
      public String mostrarDados(){
         return "Código do projeto: " + codProjeto + "\nTítulo: " + titulo + "\nOrçamento: R$" + orcamento + "\nDuração: " + duracao + "\n-------------------------------------\n";   
      }
      public String mostrarUmProjeto(){
         return "Código do projeto: " + codProjeto + "\nTítulo: " + titulo + "\nOrçamento: R$" + orcamento + "\nDuração: " + duracao;   
      }
}