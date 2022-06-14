package sol;

import src.ITreeGenerator;
import src.Row;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that implements the ITreeGenerator interface
 * used to generate a tree
 */

//top level, should be able to understand the entire tree from this class
public class TreeGenerator implements ITreeGenerator<Dataset> {
    // TODO: Implement methods declared in ITreeGenerator interface!
    private Dataset trainingData;
    private String targetAttribute;
    private List<String> attribute;
    private ITreeNode rootNode;

    @Override
    public void generateTree(Dataset trainingData, String targetAttribute) {
        this.trainingData = trainingData;
        this.targetAttribute = targetAttribute;
        if(trainingData.size() == 0){
            throw new RuntimeException("Dataset is empty");
        }
        else {
            trainingData.getAttributeList().remove(targetAttribute);
            rootNode = this.generateTreeHelper(trainingData, targetAttribute);
        }
    }

    public ITreeNode generateTreeHelper(Dataset trainingData, String targetAttribute) {
        String mostCommon = trainingData.getCommon(targetAttribute);
        if (trainingData.sameValue(targetAttribute) || trainingData.getAttributeList().size() == 0) {
            return new Leaves(mostCommon);
        } else {
            String randomAttribute = trainingData.getRandom();
            List<Dataset> partitionDataSet = trainingData.partition(randomAttribute);
            List<Edges> edgeList = new ArrayList<>();
            for (Dataset subset : partitionDataSet) {
                edgeList.add(new Edges(subset.getSharedValue(randomAttribute), generateTreeHelper(subset, targetAttribute)));
            }
            return new Nodes(randomAttribute, trainingData.getCommon(targetAttribute), edgeList);
        }
    }

    @Override
    public String getDecision(Row datum) {
        return this.rootNode.getDecision(datum);
    }
}

