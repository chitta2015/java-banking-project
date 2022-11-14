package org.banking.main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BankingOperations {
	
	public static boolean login(String userName, String password) throws ClassNotFoundException, SQLException
	{
		Connection connection= MysqlDBConnection.dbconnect();
		PreparedStatement statement=connection.prepareStatement("select * from account where username=?");
		statement.setString(1, userName);
		ResultSet result=statement.executeQuery();
		if(result.next())
		{
		
			if(result.getString("pass_word").equals(password))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
		
		
	}
	
	
	
	
	public static double balanceCheck(long accountId) throws ClassNotFoundException, SQLException
	{
		Connection connection= MysqlDBConnection.dbconnect();
		PreparedStatement statement=connection.prepareStatement("select * from account where accid=?");
		statement.setLong(1, accountId);
		
		ResultSet result=statement.executeQuery();
		result.next();
		double balance=result.getDouble("balance");
		return balance;
		
	}
	
	public static double withdraw(long accountId, double withdrawalAmount) throws ClassNotFoundException, SQLException
	{
		Connection connection= MysqlDBConnection.dbconnect();
		PreparedStatement statement=connection.prepareStatement("select * from account where accid=?");
		statement.setLong(1, accountId);
		
		ResultSet result=statement.executeQuery();
		result.next();
		double avilableBalance=result.getDouble("balance");
		
		if(withdrawalAmount<avilableBalance) //7000<8000
		{
		   double remainingBalance=avilableBalance-withdrawalAmount;
		   statement=connection.prepareStatement("update account set balance=? where accid=?");
		   statement.setDouble(1, new Double(remainingBalance));
		   statement.setLong(2, accountId);
		   
		   if(statement.executeUpdate()>0)
		   {
			   return remainingBalance;  
		   }
		   else
		   {
			   return 0;
		   }
		}
		else
		{
			   return 0;
		}
	}
	
	
	
	public static double deposit(long accountId, double depositAmount) throws ClassNotFoundException, SQLException
	{
		Connection connection= MysqlDBConnection.dbconnect();
		PreparedStatement statement=connection.prepareStatement("select * from account where accid=?");
		statement.setLong(1, accountId);
		
		ResultSet result=statement.executeQuery();
		result.next();
		double avilableBalance=result.getDouble("balance");
		double newBalance=avilableBalance+depositAmount;
		
		  statement=connection.prepareStatement("update account set balance=? where accid=?");
		   statement.setDouble(1, new Double(newBalance));
		   statement.setLong(2, accountId);
		   
		   if(statement.executeUpdate()>0)
		   {
			   return newBalance;  
		   }
		   else
		   {
			   return 0;
		   }
		
	}
	
	
	public static ResultSet checkAccountInfo(long accountId) throws ClassNotFoundException, SQLException
	{
		Connection connection= MysqlDBConnection.dbconnect();
		PreparedStatement statement=connection.prepareStatement("select * from account where accid=?");
		statement.setLong(1, accountId);
		
		ResultSet result=statement.executeQuery();
		if(result.next())
		{
			return result;
		}
		else
		{
			return null;
		}
	}
	


}
