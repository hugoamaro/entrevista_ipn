/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entrevistaipn.schedule;

/**
 *
 * @author hamaro
 */
public class GraphNode {
    
    public boolean printed = false;
    public GraphNode parent = null;
    public GraphNode left = null;
    public GraphNode right = null;
    
    public String key;
    
    public GraphNode(String k){
        key = k;
    }
    
    public boolean hasPrecedence(String oKey){
        //System.out.println("check prec: "+key+" - "+oKey);
        if(key.equals(oKey)){
            return true;
        }
        boolean ret = false;
        if(left!=null){
            ret= ret||left.hasPrecedence(oKey);
        }
        if(parent!=null){
            if(parent.left==null||!parent.left.key.equals(this.key))
                ret= ret||parent.hasPrecedence(oKey);
        }
        return ret;
    }
    
    public boolean hasAscendence(String oKey){
        //System.out.println("check acend: "+key+" - "+oKey);
        if(key.equals(oKey)){
            return true;
        }
        boolean ret = false;
        if(right!=null){
            ret= ret||right.hasAscendence(oKey);
        }
        if(parent!=null){
            if(parent.right==null||!parent.right.key.equals(this.key))
                ret= ret||parent.hasAscendence(oKey);
        }
        return ret;
    }
    
    public void putRight(GraphNode toPut){
        if(right==null){
            System.out.println(key+" right "+toPut.key);
            right = toPut;
            //este elemento vai ser parent do toPut
            //logo temos que verificar se ele já tinha parent
            //e tratar o parent de acordo
            if(toPut.parent!=null){
                if(parent.right!=null&&parent.right.key.equals(this.key))
                    toPut.putLeft(toPut.parent);
                else
                    toPut.putRight(toPut.parent);
            }
            toPut.parent = this;
        }else {
            right.putRight(toPut);
        }
    }
    
    public void putLeft(GraphNode toPut){
        if(left==null){
            System.out.println(key+" left "+toPut.key);
            left = toPut;
            if(toPut.parent!=null){
                if(parent.right!=null&&parent.right.key.equals(this.key))
                    toPut.putLeft(toPut.parent);
                else
                    toPut.putRight(toPut.parent);
            }
            toPut.parent = this;
        }else {
            left.putLeft(toPut);
        }
    }
    
    public void printLeft(StringBuilder sb){
        if(!printed){
            this.printed = true;
            if(left!=null)
                left.printLeft(sb);
            System.out.print(key);
            sb.append(key);
            if(right!=null)
                right.printLeft(sb);
        }
    }

    @Override
    public String toString() {
        return "GraphNode{" + "printed=" + printed + " key=" + key + '}';
    }
    
    
}
