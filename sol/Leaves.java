package sol;

import src.Row;

public class Leaves implements ITreeNode {
    private String name;

    //constructor defines the leaves
    public Leaves(String name){
        this.name = name;
    }

    //returns the leaf for that row
    @Override
    public String getDecision(Row forDatum){
        return this.name;
    }

}