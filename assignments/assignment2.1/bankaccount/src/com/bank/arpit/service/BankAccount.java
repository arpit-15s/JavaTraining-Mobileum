package com.bank.arpit.service;

public class BankAccount {
	private String name, accNumber, password;
	private int interestRate, balance;
	
	public BankAccount(String name, String accNumber, String password, int interestRate, int balance){
		this.name = name;
		this.accNumber = accNumber;
		this.password = password;
		this.interestRate = interestRate;
		this.balance = balance;
	}

	
	public int deposit(int amount) {
		if(amount <= 0) {
			System.out.println("Kindly enter amount greater than 0");
		}
		else setBalance(this.balance + amount);
		return getBalance();
	}
	
	public int withdraw(int amount, String password) {
		if((this.password != password) || (amount <= 0) || (amount > this.balance)) {
			System.out.println("Invalid Entry, Try Again");
		}
		else setBalance(this.balance - amount);
		return getBalance();
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
