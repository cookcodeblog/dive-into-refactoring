package org.example.refactoringtechniques;

import org.assertj.core.data.Offset;
import org.junit.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://refactoring.guru/decompose-conditional
 */
public class DecomposeConditionalTest {

    class Problem {
        private final Date SUMMER_START = new Date(2022, 6, 15);
        private final Date SUMMER_END = new Date(2022, 9, 1);

        private int quantity;
        private Date date;

        public Problem(int quantity, Date date) {
            this.quantity = quantity;
            this.date = date;
        }

        double charge() {
            double winterRate = 0.98;
            double winterServiceCharge = 2;
            double summerRate = 0.95;

            if (date.before(SUMMER_START) || date.after(SUMMER_END)) {
                return quantity * winterRate + winterServiceCharge;
            } else {
                return quantity * summerRate;
            }
        }
    }

    class Solution {
        private final Date SUMMER_START = new Date(2022, 6, 15);
        private final Date SUMMER_END = new Date(2022, 8, 1);

        private int quantity;
        private Date date;

        public Solution(int quantity, Date date) {
            this.quantity = quantity;
            this.date = date;
        }

        double charge() {
            if (isWinter(date)) {
                return winterCharge(quantity);
            } else {
                return summerCharge(quantity);
            }
        }

        private double winterCharge(int quantity) {
            double winterRate = 0.98;
            double winterServiceCharge = 2;
            return quantity * winterRate + winterServiceCharge;
        }

        private double summerCharge(int quantity) {
            double summerRate = 0.95;
            return quantity * summerRate;
        }

        private boolean isWinter(Date date) {
            return date.before(SUMMER_START) || date.after(SUMMER_END);
        }
    }

    @Test
    public void shouldHaveSameBehavior() {
        // Problem cases
        assertThat(new Problem(100, new Date(2022, 6, 20)).charge())
                .isCloseTo(95, Offset.offset(0.001));
        assertThat(new Problem(100, new Date(2022, 10, 1)).charge())
                .isCloseTo(100, Offset.offset(0.001));

        // Solution cases
        assertThat(new Solution(100, new Date(2022, 6, 20)).charge())
                .isCloseTo(95, Offset.offset(0.001));
        assertThat(new Solution(100, new Date(2022, 10, 1)).charge())
                .isCloseTo(100, Offset.offset(0.001));
    }
}
