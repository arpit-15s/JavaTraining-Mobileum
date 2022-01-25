package in.conceptarchitect.banking;

import in.conceptarchitect.banking.ATM;
import in.conceptarchitect.banking.Bank;

public class App {
	public static void main(String []args) {
		Bank bank = new Bank("Axis", 10);
		ATM client = new ATM(bank);
		client.start();
	}
}
