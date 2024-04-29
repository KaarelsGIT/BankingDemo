package banking_project;


import banking_project.model.BankAccount;
import banking_project.service.AccountManager;
import banking_project.service.TransactionManager;
import banking_project.service.UserManager;

import java.math.BigDecimal;


public class Main {

    public static void main(String[] args) {

        UserManager userManager = new UserManager();
        AccountManager accountManager = new AccountManager();
        TransactionManager transaction = new TransactionManager();

        try {
            userManager.createNewUser("John Smith", "johhn.smith@email.com", "+372 5678 9876");
            userManager.createNewUser("Anna Anderson", "anna.anderson@email.com", "+372 5675 2354");

            accountManager.createNewBankAccount(1L, "savings", new BigDecimal("2000.00"));
            accountManager.createNewBankAccount(2L, "interest", new BigDecimal("1300.00"));

            transaction.newTransaction(2L, 1L, new BigDecimal("200.00"), "Happy birthday John!");

            BankAccount senderAccount = accountManager.getAccountById(2L);
            BigDecimal senderAccountBalance = senderAccount.getBalance();

            if (senderAccountBalance.compareTo(transaction.getTransactionById(1L).getAmount()) < 0) {
                System.out.println("You do not have enough money in your account to complete this transaction!");
            } else {
                senderAccount.setBalance(senderAccountBalance.subtract(transaction.getTransactionById(1L).getAmount()));
                accountManager.updateAccount(senderAccount);

                BankAccount receiverAccount = accountManager.getAccountByUserID(1L);
                receiverAccount.setBalance(receiverAccount.getBalance().add(transaction.getTransactionById(1L).getAmount()));
                accountManager.updateAccount(receiverAccount);
            }
        } finally {
            userManager.close();
            transaction.close();
            accountManager.close();
        }
    }
}
