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
    public GraphNode parent;
    public GraphNode left;
    public GraphNode right;
    
    public String key;
    
    public GraphNode(String k){
        key = k;
    }
    
    public boolean hasPrecedence(String oKey){
        if(key.equals(oKey)){
            return true;
        }
        if(left!=null){
            return left.hasPrecedence(oKey);
        }else {
            return false;
        }
    }
    
    public boolean hasAscendence(String oKey){
        if(key.equals(oKey)){
            return true;
        }
        boolean ret = false;
        if(left!=null){
            ret= ret||left.hasAscendence(oKey);
        }
        if(parent!=null){
            ret= ret||parent.hasAscendence(oKey);
        }
        return ret;
    }
    
    public void putRight(GraphNode toPut){
        if(right==null){
            right = toPut;
            toPut.parent = this;
        }else {
            right.putRight(toPut);
        }
    }
    
    public void putLeft(GraphNode toPut){
        if(left==null){
            left = toPut;
            toPut.parent = this;
        }else {
            left.putRight(toPut);
        }
    }
    
    public void printLeft(){
        this.printed = true;
        if(left!=null)
            left.printLeft();
        System.out.print(key);
        if(right!=null)
            right.printLeft();
    }
}
