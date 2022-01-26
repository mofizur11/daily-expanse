package com.ideasoft.dailyexpense;

public class ExpanseModal {

    // variables for our expanseType,
    // expanseAmount, expanseDate and expanseTime, id.
    private String expanseType;
    private String expanseAmount;
    private String expanseDate;
    private String expanseTime;
    private int id;

    // creating getter and setter methods

    public String getExpanseType() {
        return expanseType;
    }

    public void setExpanseType(String expanseType) {
        this.expanseType = expanseType;
    }

    public String getExpanseAmount() {
        return expanseAmount;
    }

    public void setExpanseAmount(String expanseAmount) {
        this.expanseAmount = expanseAmount;
    }

    public String getExpanseDate() {
        return expanseDate;
    }

    public void setExpanseDate(String expanseDate) {
        this.expanseDate = expanseDate;
    }

    public String getExpanseTime() {
        return expanseTime;
    }

    public void setExpanseTime(String expanseTime) {
        this.expanseTime = expanseTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // constructor
    public ExpanseModal(String expanseType, String expanseAmount, String expanseDate, String expanseTime) {
        this.expanseType = expanseType;
        this.expanseAmount = expanseAmount;
        this.expanseDate = expanseDate;
        this.expanseTime = expanseTime;
    }
}
