package com.project.bank.client;

import java.util.Scanner;

import com.project.bank.service.Bank;
import com.project.bank.service.Response;

public class ATM {
	public void operations() {
		int number = 123466789;
		
		System.out.println("Let's set up a Bank");
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Name");
		String bankName = sc.nextLine();
		
		System.out.println("Enter IFSC code");
		String ifsc = sc.nextLine();
		
		System.out.println("Enter Address");
		String address = sc.nextLine();
		
		System.out.println("Bank setup done");
		System.out.println("________________________");
		System.out.println();
		
		Bank server = new Bank(bankName, ifsc, address);
		
		System.out.println("Let's add some accounts");
		
		System.out.println("How many accounts would you like to open with our Bank ?");
		int count = sc.nextInt();
		
		while((count) > 0) {
			
			System.out.println("Enter you Name");
			sc.next();
			String name = sc.nextLine();
			System.out.println("Generate password");
			String password = sc.nextLine();
			int accNumber = number++;
			System.out.println("Your account number is :" + accNumber);
			System.out.println("Let's quickly add some initial balance");
			System.out.println("Enter the amount you would like to add");
			double balance = sc.nextInt();
			System.out.println("Enter 1 for Savings account or 2 for Current account");
			int type = sc.nextInt();
			double interestRate = 10;
			if(type == 1) interestRate = 7;
			System.out.println("Setting up your account...");
			server.openAccount(accNumber, name, password, balance, interestRate);
			count--;
			System.out.println("Account created Successfully");
		}
		
		int choice = 0;
		while(choice != 5) {
			System.out.println("Enter any of the one choice");
			System.out
					.println("1. Balance Enquiry \n" + "2. Cash Withdrawal \n" + "3. Pin Change \n" + "4. Cash Deposit \n" + "5. Transfer");
			
			
			
			int option = sc.nextInt();
			choice = option;
			if(choice == 5) break;
			
			if(option == 5) System.exit(0);
			
			System.out.println("Enter your account details \n1. Account Number \n2. Password");

			sc.nextLine();
			int accNumber = sc.nextInt();
			String password = sc.nextLine();

			switch (option) {
			case 1:
				System.out.println("Your account balance is: " + server.fetchBalance(accNumber));
				break;

				case 2: 
					System.out.println("Enter the amount");
					int amount = sc.nextInt();
					Response result = server.withdraw(accNumber, password, amount);
					System.out.println(result.getMessage());

			case 3:
				System.out.println("Enter the new Pin");
				String pin = sc.nextLine();
				server.changePassword(accNumber, password, pin);
				System.out.println("Pin Changed Successfully");

				break;

			case 4:
				System.out.println("Enter the amount");
				int amount2 = sc.nextInt();
				boolean result2 = server.depositBalance(accNumber, amount2);
				if(result2)
					System.out.println("Amount added Successfully");
				else 
					System.out.println("Try Again");
			
			case 5:
				System.out.println("Enter account number and amount to be transferred");
				sc.next();
				int accnumber2 = sc.nextInt();
				int amount3 = sc.nextInt();
				server.transfer(accNumber, accnumber2, password, amount3);
				System.out.println("Amount transferred Successfully");
				
			}
				
			
			System.out.println("Enter 0 to end and any other number to continue");
			int choices = sc.nextInt();
			if(choices == 0) break;
		}
		
		System.out.println("--------------------------------");
		System.out.println("Thank you for Banking with us!");
		
		sc.close();
	}
}
