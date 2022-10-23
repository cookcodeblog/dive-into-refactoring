package org.example.refactoringtechniques;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

/**
 * https://refactoring.guru/substitute-algorithm
 */
public class SubstituteAlgorithmTest {

    class Problem {
        String foundPerson(String[] people) {
            for (int i = 0; i < people.length; i++) {
                if (people[i].equals("Don")) {
                    return "Don";
                }
                if (people[i].equals("John")) {
                    return "John";
                }
                if (people[i].equals("Kent")) {
                    return "Kent";
                }
            }
            return "";
        }
    }

    class Solution {
        String foundPerson(String[] people) {
            List<String> candidates = Arrays.asList("Don", "John", "Kent");
            for (int i = 0; i < people.length; i++) {
                if (candidates.contains(people[i])) {
                    return people[i];
                }
            }
            return "";
        }
    }

    @Test
    public void shouldHaveSameBehavior() {
        // Problem cases
        assertThat(new Problem().foundPerson(new String[] {"Don", "John", "Kent"}))
                .isEqualTo("Don");
        assertThat(new Problem().foundPerson(new String[] {"Tom", "Lucy", "Jim"}))
                .isEqualTo("");

        // Solution cases
        assertThat(new Solution().foundPerson(new String[] {"Don", "John", "Kent"}))
                .isEqualTo("Don");
        assertThat(new Solution().foundPerson(new String[] {"Tom", "Lucy", "Jim"}))
                .isEqualTo("");
    }
}
