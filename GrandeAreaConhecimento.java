public class GrandeAreaConhecimento{
   //atributos
   private int codigo;
   private String nome;
   private AreaConhecimento ac;
   //sets
   public void setCodigo(int codigo){
      this.codigo = codigo;
   }
   public void setNome(String nome){
      this.nome = nome;
   }
   public void setAC(AreaConhecimento ac){
      this.ac = ac;
   }
   //gets
   public int getCodigo(){
      return this.codigo;
   }
   public String getNome(){
      return this.nome;
   }
   public AreaConhecimento getAC(){
      return ac;
   }
}