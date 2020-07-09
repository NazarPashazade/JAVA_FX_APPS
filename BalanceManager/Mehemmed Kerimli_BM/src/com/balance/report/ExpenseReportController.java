package com.balance.report;

import com.balance.DAO.DAO;
import com.balance.DAO.DAOImpl;
import com.balance.model.ExpenseTableView;
import com.balance.util.Notification;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class ExpenseReportController implements Initializable {

    DAO dao = new DAOImpl();

    @FXML
    private Label beginDateLbl;
    @FXML
    private DatePicker beginDateDP;
    @FXML
    private Label endDateLbl;
    @FXML
    private DatePicker endDateDp;
    @FXML
    private Button filtirBtn;
    @FXML
    private TableView<ExpenseTableView> expenseReportTableView;
    @FXML
    private TableColumn<ExpenseTableView, Integer> IdCol;
    @FXML
    private TableColumn<ExpenseTableView, String> CategoryCol;
    @FXML
    private TableColumn<ExpenseTableView, Double> amountCol;
    @FXML
    private TableColumn<ExpenseTableView, String> noteCol;
    @FXML
    private TableColumn<ExpenseTableView, LocalDateTime> reg_DateCol;
    @FXML
    private Label sumAmountLbl1;
    @FXML
    private Label sumAmountLbl2;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        expenseLoadRows();
        expenseLoadColumuns();
        expenseAmounts();
        expenseLoadRowCounts();
        beginDateDP.setValue(LocalDate.now());
        endDateDp.setValue(LocalDate.now());
    }

    @FXML
    private void filtirBtnOnAction(ActionEvent event) {
        String expenseBeginDate = "" + beginDateDP.getValue();
        String expenseEndDate = "" + endDateDp.getValue();
        if (expenseBeginDate.equalsIgnoreCase("") || expenseEndDate.equalsIgnoreCase("") || beginDateDP.getValue().isAfter(endDateDp.getValue())) {

            if (beginDateDP.getValue().isAfter(endDateDp.getValue())) {
                Notification.callNotification("BAŞLANĞIC TARİXİ BİTMƏ TARİXİNDƏN BÖYÜK OLA BİLMƏZ !!!", "XƏTA !!!");
            }
            if (expenseBeginDate.equalsIgnoreCase("")) {
                Notification.callNotification("BAŞLANĞIC TARİXİ DAXİL EDİN", "XƏTA !!!");
            }
            if (expenseEndDate.equalsIgnoreCase("")) {
                Notification.callNotification("BİTMƏ TARİXİNİ DAXİL EDİN", "XƏTA !!!");
            }
        } else {
            expenseReportTableView.getItems().clear();
            expenseReportTableView.setItems(dao.searchExpense(expenseBeginDate, expenseEndDate));
            expenseLoadRowCounts();
            expenseAmounts();
        }
    }

    private void expenseLoadRows() {
        expenseReportTableView.setItems(dao.getExpenses());
    }

    private void expenseLoadColumuns() {
        IdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        noteCol.setCellValueFactory(new PropertyValueFactory<>("note"));
        reg_DateCol.setCellValueFactory(new PropertyValueFactory<>("reg_date"));
        CategoryCol.setCellValueFactory(new PropertyValueFactory<>("expenseCategory"));
    }

    private void expenseAmounts() {
        double sumAmount = 0;
        for (int i = 0; i < expenseReportTableView.getItems().size(); i++) {
            sumAmount += expenseReportTableView.getItems().get(i).getAmount();
        }
        sumAmountLbl2.setText("" + sumAmount);
    }

    private void expenseLoadRowCounts() {
        int a = expenseReportTableView.getItems().size();
        Notification.callNotification(a + " SƏTİRDƏN İBARƏT XƏRC CƏDVƏLİNİZ VAR !!!", "BİLDİRİŞ !!!");
    }

    @FXML
    private void expenseReportTableViewOnMousePressed(MouseEvent event) {
        expenseReportTableView.getItems().clear();
        expenseReportTableView.setItems(dao.getExpenses());
        Notification.callNotification(expenseReportTableView.getItems().size() + " SƏTİRDƏN İBARƏT XƏRC CƏDVƏLİNİZ VAR !!!", "BİLDİRİŞ !!!");
    }
}
