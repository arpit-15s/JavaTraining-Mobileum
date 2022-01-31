package com.project.banking;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.project.banking.exceptions.InvalidAccountException;

public class FileAccountStore {


	int accountCount=0;	
	int lastAccountNumber=0;
	
	File accounts;
	
	private void createFile() {
		accounts = new File("C:/Users/arpit.srivastav/BankAccount.txt");
		boolean result;
		try{
			result = accounts.createNewFile();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public FileAccountStore(){
		createFile();
	}
		
	
//	BankAccount [] accounts=new BankAccount[100];
	

	public int addAccount(BankAccount account) {
		int accountNumber= ++lastAccountNumber;
		accountCount++;
		try {
			account.setAccountNumber(accountNumber);
//			System.out.println(account);
//			System.out.println(account.toString());
			FileWriter accounts = new FileWriter("C:/Users/arpit.srivastav/BankAccount.txt", true);
			accounts.write(account.toString() + "\n");
			accounts.close();
			return accountNumber;
		}
		catch(IOException e){
			e.printStackTrace();
		}
		accountNumber = --lastAccountNumber;
		accountCount--;
		return accountNumber;
	}
	
	public BankAccount getAccount(int accountNumber) {
//		System.out.println(accountNumber + " " + lastAccountNumber);
		if((accountNumber < 1) || (accountNumber > lastAccountNumber))
			throw new InvalidAccountException(accountNumber);
		
		var accounts = new File("C:/Users/arpit.srivastav/BankAccount.txt");
		boolean result;
		try{
			Scanner sc = new Scanner(accounts);
			String line = "";
			int i = 1;
			while(sc.hasNextLine()) {
				line = sc.nextLine();
				System.out.println(line + "kb");
				if(i == accountNumber) {
					String[] accountString = line.split(" ");
					if(accountString[0] == null)
						throw new InvalidAccountException(accountNumber);
					
					int accNumber = Integer.parseInt(accountString[1]);
					String name = accountString[2];
					String password = null;
					double amount = Double.parseDouble(accountString[3]);
					System.out.println(amount);
					
					if(accountString[0].equals("SavingsAccount")) {
						return new SavingsAccount(accNumber, name, password, amount);
					}
					else if(accountString[0].equals("CurrentAccount")) {
						return new CurrentAccount(accNumber, name, password, amount);
					}
					else {
						return new OverdraftAccount(accNumber, name, password, amount);
					}
				}
				i++;
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		//it either returns a valid value or throws an exception
		return new SavingsAccount(0, "Invalid", "", 0);
	}

	public void removeAccount(int accountNumber) {
		if(accountNumber <= 0)
			throw new InvalidAccountException(accountNumber);
		
		var accounts = new File("C:/Users/arpit.srivastav/BankAccount.txt");
		try {
			Scanner sc = new Scanner(accounts);
			ArrayList<String> line = new ArrayList<String>();
			int i = 0;
			while(sc.hasNextLine()) {
				line.add(i, sc.nextLine());
				i++;
			}
			line.set(i, null);
			i =0;
			FileWriter accounts2 = new FileWriter("C:/Users/arpit.srivastav/BankAccount.txt", true);
			while(i < line.size()) {
				accounts2.write(line.get(i));
				i++;
			}
			System.out.println(accountCount);
			accountCount--;
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public BankAccount[] getAllActiveAccounts() {
		BankAccount [] lines = new BankAccount[100];
		var accounts = new File("C:/Users/arpit.srivastav/BankAccount.txt");
		boolean result;
		try {
			Scanner sc = new Scanner(accounts);
			int i = 0;
			while(sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] accountString = line.split(" ");
				int accNumber = Integer.parseInt(accountString[1]);
				String name = accountString[2];
				String password = null;
				double amount = Double.parseDouble(accountString[3]);
				
				if(accountString[0].equals("SavingsAccount")) {
					lines[i] = new SavingsAccount(accNumber, name, password, amount);
				}
				else if(accountString[0].equals("CurrentAccount")) {
					lines[i] = new CurrentAccount(accNumber, name, password, amount);
				}
				else {
					lines[i] = new OverdraftAccount(accNumber, name, password, amount);
				}
				i++;
			}
			
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return lines;
	}

	public int getAccountCount() {
		// TODO Auto-generated method stub
		return accountCount;
	}
	
	

}
