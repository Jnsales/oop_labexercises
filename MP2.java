package com.mycompany.mp2;

import java.util.Scanner;
class InsufficientFundsException extends Exception {
   public InsufficientFundsException(String message) {
       super(message);
   }
}
class Account {
   private int accountNumber;
   private double balance;
   public Account(int accountNumber, double balance) {
       this.accountNumber = accountNumber;
       this.balance = balance;
   }
   public int getAccountNumber() {
       return accountNumber;
   }
   public double getBalance() {
       return balance;
   }
   public void deposit(double amount) {
       if (!Double.isFinite(amount) || amount <= 0) {
           throw new IllegalArgumentException(
               "Amount must be positive."
           );
       }
       if (!Double.isFinite(balance + amount)) {
           throw new IllegalArgumentException(
               "Amount is too large."
           );
       }
       balance += amount;
   }
   public void withdraw(double amount)
           throws InsufficientFundsException {
       if (!Double.isFinite(amount) || amount <= 0) {
           throw new IllegalArgumentException(
               "Amount must be positive."
           );
       }
       if (amount > balance) {
           throw new InsufficientFundsException(
               "Insufficient funds."
           );
       }
       balance -= amount;
   }
}
public class MP2 {
   static Scanner scanner = new Scanner(System.in);
   static Account[] accounts = {
       new Account(101, 1000.00),
       new Account(102, 500.00)
   };
   public static Account findAccount(int number) {
       for (Account account : accounts) {
           if (account.getAccountNumber() == number) {
               return account;
           }
       }
       return null;
   }
   public static int readNumber(String message) {
       System.out.print(message);
       return Integer.parseInt(scanner.nextLine().trim());
   }
   public static double readAmount(String message) {
       System.out.print(message);
       double amount = Double.parseDouble(
           scanner.nextLine().trim()
       );
       if (!Double.isFinite(amount)) {
           throw new NumberFormatException(
               "Invalid amount."
           );
       }
       return amount;
   }
   public static void main(String[] args) {
       boolean running = true;
       while (running) {
           System.out.println("\n===== ATM BANKING SYSTEM =====");
           System.out.println("1. Balance Inquiry");
           System.out.println("2. Deposit");
           System.out.println("3. Withdraw");
           System.out.println("0. Exit");
           System.out.print("Enter your choice: ");
           try {
               int choice = Integer.parseInt(
                   scanner.nextLine().trim()
               );
               if (choice == 0) {
                   System.out.println("Thank you for using ATM!");
                   running = false;
                   continue;
               }
               if (choice < 1 || choice > 3) {
                   System.out.println("Error: Invalid choice.");
                   continue;
               }
               int number = readNumber(
                   "Enter account number: "
               );
               Account account = findAccount(number);
               if (account == null) {
                   System.out.println(
                       "Error: Account not found."
                   );
                   continue;
               }
               switch (choice) {
                   case 1:
                       System.out.printf(
                           "Current Balance: %.2f%n",
                           account.getBalance()
                       );
                       break;
                   case 2:
                       double depositAmount = readAmount(
                           "Enter deposit amount: "
                       );
                       account.deposit(depositAmount);
                       System.out.println("Deposit successful!");
                       System.out.printf(
                           "New Balance: %.2f%n",
                           account.getBalance()
                       );
                       break;
                   case 3:
                       double withdrawAmount = readAmount(
                           "Enter withdrawal amount: "
                       );
                       account.withdraw(withdrawAmount);
                       System.out.println("Withdrawal successful!");
                       System.out.printf(
                           "New Balance: %.2f%n",
                           account.getBalance()
                       );
                       break;
               }
           } catch (NumberFormatException e) {
               System.out.println(
                   "Error: Enter a valid number."
               );
           } catch (InsufficientFundsException e) {
               System.out.println(
                   "Error: " + e.getMessage()
               );
           } catch (IllegalArgumentException e) {
               System.out.println(
                   "Error: " + e.getMessage()
               );
           }
       }
       scanner.close();
   }
}