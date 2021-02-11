package presentation.views;

import commonDefenitions.TransactionKind;
import data.exceptions.UserIsNotLoggedInException;
import domain.interfaces.AddPeriodicalTransactionUseCase;
import domain.interfaces.AddTransactionUseCase;
import domain.interfaces.AlterTransactionUseCase;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import presentation.models.TransactionPresentationModel;
import presentation.models.TransactionSubmissionPresentationModel;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.function.Function;

public class Dialogues {

    /*
     * THIS IS A DUMPSTER FIRE! THERE ARE 3 HOURS LEFT BEFORE THE DEADLINE. I DO NOT CARE ANYMORE
     * REFACTOR AT ALL COSTS!
     * REFACTOR AT ALL COSTS!
     * REFACTOR AT ALL COSTS!
     * REFACTOR AT ALL COSTS!
     * REFACTOR AT ALL COSTS!
     * REFACTOR AT ALL COSTS!
     * REFACTOR AT ALL COSTS!
     * REFACTOR AT ALL COSTS!
     * REFACTOR AT ALL COSTS!
     * REFACTOR AT ALL COSTS!
     * REFACTOR AT ALL COSTS!
     * REFACTOR AT ALL COSTS!
     * */

    private final Function<Boolean, Boolean> refreshCallback;

    public Dialogues(Function<Boolean, Boolean> refreshCallback) {
        this.refreshCallback = refreshCallback;
    }

    public void showAddTransactionDialogue(AddTransactionUseCase addTransactionUseCase, AddPeriodicalTransactionUseCase addPeriodicalTransactionUseCase) {
        Dialog<TransactionSubmissionPresentationModel> dialog = new Dialog<>();
        dialog.setTitle("Add");
        dialog.setHeaderText("Add a transaction");

        Label lName = new Label("Title: ");
        Label lAmount = new Label("Amount: ");
        Label lSender = new Label("Sender: ");
        Label lReceiver = new Label("Receiver: ");
        Label lKind = new Label("Kind: ");
        Label lComment = new Label("Comment: ");
        Label lPeriodical = new Label("Is reoccurring: ");
        Label lDueDate = new Label("Due Date: ");
        TextField tfName = new TextField();
        TextField tfAmount = new TextField();
        TextField tfSender = new TextField();
        TextField tfReceiver = new TextField();
        TextField tfComment = new TextField();
        ComboBox<String> cbKind = new ComboBox<>();
        cbKind.setItems(FXCollections.observableArrayList("Spending", "Income"));
        CheckBox cIsPeriodical = new CheckBox();
        DatePicker dpDueDate = new DatePicker();

        lDueDate.setVisible(false);
        dpDueDate.setVisible(false);
        dpDueDate.setValue(LocalDate.now());

        cIsPeriodical.setOnAction(event -> {
            lDueDate.setVisible(!lDueDate.isVisible());
            dpDueDate.setVisible(!dpDueDate.isVisible());
        });

        GridPane grid = new GridPane();
        grid.add(lName, 1, 1);
        grid.add(lAmount, 1, 2);
        grid.add(lSender, 1, 3);
        grid.add(lReceiver, 1, 4);
        grid.add(lKind, 1, 5);
        grid.add(lComment, 1, 6);
        grid.add(lPeriodical, 1, 7);
        grid.add(lDueDate, 1, 8);
        grid.add(tfName, 2, 1);
        grid.add(tfAmount, 2, 2);
        grid.add(tfSender, 2, 3);
        grid.add(tfReceiver, 2, 4);
        grid.add(tfComment, 2, 6);
        grid.add(cbKind, 2, 5);
        grid.add(cIsPeriodical, 2, 7);
        grid.add(dpDueDate, 2, 8);

        dialog.getDialogPane().setContent(grid);

        ButtonType buttonTypeOk = new ButtonType("Okay", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);


        dialog.setResultConverter(b -> {
            if (b == buttonTypeOk) {
                try {

                    TransactionSubmissionPresentationModel transaction =
                            new TransactionSubmissionPresentationModel(
                                    tfName.getText(),
                                    cIsPeriodical.isSelected(),
                                    Float.parseFloat(tfAmount.getText()),
                                    tfSender.getText(),
                                    tfReceiver.getText(),
                                    cbKind.getValue().equals("Spending") ? TransactionKind.SPENDING : TransactionKind.INCOME,
                                    tfComment.getText(),
                                    dpDueDate.getValue()
                            );
                   if(transaction.isPeriodical()) addPeriodicalTransactionUseCase.addPeriodicalTransaction(transaction);
                   else addTransactionUseCase.addTransaction(transaction);
                    refreshCallback.apply(true);
                } catch (UserIsNotLoggedInException e) {
                    e.printStackTrace();
                }
            }
            return null;
        });
        dialog.show();
    }

