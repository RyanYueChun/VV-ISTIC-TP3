# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer
1.
The values .4 and 1.2 are of the float type. Float types are numeric values that are approximations of their real mathematical counterparts. Meaning, these float values of .4 and 1.2 are close to the theoric mathematical .4 and 1.2 values, but there can be a margin of error.
To prevent this kind of problem, we can take into account a certain level of approximation.
For example, we first calculate the float value of `3 * .4`, then we compare the difference in value between that latter result and the float value `1.2` using the substraction operator as so : `1.2 - (3 * .4)`.

If the absolute value of that substraction is lower than the level of precision we desire, then we can consider the assertion passed.
For example, for a margin of error of `0.0001` at most :`assertTrue(abs(1.2 - (3 * .4)) < 0.0001)`.

2.
`assertSame` checks if 2 Java objects are using the same pointer reference. `assertEquals` instead checks for equality for the values ; if we are comparing primitives the values will be compared using the operator `==`, or else, the method `equals()` if objects are being compared.
```
@Test
public void test1() {
	Integer i1 = new Integer(1);
	Integer unknown = new Integer(1);

	// both test pass
	assertEquals(i1, i1);
	assertSame(i1, i1);

	// not the same result
	// passes
	assertEquals(i1, unknown);
	// fails
	assertSame(i1, unknown);
}
```

3.
`fail` is a method provided by junit. When it's called in a test, it causes the test to fail. In addition to scenarios where **an exception** was expected, it's can also used in scenarios where the value obtained isn't equal to what was expected, or when there is an unfinished test case that must be written later. The situations where we may want to use `fail` instead of `assertEquals` is when we don't directly have values to compare. For example, when we are moving from an application state to another in the workflow, if we end up in an application state that should be avoided for the current test case `fail` is appropriate.
```
@Test
public void testException() {
	Mouse mouse = new Mouse();
	Cat cat = new Cat();

	// Normally, cat is stronger than mouse
	if (mouse.defeat(cat)) {
		// an exception should be thrown, if not, then fail
		fail("mouse can't defeat cat");
	}
}
```

4.
The advantages of `assertThrows` over `@Test` are :
	- The code is easier to read. We can see the type of exception expected.
	- It has more information like the type of exception, its message, the stack trace.
	- No need to use a try-catch block.
	- We can specify other assertions in addition to it.
