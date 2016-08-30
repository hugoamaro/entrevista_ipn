/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entrevistaipn.schedule;

import entrevistaipn.naive.*;

/**
 *
 * @author hamaro
 */
public class CircularDependencyException extends Exception{
    public CircularDependencyException() {
    }

    public CircularDependencyException(String message) {
        super(message);
    }
}
