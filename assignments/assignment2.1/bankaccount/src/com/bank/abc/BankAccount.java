package com.bank.abc;

public class BankAccount {
	private String name, accNumber, password;
	private int interestRate, balance;

	
	public int deposit(int amount) {
		if(amount <= 0) {
			System.out.println("Kindly enter amount greater than 0");
		}
		else this.balance += amount;
		return this.balance;
	}
	
	public int withdraw(int amount, String password) {
		if((this.password != password) || (amount <= 0) || (amount > this.balance)) {
			System.out.println("Invalid Entry, Try Again");
		}
		else this.balance -= amount;
		return this.balance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccNumber() {
		return accNumber;
	}

	public void setAccNumber(String accNumber) {
		this.accNumber = accNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(int interestRate) {
		this.interestRate = interestRate;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	
	
	
};
