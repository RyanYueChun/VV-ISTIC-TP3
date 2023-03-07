# Detecting test smells with PMD

In folder [`pmd-documentation`](../pmd-documentation) you will find the documentation of a selection of PMD rules designed to catch test smells.
Identify which of the test smells discussed in classes are implemented by these rules.

Use one of the rules to detect a test smell in one of the following projects:

- [Apache Commons Collections](https://github.com/apache/commons-collections)
- [Apache Commons CLI](https://github.com/apache/commons-cli)
- [Apache Commons Math](https://github.com/apache/commons-math)
- [Apache Commons Lang](https://github.com/apache/commons-lang)

Discuss the test smell you found with the help of PMD and propose here an improvement.
Include the improved test code in this file.

## Answer

### Target project : commons-collection
#### Analyse the project with the follwoing rules:
1. ##### DetachedTestCase

Goal: The method appears to be a test case since it has public or default visibility, non-static access, no arguments, no return value, has no annotations, but is a member of a class that has one or more JUnit test cases.

I found 18 smelles using this rule : *"category/java/errorprone.xml/DetachedTestCase"* in commons-collections.

**Example1:**
```java
public void getFromIterable() throws Exception {
    // Collection, entry exists
    final Bag<String> bag = new HashBag<>();
    bag.add("element", 1);
    assertEquals("element", IterableUtils.get(bag, 0));
}
```
this method is never used in the class test(*IterableUtilsTest.java*) and also has no annotations (@Test, @Ignore ...). 

- Suggested solution: delete the method.

**Example2:**
```java
public void resetEmpty() {
    this.map = makeObject();
    this.confirmed = makeConfirmedMap();
}
```

Used method has public visibility. => class (*AbstractMultiValuedMapTest*)
- Suggested solution: change the visibility

2. ##### JUnitUseExpected
Goal: check for tests that throw exceptions, and they haven't _**@Test(expected)**_ annotation.

I found 6 smells.

**Example:**
Test class **_AbstractMapTest_** in the `testMapPut()` test.

3. ##### UseAssertEqualsInsteadOfAssertTrue
Goal: assertions should be made by more specific methods, like assertEquals.

I found 12 smells.

**Example:**
`assertTrue(multiset.equals(multiset2));` in line 546 of **_AbstractMultiSetTest_** class.
- Suggested solution: use assertEquals instead
`assertEquals("multiset should equal multiset2", multiset, multiset2);`




