package com.balance.plan;

import com.balance.DAO.DAO;
import com.balance.DAO.DAOImpl;
import com.balance.main.MainController;
import com.balance.model.Plan;
import com.balance.model.PlanDetails;
import com.balance.util.Notification;
import com.balance.util.UserInfo;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class NewPlanController implements Initializable {

    DAO dao = new DAOImpl();

    @FXML
    private Label planNameLbl;
    @FXML
    private TextField planNameTF;
    @FXML
    private Label beginDateLbl;
    @FXML
    private DatePicker beginDateDP;
    @FXML
    private Label endDateLbl;
    @FXML
    private DatePicker endDateDP;
    @FXML
    private Label spendAmountLbl;
    @FXML
    private TextField amountTF;
    @FXML
    private Button addPlanBtn;
    @FXML
    private ListView<String> expenseCategoryListView;
    @FXML
    private ListView<Double> amountListView;
    @FXML
    private Label sumAmountLbl1;
    @FXML
    private Label sumAmountLbl2;
    @FXML
    private Label balanceLbl1;
    @FXML
    private Label balanceLbl2;
    @FXML
    private Button cixisPlanBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        beginDateDP.setValue(LocalDate.now());
        endDateDP.setValue(LocalDate.now());
        loadExpenseCategory();
        loadDefaultAmounts();
        loadSumAmount();
        loadBalance();
        makeNumeric();
        balanceLbl2.setText("" + (dao.sumIncomeAmount() - dao.sumExpenseAmount()));
    }

    double sumAmount = 0;
    int selectedCategoryIndex = 0;

    @FXML
    private void amountTFOnKeyReleased(KeyEvent event) {
        if (expenseCategoryListView.getSelectionModel() != null) {
            if (!amountTF.getText().equalsIgnoreCase("")) {
                double amount = Double.parseDouble(amountTF.getText());
                amountListView.getItems().set(selectedCategoryIndex, amount);
            }
        } else {
            Notification.callNotification("KATEQORİYA SEÇİN !!!", "XƏTA !!!");
        }
        loadSumAmount();
    }

    boolean planName(List<String> list, String str) {
        boolean result = false;
        for (String name : list) {
            if (name.equalsIgnoreCase(str)) {
                result = true;
                break;
            }
        }
        return result;
    }

    @FXML
    private void addPlanBtnOnAction(ActionEvent event) {
        boolean isExist = planName(dao.getPlans(), planNameTF.getText());
        if (beginDateDP.getValue() == null || endDateDP.getValue() == null || planNameTF.getText().equalsIgnoreCase("") || beginDateDP.getValue().isAfter(endDateDP.getValue()) || sumAmountLbl2.getText().trim().equals("0.0") || isExist) {
            if (beginDateDP.getValue().isAfter(endDateDP.getValue())) {
                Notification.callNotification("PLANIN BAŞLAMA TARİXİ BİTMƏ TARİXİNDƏN KİÇİK OLA BİLMƏZ !!!", "XƏTA !!!");
            } else {
                if (beginDateDP.getValue() == null) {
                    Notification.callNotification("PLANIN BAŞLAMA TARİXİNİ DAXİL EDİN !!!", "XƏTA !!!");
                } else {
                    if (endDateDP.getValue() == null) {
                        Notification.callNotification("PLANIN BİTMƏ TARİXİNİ DAXİL EDİN !!!", "XƏTA !!!");
                    } else {
                        if (planNameTF.getText().equalsIgnoreCase("")) {
                            Notification.callNotification("PLANIN ADINI DAXİL EDİN !!!", "XƏTA !!!");
                        } else {
                            if (sumAmountLbl2.getText().trim().equals("0.0")) {
                                Notification.callNotification("MƏBLƏĞ DAXİL EDİN !!!", "XƏTA !!!");
                            } else {
                                if (isExist) {
                                    Notification.callNotification("EYNİ ADLI PLAN ƏLAVƏ OLUNA BİLMƏZ !!!", "XƏTA !!!");
                                }
                            }
                        }
                    }
                }
            }
        } else {
            if (!beginDateDP.getValue().equals(endDateDP.getValue())) {
                ImageIcon icon = new ImageIcon();
                int response = JOptionPane.showConfirmDialog(null, "HƏR ŞEYİN DÜZGÜN OLDUĞUNDAN ƏMİNSİNİZ ?", "TƏSDİQLƏ", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, icon);
                if (response == JOptionPane.YES_OPTION) {
                    Plan plan = new Plan();
                    plan.setBegin_date("" + beginDateDP.getValue());
                    plan.setEnd_date("" + endDateDP.getValue());
                    plan.setName(planNameTF.getText().trim());
                    plan.setAmount(sumAmount);
                    if (!dao.addPlan(plan)) {
                        Notification.callNotification("PLAN ƏLAVƏ OLUNMADI !!!", "XƏTA !!!");
                    } else {
                        int planId = dao.getPlanId(planNameTF.getText().trim());
                        String selectedCategoryName = "";
                        double selectedAmount = 0;
                        for (int i = 0; i < expenseCategoryListView.getItems().size(); i++) {
                            selectedCategoryName = expenseCategoryListView.getItems().get(i);
                            selectedAmount = amountListView.getItems().get(i);

                            PlanDetails details = new PlanDetails();
                            details.setCategory(selectedCategoryName);
                            details.setAmount(selectedAmount);
                            Plan plan1 = new Plan();
                            plan1.setId(planId);
                            details.setPlan(plan1);

                            if (!dao.addplanDetails(details)) {
                                Notification.callNotification(selectedCategoryName + " ADLI PLAN ƏLAVƏ OLUNMADI !!!", "XƏTA !!!");
                            } else {
                                try {
                                    Stage stage1 = (Stage) addPlanBtn.getScene().getWindow();
                                    stage1.close();

                                    Stage stage = new Stage();
                                    stage.setTitle("Əsas Səhifəsi");
                                    stage.getIcons().add(new Image("/com/balance/images/download.jpg"));
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/balance/main/main.fxml"));
                                    Parent root = loader.load();
                                    Scene scene = new Scene(root);
                                    stage.setScene(scene);
                                    stage.show();

                                    MainController mainController = loader.getController();
                                    mainController.loadPlans();
                                    break;

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        MainController mainController = new MainController();
                        Stage stage = (Stage) endDateDP.getScene().getWindow();
                        stage.close();
                        Notification.callNotification("KATEQORIYA UĞURLA ƏLAVƏ OLUNDU !!!", "UĞURLU ƏMƏLİYYAT !!!");
                    }
                }
            } else {
                Notification.callNotification("BAŞLANĞIC VƏ SON TARİX BƏRABƏR OLA BİLMƏZ !!!", "XƏTA !!!");
            }
        }
    }

    @FXML
    private void expenseCategoryListViewOnMousePressed(MouseEvent event) {
        if (expenseCategoryListView.getSelectionModel() != null) {
            amountTF.clear();
        }
        selectedCategoryIndex = expenseCategoryListView.getSelectionModel().getSelectedIndex();
        amountListView.getSelectionModel().select(selectedCategoryIndex);
    }

    @FXML
    private void amountListViewOnMousePressed(MouseEvent event) {
    }

    private void loadExpenseCategory() {
        expenseCategoryListView.getItems().addAll(dao.getExpenseCategory());
    }

    private void loadDefaultAmounts() {
        for (int i = 0; i < expenseCategoryListView.getItems().size(); i++) {
            amountListView.getItems().addAll(0.0);
        }
    }

    private void loadSumAmount() {
        sumAmount = 0;
        for (int i = 0; i < amountListView.getItems().size(); i++) {
            List<Double> amountList = amountListView.getItems();
            sumAmount += amountList.get(i);
            sumAmountLbl2.setText("" + sumAmount);
        }

    }

    private void loadBalance() {
        balanceLbl2.setText("" + dao.getBalanceByUserId(UserInfo.userId));
    }

    public void makeNumeric() {
        amountTF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue< ? extends String> observable, String oldValue, String newValue) {

                if (!newValue.matches("\\d{0,50}(\\d{0,0})?")) {
                    amountTF.setText(oldValue);
                }

            }
        });
    }

    @FXML
    private void cixisPlanOnAction(ActionEvent event) {
        ImageIcon icon = new ImageIcon();
        int response = JOptionPane.showConfirmDialog(null, "ÇIXMAQ İSTƏDYİNİZDƏN ƏMİNSİNİZ ?", "TƏSDİQLƏ", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, icon);
        if (response == JOptionPane.YES_OPTION) {
            try {
                Stage stage1 = (Stage) cixisPlanBtn.getScene().getWindow();
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
        }
    }
}
