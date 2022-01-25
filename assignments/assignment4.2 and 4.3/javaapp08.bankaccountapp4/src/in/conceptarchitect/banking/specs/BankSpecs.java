package in.conceptarchitect.banking.specs;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import in.conceptarchitect.banking.ResponseStatus;

import in.conceptarchitect.banking.Bank;

public class BankSpecs {
	
	final String bankName="ICICI";
	final double rate=12;
	final String correctPassword="p@ss";
	final double initialBalance=50000;
	
	int existingAccount1, existingAccount2;
	int initialTotalAccounts;
	Bank bank;
	
	@Before
	public void arrange() {
		//ARRANGE
		bank=new Bank(bankName,rate);
		existingAccount1=bank.openAccount("Name1", correctPassword, initialBalance);
		existingAccount2=bank.openAccount("Name", correctPassword, initialBalance);
		initialTotalAccounts=bank.getAccountCount();
	}
	
	
	@Test
	public void bankShouldHaveAName() {
				
		//ACT
		Bank bank=new Bank(bankName,10);
		
		//ASSERT
		assertEquals(bankName, bank.getName());
		
	}
	
	
	@Test
	public void bankShouldHaveAInterestRAte() {
		
		//ACT
		Bank bank=new Bank("Some Name",rate);
		
		//ASSERT
		assertEquals(rate, bank.getRate(),0);
				
		
	}
	
	@Test
	public void openAccountShouldTakeNamePasswordAndBalanceAndReturnAccountNumber() {
		
		//ACT
		int accountNumber1 = bank.openAccount("Aman", "mypassword", 1000.0);
		
		// ASSERT
		assertTrue(accountNumber1 > 0);
	}
	
	
	@Test		
	public void openAccountShouldReturnUniqueAccountNumber() {
		
		
		// ACT 
		var accountNumber1 = bank.openAccount("aman", "mypassword", 1000.0 );
		var accountNumber2 = bank.openAccount("arpit", "hispassword", 2000.0 );
		
		// ASSERT
		assertNotEquals(accountNumber1, accountNumber2);
	}
	
	
	@Test
	public void openAccountShouldIncreaseTotalAccountCountInTheBank() {
		
		
		// ACT 
		var accountNumber1 = bank.openAccount("aman", "mypassword", 1000.0 );
		
		
		// ASSERT
		assertEquals(initialTotalAccounts+1, bank.getAccountCount());
	}
	
	
	
	@Test
	public void closeAccountShouldFailFromInvalidAccountNumber() {
		
		//ACT
		var result = bank.closeAccount(initialTotalAccounts+1, "any-password");
		
		assertEquals(-1, result,0);
		
		
	}
	
	@Test
	public void closeAccountShouldFailFromInvalidAccountPassword() {
		//ACT
		var result = bank.closeAccount(existingAccount1, "wrong-password");
		
		assertEquals(-1, result,0);
		
	}
	
	
	
	@Test
	public void closeAccountShouldCloseTheAccountWithRightCredentials() {
		//ACT
		var result = bank.closeAccount(existingAccount1, correctPassword);
		
		//ASSERT
		assertNotEquals(-1, result,0);
	}
	
	

	@Test
	public void closeAccountShouldReturnBalanceOnSuccessfulClosure() {
		
		//ACT
		var result= bank.closeAccount(existingAccount1, correctPassword);
		//ASSERT
		assertEquals(initialBalance, result,0.01);
		
		
	}
	
	@Test
	public void closeAccountShouldReduceTheAccountCountInTheBank() {
		//ACT
		var result= bank.closeAccount(existingAccount1, correctPassword);
		
		//ASSERT
		assertEquals(initialTotalAccounts-1, bank.getAccountCount());
	}
	
	@Test
	public void closeShouldFailForAlreadyClosedAccount() {
		
		//ARRANGE
		bank.closeAccount(existingAccount1, correctPassword);
		//Now existingAccount1 is closed. It can't be closed again
		
		//ACT
		var result=bank.closeAccount(existingAccount1, correctPassword);		
		
		//ASSERT
		assertEquals(-1, result,0);
		
	}
	
