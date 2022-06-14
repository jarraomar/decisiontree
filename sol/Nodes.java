package sol;

import src.Row;

import java.util.List;

public class Nodes implements ITreeNode{
    private String name;
    private String defaultCase;
    private List<Edges> edgeCases;

    //constructor defines the node and characterizes the information it holds.
    public Nodes(String name, String defaultCase, List<Edges> edgeCases){
        this.name = name;
        this.defaultCase = defaultCase;
        this.edgeCases = edgeCases;
    }

    //would return the node for the row
    @Override
    public String getDecision(Row forDatum){
        for(Edges edge : this.edgeCases){
            if(edge.getValue().equals(forDatum.getAttributeValue(this.name))){
                ITreeNode nextNode = edge.getNode();
                return nextNode.getDecision(forDatum);
            }
        }
        return this.defaultCase;
    }



}
