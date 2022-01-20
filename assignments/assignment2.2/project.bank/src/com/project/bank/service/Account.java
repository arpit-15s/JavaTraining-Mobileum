package com.project.bank.service;

public class Account {

	private String name, accNumber, password;
	private int balance;
	private double interestRate;

	// constructors
	public Account() {

	}

	public Account(String name, String accNumber, String password, double interestRate, int balance) {
		this.name = name;
		this.accNumber = accNumber;
		this.password = password;
		this.interestRate = interestRate;
		this.balance = balance;
	}

	// deposit
	public boolean deposit(int amount) {
		if (amount <= 0) {
			return false;
		} else
			setBalance(this.balance + amount);

		return true;
	}

	public int withdraw(int amount, String password) {
		if (this.password != password)
			return -1;
		if (amount <= 0)
			return -2;
		if (amount > this.balance)
			return -3;

		setBalance(this.balance - amount);
		return 0;
	}

	public boolean validateDetails(String name, String accNumber, String password) {
//		System.out.println(name + " " + accNumber + " " + password);
//		System.out.println(this.name + " " + this.accNumber + " " + this.password);
		if ((this.name.equals(name)) && (this.accNumber.equals(accNumber)) && (this.password.equals(password)))
			return true;
		return false;
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

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

};