	@Test
	public void accountNumberShouldNotBeReused() {
		//ARRANGE
		String a4Name="Account 4";
		String a5Name="Account 5";
		bank.openAccount("Name", correctPassword, initialBalance); //3
		bank.openAccount(a4Name, correctPassword, initialBalance); //4
		
		bank.closeAccount(3, correctPassword); //we closed account 3
		
		//ACT
		var accountNumber= bank.openAccount(a5Name, correctPassword, initialBalance);
		var account4= bank.getAccount(4,correctPassword);
		
		//ASSERT
		assertEquals(5,accountNumber);
		assertEquals(a4Name, account4.getName());	
		
	}
	
	
	
	@Test
	public void weShouldNotBeAbleToGetClosedAccount() {
		String a3Name="Account 3";
		String a4Name="Account 4";
		bank.openAccount(a3Name, correctPassword, initialBalance); //3
		bank.openAccount(a4Name, correctPassword, initialBalance);
		bank.closeAccount(3, correctPassword);
		
		var account3 = bank.getAccount(3, correctPassword);
//		System.out.println(num);
		assertNull(account3);
		
	}
	
	@Test
	public void creditInterstShouldCreditInterstToAllAccounts() {
		String a3Name="Account 3";
		bank.openAccount(a3Name, correctPassword, initialBalance);

		bank.creditInterest();
		var balance3 = bank.getBalance(3, correctPassword);
		
		assertNotEquals(initialBalance, balance3);
	}
	
	@Test
	public void getBalanceShouldReturnBalanceForCorrectAccountAndPassword() {
		String a3Name="Account 3";
		bank.openAccount(a3Name, correctPassword, initialBalance);
		bank.closeAccount(3, correctPassword);
		
		var balance3 = bank.getBalance(3, correctPassword);
		
		assertEquals(-1, balance3, 0.0001);
	}
	
	@Test
	public void getBalanceShouldFailForInvalidAccountNumber() {
		String a3Name="Account 3";
		bank.openAccount(a3Name, correctPassword, initialBalance);

		
		var balance4 = bank.getBalance(4, correctPassword);
		
		assertEquals(-1, balance4, 0.001);
	}
	
	@Test
	public void getBalanceShouldFailForInvalidPassword() {
		String a3Name="Account 3";
		bank.openAccount(a3Name, correctPassword, initialBalance);

		
		var balance3 = bank.getBalance(3, "password");
		
		assertEquals(-1, balance3, 0.001);
	}
	
	@Test
	public void depositShouldFailForInvalidAccountNumber() {
		String a3Name="Account 3";
		String a4Name="Account 4";
		int amount = 1000;
		bank.openAccount(a3Name, correctPassword, initialBalance); //3
		bank.openAccount(a4Name, correctPassword, initialBalance);
		bank.closeAccount(3, correctPassword);
		
		var result = bank.deposit(3, amount);

		assertFalse(result);
	}

	@Test
	public void depositShouldFailForInvalidAmount() {
		String a3Name="Account 3";
		int amount = -1000;
		bank.openAccount(a3Name, correctPassword, initialBalance); //3
		
		var result = bank.deposit(3, amount);

		assertFalse(result);
	}
	
	@Test
	public void depositShouldCreditBalanceOnSuccess() {
		String a3Name="Account 3";
		int amount = 1000;
		bank.openAccount(a3Name, correctPassword, initialBalance); //3
		
		var result = bank.deposit(3, amount);

		assertTrue(result);
	}
	
	@Test
	public void withdrawShouldFailForInvalidAccountNumber() {
		String a3Name="Account 3";
		bank.openAccount(a3Name, correctPassword, initialBalance); //3
		
		bank.closeAccount(3, correctPassword);
		int amount = 1000;
		var result = bank.withdraw(3, amount, correctPassword);

		assertEquals(ResponseStatus.INVALID_CREDENTIALS, result.getCode());
	}
	
	@Test
	public void withdrawShouldFailForInvalidPassword() {
		String a3Name="Account 3";
		bank.openAccount(a3Name, correctPassword, initialBalance); //3
		int amount = 1000;
		var result = bank.withdraw(3, amount, "password");

		assertEquals(ResponseStatus.INVALID_CREDENTIALS, result.getCode());
	}
	
