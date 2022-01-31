package com.project.banking;

import java.util.ArrayList;

import com.project.banking.exceptions.InvalidAccountException;

public class ListAccountStore {


	int accountCount=0;	
	int lastAccountNumber=0;
	ArrayList<BankAccount> accounts=new ArrayList<BankAccount>(100);
	

	public int addAccount(BankAccount account) {
		int accountNumber= ++lastAccountNumber;
		accountCount++;
		account.setAccountNumber(accountNumber);
		accounts.add(account);
		return accountNumber;
	}
	
	public BankAccount getAccount(int accountNumber) {
		if(accountNumber<1 || accountNumber>lastAccountNumber || accounts.get(accountNumber)==null)
			throw new InvalidAccountException(accountNumber);

		//it either returns a valid value or throws an exception
		return accounts.get(accountNumber - 1);
	}

	public void removeAccount(int accountNumber) {
		if(accountNumber<1 || accountNumber>lastAccountNumber || accounts.get(accountNumber)==null)
			throw new InvalidAccountException(accountNumber);
		accounts.set(accountNumber, null);
		accountCount--;
	}
	
	public BankAccount[] getAllActiveAccounts() {
		
		BankAccount [] activeAccounts=new BankAccount[accountCount];
		var i=0;
		for(var account : accounts) {
			if(account!=null) {
				activeAccounts[i]=account;
				i++;
			}
		}
		
		return activeAccounts;
	
	}

	public int getAccountCount() {
		// TODO Auto-generated method stub
		return accountCount;
	}
	
	

}
