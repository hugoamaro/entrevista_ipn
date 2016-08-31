/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entrevistaipn.schedule;

import java.util.HashMap;

/**
 *
 * @author hamaro
 */
public class TaskScheduler {

    //hashmap to have a relative fast way of tracking nodes already inserted
    private HashMap<String, GraphNode> nodes;

    //private GraphNode linkToLast = null;

    public TaskScheduler() {
        nodes = new HashMap<>();

    }

    public String orderTasks(String tasksin) throws DependsOnItSelfException, CircularDependencyException {
        
        nodes.clear();
        

        String[] lines = tasksin.replaceAll(" ", "").split("\n");
        //Para cada linha dos dados
        for (int i = 0; i < lines.length; i++) {

            //System.out.println("in-"+lines[i]);
            //separamos em elemento da esquerda e direita
            String[] parts = lines[i].split("=>");
            
            if(parts.length==0)
                continue;

            GraphNode leftIn;
            boolean leftIsNew = true;
            boolean rightIsNew = true;
            //Criamos o elemento da esquerda se ainda não existe
            //e adicionamos ao HashMap
            if (!nodes.containsKey(parts[0])) {
                leftIn = new GraphNode(parts[0]);
                nodes.put(parts[0], leftIn);

            } else { //se já existe usamos a referência para o mesmo
                leftIn = nodes.get(parts[0]);
                leftIsNew = false;
            }

            //se há um elemento á direita (do qual o primeiro depende)
            if (parts.length > 1) {

                //testamos se são o mesmo cedo
                if (parts[0].equals(parts[1])) {
                    throw (new DependsOnItSelfException("Task and its predecessor were the same on "+lines[i]+"."));
                }

                GraphNode rightIn;
                //se o elemente á direita não existe
                //criamos um novo e adicionamso ao hashmap
                if (!nodes.containsKey(parts[1])) {
                    rightIn = new GraphNode(parts[1]);
                    nodes.put(parts[1], rightIn);

                    //uma vez que o elemento da direita ainda não existia
                    //não contém filhos ou pais com os quais temos que
                    //nos preocupar, simplesmente adicionamos como dependência
                    //ao da esquerda
                    leftIn.putLeft(rightIn);

                    
                } else { //se já existe usamos a sua referência
                    rightIn = nodes.get(parts[1]);
                    //verificamos se o elemento da esquerda é dependência do da direita
                    if (leftIn.hasAscendence(parts[1])) {
                        throw (new CircularDependencyException("Tasks have circular dependencies on "+lines[i]+"."));
                    } else {
                        //verificamos também se esta dependência ainda não existe
                        if (!leftIn.hasPrecedence(parts[1])) {
                            //se o da esquerda é novo
                            //podemos colocar para a direita do "da direita"
                            //uma vez que depende dele
                            if(leftIsNew)
                                rightIn.putRight(leftIn);
                            else //caso contrario ao da esquerda para a esquerda
                                leftIn.putLeft(rightIn);
                        }
                    }
                }

            }
        }
        StringBuilder sb = new StringBuilder(nodes.size());
        //System.out.println("Debug print solution:");
        for (GraphNode value : nodes.values()) {
            //System.out.println(value.toString());
            //marcar os elementos com printed permite percorrer
            //grafo a grafo em vez de elemento a elemento
            if(value.printed)
                continue;
            //percorre-mos o grafo (em àrvore) até ao topo (root parent)
            while (value.parent!=null){
                //System.out.println("looking "+value.key+" "+value.parent.key);
                value = value.parent;
            }
            //esta função percorre a àrvore do leftmost até ao rightmost (Depth first)
            //para imprimir as dependência na sequência correcta
            value.printLeft(sb);
            //System.out.print("\n");
        }
        //System.out.println("Debug print solution END");
        
        return sb.toString();
    }

}
