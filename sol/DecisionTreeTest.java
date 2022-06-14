package sol;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;
import src.DecisionTreeCSVParser;
import src.Row;
import java.util.ArrayList;
import java.util.List;

public class DecisionTreeTest {

    // Constructor for DecisionTreeTest tester class
    public DecisionTreeTest() {
    }

    @Test
    public void testExample() {
        assertEquals(6, 1 + 2 + 3);
    }

    //test to see if the uniqueAttributes function produces a string list of the actual unique values
    @Test
    public void testUniqueAttributes(){
        List<Row> dataObjects = DecisionTreeCSVParser.parse("/Users/jarraomar/Desktop/cs200/projects/decision-tree/data/candidates/fruits-and-vegetables.csv");
        List<String> attributeList = new ArrayList<>(dataObjects.get(0).getAttributes());
        Dataset training = new Dataset(attributeList, dataObjects);
        List<String> distinctValues = training.uniqueAttributes("color");
        List<String> testDistinctValues = new ArrayList<>();
        testDistinctValues.add("green");
        testDistinctValues.add("orange");
        testDistinctValues.add("yellow");
        Assert.assertEquals(distinctValues,testDistinctValues);
    }

    /*test to see if the function removeAttributes works by making a copy of the original list of strings and removes
    that attribute from the copied list and sees if it's equal to removeAttribute function which parses through the
    dataset.*/
    @Test
    public void testRemoveAttribute(){
        List<Row> dataObjects = DecisionTreeCSVParser.parse("/Users/jarraomar/Desktop/cs200/projects/decision-tree/data/candidates/fruits-and-vegetables.csv");
        List<String> attributeList = new ArrayList<>(dataObjects.get(0).getAttributes());
        Dataset training = new Dataset(attributeList, dataObjects);
        List<String> originalList = new ArrayList<>();
        originalList.add("name");
        originalList.add("color");
        originalList.add("highProtein");
        originalList.add("calories");
        originalList.add("foodType");
        List<String> copiedList = new ArrayList<>(originalList);
        copiedList.remove("color");
        Assert.assertEquals(training.removeAttribute("color"),copiedList);
    }

    /*test to see if it makes a list of dataset by partitioning on that original attribute. By checking the size of
    partitioned list, we're able to see if that partition function actually works.*/
    @Test
    public void testPartition() {
        List<Row> dataObjects = DecisionTreeCSVParser.parse("/Users/jarraomar/Desktop/cs200/projects/decision-tree/data/candidates/fruits-and-vegetables.csv");
        List<String> attributeList = new ArrayList<>(dataObjects.get(0).getAttributes());
        Dataset originalDatatset = new Dataset(attributeList, dataObjects);
        List<Dataset> checker = originalDatatset.partition("color");
        Assert.assertEquals(checker.size(), 3);
    }

    /*test to see if the decision tree actually works correctly. By adding in the addition of a tangerine we can see if
    /the tree can use the information available at each node within the list of strings and the list of rows and make a
    /decision whether a tangerine is a fruit or not.*/
    @Test
    public void getDecision() {
        List<Row> dataObjects = DecisionTreeCSVParser.parse("/Users/jarraomar/Desktop/cs200/projects/decision-tree/data/candidates/fruits-and-vegetables.csv");
        List<String> attributeList = new ArrayList<>(dataObjects.get(0).getAttributes());
        Dataset training = new Dataset(attributeList, dataObjects);
        TreeGenerator generator = new TreeGenerator();
        generator.generateTree(training, "foodType");
        Row tangerine = new Row("tangerine");
        dataObjects.add(tangerine);
        tangerine.setAttributeValue("name", "tangerine");
        tangerine.setAttributeValue("color", "orange");
        tangerine.setAttributeValue("highProtein", "false");
        tangerine.setAttributeValue("calories", "high");
        String decision = generator.getDecision(tangerine);
        Assert.assertEquals(decision, "fruit");
    }
}