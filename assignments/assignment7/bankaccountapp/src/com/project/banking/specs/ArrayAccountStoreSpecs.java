package com.project.banking.specs;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.project.banking.ArrayAccountStore;
import com.project.banking.BankAccount;
import com.project.banking.CurrentAccount;
import com.project.banking.OverdraftAccount;
import com.project.banking.SavingsAccount;
import com.project.banking.exceptions.InvalidAccountException;

public class ArrayAccountStoreSpecs {
	final String correctPassword="p@ss";
	final double initialBalance=50000;
	
	ArrayAccountStore accounts = new ArrayAccountStore();
	int savingsAccountNumber, currentAccountNumber,odAccountNumber;
	int initialTotalAccounts;
	
	
	@Before
	public void arrange() {
		//ARRANGE
		
		savingsAccountNumber= accounts.addAccount(new SavingsAccount(0,"Name1", correctPassword, initialBalance));
		currentAccountNumber= accounts.addAccount(new CurrentAccount(0,"Name", correctPassword, initialBalance));
		odAccountNumber= accounts.addAccount(new OverdraftAccount(0, "Name1", correctPassword,initialBalance));
		initialTotalAccounts = 3;
	}
	
	@Test 
	public void addAccountShouldAddNewAccount() {
		int newAccountNumber = accounts.addAccount(new CurrentAccount(0, "Name1", correctPassword, initialBalance));
		int expected = 4;
		assertEquals(expected, accounts.getAccountCount());
	}
	
	@Test(expected = InvalidAccountException.class)
	public void getAccountShouldFailForAccountNumberLessThanOne() {
		accounts.getAccount(0);
	}
	
	@Test(expected = InvalidAccountException.class)
	public void getAccountShouldFailForInvalidAccountNumber() {
		int invalidAccount = 10;
		accounts.getAccount(invalidAccount);
	}
	
	@Test(expected = InvalidAccountException.class)
	public void getAccountShouldFailForClosedAccount() {
		accounts.removeAccount(savingsAccountNumber);
		accounts.getAccount(savingsAccountNumber);
	}
	
	@Test(expected = InvalidAccountException.class)
	public void removeAccountShouldFailForClosedAccount() {
		accounts.removeAccount(currentAccountNumber);
		accounts.removeAccount(currentAccountNumber);
	}
	
	@Test(expected = InvalidAccountException.class)
	public void removeAccountShouldFailForInvalidAccountNumber() {
		int invalidAccountNumber = 10;
		accounts.removeAccount(invalidAccountNumber);
	}
	
	@Test
	public void removeAccountShouldRemoveTheAccounts() {
		int expected = accounts.getAccountCount() - 1;
		accounts.removeAccount(currentAccountNumber);
		int result = accounts.getAccountCount();
		assertEquals(expected, result);
	}
	
	@Test
	public void getAllACtiveAccountsShouldGiveAllActiveAccounts() {
		int expected = accounts.getAccountCount();
		BankAccount [] result = accounts.getAllActiveAccounts();
		
		assertEquals(expected, result.length);
	}
	
	@Test
	public void getAccountCountShouldGiveTHeNUmberOfActiveAccounts() {
		int result = accounts.getAccountCount();
		
		assertEquals(initialTotalAccounts, result);
	}
}
