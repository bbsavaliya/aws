package com.aws.sqs.awssqseventdriven.eventmessage;



public class ReferralCreateEvent {
    private long referralId;
    private long fromDocId;
    private long toDocId;
    private String FromDocFirstName;
    private String FromDocLastName;
    private String toDocFirstName;
    private String toDocLastName;

    public long getReferralId() {
        return referralId;
    }

    public long getFromDocId() {
        return fromDocId;
    }

    public long getToDocId() {
        return toDocId;
    }

    public String getFromDocFirstName() {
        return FromDocFirstName;
    }

    public String getFromDocLastName() {
        return FromDocLastName;
    }

    public String getToDocFirstName() {
        return toDocFirstName;
    }

    public String getToDocLastName() {
        return toDocLastName;
    }

    public void setReferralId(long referralId) {
        this.referralId = referralId;
    }

    public void setFromDocId(long fromDocId) {
        this.fromDocId = fromDocId;
    }

    public void setToDocId(long toDocId) {
        this.toDocId = toDocId;
    }

    public void setFromDocFirstName(String fromDocFirstName) {
        FromDocFirstName = fromDocFirstName;
    }

    public void setFromDocLastName(String fromDocLastName) {
        FromDocLastName = fromDocLastName;
    }

    public void setToDocFirstName(String toDocFirstName) {
        this.toDocFirstName = toDocFirstName;
    }

    public void setToDocLastName(String toDocLastName) {
        this.toDocLastName = toDocLastName;
    }
}
