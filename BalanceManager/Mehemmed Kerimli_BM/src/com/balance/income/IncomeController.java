package com.balance.income;

import com.balance.DAO.DAO;
import com.balance.DAO.DAOImpl;
import com.balance.model.Income;
import com.balance.model.IncomeCategory;
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

public class IncomeController implements Initializable {

    DAO dao = new DAOImpl();
    @FXML
    private Button cixisBtn;

    public void setIncomeCategoryLabelText(String category) {
        incomeNameLbl2.setText(category);
    }

    @FXML
    private Label incomeNameLbl1;
    @FXML
    private Label incomeNameLbl2;
    @FXML
    private Label incomeCategoryLbl1;
    @FXML
    private Label incomeNameLbl;
    @FXML
    private Label incomeDateLbl;
    @FXML
    private Label incomeAmountLbl;
    @FXML
    private TextField incomeNameTF;
    @FXML
    private TextField incomeAmountTF;
    @FXML
    private DatePicker incomeDateDP;
    @FXML
    private Button saveİncomeBtn;
    @FXML
    private Button clearAllİncomeBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        makeNumeric();
        incomeDateDP.setValue(LocalDate.now());
    }

    @FXML
    private void saveİncomeBtnOnAction(ActionEvent event) {
        if (!incomeNameTF.getText().trim().equalsIgnoreCase("") && !incomeAmountTF.getText().trim().equalsIgnoreCase("") && incomeDateDP.getValue() != null) {

            String incomeAmount = incomeAmountTF.getText().trim();

            String incomeName = incomeNameTF.getText().trim();
            double incomeAmount1 = Double.parseDouble(incomeAmount);
            LocalDate incomeDate = incomeDateDP.getValue();

            int incomeUserId = UserInfo.userId;
            String incomeCategoryName = incomeNameLbl2.getText();
            int incomeCategoryId = dao.find_IncomeCategoryId_ByName(incomeCategoryName, incomeUserId);

            Income income = new Income();
            income.setNote(incomeName);
            income.setAmount(incomeAmount1);
            income.setReg_date(incomeDate + "");

            User user = new User();
            user.setId(incomeUserId);
            income.setUser(user);

            IncomeCategory incomeCategory = new IncomeCategory();
            incomeCategory.setId(incomeCategoryId);
            income.setIncomeCategory(incomeCategory);

            if (dao.addIncome(income)) {
                Notification.callNotification("UĞURLA ƏLAVƏ EDİLDİ !!!", "UĞURLU ƏMƏLİYYAT !!!");
                try {
                    Stage stage1 = (Stage) saveİncomeBtn.getScene().getWindow();
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
                Notification.callNotification("ƏLAVƏ OLUNMADI YENİDƏN CƏHD EDİN !!!", "XƏTA !!!");
            }
        } else {
            if (incomeNameTF.getText().trim().equalsIgnoreCase("")) {
                Notification.callNotification("GƏLİRİN ADINI DAXİL EDİN !!!", "XƏTA !!!");
            }
            if (incomeAmountTF.getText().trim().equalsIgnoreCase("")) {
                Notification.callNotification("GƏLİRİN MƏBLƏĞİNİ DAXİL EDİN !!!", "XƏTA !!!");
            }
            if (incomeDateDP.getValue() == null) {
                Notification.callNotification("GƏLİRİN TARİXİNİ DAXİL EDİN !!!", "XƏTA !!!");
            }
        }
    }

    @FXML
    private void clearAllİncomeBtnOnAction(ActionEvent event) {
        incomeNameTF.setText("");
        incomeAmountTF.setText("");
        incomeDateDP.setValue(LocalDate.now());
        Notification.callNotification("UĞURLA SİLİNDİ !!!", "UĞURLU ƏMƏLİYYAT");
    }

    @FXML
    private void incomeNameTFOnMousePressed(MouseEvent event) {
        Tooltip.install(incomeNameTF, new Tooltip("GƏLİRİN ADINI DAXİL ETMƏK ÜÇÜN"));
    }

    @FXML
    private void incomeAmountTFOnMousePressed(MouseEvent event) {
        Tooltip.install(incomeAmountTF, new Tooltip("GƏLİRİN MƏBLƏĞİNİ DAXİL ETMƏK ÜÇÜN"));
    }

    @FXML
    private void incomeDateDPOnMousePressed(MouseEvent event) {
        Tooltip.install(incomeDateDP, new Tooltip("GƏLİRİN TARİXİNİ DAXİL ETMƏK ÜÇÜN"));
    }

    @FXML
    private void saveİncomeBtnOnMousePressed(MouseEvent event) {
        Tooltip.install(saveİncomeBtn, new Tooltip("GƏLİRİNİZİ YADDA SAXLAMQ ÜÇÜN"));
    }

    @FXML
    private void clearAllİncomeBtnOnMousePressed(MouseEvent event) {
        Tooltip.install(clearAllİncomeBtn, new Tooltip("GƏLİRİNİZİ SİLMƏK ÜÇÜN"));
    }

    @FXML
    private void incomeNameLbl2OnMousePressed(MouseEvent event) {
        Tooltip.install(incomeNameLbl2, new Tooltip("GƏLİRİNİZİN ADI"));
    }

    public void makeNumeric() {
        incomeAmountTF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue< ? extends String> observable, String oldValue, String newValue) {

                if (!newValue.matches("\\d{0,50}(\\d{0,0})?")) {
                    incomeAmountTF.setText(oldValue);
                }

            }
        });
    }

    @FXML
    private void cixisBtnOnAction(ActionEvent event) {
        try {
            ImageIcon icon = new ImageIcon();
            int response = JOptionPane.showConfirmDialog(null, "HƏR ŞEYİN DÜZGÜN OLSUĞUNDAN ƏMİNSİNİZ ?", "TƏSDİQLƏ", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, icon);

            if (response == JOptionPane.YES_OPTION) {
                Stage stage1 = (Stage) saveİncomeBtn.getScene().getWindow();
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
