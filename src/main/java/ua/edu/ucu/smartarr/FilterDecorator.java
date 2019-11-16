package ua.edu.ucu.smartarr;

import ua.edu.ucu.functions.MyPredicate;

import java.util.Arrays;

// Tests every element and removes it if it doesn't satisfy MyPredicate
public class FilterDecorator extends SmartArrayDecorator {

    public FilterDecorator(SmartArray sa, MyPredicate pr) {
        super(sa);
        arr = Arrays.stream(arr).filter(pr::test).toArray();
    }

    @Override
    public Object[] toArray() {
        return arr.clone();
    }

    @Override
    public String operationDescription() {
        return "FilterDecorator";
    }

    @Override
    public int size() {
        return arr.length;
    }
}
