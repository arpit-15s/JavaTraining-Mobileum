package com.project.bank.client;

import java.util.Scanner;

import com.project.bank.service.Bank;
import com.project.bank.service.Response;

public class ATM {
	public void operations() {
		System.out.println("Enter any of the one choice");
		System.out
				.println("1. Balance Enquiry \n" + "2. Cash Withdrawal \n" + "3. Pin Change \n" + "4. Cash Deposit \n" + "5. Clear Transaction");
		
		Scanner sc = new Scanner(System.in);
		
		int option = sc.nextInt();
		
		if(option == 5) System.exit(0);
		
		System.out.println("Enter your account details \n1. Name \n2. Account Number \n3. Password");

		sc.nextLine();

		String name = sc.nextLine();
		int accNumber = sc.nextInt();
		String password = sc.nextLine();

		Bank server = new Bank("Axis", "123456ax", "Indira nagar");

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
		}
		
		sc.close();
	}
}
