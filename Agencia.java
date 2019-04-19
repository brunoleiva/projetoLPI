/*A quarta classe ser� Agencia, que ter� um �nico atributo, que � um ArrayList do tipo Projeto.
A Agencia ter� m�todos para

6

� criar projetos: adiciona no arraylist;
� encontrar um projeto: faz uma busca sequencial no arraylist pelo c�digo do projeto;
� alterar projetos: usa o m�todo de busca para encontrar um projeto, remove do arraylist
e insere um novo com os novos dados;
� apagar projetos: usa o m�todo de busca para encontrar um projeto e remove do arraylist;
� listar um projeto: usa o m�todo de busca para encontrar um projeto e mostrar todos os
seus atributos;
� listar todos os projetos: percorrer todo o arraylist e mostrar os atributos de cada projeto.
*/
import java.util.ArrayList;

public class Agencia{
   private ArrayList <Projetos> listaProjetos;
   
   public Agencia(){
      listaProjetos = new ArrayList<Projetos>();
   }
   
   //m�todo get para resgatar o array em otura classe
   public ArrayList <Projetos> getListaProjetos(){
      return listaProjetos;
   }
   
   //m�todo apra adicionar um projeto
   public int adicionarProjeto(int codProjeto, double orcamento, String duracao, String titulo){
      if(buscarProjeto(codProjeto) == -1){
         Projetos novoProjeto = new Projetos(codProjeto, orcamento, duracao, titulo);
         listaProjetos.add(novoProjeto);
         return 1;
      }else{
         return -1;
      }
   }
   
   // metodo para buscar a posi��o do projeto no array
   public int buscarProjeto(int codProjeto){
      for (int i = 0; i < listaProjetos.size(); i++){
         if (listaProjetos.get(i).getCodProjeto() == codProjeto){
            return i;
         }
      }
      return -1; // nao achou nada
   }
   
   // m�todo de altera��o de um projeto
   public boolean alterarProjeto(int codAntigo, int codNovo, double orcamento, String duracao, String titulo){
      int posicao = buscarProjeto(codAntigo);
      if(!(posicao == -1)){
         listaProjetos.remove(posicao);
         adicionarProjeto(codNovo, orcamento, duracao, titulo);
         return true;
      }else{
         return false;
      }
   
      
      
   }
   
   //m�todo de remo��o de projeto
   public boolean removerProjeto(int codProjeto){
      int posicao = buscarProjeto(codProjeto);
      if(!(posicao == -1)){
         listaProjetos.remove(posicao);
         return true;
      }else{
         return false;
      }
   }

   public String mostrarProjeto(int codProjeto){
      int posicao = buscarProjeto(codProjeto); 
      String dadosProjeto = listaProjetos.get(posicao).mostrarUmProjeto();
      return dadosProjeto;
   }
   
   
   //� listar todos os projetos: percorrer todo o arraylist e mostrar os atributos de cada projeto.
   public String todosProjetos(){
      String saida = "";
          
      for(Projetos projeto: listaProjetos){
         saida += projeto.mostrarDados();
      }
      return saida;
   }

   
   
     
}