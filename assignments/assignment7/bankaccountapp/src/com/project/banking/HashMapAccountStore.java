package com.project.banking;

import java.util.HashMap;
import java.util.Map;

import com.project.banking.exceptions.InvalidAccountException;

public class HashMapAccountStore {


	int accountCount=0;	
	int lastAccountNumber=0;
	HashMap<Integer, BankAccount> accounts=new HashMap<>();
	

	public int addAccount(BankAccount account) {
		int accountNumber= ++lastAccountNumber;
		accountCount++;
		account.setAccountNumber(accountNumber);
		accounts.put(accountNumber, account);
		return accountNumber;
	}
	
	public BankAccount getAccount(int accountNumber) {
		if(accountNumber<1 || accountNumber>lastAccountNumber || accounts.get(accountNumber)==null)
			throw new InvalidAccountException(accountNumber);

		//it either returns a valid value or throws an exception
		return accounts.get(accountNumber);
	}

	public void removeAccount(int accountNumber) {
		if(accountNumber<1 || accountNumber>lastAccountNumber || accounts.get(accountNumber)==null)
			throw new InvalidAccountException(accountNumber);
		accounts.put(accountNumber, null);
		accountCount--;
	}
	
	public BankAccount[] getAllActiveAccounts() {
		
		BankAccount [] activeAccounts=new BankAccount[accountCount];

		for (Map.Entry<Integer, BankAccount> e : accounts.entrySet()) {
			if(e.getValue() != null) {
				activeAccounts[e.getKey() - 1]=e.getValue();
			}
		}
		return activeAccounts;
	
	}

	public int getAccountCount() {
		// TODO Auto-generated method stub
		return accountCount;
	}
	
	

}
