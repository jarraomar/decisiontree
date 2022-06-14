package sol;

import src.Row;

//implement itreenode
public class Edges{

    //has a string with the name of the edge
    //stores the itreenode that it links to
    //also need getter methods

    private String name;
    private ITreeNode nextNode;

    //constructor defines the node and characterizes the information it holds.
    public Edges(String name, ITreeNode nextNode){
        this.name = name;
        this.nextNode = nextNode;
    }

    public String getValue(){
        return this.name;
    }

    public ITreeNode getNode(){
        return this.nextNode;
    }
}
