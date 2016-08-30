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

    private GraphNode linkToLast = null;

    public TaskScheduler() {
        nodes = new HashMap<>();

    }

    public String orderTasks(String tasksin) throws DependsOnItSelfException, CircularDependencyException {

        String[] lines = tasksin.replace(" ", "").split("\n");
        for (int i = 0; i < lines.length; i++) {

            System.out.println("StartParse l:" + i + " c:" + lines[i]);
            String[] parts = lines[i].split("=>");

            GraphNode leftIn;
            boolean leftIsNew = true;
            boolean rightIsNew = true;
            if (!nodes.containsKey(parts[0])) {
                leftIn = new GraphNode(parts[0]);
                nodes.put(parts[0], leftIn);

            } else {
                leftIn = nodes.get(parts[0]);
                leftIsNew = false;
            }

            if (parts.length > 1) {

                //check if predecessor and task are the same
                if (parts[0].equals(parts[1])) {
                    throw (new DependsOnItSelfException("Task and its predecessor were the same."));
                }

                GraphNode rightIn;
                if (!nodes.containsKey(parts[1])) {
                    rightIn = new GraphNode(parts[1]);
                    nodes.put(parts[1], rightIn);
//                    if(leftIsNew)
//                        leftIn.putLeft(rightIn);
//                    else
                        leftIn.putLeft(rightIn);

                    
                } else {
                    rightIn = nodes.get(parts[1]);
                    if (leftIn.hasAscendence(parts[1])) {
                        throw (new CircularDependencyException("Tasks have circular dependencies."));
                    } else {
                        leftIn.putLeft(rightIn);
                    }
                }

            }
        }
        for (GraphNode value : nodes.values()) {
            if(value.printed)
                continue;
            while (value.parent!=null){
                value = value.parent;
            }
            System.out.print("|");
            value.printLeft();
        }
        String tasksout = "";
        return tasksout;
    }

}