	@Test
	public void withdrawShouldFailForInvalidAmount() {
		String a3Name="Account 3";
		bank.openAccount(a3Name, correctPassword, initialBalance); //3
		int amount1 = 0, amount2 = -100;
		var result1 = bank.withdraw(3, amount1, correctPassword);
		var result3 = bank.withdraw(3, amount2, correctPassword);

		assertEquals(ResponseStatus.INVALID_AMOUNT, result1.getCode());
		assertEquals(ResponseStatus.INVALID_AMOUNT, result3.getCode());
	}
	
	@Test
	public void withdrawShouldFailForOverDraft() {
		String a3Name="Account 3";
		bank.openAccount(a3Name, correctPassword, initialBalance); //3
		double amount = initialBalance + 1000;
		
		var result2 = bank.withdraw(3, amount, correctPassword);
		
		assertEquals(ResponseStatus.INSUFFICIENT_FUNDS, result2.getCode());
	}
	
	@Test
	public void withdrawShouldReduceBalanceByAmountOnSuccess() {
		String a3Name="Account 3";
		bank.openAccount(a3Name, correctPassword, initialBalance); //3
		int amount = 1000;
		
		bank.withdraw(3, amount, correctPassword);
		
		var result = bank.getBalance(3, correctPassword);
		
		assertEquals(initialBalance - amount, result, 0.0001);

	}
	
	@Test
	public void transferShouldFailForInvalidSourceAccountNumber() {
		
		int amount = 1000;
		var result = bank.transfer(-1, amount, correctPassword, existingAccount2);
	
		assertEquals(ResponseStatus.INVALID_CREDENTIALS, result.getCode());
	}

	@Test
	public void transferShouldFailForInvalidPassword() {
		int amount = 1000;
		String wrongPass = "password";
		var result = bank.transfer(existingAccount1, amount, wrongPass, existingAccount2);
	
		assertEquals(ResponseStatus.INVALID_CREDENTIALS, result.getCode());
	}
	
	@Test
	public void transferShouldFailForInvalidAmount() {
		int amount1 = -1, amount2 = 0;
		var result1 = bank.transfer(existingAccount1, amount1, correctPassword, existingAccount2);
		var result2 = bank.transfer(existingAccount1, amount2, correctPassword, existingAccount2);
	
		assertEquals(ResponseStatus.INVALID_AMOUNT, result1.getCode());
		assertEquals(ResponseStatus.INVALID_AMOUNT, result2.getCode());
	}
	
	@Test
	public void transferShouldFailForOverDraft() {
		String a3Name="Account 3";
		bank.openAccount(a3Name, correctPassword, initialBalance); //3
		int amount = (int)initialBalance + 1000;
		
		var result2 = bank.transfer(3, amount, correctPassword, existingAccount2);
		
		assertEquals(ResponseStatus.INSUFFICIENT_FUNDS, result2.getCode());
	}
	
	@Test
	public void transferShouldReduceBalanceInSourceAccountOnSuccess() {
		
		int amount = 1000;
		
		bank.transfer(existingAccount1, amount, correctPassword, existingAccount2);
		
		var result = bank.getBalance(existingAccount1, correctPassword);
		
		assertEquals(initialBalance - amount, result, 0.0001);
	}
	
	@Test
	public void transferShouldFailForInvalidTargetAccountNumber() {
		int amount = 1000;
		var result = bank.transfer(existingAccount1, amount, correctPassword, -1);
	
		assertEquals(ResponseStatus.INVALID_CREDENTIALS, result.getCode());
	}

	@Test
	public void transferShouldAddBalanceInTargetOnSuccess() {
		int amount = 1000;
		
		bank.transfer(existingAccount1, amount, correctPassword, existingAccount2);
		
		var result = bank.getBalance(existingAccount2, correctPassword);
		
		assertEquals(initialBalance + amount, result, 0.0001);
	}
	
	
	
	
	
	
	
	
	
	

}
