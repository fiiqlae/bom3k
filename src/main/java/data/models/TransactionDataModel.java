package data.models;

import commonDefenitions.TransactionKind;

public class TransactionDataModel {
    private final long userId;
    private final String transactionName;
    private final TransactionKind kind;
    private final boolean isPeriodical;
    private final String timestamp;
    private final String dueDate;
    private final String category;
    private final String comment;
    private final String senderName;
    private final String receiverName;

    public TransactionDataModel(long userId, String transactionName, TransactionKind kind, boolean isPeriodical, String timestamp, String dueDate, String category, String comment, String senderName, String receiverName) {
        this.userId = userId;
        this.transactionName = transactionName;
        this.kind = kind;
        this.isPeriodical = isPeriodical;
        this.timestamp = timestamp;
        this.dueDate = dueDate;
        this.category = category;
        this.comment = comment;
        this.senderName = senderName;
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

    public String getSenderName() {
        return senderName;
    }

    public String getReceiverName() {
        return receiverName;
    }
}
