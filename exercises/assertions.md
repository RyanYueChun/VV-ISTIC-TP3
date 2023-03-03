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