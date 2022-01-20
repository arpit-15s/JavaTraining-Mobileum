package app;

import java.util.Scanner;

import com.bank.abc.BankAccount;

public class App {
	public static void main(String []args) {
		
		Scanner sc = new Scanner(System.in);
		String name = sc.nextLine();
		String accNumber = sc.nextLine();
		String password = sc.nextLine();
		int interestRate = sc.nextInt();
		int balance = sc.nextInt();
		sc.close();
		
		BankAccount b = new BankAccount();
		
		b.setName(name);
		b.setAccNumber(accNumber);
		b.setPassword(password);
		b.setInterestRate(interestRate);
		b.setBalance(balance);
		System.out.println(b.getName() + " " + b.getAccNumber() + " " + b.getPassword() + " " + b.getInterestRate() + " " + b.getBalance());
	}
}
