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
        
        try {
            t.orderTasks(test);
        } catch (DependsOnItSelfException ex) {
            Logger.getLogger(EntrevistaIPN.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CircularDependencyException ex) {
            Logger.getLogger(EntrevistaIPN.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
