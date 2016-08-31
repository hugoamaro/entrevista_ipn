/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entrevistaipn;

import entrevistaipn.schedule.CircularDependencyException;
import entrevistaipn.schedule.DependsOnItSelfException;
import entrevistaipn.schedule.TaskScheduler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hamaro
 */
public class EntrevistaIPN {
    
    private TaskScheduler t;
    
    public EntrevistaIPN(){
        t = new TaskScheduler();
        runtests();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        EntrevistaIPN e = new EntrevistaIPN();
    }
    
    public void runtests(){
        
        String test = "A =>\nB => C\nC => F\nD => A\nE => B\nF =>";
        
        String test1 = "A =>\nB => C\nC => F\nD => A\nE =>\nF => B";
        
        String test2 = "H=>A\nA=>D\nD=>E\nB=>C\nC=>F\nC=>D";
        String test3 = "H=>A\nA=>D\nD=>E\nB=>C\nC=>F\nC=>D\nH=>E";
        String test4 = "H=>A\nA=>D\nD=>E\nH=>E\nB=>C\nC=>F\nC=>D\nE=>H";
        String test5 = "A=>H\nA=>D\nD=>E\nH=>E\nB=>C\nC=>F\nC=>D\n";
        
        String test6 = "F=>D\nD=>X\nB=>C\nA=>B\nF=>B\nJ=>K\nI=>J\nL=>J\nR=>K\nM=>D\nN=>F\nR=>C";
        String test7 = "F=>D\nD=>X\nB=>C\nA=>B\nF=>B\nJ=>K\nI=>J\nL=>J\nR=>K\nM=>D\nN=>F\nR=>B";
        String test8 = "F=>D\nD=>X\nB=>C\nA=>B\nF=>B\nJ=>K\nI=>J\nL=>J\nR=>K\nM=>D\nN=>F\nM=>C";
       
            testTestCase(test);
//            testTestCase(test1); //circular error
            testTestCase(test2);
            testTestCase(test3);
//            testTestCase(test4); //circular error
            testTestCase(test5);
            testTestCase(test6);
            testTestCase(test7);
            testTestCase(test8);
       
    }
    
    
    public void testTestCase(String testIn){
        String result;
        System.out.println("Test start...");
        try {
            result = t.orderTasks(testIn);
            
            
            String[] lines = testIn.replaceAll(" ", "").split("\n");
            //Para cada linha dos dados
            for (int i = 0; i < lines.length; i++) {

                //System.out.println("in-"+lines[i]);
                //separamos em elemento da esquerda e direita
                String[] parts = lines[i].split("=>");
                if(parts.length==0) {
                } else if(parts.length==1)
                    System.out.println(lines[i]+" test->"+result.contains(parts[0]));
                else
                    System.out.println(lines[i]+" test->"+isBeforeOf(result,parts[0],parts[1]));
            }
        } catch (DependsOnItSelfException ex) {
            Logger.getLogger(EntrevistaIPN.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CircularDependencyException ex) {
            Logger.getLogger(EntrevistaIPN.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
        
    public boolean isBeforeOf(String hay, String one, String two){
        if (!hay.contains(one)||!hay.contains(two)){
            return false;
        }
        return hay.indexOf(one) > hay.indexOf(two);
    }
    
}
