package com.project.bank.service;

import com.project.bank.service.Response;
import com.project.bank.service.ResponseStatus;

import java.util.ArrayList;
import com.project.bank.service.BankAccount;

public class Bank {
	private ArrayList<BankAccount> bankAccounts = new ArrayList<BankAccount>();
	String name, ifsc, address;
	
	public Bank(String name, String ifsc, String address){
		this.name = name;
		this.ifsc = ifsc;
		this.address = address;
	}
	
	public boolean openAccount(int accNumber, String name, String password, double amount,  double interestRate) {
		for (int i = 0; i < bankAccounts.size(); i++) {
			if(bankAccounts.get(i).validateDetails(accNumber)) {
				return false;
			}
		}
		this.bankAccounts.add(new BankAccount(accNumber, name, password, amount, interestRate));
		return true;
	}
	
	public boolean closeAccount(int accNumber) {
		for (int i = 0; i < bankAccounts.size(); i++) {
			if(bankAccounts.get(i).validateDetails(accNumber)) {
				bankAccounts.remove(i);
				return true;
			}
		}
		return false;
	}
	
	public double fetchBalance(int accNumber) {
		for (int i = 0; i < bankAccounts.size(); i++) {
			if(bankAccounts.get(i).validateDetails(accNumber)) {
				return bankAccounts.get(i).getBalance();

			}
		}
		return -1;
	}
	
	public boolean changePassword(int accNumber, String oldPass, String newPass) {
		for (int i = 0; i < bankAccounts.size(); i++) {
			if(bankAccounts.get(i).validateDetails(accNumber)) {
				return bankAccounts.get(i).changePassword(oldPass, newPass);
			}
		}
		return false;
	}
	
	public boolean creditInterest(int accNumber) {
		for (int i = 0; i < bankAccounts.size(); i++) {
			if(bankAccounts.get(i).validateDetails(accNumber)) {
				bankAccounts.get(i).creditInterest();
				return true;
			}
		}
		return false;
	}
	
	public boolean depositBalance(int accNumber, int amount) {
		for (int i = 0; i < bankAccounts.size(); i++) {
			if(bankAccounts.get(i).validateDetails(accNumber)) {
				return bankAccounts.get(i).deposit(amount);
			}
		}
		return false;
	}
	
	public Response withdraw(int accNumber, String password, int amount) {
		for (int i = 0; i < bankAccounts.size(); i++) {
			if(bankAccounts.get(i).validateDetails(accNumber)) {
				return bankAccounts.get(i).withdraw(amount, password);
			}
		}
		return new Response(ResponseStatus.INVALID_CREDENTIALS,"Invalid Credentials");
	}

	public boolean transfer(int accNumber1, int accNumber2, String password, int amount) {
		int from = -1, to = -1;
		for (int i = 0; i < bankAccounts.size(); i++) {
			if(bankAccounts.get(i).validateDetails(accNumber1)) {
				from = i;
			}
			if(bankAccounts.get(i).validateDetails(accNumber2)) {
				to = i;
			}
		}
		if((to != -1) && (from != -1)) {
			Response response = bankAccounts.get(from).withdraw(amount, password);
			boolean deposited = bankAccounts.get(to).deposit(amount);
			if(deposited && response.getCode() == ResponseStatus.SUCCESS)
				return true;
		}
		return false;
	}
	
}
