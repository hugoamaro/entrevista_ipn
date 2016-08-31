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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        TaskScheduler t = new TaskScheduler();
        
        String test = "A =>\n" +
"B => C\nC => F\nD => A\nE => B\n" +
"  F =>";
        
        String test1 = "A =>\n" +
"B => C\nC => F\nD => A\nE =>\n" +
"F => B";
        
        String test2 = "H=>A\nA=>D\nD=>E\nB=>C\nC=>F\nC=>D";
        String test3 = "H=>A\nA=>D\nD=>E\nB=>C\nC=>F\nC=>D\nH=>E";
        String test4 = "H=>A\nA=>D\nD=>E\nH=>E\nB=>C\nC=>F\nC=>D\nE=>H";
        String test5 = "A=>H\nA=>D\nD=>E\nH=>E\nB=>C\nC=>F\nC=>D\n";
        try {
            System.out.println(t.orderTasks(test));
            //System.out.println(t.orderTasks(test1)); //circular error
            System.out.println(t.orderTasks(test2));
            System.out.println(t.orderTasks(test3));
            //System.out.println(t.orderTasks(test4)); //circular error
            System.out.println(t.orderTasks(test5));
            
        } catch (DependsOnItSelfException ex) {
            Logger.getLogger(EntrevistaIPN.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CircularDependencyException ex) {
            Logger.getLogger(EntrevistaIPN.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
