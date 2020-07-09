package com.balance.expense;

import com.balance.DAO.DAO;
import com.balance.DAO.DAOImpl;
import com.balance.model.Expense;
import com.balance.model.ExpenseCategory;
import com.balance.model.User;
import com.balance.util.Notification;
import com.balance.util.UserInfo;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class ExpenseController implements Initializable {

    DAO dao = new DAOImpl();
    @FXML
    private Button cixisexpenseBtn;

    public void setExpenseCategoryLabelText(String categori) {
        expenseNameLbl2.setText(categori);
    }

    @FXML
    private Label expenseCategoryLbl1;
    @FXML
    private Label expenseNameLbl1;
    @FXML
    private Label expenseNameLbl2;
    @FXML
    private Label expenseNameLbl;
    @FXML
    private Label expenseDateLbl;
    @FXML
    private Label expenseAmountLbl;
    @FXML
    private TextField expenseNameTF;
    @FXML
    private TextField expenseAmountTF;
    @FXML
    private DatePicker expenseDateDP;
    @FXML
    private Button saveExpenseBtn;
    @FXML
    private Button clearAllExpenseBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        makeNumeric();
        expenseDateDP.setValue(LocalDate.now());

    }

    @FXML
    private void saveExpenseBtnOnAction(ActionEvent event) {
        if (!expenseNameTF.getText().trim().equalsIgnoreCase("") && !expenseAmountTF.getText().trim().equalsIgnoreCase("") && expenseDateDP.getValue() != null) {
            String expenseAmount = expenseAmountTF.getText().trim();

            String expenseName = expenseNameTF.getText().trim();
            double expenseAmount1 = Double.parseDouble(expenseAmount);
            LocalDate expenseDate = expenseDateDP.getValue();

            int expenseUserId = UserInfo.userId;
            String expenseCategoryName = expenseNameLbl2.getText();
            int expenseCategoryId = dao.find_ExpenseCategoryId_ByName(expenseCategoryName, expenseUserId);

            Expense expense = new Expense();
            expense.setNote(expenseName);
            expense.setAmount(expenseAmount1);
            expense.setReg_date(expenseDate + "");

            User user = new User();
            user.setId(expenseUserId);
            expense.setUser(user);

            ExpenseCategory expenseCategory = new ExpenseCategory();
            expenseCategory.setId(expenseCategoryId);
            expense.setExpenseCategory(expenseCategory);

            if (dao.addExpense(expense)) {
                Notification.callNotification("UĞURLA ƏLAVƏ OLUNDU !!!", "UĞURLU ƏMƏLİYYAT");
                try {
                    Stage stage1 = (Stage) saveExpenseBtn.getScene().getWindow();
                    stage1.close();
                    Stage stage = new Stage();
                    stage.setTitle("Əsas Səhifə");
                    stage.getIcons().add(new Image("/com/balance/images/download.jpg"));
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/balance/main/main.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Notification.callNotification("ƏLAVƏ OLUNMADI !!!", "XƏTA !!!");
            }

        } else {
            if (expenseNameTF.getText().trim().equalsIgnoreCase("")) {
                Notification.callNotification("XƏRCİN ADINI DAXİL EDİN", "XƏTA !!!");
            }
            if (expenseAmountTF.getText().trim().equalsIgnoreCase("")) {
                Notification.callNotification("XƏRCİN MƏBLƏĞİNİ DAXİL EDİN", "XƏTA !!!");
            }
            if (expenseDateDP.getValue() == null) {
                Notification.callNotification("XƏRCİN TARİXİNİ DAXİL EDİN", "XƏTA !!!");
            }
        }
    }

    @FXML
    private void clearAllExpenseBtnOnAction(ActionEvent event) {
        expenseNameTF.setText("");
        expenseAmountTF.setText("");
        expenseDateDP.setValue(LocalDate.now());
        Notification.callNotification("UĞURLA SİLİNDİ", "UĞURLU ƏMƏLİYYAT");
    }

    @FXML
    private void expenseCategoryNameOnMousePressed(MouseEvent event) {
        Tooltip.install(expenseNameLbl2, new Tooltip("XƏRCİN ADI"));
    }

    @FXML
    private void expenseCategoryNameOnMousePressedTF(MouseEvent event) {
        Tooltip.install(expenseNameTF, new Tooltip("XƏRCİN ADINI DAXİL ETMƏK ÜÇÜN"));
    }

    @FXML
    private void expenseCategoryAmountOnMousePressedTF(MouseEvent event) {
        Tooltip.install(expenseAmountTF, new Tooltip("XƏRCİN MƏBLƏĞİNİ DAXİL ETMƏK ÜÇÜN"));
    }

    @FXML
    private void expenseCategoryDateOnMousePressedDP(MouseEvent event) {
        Tooltip.install(expenseDateDP, new Tooltip("XƏRCİN TARİXİNİ DAXİL ETMƏK ÜÇÜN"));
    }

    @FXML
    private void expenseCategorySaveOnMousePressedBtn(MouseEvent event) {
        Tooltip.install(saveExpenseBtn, new Tooltip("XƏRCİNİZİ YADDA SAXLAMAQ ÜÇÜN"));
    }

    @FXML
    private void expenseCategoryClearOnMousePressedBtn(MouseEvent event) {
        Tooltip.install(clearAllExpenseBtn, new Tooltip("XƏRCİNİZİ SİLMƏK ÜÇÜN"));
    }

    public void makeNumeric() {
        expenseAmountTF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue< ? extends String> observable, String oldValue, String newValue) {

                if (!newValue.matches("\\d{0,50}(\\d{0,0})?")) {
                    expenseAmountTF.setText(oldValue);
                }

            }
        });
    }

    @FXML
    private void cixisExpenseBtnOnAction(ActionEvent event) {
        try {
            ImageIcon icon = new ImageIcon();
            int response = JOptionPane.showConfirmDialog(null, "ÇIXMAQ İSTƏDYİNİZDƏN ƏMİNSİNİZ ?", "TƏSDİQLƏ", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, icon);
            if (response == JOptionPane.YES_OPTION) {
                Stage stage1 = (Stage) cixisexpenseBtn.getScene().getWindow();
                stage1.close();
                Stage stage = new Stage();
                stage.setTitle("Əsas Səhifə");
                stage.getIcons().add(new Image("/com/balance/images/download.jpg"));
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/balance/main/main.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
