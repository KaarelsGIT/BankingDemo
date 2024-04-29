package banking_project.service;

import banking_project.model.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionManager {
    private final EntityManagerFactory emf;

    public TransactionManager() {
        emf = Persistence.createEntityManagerFactory("persistence-unit");
    }

    public void newTransaction(Long senderId, Long receiverId, BigDecimal amount, String description) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Transaction transaction = new Transaction();
        transaction.setSenderId(senderId);
        transaction.setReceiverId(receiverId);
        transaction.setAmount(amount);
        transaction.setDescription(description);
        transaction.setDateTime(LocalDateTime.now());

        em.persist(transaction);

        em.getTransaction().commit();
        em.close();
    }

    public Transaction getTransactionById(long id) {
        EntityManager em = emf.createEntityManager();
        Transaction transaction = em.find(Transaction.class, id);
        em.close();
        return transaction;
    }

    public void updateAccount(Transaction transaction) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        em.merge(transaction);

        em.getTransaction().commit();
        em.close();
    }

    public void close() {
        emf.close();
    }
}
