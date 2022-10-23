package org.example.refactoringtechniques;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://refactoring.guru/remove-assignments-to-parameters
 */
public class RemoveAssignmentsToParametersTest {

    class Order {
        private int inputVal;
        private int quantity;

        public Order(int inputVal, int quantity) {
            this.inputVal = inputVal;
            this.quantity = quantity;
        }

        public int getInputVal() {
            return inputVal;
        }

        public int getQuantity() {
            return quantity;
        }
    }

    class Problem {

        int discount(int inputVal, int quantity) {
            if (inputVal > 50) {
                inputVal -= 2;
            }

            return inputVal * quantity;
        }
    }

    class Solution {

        int discount(int inputVal, int quantity) {
            int result = inputVal;
            if (inputVal > 50) {
                result -= 2;
            }

            return result * quantity;
        }

    }

    @Test
    public void shouldHaveSameBehavior() {

        // Problem cases
        Order order1 = new Order(100, 10);
        assertEquals(980, new Problem().discount(order1.getInputVal(), order1.getQuantity()));
        assertEquals(100, order1.getInputVal());

        // Solution cases
        Order order2 = new Order(100, 10);
        assertEquals(980, new Solution().discount(order2.getInputVal(), order2.getQuantity()));
        assertEquals(100, order2.getInputVal());
    }
}
