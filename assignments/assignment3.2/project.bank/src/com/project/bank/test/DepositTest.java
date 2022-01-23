package com.project.bank.test;

import org.junit.Test;

import com.project.bank.service.BankAccount;
import static org.junit.Assert.*;

public class DepositTest {
	BankAccount acc = new BankAccount(1234554321, "Aman", "1441", 100000, 10.6);
	
	@Test
	public void test1() {
		int amount = 0;
		boolean result = acc.deposit(amount);
		assertEquals(false, result);
	}
	
	@Test
	public void test2() {
		int amount = -1;
		boolean result = acc.deposit(amount);
		assertEquals(false, result);
	}
	
	@Test
	public void test3() {
		int amount = 700;
		boolean result = acc.deposit(amount);
		assertEquals(true, result);
	}
}
