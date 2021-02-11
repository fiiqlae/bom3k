package presentation.models;

import commonDefenitions.TransactionKind;

import java.time.LocalDate;

public class TransactionSubmissionPresentationModel {
    private final String name;
    private final boolean isPeriodical;
    private final float amount;
    private final String sender;
    private final String receiver;
    private final TransactionKind kind;
    private final String comment;
    private final LocalDate dueDate;

    public TransactionSubmissionPresentationModel(String name, boolean isPeriodical, float amount, String sender, String receiver, TransactionKind kind, String comment, LocalDate dueDate) {
        this.name = name;
        this.isPeriodical = isPeriodical;
        this.amount = amount;
        this.sender = sender;
        this.receiver = receiver;
        this.kind = kind;
        this.comment = comment;
        this.dueDate = dueDate;
    }


    public String getName() {
        return name;
    }

    public boolean isPeriodical() {
        return isPeriodical;
    }

    public float getAmount() {
        return amount;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public TransactionKind getKind() {
        return kind;
    }

    public String getComment() {
        return comment;
    }

    public LocalDate getDueDate() {
        return this.dueDate;
    }
}
