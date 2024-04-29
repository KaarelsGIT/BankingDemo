package banking_project.service;

import banking_project.model.BankAccount;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

public class AccountManager {
    private final EntityManagerFactory emf;

    public AccountManager() {
        emf = Persistence.createEntityManagerFactory("persistence-unit");
    }

    public void createNewBankAccount(Long userId, String accountType, BigDecimal balance) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        BankAccount account = new BankAccount();
        account.setUserId(userId);
        account.setAccountType(accountType);
        account.setBalance(balance);

        em.persist(account);

        em.getTransaction().commit();
        em.close();
    }

    public BankAccount getAccountById(Long id) {
        EntityManager em = emf.createEntityManager();
        BankAccount account = em.find(BankAccount.class, id);
        em.close();
        return account;
    }

    public BankAccount getAccountByUserID(Long userID) {
        EntityManager em = emf.createEntityManager();
        BankAccount account = em.find(BankAccount.class, userID);
        em.close();
        return account;
    }

    public void updateAccount(BankAccount account) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        em.merge(account);

        em.getTransaction().commit();
        em.close();
    }

//    public void updateAccountBalance(BankAccount account, BigDecimal balance) {
//        EntityManager em = emf.createEntityManager();
//        em.getTransaction().begin();
//
//        em.merge(account);
//
//        em.getTransaction().commit();
//        em.close();
//    }

    public void deleteAccount(BankAccount account) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        account = em.merge(account);
        em.remove(account);

        em.getTransaction().commit();
        em.close();
    }

    public List<BankAccount> getAllAccounts() {
        EntityManager em = emf.createEntityManager();
        List<BankAccount> accounts = em.createQuery("SELECT a FROM BankAccount a", BankAccount.class).getResultList();
        em.close();
        return accounts;
    }

    public void close() {
        emf.close();
    }
}
