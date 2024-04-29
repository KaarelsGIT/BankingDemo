package banking_project.model;

import javax.persistence.*;
import java.math.BigDecimal;


@Entity
@Table(name = "account")
public class BankAccount {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "balance")
    private BigDecimal balance;




    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "userId=" + userId +
                ", accountType='" + accountType + '\'' +
                ", balance=" + balance +
                '}';
    }
}
