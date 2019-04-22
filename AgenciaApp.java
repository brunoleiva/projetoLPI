/*
Para rodar o programa, crie a classe AgenciaApp que ter� o m�todo main e um menu,
apresentado por meio de um JOptionPane, com as op��es:
1. Inserir um novo projeto.
2. Listar todos os projetos
3. Atualizar a avalia��o do projeto
4. Excluir um projeto
5. Listar um projeto pelo n�mero
6. Sair
Todos as entradas e sa�das de dados devem ser feitas por meio de JOptionPane.
*/
import javax.swing.JOptionPane;
public class AgenciaApp{
   public static void main(String [] args){
      Agencia agencia = new Agencia();
      int menu;
      
      do{
         menu = Integer.parseInt(JOptionPane.showInputDialog(
            "1. Inserir um novo projeto\n2. Listar todos os projetos\n3. Alterar o projeto"+
            "\n4. Excluir um projeto\n5. Listar um projeto pelo n�mero\n6. Sair"));
         if(menu == 1){  
         
            String titulo = JOptionPane.showInputDialog("Qual o titulo do projeto?");
            int codigo = Integer.parseInt(JOptionPane.showInputDialog("Qual o c�digo do projeto que deseja adicionar?"));
            double orcamento = Double.parseDouble(JOptionPane.showInputDialog("Qual o or�amento do projeto?(R$)"));
            String duracao =  JOptionPane.showInputDialog("Qual a dura��o do projeto?");
            
            boolean retorno = agencia.adicionarProjeto(codigo,orcamento,duracao,titulo);
            if(retorno == true)
            {
               JOptionPane.showMessageDialog(null, "Projeto adicionado!");
            }
            else if(retorno == false){
               JOptionPane.showMessageDialog(null, "N�o foi poss�vel inserir projeto: j� existe um projeto com esse c�digo!");
            }
            
            
         }
         else if(menu == 2){
         
            if(!(agencia.todosProjetos().equals(""))){
               JOptionPane.showMessageDialog(null, agencia.todosProjetos());      
            }
            else{
               JOptionPane.showMessageDialog(null, "Nenhum projeto adicionado ainda, volte para o menu e adicione um projeto!!");
            }
            
         }
         else if(menu == 3){
         
            int codigoProjeto = Integer.parseInt(JOptionPane.showInputDialog("Qual o c�digo do projeto que deseja alterar?"));
            
            if(!(agencia.buscarProjeto(codigoProjeto) < 0)){
               String alterar = JOptionPane.showInputDialog(agencia.mostrarProjeto(codigoProjeto) + "\n\nO que deseja alterar? Escreva sem acento por favor:(Codigo/Titulo/Or�amento/Dura�ao)");
               int posicao = agencia.buscarProjeto(codigoProjeto);
               if(alterar.equalsIgnoreCase("codigo")){
               
                  int novoCodProjeto = Integer.parseInt(JOptionPane.showInputDialog("Qual o novo codigo do projeto?"));
                  String titulo = agencia.getListaProjetos().get(posicao).getTitulo();
                  double orcamento = agencia.getListaProjetos().get(posicao).getOrcamento();
                  String duracao = agencia.getListaProjetos().get(posicao).getDuracao();
                  
                  boolean retorno = agencia.alterarProjeto(codigoProjeto, novoCodProjeto, orcamento, duracao, titulo);
                  if(retorno == true){
                     JOptionPane.showMessageDialog(null, "Projeto alterado com sucesso!!\n\n" + agencia.mostrarProjeto(novoCodProjeto) + "\n");
                  }
                  else if(retorno == false){
                     JOptionPane.showMessageDialog(null, "Erro ao tentar alterar projeto, certifique-se que o projeto desejado existe!!");
                  }
                  
               }
               else if(alterar.equalsIgnoreCase("titulo")){
               
                  String novoTitulo = JOptionPane.showInputDialog("Qual o novo titulo do projeto?");
                  int codProjeto = agencia.getListaProjetos().get(posicao).getCodProjeto();
                  double orcamento = agencia.getListaProjetos().get(posicao).getOrcamento();
                  String duracao = agencia.getListaProjetos().get(posicao).getDuracao();
                  
                  boolean retorno = agencia.alterarProjeto(codigoProjeto, codProjeto, orcamento, duracao, novoTitulo);
                  if(retorno == true){
                     JOptionPane.showMessageDialog(null, "Projeto alterado com sucesso!!\n\n" + agencia.mostrarProjeto(codProjeto) + "\n");
                  }
                  else if(retorno == false){
                     JOptionPane.showMessageDialog(null, "Erro ao tentar alterar projeto, certifique-se que o projeto desejado existe!!");
                  }
               
               
               }
               else if(alterar.equalsIgnoreCase("Or�amento")){
                  
                  double novoOrcamento = Double.parseDouble(JOptionPane.showInputDialog("Qual o novo or�amento do projeto?"));
                  String titulo = agencia.getListaProjetos().get(posicao).getTitulo();
                  int codProjeto = agencia.getListaProjetos().get(posicao).getCodProjeto();
                  String duracao = agencia.getListaProjetos().get(posicao).getDuracao();
                  
                  boolean retorno = agencia.alterarProjeto(codigoProjeto, codProjeto, novoOrcamento, duracao, titulo);
                  if(retorno == true){
                     JOptionPane.showMessageDialog(null, "Projeto alterado com sucesso!!\n\n" + agencia.mostrarProjeto(codProjeto) + "\n");
                  }
                  else if(retorno == false){
                     JOptionPane.showMessageDialog(null, "Erro ao tentar alterar projeto, certifique-se que o projeto desejado existe!!");
                  }
               
               
               }
               else if(alterar.equalsIgnoreCase("Dura�ao")){
               
                  String novaDuracao = JOptionPane.showInputDialog("Qual a nova dura��o do projeto?");
                  int codProjeto = agencia.getListaProjetos().get(posicao).getCodProjeto();
                  double orcamento = agencia.getListaProjetos().get(posicao).getOrcamento();
                  String titulo = agencia.getListaProjetos().get(posicao).getTitulo();
                  
                  boolean retorno = agencia.alterarProjeto(codigoProjeto, codProjeto, orcamento, novaDuracao, titulo);
                  if(retorno == true){
                     JOptionPane.showMessageDialog(null, "Projeto alterado com sucesso!!\n\n" + agencia.mostrarProjeto(codProjeto) + "\n");
                  }
                  else if(retorno == false){
                     JOptionPane.showMessageDialog(null, "Erro ao tentar alterar projeto, certifique-se que o projeto desejado existe!!");
                  }
                  
               }
               
            }
            else{
               JOptionPane.showMessageDialog(null, "C�digo digitado inv�lido ou projeto nao existente!!");
            }
         
            
         }
         else if(menu == 4){
         
            boolean retorno = agencia.removerProjeto(Integer.parseInt(JOptionPane.showInputDialog("Qual o c�digo do projeto que deseja remover?")));
            if(retorno == true){
               JOptionPane.showMessageDialog(null, "Projeto removido!!");
            }
            else if(retorno == false){
               JOptionPane.showMessageDialog(null, "Erro ao tentar remover projeto, certifique-se que o projeto desejado existe!!");
            }
            
          
         }
         else if(menu == 5){
         
            int codigoProjeto = Integer.parseInt(JOptionPane.showInputDialog("Qual o c�digo do projeto que deseja buscar?"));
            if(!(agencia.buscarProjeto(codigoProjeto) < 0)){
               JOptionPane.showMessageDialog(null, agencia.mostrarProjeto(codigoProjeto));
            }
            else{
               JOptionPane.showMessageDialog(null, "C�digo digitado inv�lido ou projeto nao existente!!");
            }
            
         
         }
         else if(menu == 6){
         
         }
         else {
            JOptionPane.showMessageDialog(null,
               "Opcao invalida");
         }
      }while(menu != 6);
   
   
   }

}