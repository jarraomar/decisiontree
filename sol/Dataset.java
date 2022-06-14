package sol;

import src.IDataset;
import src.Row;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A class that implements the IDataset interface,
 * representing a training data set.
 */
public class Dataset implements IDataset {
    private List<String> attribute;
    private List<Row> dataObjects;

    // TODO: Implement methods declared in IDataset interface!

    //this constructor characterizes the dataset with the attribute and dataObjects that are defined within them
    public Dataset(List<String> attribute, List<Row> dataObjects) {
        this.attribute = attribute;
        this.dataObjects = dataObjects;
    }

    //returns the attribute
    @Override
    public List<String> getAttributeList() {
        return this.attribute;
    }

    //returns the data objects
    @Override
    public List<Row> getDataObjects() {
        return this.dataObjects;
    }

    //returns the size of the data objects
    @Override
    public int size() {
        return this.dataObjects.size();
    }

    /*returns a list of strings of distinct attributes by iterating through each row of the dataObjects and if the
    empty string list doesn't contain the initial attribute, then it would add it to the string list. Therefore,
    after a string is added to the list, it can't be added again.*/
    public List<String> uniqueAttributes(String initialAttribute){
        List<String> newStringList = new ArrayList<>();
        String localAttribute;

        for(Row row : this.dataObjects){
            localAttribute = row.getAttributeValue(initialAttribute);
            if(!newStringList.contains(localAttribute)){
                newStringList.add(localAttribute);
            }
        }
        return newStringList;
    }

    //this creates a list of attributes and removes it an attribute once it's been used
    public List<String> removeAttribute(String initialAttribute){
        List<String> newStringList = new ArrayList<>(this.attribute);
        newStringList.remove(initialAttribute);
        return newStringList;
    }

    //helper method that randomizes a list of attributes and returns one
    public String getRandom(){
        Random random = new Random();
        int upperBound = this.attribute.size();
        int randomNum = random.nextInt(upperBound);
        return this.attribute.get(randomNum);
    }

    /*takes in an initial attribute which would be used to split on. By checking each string within the list of
    strings all within each row of the data object, we can check if the current attribute value equals the list
    of strings. If it is, we can update that list of row (within a new list) and instantiate a new list of datasets
    with those rows.*/
    public List<Dataset> partition(String initialAttribute){
        List<String> unique = this.uniqueAttributes(initialAttribute);
        List<Dataset> subset = new ArrayList<>();
        List<String> filtered = this.removeAttribute(initialAttribute);
        for(String currentValue : unique){
            List<Row> dataObjectsCurrentValue = new ArrayList<>();
            for(Row row : this.dataObjects){
                if(row.getAttributeValue(initialAttribute).equals(currentValue)){
                    dataObjectsCurrentValue.add(row);
                }
            }
            Dataset ds = new Dataset(filtered,dataObjectsCurrentValue);
            subset.add(ds);
        }
        return subset;
    }

    //checks to see if the string attribute value is within the same row and returns true if it is.
    public Boolean sameValue(String initialAttribute){
        String val = this.dataObjects.get(0).getAttributeValue(initialAttribute);
        for(Row row : this.dataObjects){
            if (!row.getAttributeValue(initialAttribute).equals(val)){
                return false;
            }
        }
        return true;
    }

    public Boolean isEmpty() {
        return this.attribute.size() == 0;
    }

    //returns the shared value
    public String getSharedValue(String initialAttribute){
        return this.dataObjects.get(0).getAttributeValue(initialAttribute);
    }

    /*by checking the size of each dataset in the list of partitioned datasets, we can find the common case by the
    size of that partitioned data set since each node will contain the same attribute for which it was partitioned
    on.*/
    public String getCommon(String initialAttribute){
        List<Dataset> subset = this.partition(initialAttribute);
        String defaultString = null;
        int counter = -1;
        for (Dataset dataset : subset){
            if(dataset.size() > counter){
                counter = dataset.size();
                defaultString = dataset.getSharedValue(initialAttribute);
            }
        }
        return defaultString;
    }

    @Override
    public String toString(){
        String output = "";
        for(Row row: dataObjects) {
            String cur = row.toString();
            output += "\r\n" + cur;
        }
        return output;
    }
}
