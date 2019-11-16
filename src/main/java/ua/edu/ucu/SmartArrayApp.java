package ua.edu.ucu;

import java.util.Arrays;
import ua.edu.ucu.functions.MyComparator;
import ua.edu.ucu.functions.MyFunction;
import ua.edu.ucu.functions.MyPredicate;
import ua.edu.ucu.smartarr.*;

public class SmartArrayApp {

    static final double GPATHRESHOLD = 4;
    static final int MINYEAR = 2;

    public static Integer[]
            filterPositiveIntegersSortAndMultiplyBy2(Integer[] integers) {
                
        MyPredicate pr = new MyPredicate() {
            @Override
            public boolean test(Object t) {
                return ((Integer) t) > 0;
            }
        };

        MyComparator cmp = new MyComparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return ((Integer) o1) - ((Integer) o2);
            }
        };

        MyFunction func = new MyFunction() {
            @Override
            public Object apply(Object t) {
                return 2 * ((Integer) t);
            }
        };

        // Input: [-1, 2, 0, 1, -5, 3]
        SmartArray sa = new BaseArray(integers);

        sa = new FilterDecorator(sa, pr); // Result: [2, 1, 3];
        sa = new SortDecorator(sa, cmp); // Result: [1, 2, 3]
        sa = new MapDecorator(sa, func); // Result: [2, 4, 6]

        // Alternative
//        sa = new MapDecorator(
//                    new SortDecorator(
//                        new FilterDecorator(sa, pr),
//                    cmp),
//                func);
        Object[] result = sa.toArray();
        return Arrays.copyOf(result, result.length, Integer[].class);
    }

    public static String[]
            findDistinctStudentNamesFrom2ndYearWithGPAgt4AndOrderedBySurname(Student[] students) {

        // Hint: to convert Object[] to String[] - use the following code
        //Object[] result = studentSmartArray.toArray();
        //return Arrays.copyOf(result, result.length, String[].class);
        SmartArray studentSmartArray = new BaseArray(students);
        studentSmartArray = new DistinctDecorator(studentSmartArray);

        MyPredicate pr = new MyPredicate() {
            @Override
            public boolean test(Object t) {
                return ((Student) t).getYear() == MINYEAR;
            }
        };
        studentSmartArray = new FilterDecorator(studentSmartArray, pr);

        MyPredicate pr2 = new MyPredicate() {
            @Override
            public boolean test(Object t) {
                return ((Student) t).getGPA() >= GPATHRESHOLD;
            }
        };
        studentSmartArray = new FilterDecorator(studentSmartArray, pr2);

        MyComparator cmp = new MyComparator() {
            @Override
            public int compare(Object o1, Object o2) {
                String surname1 = ((Student) o1).getSurname();
                String surname2 = ((Student) o2).getSurname();
                return surname1.compareTo(surname2);
            }
        };
        studentSmartArray = new SortDecorator(studentSmartArray, cmp);

        MyFunction fnc = new MyFunction() {
            @Override
            public Object apply(Object t) {
                return ((Student) t).getSurname() + ' ' + ((Student) t).getName();
            }
        };
        studentSmartArray = new MapDecorator(studentSmartArray, fnc);
        Object[] result = studentSmartArray.toArray();
        return Arrays.copyOf(result, result.length, String[].class);
    }
}
