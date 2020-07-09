package com.balance.report;

import com.balance.DAO.DAO;
import com.balance.DAO.DAOImpl;
import com.balance.model.Income;
import com.balance.model.IncomeTableView;
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

public class IncomeReportController implements Initializable {

    DAO dao = new DAOImpl();

    @FXML
    private Label beginDateLbl;
    @FXML
    private Label endDateLbl;
    @FXML
    private DatePicker beginDateDp;
    @FXML
    private DatePicker endDateDP;
    @FXML
    private Button filtirBtn;
    @FXML
    private TableView<IncomeTableView> incomeReportTableView;
    @FXML
    private TableColumn<IncomeTableView, Integer> idCol;
    @FXML
    private TableColumn<IncomeTableView, String> CategoryCol;
    @FXML
    private TableColumn<IncomeTableView, Double> AmountCol;
    @FXML
    private TableColumn<IncomeTableView, String> noteCol;
    @FXML
    private TableColumn<IncomeTableView, LocalDateTime> reg_DateCol;
    @FXML
    private Label sumAmountLbl1;
    @FXML
    private Label sumAmountLbl2;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        incomeLoadRows();
        incomeLoadColumuns();
        incomeAmounts();
        incomeLoadRowCounts();
        beginDateDp.setValue(LocalDate.now());
        endDateDP.setValue(LocalDate.now());
    }

    @FXML
    private void filtirBtnOnAction(ActionEvent event) {
        String incomeBeginDate = "" + beginDateDp.getValue();
        String incomeEndDate = "" + endDateDP.getValue();
        incomeBeginDate = incomeBeginDate.substring(0, 10);
        incomeEndDate = incomeEndDate.substring(0, 10);
        if (incomeBeginDate.equalsIgnoreCase("") || incomeEndDate.equalsIgnoreCase("") || beginDateDp.getValue().isAfter(endDateDP.getValue())) {

            if (beginDateDp.getValue().isAfter(endDateDP.getValue())) {
                Notification.callNotification("BAŞLANĞIC TARİXİ BİTMƏ TARİXİNDƏN BÖYÜK OLA BİLMƏZ !!!", "XƏTA !!!");
            }
            if (incomeBeginDate.equalsIgnoreCase("")) {
                Notification.callNotification("BAŞLANĞIC TARİXİ DAXİL EDİN !!!", "XƏTA !!!");
            }
            if (incomeEndDate.equalsIgnoreCase("")) {
                Notification.callNotification("BİTMƏ TARİXİNİ DAXİL EDİN !!!", "XƏTA !!!");
            }
        } else {
            incomeReportTableView.getItems().clear();
            incomeReportTableView.setItems(dao.searchIncome(incomeBeginDate, incomeEndDate));
            incomeLoadRowCounts();
            incomeAmounts();
        }
    }

    private void incomeLoadRows() {
        incomeReportTableView.setItems(dao.getIncomes());
    }

    private void incomeLoadColumuns() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        AmountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        noteCol.setCellValueFactory(new PropertyValueFactory<>("note"));
        reg_DateCol.setCellValueFactory(new PropertyValueFactory<>("reg_date"));
        CategoryCol.setCellValueFactory(new PropertyValueFactory<>("incomeCategory"));
    }

    private void incomeAmounts() {
        double sumAmount = 0;
        for (int i = 0; i < incomeReportTableView.getItems().size(); i++) {
            sumAmount += incomeReportTableView.getItems().get(i).getAmount();
        }
        sumAmountLbl2.setText("" + sumAmount);
    }

    private void incomeLoadRowCounts() {
        int a = incomeReportTableView.getItems().size();
        Notification.callNotification(a + " SƏTİRDƏN İBARƏT GƏLİR CƏDVƏLİNİZ VAR !!!", "BİLDİRİŞ !!!");
    }

    @FXML
    private void incomeReportTableViewOnMousePressed(MouseEvent event) {
        incomeReportTableView.getItems().clear();
        incomeReportTableView.setItems(dao.getIncomes());
        Notification.callNotification(incomeReportTableView.getItems().size() + " SƏTİRDƏN İBARƏT GƏLİR CƏDVƏLİNİZ VAR !!!", "BİLDİRİŞ !!!");
    }

}
