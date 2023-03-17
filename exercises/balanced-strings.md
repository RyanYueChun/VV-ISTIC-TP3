# Balanced strings

A string containing grouping symbols `{}[]()` is said to be balanced if every open symbol `{[(` has a matching closed symbol `]}` and the substrings before, after and between each pair of symbols is also balanced. The empty string is considered as balanced.

For example: `{[][]}({})` is balanced, while `][`, `([)]`, `{`, `{(}{}` are not.

Implement the following method:

```java
public static boolean isBalanced(String str) {
        ...
}
```

`isBalanced` returns `true` if `str` is balanced according to the rules explained above. Otherwise, it returns `false`.

Use the coverage criteria studied in classes as follows:

1. Use input space partitioning to design an initial set of inputs. Explain below the characteristics and partition blocks you identified.
2. Evaluate the statement coverage of the test cases designed in the previous step. If needed, add new test cases to increase the coverage. Describe below what you did in this step.
3. If you have in your code any predicate that uses more than two boolean operators check if the test cases written so far satisfy *Base Choice Coverage*. If needed add new test cases. Describe below how you evaluated the logic coverage and the new test cases you added.
4. Use PIT to evaluate the test suite you have so far. Describe below the mutation score and the live mutants. Add new test cases or refactor the existing ones to achieve a high mutation score.

Write below the actions you took on each step and the results you obtained.
Use the project in [tp3-balanced-strings](../code/tp3-balanced-strings) to complete this exercise.

## Answer
You can find the program code [here](../code/tp3-balanced-strings)
1. I have divided my test cases into four partitions based on specific criteria.
- The **first** partition focuses on valid strings, where all symbols are correctly placed.
- The **second** and **third** partitions deal with strings that are missing either a closing or opening symbol, respectively.
- The **fourth** partition deals with strings that contain symbols with an incorrect or random order.

2. To see the coverage of my tests, I used the Jacoco library. 
Within my test class, I created 2 parameterized  tests and 4 MethodSources for 
the partitions that I defined.
these defined test cases, I was able to achieve 100% coverage (Missed Instructions: 0 & Missed Branches 0)

To get the report , follow this steps :
- `mvn test`
- `mvn jacoco:report`

You will find the report in **/code/tp3-balanced-strings/target/site/jacoco/fr.istic.vv/index.html**

3. Done in previous question
4. In my first PIT test, I achieved 93% mutation coverage. 
The PIT tool generated a total of 15 mutations, out of which 14 were killed and only 1 survived.
Upon further analysis of the mutations, I realized that I was missing a test case for special characters.
I added a new partition for this scenario and my mutation score had improved to 100%.

In the code u will find this new partition.

For the report, you can use the command: `mvn clean test pitest:mutationCoverage`.
You will find the generated reports in **target/pit-reports**


