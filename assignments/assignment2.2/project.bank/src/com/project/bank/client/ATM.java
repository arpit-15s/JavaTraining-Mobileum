package com.project.bank.client;

import java.util.Scanner;

import com.project.bank.service.Server;

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
		String accNumber = sc.nextLine();
		String password = sc.nextLine();

		Server server = new Server();

		if (!(server.validateAccount(name, accNumber, password))) {
			System.out.println("Information Entered is Incorrect, Try Again!!!");
		} else {
			switch (option) {
			case 1:
				System.out.println("Your account balance is: " + server.fetchBalance());
				break;

				case 2: 
					System.out.println("Enter the amount");
					int amount = sc.nextInt();
					int result = server.withdraw(amount);
					if (result == -1)
						System.out.println("Enter the valid Password");
					else if (result == -2 || result == -3)
						System.out.println("Enter the valid Amount");
					else
						System.out.println("Your remaining balance is: " + result);
	
					break;

			case 3:
				System.out.println("Enter the new Pin");
				String pin = sc.nextLine();
				server.changePin(pin);
				System.out.println("Pin Changed Successfully");

				break;

			case 4:
				System.out.println("Enter the amount");
				int amount2 = sc.nextInt();
				int result2 = server.depositBalance(amount2);
				if (result2 == -1)
					System.out.println("Enter the valid Amount");
				else
					System.out.println("Your balance is: " + result2);
				

			}
		}
		sc.close();
	}
}
