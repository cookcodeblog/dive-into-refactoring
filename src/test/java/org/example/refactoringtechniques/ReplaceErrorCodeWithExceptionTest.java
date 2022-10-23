package org.example.refactoringtechniques;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ReplaceErrorCodeWithExceptionTest {

    class ProblemAccount {
        private int balance;

        public ProblemAccount(int balance) {
            this.balance = balance;
        }

        int withdraw(int amount) {
            if (amount > balance) {
                return -1;
            } else {
                balance -= amount;
                return 0;
            }
        }
    }

    class SolutionAccount {
        private int balance;

        public SolutionAccount(int balance) {
            this.balance = balance;
        }

        void withdraw(int amount) {
            if (amount > balance) {
                throw new BalanceException();
            }
            balance -= amount;
        }

        public int getBalance() {
            return balance;
        }
    }

    class BalanceException extends RuntimeException {

    }

    @Test
    public void shouldHaveSameBehavior() {
        // Problem cases
        assertThat(new ProblemAccount(1000).withdraw(300)).isEqualTo(0);
        // assert error code
        assertThat(new ProblemAccount(1000).withdraw(1200)).isEqualTo(-1);

        // Solution cases
        SolutionAccount account1 = new SolutionAccount(1000);
        account1.withdraw(300);
        assertThat(account1.getBalance()).isEqualTo(700);

        // assert exception
        assertThatThrownBy(() -> {
            SolutionAccount account2 = new SolutionAccount(1000);
            account2.withdraw(1200);
        }).isInstanceOf(BalanceException.class);
    }
}
