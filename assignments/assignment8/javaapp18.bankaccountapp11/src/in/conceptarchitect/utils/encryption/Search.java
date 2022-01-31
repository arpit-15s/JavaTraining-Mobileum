package in.conceptarchitect.utils.encryption;

import java.util.ArrayList;

import in.conceptarchitect.banking.BankAccount;
import in.conceptarchitect.banking.CurrentAccount;

public class Search {
	
	ArrayList<BankAccount> selectedAccounts;
	
	public ArrayList<BankAccount> searchCurrentAccount(ArrayList<BankAccount> accounts) {
		
		selectedAccounts = new ArrayList<BankAccount>();
		for(BankAccount account: accounts) {
			if(account instanceof CurrentAccount) {
				selectedAccounts.add(account);
			}
		}
		return selectedAccounts;
	}
	
	public ArrayList<BankAccount> searchNegativeBalanceAccount(ArrayList<BankAccount> accounts) {
			
		selectedAccounts = new ArrayList<BankAccount>();
		for(BankAccount account: accounts) {
			if(account.getBalance() < 0) {
				selectedAccounts.add(account);
			}
		}
			
		return selectedAccounts;
	}
	
	
	public ArrayList<BankAccount> searchLastNameMishra(ArrayList<BankAccount> accounts) {
		
		selectedAccounts = new ArrayList<BankAccount>();
		for(BankAccount account: accounts) {
			String[] name = (account.getName()).split(" ");
			String lastName = name[name.length - 1];
			if(lastName.equals("Mishra") ) {
				selectedAccounts.add(account);
			}
		}	
		return selectedAccounts;
	}
	
}
