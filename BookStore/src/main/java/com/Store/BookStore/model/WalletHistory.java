package com.Store.BookStore.model;

import javax.persistence.*;

@Entity
@Table(name = "wallet_transaction")

public class WalletHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int transaction_id;

    @Column(name="user_id")
    private int userId;

    @Column(name="balance_debited")
    private int balanceDebited;

    @Column(name="balance_credited")
    private int balanceCredited;

    public int getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(int transaction_id) {
        this.transaction_id = transaction_id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBalanceDebited() {
        return balanceDebited;
    }

    public void setBalanceDebited(int balanceDebited) {
        this.balanceDebited = balanceDebited;
    }

    public int getBalanceCredited() {
        return balanceCredited;
    }

    public void setBalanceCredited(int balanceCredited) {
        this.balanceCredited = balanceCredited;
    }
}