    public void showAlterTransactionDialogue(AlterTransactionUseCase alterTransactionUseCase, TransactionPresentationModel selected, int position) {
        Dialog<TransactionSubmissionPresentationModel> dialog = new Dialog<>();
        dialog.setTitle("Alter");
        dialog.setHeaderText("Alter a transaction");

        Label lName = new Label("Title: ");
        Label lAmount = new Label("Amount: ");
        Label lSender = new Label("Sender: ");
        Label lReceiver = new Label("Receiver: ");
        Label lKind = new Label("Kind: ");
        Label lComment = new Label("Comment: ");
        Label lPeriodical = new Label("Is reoccurring: ");
        Label lDueDate = new Label("Due Date: ");
        TextField tfName = new TextField();
        tfName.setText(selected.getTransactionName());
        TextField tfAmount = new TextField();
        tfAmount.setText(String.format("%.2f", selected.getAmount()));
        TextField tfSender = new TextField();
        tfSender.setText(selected.getSenderName());
        TextField tfReceiver = new TextField();
        tfReceiver.setText(selected.getReceiverName());
        TextField tfComment = new TextField();
        tfComment.setText(selected.getComment());
        ComboBox<String> cbKind = new ComboBox<>();
        cbKind.setItems(FXCollections.observableArrayList("Spending", "Income"));
        cbKind.setValue(selected.getKind() == TransactionKind.SPENDING ? "Spending" : "Income");
        CheckBox cIsPeriodical = new CheckBox();
        cIsPeriodical.setSelected(selected.isPeriodical());
        DatePicker dpDueDate = new DatePicker();
        LocalDate dueDate = Instant.ofEpochMilli(Long.parseLong(selected.getDueDate())).atZone(ZoneId.systemDefault()).toLocalDate();

        lDueDate.setVisible(false);
        dpDueDate.setVisible(false);
        dpDueDate.setValue(dueDate);

        cIsPeriodical.setOnAction(event -> {
            lDueDate.setVisible(!lDueDate.isVisible());
            dpDueDate.setVisible(!dpDueDate.isVisible());
        });
        if(selected.isPeriodical()) {
            lDueDate.setVisible(!lDueDate.isVisible());
            dpDueDate.setVisible(!dpDueDate.isVisible());
        }

        GridPane grid = new GridPane();
        grid.add(lName, 1, 1);
        grid.add(lAmount, 1, 2);
        grid.add(lSender, 1, 3);
        grid.add(lReceiver, 1, 4);
        grid.add(lKind, 1, 5);
        grid.add(lComment, 1, 6);
        grid.add(lPeriodical, 1, 7);
        grid.add(lDueDate, 1, 8);
        grid.add(tfName, 2, 1);
        grid.add(tfAmount, 2, 2);
        grid.add(tfSender, 2, 3);
        grid.add(tfReceiver, 2, 4);
        grid.add(tfComment, 2, 6);
        grid.add(cbKind, 2, 5);
        grid.add(cIsPeriodical, 2, 7);
        grid.add(dpDueDate, 2, 8);

        dialog.getDialogPane().setContent(grid);

        ButtonType buttonTypeOk = new ButtonType("Okay", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);


        dialog.setResultConverter(b -> {
            if (b == buttonTypeOk) {
                try {
                    alterTransactionUseCase.alterTransaction(
                            new TransactionSubmissionPresentationModel(
                                    tfName.getText(),
                                    cIsPeriodical.isSelected(),
                                    Float.parseFloat(tfAmount.getText()),
                                    tfSender.getText(),
                                    tfReceiver.getText(),
                                    cbKind.getValue().equals("Spending") ? TransactionKind.SPENDING : TransactionKind.INCOME,
                                    tfComment.getText(),
                                    dpDueDate.getValue()
                            ), position
                    );
                    refreshCallback.apply(true);
                } catch (UserIsNotLoggedInException e) {
                    e.printStackTrace();
                }
            }
            return null;
        });
        dialog.show();
    }
}
