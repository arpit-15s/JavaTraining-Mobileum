package in.conceptarchitect.banking;

import in.conceptarchitect.utils.Input;

public class ATM {

	Bank bank; //associated parent bank.
	int accountNumber;
	Input keyboard=new Input();
	private String password;
	

	public ATM(Bank bank) {
		super();
		this.bank = bank; 
	}
	
	public void start() {
		accountNumber=keyboard.readInt("account number?");
		password=keyboard.readString("password?");
		//A secret menu
		if(accountNumber==-1 && password=="NIMDA")
			adminMenu();
		else
			mainMenu();
	}
	
	
	
	private void adminMenu() {
		// TODO Auto-generated method stub
		while(true) {
			var choice=keyboard.readInt("1. open account 2. credit interest 3. view all accounts 0. exit ?");
			switch(choice) {
				case 0:
					return ;
					
				case 1:
					openAccount();
					
				case 2:
					creditInterest();
					
				case 3:
					viewAccounts();
					
				default:
					showError("invalid choice. retry");
			}
		}
	}

	private void mainMenu() {
		
		while(true) {
			var choice=keyboard.readInt("1. deposit 2. withdraw 3. check balance 4. transfer 5. close account 0. exit ?");
			switch(choice) {
			case 1:
				doDeposit();
				break;
			case 2:
				doWithdraw();
				break;
				
			case 3:
				doCheckBlance();
				break;
				
			case 4:
				doTransfer();
				break;
				
			case 5:
				doCloseAccount();
				break;
				
			case 0:
				return ;
				
			default:
				showError("invalid choice. retry");
			
			}
		}
	}

	private void showError(String string) {
		// TODO Auto-generated method stub
		System.err.println(string);
	}

	private void doCloseAccount() {
		// TODO Auto-generated method stub
		var amount = bank.closeAccount(accountNumber, password);
		if(amount == -1) {
			System.out.println("Invalid Credentials");
		}
		else {
			System.out.println("Account Closed Successfully");
			if(amount > 0) {
				System.out.println("Please collect your cash : "+amount);
			}
		}
	}

	private void doTransfer() {
		// TODO Auto-generated method stub
		int amount=keyboard.readInt("amount?");
		int targetAccount=keyboard.readInt("target account?");
		Response response= bank.transfer(accountNumber, amount, password, targetAccount);
		if(response.getCode()==ResponseStatus.SUCCESS) {
			showInfo("Amount Transferred Successfully");
		} else {
			showError(response.getMessage());
		}
		
	}

	private void doCheckBlance() {
		// TODO Auto-generated method stub
		var account=bank.getAccount(accountNumber,password);
		if(account==null){
			showError("Invalid Credentials");
			return;
		}
		double balance=account.getBalance();
		showInfo("Your Balance:"+balance);
		
	}

	private void doWithdraw() {
		// TODO Auto-generated method stub
		int amount=keyboard.readInt("Amount to withdraw?");
		Response result= bank.withdraw(accountNumber, amount, password);
		if(result.getCode()==ResponseStatus.SUCCESS)
			dispenseCash(amount);
		else
			showError(result.getMessage());
		
	}

	private void dispenseCash(int amount) {
		// TODO Auto-generated method stub
		System.out.println("Please collect your cash : "+amount);
	}

	private void doDeposit() {
		// TODO Auto-generated method stub
		int amount=keyboard.readInt("Deposit Amount?"); //ATM allows only whole sum (actully multiple of 100)
		if(amount%100!=0) {
			showError("Invalid Denomination");
			return ;
		}
		
		if(bank.deposit(accountNumber, amount))
			showInfo("Amount deposited");
		else
			showInfo("Deposit failed");
		
		
	}

	private void showInfo(String string) {
		// TODO Auto-generated method stub
		System.out.println(string);
	}
	
	private void openAccount(){
		String name = keyboard.readString("Enter you Name");
		String password = keyboard.readString("Generate password");
		
		System.out.println("Let's quickly add some initial balance");
		double balance = keyboard.readInt("Enter the amount you would like to add");
		int type = keyboard.readInt("Enter 1 for Savings account or 2 for Current account or 3 for Over draft account");
		
		System.out.println("Setting up your account...");
		bank.openAccount(name, password, balance);
		System.out.println("Account created Successfully");
	}
	
	private void creditInterest() {
		bank.creditInterest();
		System.out.println("Credits Added Successfully!");
	}
	
	private void viewAccounts() {
		BankAccount result[] = bank.viewAccount();
		if(result.length == 0) {
			System.out.println("No Account Found");
		}
		else {
			System.out.println("List of accounts are as follows");
			for(int i = 0; i < result.length; i++) {
				if(result[i] != null) {
					System.out.println(result[i]);
				}
			}
		}
	}
}
