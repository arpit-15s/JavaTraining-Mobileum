package com.project.bank.test;

import static org.junit.Assert.assertEquals;

import com.project.bank.service.Response;
import com.project.bank.service.ResponseStatus;

import org.junit.Test;

import com.project.bank.service.BankAccount;

public class WithdrawTest {
	
	BankAccount account = new BankAccount(1234554321, "Aman", "1441", 100000, 10.6);
	@Test
	public void test1() {
		int amount = 0;
//		Response expected = new Response(ResponseStatus.INVALID_AMOUNT,"Enter Positive Amount");
		Response result = account.withdraw(amount, "1441");
//		System.out.println(result);
		assertEquals(ResponseStatus.INVALID_AMOUNT, result.getCode());
	}
	
	@Test
	public void test2() {
		int amount = -100;
		Response result = account.withdraw(amount, "1441");
		assertEquals(ResponseStatus.INVALID_AMOUNT, result.getCode());
	}
	
	@Test
	public void test3() {
		int amount = 1000000;
		Response result = account.withdraw(amount, "1441");
		assertEquals(ResponseStatus.INSUFFICIENT_FUNDS, result.getCode());
	}
	
	@Test
	public void test4() {
		int amount = 100;
		Response result = account.withdraw(amount, "141");
		assertEquals(ResponseStatus.INVALID_CREDENTIALS, result.getCode());
	}
	
	@Test
	public void test5() {
		int amount = 1000;
		Response result = account.withdraw(amount, "1441");
		assertEquals(ResponseStatus.SUCCESS, result.getCode());
	}
}

//this.bankAccounts.add(new Account("Aman", "1234554321", "1441", 10.2, 100000));
//this.bankAccounts.add(new Account("Vijay", "890765463", "1411", 9.7, 100000));
//this.bankAccounts.add(new Account("Suraj", "123450021", "2941", 10, 6000000));
