package fr.istic.vv;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StringUtilsTest {

    @ParameterizedTest
    @MethodSource("validBalancedString")
    public void itShouldReturnTrue(String input) {
        assertTrue(StringUtils.isBalanced(input));
    }

    @ParameterizedTest
    @MethodSource({"wrongOrder", "missingOpeningSymbol", "missingClosingSymbol", "invalidString"})
    public void itShouldReturnFalse(String input) {
        assertFalse(StringUtils.isBalanced(input));
    }

    static Stream<String> missingOpeningSymbol() {
        return Stream.of(
                "][()",
                "}}{{",
                "][()"
        );
    }

    static Stream<String> missingClosingSymbol() {
        return Stream.of(
                "{()[]",
                "{}[",
                "[{}()[]"
        );
    }

    static Stream<String> wrongOrder() {
        return Stream.of(
                "{(})",
                "[(){}[(])",
                "{}()([)]",
                "][",
                "{(}{}"
        );
    }

    /**
     * Partition created after PIT Evaluation
     */
    static Stream<String> invalidString() {
        return Stream.of(
                "lmsp",
                "${}",
                "**",
                "AABB(("
        );
    }

    static Stream<String> validBalancedString() {
        return Stream.of(
                "",
                "{[][]}({})",
                "[()]{}",
                "{{{(([]))}}}"
        );
    }

}