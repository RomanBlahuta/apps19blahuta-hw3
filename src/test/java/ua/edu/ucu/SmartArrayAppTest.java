package ua.edu.ucu;

import org.junit.Test;
import ua.edu.ucu.functions.MyComparator;
import ua.edu.ucu.functions.MyFunction;
import ua.edu.ucu.functions.MyPredicate;
import ua.edu.ucu.smartarr.*;

import static org.junit.Assert.*;

/**
 *
 * @author Andrii_Rodionov
 */
public class SmartArrayAppTest {

    @Test
    public void testFilterPositiveIntegersSortAndMultiplyBy2() {
        Integer[] integers = {-1, 2, 0, 1, -5, 3};
        
        Integer[] res = 
                SmartArrayApp.filterPositiveIntegersSortAndMultiplyBy2(integers);
        Integer[] expectedRes = {2, 4, 6};
        
        assertArrayEquals(expectedRes, res);        
    }

    @Test
    public void testFindDistinctStudentNamesFrom2ndYearWithGPAgt4AndOrderedBySurname() {
        Student[] students = {
            new Student("Ivar", "Grimstad", 3.9, 2),
            new Student("Ittai", "Zeidman", 4.5, 1),
            new Student("Antons", "Kranga", 4.0, 2),
            new Student("Burr", "Sutter", 4.2, 2),
            new Student("Philipp", "Krenn", 4.3, 3),
            new Student("Tomasz", "Borek", 4.1, 2),
            new Student("Ittai", "Zeidman", 4.5, 1),
            new Student("Burr", "Sutter", 4.2, 2)};
        String[] studentNames = 
                SmartArrayApp.findDistinctStudentNamesFrom2ndYearWithGPAgt4AndOrderedBySurname(students);
        String[] expectedStudentNames = {"Borek Tomasz", "Kranga Antons", "Sutter Burr"};

        assertArrayEquals(expectedStudentNames, studentNames);
    }

    @Test
    public void sortDecoratorTest() {
        Object[] arr = {5, 4, 8, 1, 0};
        SmartArray sa = new BaseArray(arr);

        MyComparator cmp = new MyComparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return (int) o2 - (int) o1;
            }
        };
        sa = new SortDecorator(sa, cmp);
        Object[] res = sa.toArray();
        Object[] expect = {8, 5, 4, 1, 0};
        assertArrayEquals(res, expect);
        assertEquals("SortDecorator", sa.operationDescription());
        assertEquals(5, sa.size());
    }

    @Test
    public void distinctDecoratorTest() {
        Object[] arr = {5, 4, 8, 8, 1, 4, 0};
        SmartArray sa = new BaseArray(arr);

        sa = new DistinctDecorator(sa);
        Object[] res = sa.toArray();
        Object[] expect = {5, 4, 8, 1, 0};
        assertArrayEquals(res, expect);
        assertEquals("DistinctDecorator", sa.operationDescription());
        assertEquals(5, sa.size());
    }

    @Test
    public void filterDecoratorTest() {
        Object[] arr = {7, 4, 8, 8, 1, 4, 0};
        SmartArray sa = new BaseArray(arr);

        MyPredicate pr = new MyPredicate() {
            @Override
            public boolean test(Object t) {
                return (int) t >= 7;
            }
        };
        sa = new FilterDecorator(sa, pr);
        Object[] res = sa.toArray();
        Object[] expect = {7, 8, 8};
        assertArrayEquals(res, expect);
        assertEquals("FilterDecorator", sa.operationDescription());
        assertEquals(3, sa.size());
    }

    @Test
    public void mapDecoratorTest() {
        Object[] arr = {-3, 0, 17, 10, 123};
        SmartArray sa = new BaseArray(arr);

        MyFunction fnc = new MyFunction() {
            @Override
            public Object apply(Object t) {
                return (int) t * 10;
            }
        };
        sa = new MapDecorator(sa, fnc);
        Object[] res = sa.toArray();
        Object[] expect = {-30, 0, 170, 100, 1230};
        assertArrayEquals(res, expect);
        assertEquals("MapDecorator", sa.operationDescription());
        assertEquals(5, sa.size());
    }

    @Test
    public void emptyDecoratorsTestF() {
        Object[] arr = {};
        SmartArray sa = new BaseArray(arr);

        MyPredicate pr = new MyPredicate() {
            @Override
            public boolean test(Object t) {
                return (int) t >= 7;
            }
        };
        sa = new FilterDecorator(sa, pr);
        Object[] res = sa.toArray();
        Object[] expect = {};
        assertArrayEquals(res, expect);
        assertEquals("FilterDecorator", sa.operationDescription());
        assertEquals(0, sa.size());
    }

    @Test
    public void emptyDecoratorsTestM() {
        Object[] arr = {};
        SmartArray sa = new BaseArray(arr);

        MyFunction fnc = new MyFunction() {
            @Override
            public Object apply(Object t) {
                return (int) t * 10;
            }
        };
        sa = new MapDecorator(sa, fnc);
        Object[] res = sa.toArray();
        Object[] expect = {};
        assertArrayEquals(res, expect);
        assertEquals("MapDecorator", sa.operationDescription());
        assertEquals(0, sa.size());
    }

    @Test
    public void emptyDecoratorsTestD() {
        Object[] arr = {};
        SmartArray sa = new BaseArray(arr);

        sa = new DistinctDecorator(sa);
        Object[] res = sa.toArray();
        Object[] expect = {};
        assertArrayEquals(res, expect);
        assertEquals("DistinctDecorator", sa.operationDescription());
        assertEquals(0, sa.size());
    }

    @Test
    public void emptyDecoratorsTestC() {
        Object[] arr = {};
        SmartArray sa = new BaseArray(arr);

        MyComparator cmp = new MyComparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return (int) o2 - (int) o1;
            }
        };
        sa = new SortDecorator(sa, cmp);
        Object[] res = sa.toArray();
        Object[] expect = {};
        assertArrayEquals(res, expect);
        assertEquals("SortDecorator", sa.operationDescription());
        assertEquals(0, sa.size());
    }
}