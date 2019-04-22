/*
Para rodar o programa, crie a classe AgenciaApp que terá o método main e um menu,
apresentado por meio de um JOptionPane, com as opções:
1. Inserir um novo projeto.
2. Listar todos os projetos
3. Atualizar a avaliação do projeto
4. Excluir um projeto
5. Listar um projeto pelo número
6. Sair
Todos as entradas e saídas de dados devem ser feitas por meio de JOptionPane.
*/
import javax.swing.JOptionPane;
public class AgenciaApp{
   public static void main(String [] args){
      Agencia agencia = new Agencia();
      int menu;
      
      do{
         menu = Integer.parseInt(JOptionPane.showInputDialog(
            "1. Inserir um novo projeto\n2. Listar todos os projetos\n3. Alterar o projeto"+
            "\n4. Excluir um projeto\n5. Listar um projeto pelo número\n6. Sair"));
         if(menu == 1){  
         
            String titulo = JOptionPane.showInputDialog("Qual o titulo do projeto?");
            int codigo = Integer.parseInt(JOptionPane.showInputDialog("Qual o código do projeto que deseja adicionar?"));
            double orcamento = Double.parseDouble(JOptionPane.showInputDialog("Qual o orçamento do projeto?(R$)"));
            String duracao =  JOptionPane.showInputDialog("Qual a duração do projeto?");
            
            boolean retorno = agencia.adicionarProjeto(codigo,orcamento,duracao,titulo);
            if(retorno == true)
            {
               JOptionPane.showMessageDialog(null, "Projeto adicionado!");
            }
            else if(retorno == false){
               JOptionPane.showMessageDialog(null, "Não foi possível inserir projeto: já existe um projeto com esse código!");
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
         
            int codigoProjeto = Integer.parseInt(JOptionPane.showInputDialog("Qual o código do projeto que deseja alterar?"));
            
            if(!(agencia.buscarProjeto(codigoProjeto) < 0)){
               String alterar = JOptionPane.showInputDialog(agencia.mostrarProjeto(codigoProjeto) + "\n\nO que deseja alterar? Escreva sem acento por favor:(Codigo/Titulo/Orçamento/Duraçao)");
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
               else if(alterar.equalsIgnoreCase("Orçamento")){
                  
                  double novoOrcamento = Double.parseDouble(JOptionPane.showInputDialog("Qual o novo orçamento do projeto?"));
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
               else if(alterar.equalsIgnoreCase("Duraçao")){
               
                  String novaDuracao = JOptionPane.showInputDialog("Qual a nova duração do projeto?");
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
               JOptionPane.showMessageDialog(null, "Código digitado inválido ou projeto nao existente!!");
            }
         
            
         }
         else if(menu == 4){
         
            boolean retorno = agencia.removerProjeto(Integer.parseInt(JOptionPane.showInputDialog("Qual o código do projeto que deseja remover?")));
            if(retorno == true){
               JOptionPane.showMessageDialog(null, "Projeto removido!!");
            }
            else if(retorno == false){
               JOptionPane.showMessageDialog(null, "Erro ao tentar remover projeto, certifique-se que o projeto desejado existe!!");
            }
            
          
         }
         else if(menu == 5){
         
            int codigoProjeto = Integer.parseInt(JOptionPane.showInputDialog("Qual o código do projeto que deseja buscar?"));
            if(!(agencia.buscarProjeto(codigoProjeto) < 0)){
               JOptionPane.showMessageDialog(null, agencia.mostrarProjeto(codigoProjeto));
            }
            else{
               JOptionPane.showMessageDialog(null, "Código digitado inválido ou projeto nao existente!!");
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