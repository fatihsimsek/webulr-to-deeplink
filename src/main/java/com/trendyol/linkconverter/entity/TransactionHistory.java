package com.trendyol.linkconverter.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="transaction_history")
public class TransactionHistory {
    @Id
    private String id;
    private String input;
    private String output;
    private String type;
    private Date createTime;

    public TransactionHistory() {
    }

    public TransactionHistory(String input, String output, String type) {
        this.id = UUID.randomUUID().toString();
        this.input = input;
        this.output = output;
        this.type = type;
        this.createTime = new Date();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
