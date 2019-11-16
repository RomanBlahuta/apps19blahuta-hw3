package ua.edu.ucu.smartarr;

import ua.edu.ucu.functions.MyComparator;

import java.util.Arrays;

// Sorts elements using MyComparator to compare them
public class SortDecorator extends SmartArrayDecorator {

    public SortDecorator(SmartArray sa, MyComparator comparator) {
        super(sa);
        Arrays.sort(arr, comparator);
    }

    @Override
    public Object[] toArray() {
        return arr.clone();
    }

    @Override
    public String operationDescription() {
        return "SortDecorator";
    }

    @Override
    public int size() {
        return arr.length;
    }
}
