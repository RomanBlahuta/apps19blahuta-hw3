package ua.edu.ucu.smartarr;

abstract class SmartArrayDecorator implements SmartArray {

    protected SmartArray smartArray;
    protected Object[] arr;

    public SmartArrayDecorator(SmartArray smartArray) {
        this.smartArray = smartArray;
        arr = smartArray.toArray();
    }

}
