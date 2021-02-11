package data.models;

import commonDefenitions.TransactionKind;

public class TransactionDataModel {
    private final long              userId;
    private final long              transactionId;
    private String                  transactionName;
    private TransactionKind         kind;
    private boolean                 isPeriodical;
    private final String            timestamp;
    private String                  dueDate;
    private String                  category;
    private String                  comment;
    private String                  senderName;
    private String                  receiverName;
    private float                   amount;

    public TransactionDataModel(long userId, long transactionId, String transactionName, TransactionKind kind, boolean isPeriodical, String timestamp, String dueDate, String category, String comment, String senderName, String receiverName, float amount) {
        this.userId = userId;
        this.transactionId = transactionId;
        this.transactionName = transactionName;
        this.kind = kind;
        this.isPeriodical = isPeriodical;
        this.timestamp = timestamp;
        this.dueDate = dueDate;
        this.category = category;
        this.comment = comment;
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.amount = amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }

    public void setKind(TransactionKind kind) {
        this.kind = kind;
    }

    public void setPeriodical(boolean periodical) {
        isPeriodical = periodical;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public long getUserId() {
        return userId;
    }

    public String getTransactionName() {
        return transactionName;
    }

    public TransactionKind getKind() {
        return kind;
    }

    public boolean isPeriodical() {
        return isPeriodical;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getCategory() {
        return category;
    }

    public String getComment() {
        return comment;
    }

    public float getAmount() {
        return amount;
    }

    public String getSenderName() {
        return senderName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public long getTransactionId() {
        return transactionId;
    }
}
