package fr.istic.vv;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class BinaryHeapTest {
    private BinaryHeap<Integer> binaryHeap;
    @BeforeEach
    public void setUp() {
        // using natural comparator
        this.binaryHeap = new BinaryHeap<>((a,b) -> a-b);
    }
    @Test
    @DisplayName("throw exception when the heap is empty")
    public void shouldThrowException() {
        // empty heap
        assertThrows(NoSuchElementException.class, binaryHeap::peek);
        assertThrows(NoSuchElementException.class, binaryHeap::pop);

        // push null value
        assertThrows(RuntimeException.class, () -> binaryHeap.push(null));

        // test added after static analysis
        assertDoesNotThrow(() -> binaryHeap.shiftUp(-1));
    }

    @ParameterizedTest
    @MethodSource("IntegerDataPopPush")
    public void testPushMethod(List<Integer> data) {
        for(int i: data) {
            binaryHeap.push(i);
        }

        // verify size
        assertEquals(data.size(), binaryHeap.count());
    }

    @ParameterizedTest
    @MethodSource("IntegerDataPopPush")
    public void testPopMethod(List<Integer> data, Integer min, List<Integer> finalList) {
        for(Integer i: data) {
            binaryHeap.push(i);
        }
        // check the minimum element returned
        assertEquals(binaryHeap.pop(), min);

        // pop remove also the minimum value
        assertEquals(binaryHeap.count(), finalList.size());

        // check the order
        assertEquals(binaryHeap.getHeap(), finalList);
    }

    @ParameterizedTest
    @MethodSource("IntegerDataPopPush")
    public void testPeekMethod(List<Integer> data, Integer expected) {
        for(Integer i: data) {
            binaryHeap.push(i);
        }
        assertEquals(binaryHeap.peek(), expected);

        // peek don't remove the minimum element
        // it should equal the size of data
        assertEquals(binaryHeap.count(), data.size());

    }

    @Test
    public void testCountMethod() {
        this.binaryHeap = new BinaryHeap<>((a, b) -> a - b);

        // first test the heap is empty
        assertEquals(binaryHeap.count(), 0);

        // second test case: adding one element
        binaryHeap.push(1);
        assertEquals(binaryHeap.count(), 1);

        // third test case: multiple elements
        binaryHeap.push(2);
        binaryHeap.push(3);
        binaryHeap.push(4);
        binaryHeap.push(5);

        assertEquals(binaryHeap.count(), 5);
    }

    static Stream<Arguments> IntegerDataPopPush() {
        return Stream.of(
                // Integer values => one elements
                Arguments.arguments(List.of(1), 1, Collections.emptyList()),

                // Integer values => multiple elements
                Arguments.arguments(Arrays.asList(1,3,2,-2,4), -2, Arrays.asList(1, 3, 2, 4)),
                Arguments.arguments(Arrays.asList(1,3,0,7,9), 0, Arrays.asList(1, 3, 9, 7)),
                Arguments.arguments(Arrays.asList(3,4,6,9), 3, Arrays.asList(4,9,6)),
                // using some duplicated data
                Arguments.arguments(Arrays.asList(0,-2,9,9,5,4,7,-6,2,4), -6, Arrays.asList(-2, 0, 4, 2, 4, 9, 7, 9, 5))
        );
    }
}