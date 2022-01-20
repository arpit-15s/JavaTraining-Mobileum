package com.project.bank.service;

import java.util.ArrayList;
import com.project.bank.service.Account;

public class Server {
	private ArrayList<Account> bankAccounts = new ArrayList<Account>();
	
//	Server(String name, String accNumber, String password, double interestRate, int balance){
//		bankAccounts.add(new Account(name, accNumber, password, interestRate, balance));
//	}
	
	public Server(){
		this.bankAccounts.add(new Account("Aman", "1234554321", "1441", 10.2, 100000));
		this.bankAccounts.add(new Account("Vijay", "890765463", "1411", 9.7, 100000));
		this.bankAccounts.add(new Account("Suraj", "123450021", "2941", 10, 6000000));
	}
	
	Account tempAccount = new Account();
	public boolean validateAccount(String name, String accNumber, String password) {
		for (Account account : bankAccounts) {
			if(account.validateDetails(name, accNumber, password)) {
				tempAccount = account;
				return true;
			}
		}
		return false;
	}
	
	public int fetchBalance() {
		return tempAccount.getBalance();
	}
	
	public int depositBalance(int amount) {
		boolean success = tempAccount.deposit(amount);
		if(success) return fetchBalance();
		else return -1;
	}
	
	public int withdraw(int amount) {
		int success = tempAccount.withdraw(amount, tempAccount.getPassword());
		if(success < 0) return success;
		else return fetchBalance();
	}
	
	public void changePin(String pin) {
		tempAccount.setPassword(pin);
		return;
	}
	
}
