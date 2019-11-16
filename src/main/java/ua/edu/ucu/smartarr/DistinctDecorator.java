package ua.edu.ucu.smartarr;

import java.util.Arrays;

// Remove duplicates from SmartArray. Use method equals() to compare objects
public class DistinctDecorator extends SmartArrayDecorator {

    public DistinctDecorator(SmartArray sa) {
        super(sa);
        arr = Arrays.stream(arr).distinct().toArray();
    }

    @Override
    public Object[] toArray() {
        return arr.clone();
    }

    @Override
    public String operationDescription() {
        return "DistinctDecorator";
    }

    @Override
    public int size() {
        return arr.length;
    }
}
