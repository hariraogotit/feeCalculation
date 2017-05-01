package com.utilities;

/**
 * Created by Hari Rao on 25/04/17.
 */
public enum PriorityFlag {

    Y("Y"),
    N("N");

    private String priorityFlag;
    PriorityFlag(String priorityFlag){
        this.priorityFlag = priorityFlag;
    }

    public String getPriorityFlag() {
        return priorityFlag;
    }
}
