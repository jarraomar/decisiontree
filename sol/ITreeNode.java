package sol;

import src.Row;

/**
 * Interface for nodes and leaves of tree.
 *
 * Feel free to add more methods to this interface as you see fit!
 */
public interface ITreeNode {

    /**
     * Recursively traverses decision tree to return tree's decision for a row.
     *
     * @param forDatum the datum to lookup a decision for
     * @return the decision tree's decision
     */

    //create a method(s) that takes in a specific or specific object and makes a sublist of the rows that have that object
// then generate a tree with nodes and leaves and set default cases for the nodes
    //dont really need a lot of other methods
    String getDecision(Row forDatum);
}
