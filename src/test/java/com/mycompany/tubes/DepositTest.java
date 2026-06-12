package com.mycompany.tubes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DepositTest {

    @Test
    void tambahDepositShouldIncreaseSaldo() {
        Deposit deposit = new Deposit(1, 1, 100_000);

        deposit.tambahDeposit(50_000);

        assertEquals(150_000, deposit.getSaldo());
    }

    @Test
    void tarikDepositShouldDecreaseSaldoWhenEnoughBalance() {
        Deposit deposit = new Deposit(1, 1, 100_000);

        deposit.tarikDeposit(40_000);

        assertEquals(60_000, deposit.getSaldo());
    }

    @Test
    void tarikDepositShouldNotChangeSaldoWhenBalanceIsNotEnough() {
        Deposit deposit = new Deposit(1, 1, 100_000);

        deposit.tarikDeposit(200_000);

        assertEquals(100_000, deposit.getSaldo());
    }
}
