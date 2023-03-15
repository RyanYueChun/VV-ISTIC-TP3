package fr.istic.vv;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class DateTest {

    @ParameterizedTest
    @ValueSource(ints = {1001, 1002, 1700, 1900, 2100, -1, 0002})
    public void shouldNotBeALeapYear(int year) {
        assertFalse(Date.isLeapYear(year));
    }

    @ParameterizedTest
    @ValueSource(ints = {1200, 2000, 1404, 1412})
    public void shouldBeALeapYear(int year) {
        assertTrue(Date.isLeapYear(year));
    }

    @ParameterizedTest
    @MethodSource("invalidNumberOfDays")
    public void shoudReturnTheWrongNumberOfDays(int mounth, int year, int expected) {
        assertNotEquals(Date.numberOfDaysInMonth(mounth, year), expected);
    }

    @Test
    public void shouldThrowExecptioWithThisMounth() {
        assertThrows(Exception.class, () -> Date.numberOfDaysInMonth(-5, 2023));
        assertThrows(Exception.class, () -> Date.numberOfDaysInMonth(13, 2023));
        assertThrows(Exception.class, () -> Date.numberOfDaysInMonth(29/2, 2023));
        assertThrows(Exception.class, () -> Date.numberOfDaysInMonth(0, 2023));
    }

    @ParameterizedTest
    @MethodSource("validNumberOfDays")
    public void shoudReturnTheRightNumberOfDays(int mounth, int year, int expected) {
        assertEquals(Date.numberOfDaysInMonth(mounth, year), expected);
    }

    @ParameterizedTest
    @MethodSource("validDateInput")
    public void isValidDate(int day, int month, int year) {
        assertTrue(Date.isValidDate(day, month, year));
    }

    @ParameterizedTest
    @MethodSource("invalidDateInput")
    public void isInvalidDate(int day, int month, int year) {
        assertFalse(Date.isValidDate(day, month, year));
    }

    @ParameterizedTest
    @MethodSource("validInputNextDate")
    public void checkNextDate(List<Integer> date1, List<Integer> date2) {
        Date dd = new Date(date1.get(0), date1.get(1), date1.get(2));
        Date nextDate = dd.nextDate();
        assertEquals(nextDate.getDay(), date2.get(0));
        assertEquals(nextDate.getMonth(), date2.get(1));
        assertEquals(nextDate.getYear(), date2.get(2));
    }

    @Test
    public void testConstructorWihtInvalidDate() {
        assertThrows(RuntimeException.class, () -> new Date(1,13,2022));
    }

    @Test
    public void testConstructorWihtValidDate() {
        Date d1 = new Date(15,03,2023);
        assertEquals(d1.getDay(), 15);
        assertEquals(d1.getMonth(), 3);
        assertEquals(d1.getYear(), 2023);
    }

    @ParameterizedTest
    @MethodSource("validInputPreviousDate")
    public void checkPreviousDate(List<Integer> date1, List<Integer> date2) {
        Date dd = new Date(date1.get(0), date1.get(1), date1.get(2));
        Date previousDate = dd.previousDate();
        assertEquals(previousDate.getDay(), date2.get(0));
        assertEquals(previousDate.getMonth(), date2.get(1));
        assertEquals(previousDate.getYear(), date2.get(2));
    }

    @Test
    public void shouldbeEqualDate() {
        Date d1 = new Date(15,02,2023);
        Date d2 = new Date(15,02,2023);
        assertEquals(d1.compareTo(d2), 0);
    }

    @ParameterizedTest
    @MethodSource("differentDates")
    public void compareDifferentValidDate(Date d1, Date d2, int expected) {
        assertEquals(d1.compareTo(d2), expected);
    }

    @Test
    public void shouldThrowExceptionInCompareTo() {
        Date d1 = new Date(1,1,2011);
        assertThrows(NullPointerException.class, () -> d1.compareTo(null));
    }

    static Stream<Arguments> invalidNumberOfDays() {
        return Stream.of(
                arguments(1, 2023, 50),
                arguments(6, 2023, 2),
                arguments(2, 2000, 28),
                arguments(2, 1900, 29)
        );
    }

    static Stream<Arguments> validNumberOfDays() {
        return Stream.of(
                arguments(2, 2000, 29),
                arguments(2, 1900, 28),
                arguments(1, 2023, 31),
                arguments(4, 2022, 30)
                );
    }

    static Stream<Arguments> validInputPreviousDate() {
        return Stream.of(
                arguments(Arrays.asList(1,1,2022), Arrays.asList(31,12,2021)),
                arguments(Arrays.asList(10,5,2021), Arrays.asList(9,5,2021)),
                arguments(Arrays.asList(1,3,2023), Arrays.asList(28,2,2023))
        );
    }

    static Stream<Arguments> validInputNextDate() {
        return Stream.of(
                arguments(Arrays.asList(15,11,2021), Arrays.asList(16,11,2021)),
                arguments(Arrays.asList(31,12,2023), Arrays.asList(1,1,2024)),
                arguments(Arrays.asList(28,2,2023), Arrays.asList(1,3,2023))
        );
    }

    static Stream<Arguments> invalidDateInput() {
        return Stream.of(
                arguments(29, 2, 2023),
                arguments(31, 6, 2015),
                arguments(-1, 2, 2000),
                arguments(1,1,-5),
                arguments(10,-1,2011),
                arguments(42,12,2010),
                arguments(10,15,2010)
        );
    }

    static Stream<Arguments> validDateInput() {
        return Stream.of(
                arguments(28, 2, 2023),
                arguments(1, 1, 1),
                arguments(29,2,2000)
        );
    }

    static Stream<Arguments> differentDates() {
        return Stream.of(
                arguments(new Date(11,11,2020), new Date(10, 10, 2010), 1),
                arguments(new Date(11,11,1998), new Date(10, 10, 2020), -1),
                arguments(new Date(11,5,2000), new Date(10, 6, 2000), -1),
                arguments(new Date(11,11,2000), new Date(10, 9, 2000), 1),
                arguments(new Date(25,9,2000), new Date(15, 9, 2000), 1),
                arguments(new Date(12,9,2000), new Date(17, 9, 2000), -1)
        );
    }



}