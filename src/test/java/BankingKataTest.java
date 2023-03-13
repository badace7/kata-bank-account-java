import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;


class BankingKataTest {
    Account accountWithoutOverDraftAuthorization = new Account(100, false,0);
    Account accountWithOverDraftAuthorization = new Account(100, true,100);
    @Test
    void Should_deposit_hundred_dollars_and_return_the_balance() {
        accountWithoutOverDraftAuthorization.deposit(100);
        assertThat(accountWithoutOverDraftAuthorization.getBalance())
                .isEqualTo(200);
    }
    @Test
    void Should_withdraw_fifty_dollars_and_return_the_balance() throws Exception {
        accountWithoutOverDraftAuthorization.withdraw(50);
        assertThat(accountWithoutOverDraftAuthorization.getBalance())
                .isEqualTo(50);
    }

    @Test
    void Should_deposit_two_thousand_dollars_and_return_the_statement() {
        accountWithoutOverDraftAuthorization.deposit(2000);
        assertThat(accountWithoutOverDraftAuthorization.printStatement())
                .isEqualTo(LocalDate.now() + " +2000 2100");
    }
    @Test
    void Should_withdraw_four_dollars_and_return_the_statement() throws Exception {
        accountWithoutOverDraftAuthorization.withdraw(4);
        assertThat(accountWithoutOverDraftAuthorization.printStatement())
                .isEqualTo(LocalDate.now() + " -4 96");
    }

    @Test
    void Should_try_to_withdraw_one_hundred_and_twenty_dollars_and_return_an_exception() {
        Exception tryToWithdraw = Assertions.assertThrows(Exception.class, () -> {
            accountWithoutOverDraftAuthorization.withdraw(120);
        });
        Assertions.assertEquals("Cannot withdraw, balance insufficient", tryToWithdraw.getMessage());
    }
    @Test
    void Should_withdraw_one_hundred_and_twenty_dollars_with_overdraft_authorization() throws Exception {
        accountWithOverDraftAuthorization.withdraw(120);
        assertThat(accountWithOverDraftAuthorization.printStatement()).isEqualTo(LocalDate.now() + " -120 -20");
    }

    @Test
    void Should_try_to_withdraw_two_hundred_and_twenty_dollars_with_overdraft_authorization() {
        Exception tryToWithdraw = Assertions.assertThrows(Exception.class, () -> {
            accountWithOverDraftAuthorization.withdraw(220);
        });
        Assertions.assertEquals("Cannot withdraw, balance insufficient", tryToWithdraw.getMessage());
    }
}
