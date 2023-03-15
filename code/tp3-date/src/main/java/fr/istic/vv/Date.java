package fr.istic.vv;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

class Date implements Comparable<Date> {
    private int day;
    private int month;
    private int year;

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public Date(int day, int month, int year) {
        if (!isValidDate(day, month, year)) {
            throw new RuntimeException("Date not valid");
        }
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public static boolean isValidDate(int day, int month, int year) {
        // normal test
        if (year < 1 || month > 12 || month < 1 || day < 1 || day > 31) {
            return false;
        }

        return day <= numberOfDaysInMonth(month, year);
    }

    public static int numberOfDaysInMonth(int month, int year) {
        int[] daysInMonth = {31, isLeapYear(year) ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        return daysInMonth[month - 1];
    }

    public static boolean isLeapYear(int year) {
        if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
            return true;
        }
        return false;
    }

    public Date nextDate() {
        int d = day;
        int m = month;
        int y = year;
        if (d < numberOfDaysInMonth(m, y)) {
            d++;
        }
        else {
            d = 1;
            if (m == 12) {
                m = 1;
                y++;
            } else {
                m++;
            }
        }

        return new Date(d, m, y);
    }

    public Date previousDate() {
        int d = day;
        int m = month;
        int y = year;
        if (d == 1) {
            if (m == 1) {
                y--;
                m = 12;
            } else {
                m--;
            }
            d = numberOfDaysInMonth(m, y);
        } else {
            d--;
        }

        return new Date(d, m, y);
    }

    public int compareTo(Date other) {
        if (other == null)
            throw new NullPointerException("the other should not be null");
        if (year != other.year) {
            return year > other.year ? 1 : -1;
        }
        if (month != other.month) {
            return month > other.month ? 1 : -1;
        }

        return day == other.day ? 0 : day > other.day ? 1 : -1;
    }
}