package com.project.banking;

import com.project.banking.ATM;
import com.project.banking.Bank;

public class App {
	public static void main(String []args) {
		Bank bank = new Bank("Axis", 10);
		ATM client = new ATM(bank);
		client.start();
	}
}

