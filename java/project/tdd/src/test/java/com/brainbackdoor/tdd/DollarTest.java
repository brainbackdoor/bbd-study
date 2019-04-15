package com.brainbackdoor.tdd;

import org.junit.Test;

import static org.junit.Assert.*;

public class DollarTest {
    @Test
    public void testMultiplication() {
        Money five = new Dollar(5, "USD");
        assertEquals(new Dollar(10, "USD"), five.times(2));
        assertEquals(new Dollar(15, "USD"), five.times(3));
    }

    @Test
    public void testEquality() {
        assertTrue(Money.dollor(5).equals(Money.dollor(5)));
        assertFalse(Money.dollor(5).equals(Money.dollor(6)));
        assertTrue(Money.franc(5).equals(Money.franc(5)));
        assertFalse(Money.franc(5).equals(Money.franc(6)));
    }

    @Test
    public void testCurrency() {
        assertEquals("USD", ((Money) new Dollar(1, "USD")).currency());
        assertEquals("CHF", ((Money) new Franc(1, "CHF")).currency());
    }

    @Test
    public void testDifferentClassEquality() {
        assertTrue(new Money(10, "CHF").equals(new Franc(10, "CHF")));
    }

    @Test
    public void testSimpleAddition() {
        Money five = Money.dollor(5);
        Expression result = five.plus(five);
        Sum sum = (Sum) result;
        assertEquals(five, sum.addend);
        assertEquals(five, sum.augend);
    }
}
