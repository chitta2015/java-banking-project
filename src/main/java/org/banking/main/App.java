package org.banking.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.banking.main.dao.BankingOperations;
import org.banking.main.exception.InvalidAmountException;

public class App 
{
	static BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(System.in)); 
	
	public static void main(String args[]) throws NumberFormatException, IOException, ClassNotFoundException, SQLException, InvalidAmountException
	{
		
		System.out.println("====================================================================================");
		System.out.println("=========================== WELCOME TO BANKING APPLICATION =========================");
		System.out.println("====================================================================================");
		
		boolean status=false;
		do
		{
		
			System.out.println("====================================================================================");
			System.out.print("\t\t  Username : ");
			String userName=bufferedReader.readLine();
			System.out.println();
			System.out.print("\t\t  Password : ");
			String password=bufferedReader.readLine();
			System.out.println("====================================================================================");
			status=BankingOperations.login(userName, password);
		
		
				if(status)
				{
				do
				{
					System.out.println("====================================================================================");
					System.out.println("=========================== BANK OPERATIONS =========================");
					System.out.println("====================================================================================");
					System.out.println("\t\t  Enter 1-> Balance Check");
					System.out.println("\t\t  Enter 2-> Withdraw");
					System.out.println("\t\t  Enter 3-> Deposit");
					System.out.println("\t\t  Enter 4-> Check Account Info.");
					System.out.println("\t\t  Enter 5-> Logout");
					System.out.println("====================================================================================");
					System.out.println("\t\t  Enter a valid input between 1 to 4:");
					int choice=Integer.parseInt(bufferedReader.readLine());
					
					switch(choice)
					{
					case 1: System.out.println("Enter valid account id:");
							long accountId=Integer.parseInt(bufferedReader.readLine());
							System.out.println( "Current available balance is :"+BankingOperations.balanceCheck(accountId));
							break;
							
					case 2: System.out.println("Enter valid account id:");
							accountId=Integer.parseInt(bufferedReader.readLine());
							System.out.println("Enter withdraw amount:");
							double withdrawalAmount=Double.parseDouble(bufferedReader.readLine());
							
							try
							{
								double result=BankingOperations.withdraw(accountId, withdrawalAmount);						
								System.out.println("Transaction successfull!!");
								System.out.println("Remaining balance is:"+result);
							}
							catch(InvalidAmountException e)
							{
								System.out.println("Invalid Withdrawal amount!!");
							}
							break;
							
					case 3: System.out.println("Enter valid account id:");
							accountId=Integer.parseInt(bufferedReader.readLine());
							System.out.println("Enter deposit amount:");
							double depositAmount=Double.parseDouble(bufferedReader.readLine());
							double result=BankingOperations.deposit(accountId, depositAmount);
							
							if(result==0)
							{
								
								System.out.println("Transaction is unsuccessfull!!");
							}
							else
							{
								System.out.println("Transaction successfull!!");
								System.out.println("New balance is:"+result);
							}
							break;	
					case 4: System.out.println("Enter valid account id:");
							accountId=Integer.parseInt(bufferedReader.readLine());
							System.out.println("====================================================================================");
							System.out.println("=================== ACCOUNT DETAIL ================================");
 
							System.out.println("====================================================================================");

							ResultSet accountInfo=BankingOperations.checkAccountInfo(accountId); 
			        		System.out.println("\t\t  Account Id :"+accountInfo.getLong("accid"));
			        		System.out.println("\t\t  Name :"+accountInfo.getString("accname"));
			        		System.out.println("\t\t  Gender :"+accountInfo.getString("gender"));
			        		System.out.println("\t\t  Adress :"+accountInfo.getString("address"));
			        		System.out.println("\t\t  Phone :"+accountInfo.getLong("phone"));
			        		System.out.println("\t\t  Email :"+accountInfo.getString("email"));
			        		System.out.println("\t\t  Available Balance :"+accountInfo.getString("balance"));
							System.out.println("====================================================================================");
							break;	
					
					case 5: status=false;
					        System.out.println("====================================================================================");
							System.out.println("You have successfully logged out!!");
							System.out.println("Bye");
							System.out.println("====================================================================================");
							break;
							
					default: System.out.println("====================================================================================");
							 System.out.println("Wrong Choice!!");		
							 System.out.println("====================================================================================");

					
					}
					
					
				}
				while(status);
				}
				
				else
				{
					System.out.println("====================================================================================");
					System.out.println("Incorrect username or password!!");
					System.out.println("====================================================================================");
				}
		}
		while(status);
	}
		
}
