# Test the Date class

Implement a class `Date` with the interface shown below:

```java
class Date implements Comparable<Date> {

    public Date(int day, int month, int year) { ... }

    public static boolean isValidDate(int day, int month, int year) { ... }

    public static boolean isLeapYear(int year) { ... }

    public Date nextDate() { ... }

    public Date previousDate { ... }

    public int compareTo(Date other) { ... }

}
```

The constructor throws an exception if the three given integers do not form a valid date.

`isValidDate` returns `true` if the three integers form a valid year, otherwise `false`.

`isLeapYear` says if the given integer is a leap year.

`nextDate` returns a new `Date` instance representing the date of the following day.

`previousDate` returns a new `Date` instance representing the date of the previous day.

`compareTo` follows the `Comparable` convention:

* `date.compareTo(other)` returns a positive integer if `date` is posterior to `other`
* `date.compareTo(other)` returns a negative integer if `date` is anterior to `other`
* `date.compareTo(other)` returns `0` if `date` and `other` represent the same date.
* the method throws a `NullPointerException` if `other` is `null` 

Design and implement a test suite for this `Date` class.
You may use the test cases discussed in classes as a starting point. 
Also, feel free to add any extra method you may need to the `Date` class.


Use the following steps to design the test suite:

1. With the help of *Input Space Partitioning* design a set of initial test inputs for each method. Write below the characteristics and blocks you identified for each method. Specify which characteristics are common to more than one method.
2. Evaluate the statement coverage of the test cases designed in the previous step. If needed, add new test cases to increase the coverage. Describe below what you did in this step.
3. If you have in your code any predicate that uses more than two boolean operators check if the test cases written to far satisfy *Base Choice Coverage*. If needed to add new test cases. Describe below how you evaluated the logic coverage and the new test cases you added.
4. Use PIT to evaluate the test suite you have so far. Describe below the mutation score and the live mutants. Add new test cases or refactor the existing ones to achieve a high mutation score.

Use the project in [tp3-date](../code/tp3-date) to complete this exercise.

## Answer

You can find the program code [here](../code/tp3-date)

1. #### Test inputs of isLeapYear() Method

In this method I use this formula : `(year % 4 == 0 && year % 100 != 0) || year % 400 == 0)`
to check if a year is leap or not.

As u know in java, when we use `&&`, the compiler will not verify the second condition, if the first is false.
And the same for `||`, the compiler will not check the second condition if the first is True.
I have defined 2 global partitions that will check all the conditions.
We consider that this is our formula `(A && B) || C`.
##### Valid Partition:
- A: True, B: False, C: True => True (1200, 2000)
- A: TRue, B: True, C: False => TRue (1404, 1408, 1412)

##### Invalid Partition
- A: false, B: true, c: false => false (1001, 1002)
- A: True, B: False, C: False => False (1700,1900, 2100)
- Random : -1, 0002

#### Test inputs of isValideDate() Method

Conditions used in my function
```java
if (year < 1 || month > 12 || month < 1 || day < 1 || day > 31) {
    return false;
}

return day <= numberOfDaysInMonth(month, year);
```
So I defined some input partitions to verify all condition

##### Invalid Input
- 31 in month like (4, 6)
- invalid month
- invalid day
- wrong day in non-leap year

##### Valid Input
- first possible date
- a normal valid date
- leap year date
- non leap year date

#### Test inputs of **numberOfDaysInMonth**
The purpose of this method is to return the number of days for each month, utilizing the isLeapYear function within. 
As we know, the only difference between leap years and non-leap years is the month of February.
So I have defined 3 partitions for this method: 
##### Invalid input:
- Invalid number of days, 
- Incorrect number of days in leap years.
- Incorrect number of days in non-leap years.

##### Valid input:
- Correct number of days in both leap and non-leap years.

#### Data that's throw Exception

#### Test inputs of compareDate()
The purpose of this method is to return 0 if the both dates are equal, 1 if this is posterior to other,
-1 if it's anterior to others.

So i have defined 3 partitions:
- Using sameDate
- Using different date
- Using invalidDate

#### Test inputs of **nextDate**
This function returns a new Date of the following day.

I have added 2 test for this function
##### Valid input:
To check all possibilities for valid date, I made 3 examples :
- day < number of days in a mount
- last day in december
- last day in another month (preference February)

#### Invalid input:
this function uses the constructor which already checks if the 
date is valid or not, so no need to retest that.
#### Test inputs of **previousDate**
This function returns a new Date of the previous day.

I have added 2 test for this function
##### Valid input:
To check all possibilities for valid date, I made 3 examples :
- first day in January
- first day in another month except January
- day != 1

#### Invalid input:
same as nextDate function

2. Test Coverage
With the tests and the test cases I added, I got this result (100%)
![Semantic description of image](images/TP3_date_first_check.png "First Jacoco report")

3. Using PIT to evaluate the test suite

![Semantic description of image](images/first_pit_evaluation.png "First PIT evaluation")
![Semantic description of image](images/first_PIT_evalua.png "First PIT evaluation")

When I analyzed the mutations, I found that changing the operators for the compareTo function did not have an impact on the test that checks the equality of two dates. Because I separated the two tests (one for two equal dates 
and the other for different dates), this is why we obtained 3 mutations.
However, the other test **compareDifferentValidDate** failed, so I think it's logical, and we don't really need to do a refactor for code function.
I paid attention to something regarding my test cases in case I test different dates. 
I used some test cases with much equality in terms of year or month, 
which allowed me to refactor and improve my test cases.







