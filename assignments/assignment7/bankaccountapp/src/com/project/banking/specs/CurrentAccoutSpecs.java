package com.project.banking.specs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.project.banking.BankAccount;
import com.project.banking.CurrentAccount;
import com.project.banking.exceptions.InvalidCredentialsException;

public class CurrentAccoutSpecs {
	String name="Name";
	double balance=50000;
	String correctPassword="p@ss";
	double interestRate=12;
	CurrentAccount account;

	@Before
	public void setUp() throws Exception {
		account=new CurrentAccount(1,name,correctPassword,balance);
	}

	
	@Test
	public void currentAccountIsATypeOfBankAccount() {
		assertTrue(account instanceof BankAccount);
	}
	
	
	@Test
	public void creditInterstDoesntCreditInterest() {
		account.creditInterest(interestRate);
		assertEquals(balance, account.getBalance(),0);
	}
	

	
	
	@Test
	public void withdrawCanWithdrawEntireBlance() {
		
		account.withdraw(balance, correctPassword);
	
		assertEquals(0, account.getBalance(),0);
	}
	
	
	@Test(expected = InvalidCredentialsException.class)
	public void withdrawFailsForWrongPassword() {
		
		account.withdraw(100, "not-"+correctPassword);
	}
}