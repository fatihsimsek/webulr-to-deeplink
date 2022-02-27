package com.trendyol.linkconverter.entity;

import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="transaction_history")
public class TransactionHistory {
    @Id
    @GeneratedValue
    @Type(type="uuid-char")
    private UUID id;
    private String input;
    private String output;
    private String type;
    private Date createTime;

    public TransactionHistory() {
    }

    public TransactionHistory(String input, String output, String type) {
        this.input = input;
        this.output = output;
        this.type = type;
        this.createTime = new Date();
    }

    public UUID getId() {
        return id;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
