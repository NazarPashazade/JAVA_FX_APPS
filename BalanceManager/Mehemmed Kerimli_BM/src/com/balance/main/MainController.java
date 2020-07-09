package com.balance.main;

import com.balance.DAO.DAO;
import com.balance.DAO.DAOImpl;
import com.balance.expense.ExpenseController;
import com.balance.income.IncomeController;
import com.balance.model.Expense;
import com.balance.model.ExpenseCategory;
import com.balance.model.Income;
import com.balance.model.IncomeCategory;
import com.balance.model.Plan;
import com.balance.model.PlanDetails;
import com.balance.model.User;
import com.balance.util.Notification;
import com.balance.util.UserInfo;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class MainController implements Initializable {

    String selectedCategory = "";
    String selectedCategori = "";
    DAO dao = new DAOImpl();

    @FXML
    private ListView<String> incomeListView;
    @FXML
    private Button incomeNewCategoryBtn;
    @FXML
    private Button newIncomeBtn;
    @FXML
    private Button updateIncomeBtn;
    @FXML
    private Button deleteIncomeBtn;
    @FXML
    private Button moreInfoIncomeBtn;
    @FXML
    private TextField incomeNewCategoryTxt;
    @FXML
    private ListView<String> expenseListView;
    @FXML
    private Button expenseNewCategoryBtn;
    @FXML
    private TextField expenseNewCategoryTxt;
    @FXML
    private Button newExpenseBtn;
    @FXML
    private Button updateExpenseBtn;
    @FXML
    private Button deleteExpenseBtn;
    @FXML
    private Button moreInfoExpenseBtn;
    @FXML
    private ListView<String> planListView;
    @FXML
    private Button newPlanBtn;
    @FXML
    private Label balanceLbl;
    @FXML
    private Label balanceLbl2;
    @FXML
    private Button logOutBtn;
    @FXML
    private PieChart reportPieChart;
    @FXML
    private Label sumAmountLbl;
    @FXML
    private Label beginDateLbl;
    @FXML
    private Label endDateLbl;
    @FXML
    private ListView<HBox> planReportListView;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadBalance();
        fillInIncomeCategoryListView();
        fillInExpenseCategoryListView();
        loadPlans();
    }

    @FXML
    private void incomeListViewOnMousePressed(MouseEvent event) {
        selectedCategory = incomeListView.getSelectionModel().getSelectedItem();
        Tooltip.install(incomeListView, new Tooltip("GƏLİRLƏRİNİZ"));
        incomeNewCategoryTxt.setText(selectedCategory);
    }

    @FXML
    private void incomeNewCategoryBtnOnAction(ActionEvent event) {
        String incomeCategoryName = incomeNewCategoryTxt.getText().trim();
        if (incomeCategoryName.equalsIgnoreCase("")) {
            Notification.callNotification("GƏLİR ADI BOŞ OLA BİLMƏZ", "XƏTA !!!");
        } else {
            List<String> incomeCategoryNameList = new ArrayList<String>();
            incomeCategoryNameList = incomeListView.getItems();
            boolean result = incomeCategoryIsCheckInList(incomeCategoryNameList, incomeCategoryName);
            if (result) {
                Notification.callNotification("ARTIQ EYNİSİ MÖVUDDUR !!!", "XƏTA !!!");
            } else {
                IncomeCategory incomeCategory = new IncomeCategory();
                User user = new User();
                user.setId(UserInfo.userId);
                incomeCategory.setUser(user);
                incomeCategory.setCategory(incomeCategoryName);
                if (dao.addIncomeCategory(incomeCategory)) {
                    Notification.callNotification("KATEQORİYA UĞURLA ƏLAVƏ OLUNDU !!!", "UĞURLU ƏMƏLİYYAT !!!");
                    incomeListView.getItems().clear();
                    fillInIncomeCategoryListView();
                    incomeNewCategoryTxt.setText("");
                } else {
                    Notification.callNotification("KATEQORİYA ƏLAVƏ OLUNMADI !!!", "XƏTA !!!");
                }
            }
        }
    }

    @FXML
    private void newIncomeBtnOnAction(ActionEvent event) {
        try {
            if (selectedCategory.trim().equalsIgnoreCase("")) {
                Notification.callNotification("HƏR HANSI BİR GƏLİR NÖVÜ SEÇİN", "XƏTA !!!");
            } else {
                Stage stage1 = (Stage) incomeNewCategoryBtn.getScene().getWindow();
                stage1.close();
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Yeni Gəlir");
                stage.getIcons().add(new Image("/com/balance/images/download.jpg"));
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/balance/income/income.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();

                IncomeController incomeController = loader.getController();
                incomeController.setIncomeCategoryLabelText(selectedCategory);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void updateIncomeBtnOnAction(ActionEvent event) {
        String newİncomeCategory = incomeNewCategoryTxt.getText();
        if (newİncomeCategory.trim().equalsIgnoreCase("")) {
            Notification.callNotification("GƏLİRİN ADINI SEÇİN !!!", "XƏTA !!!");
        } else {
            boolean isExist = incomeCategoryIsCheckInList(incomeListView.getItems(), newİncomeCategory);
            if (isExist) {
                Notification.callNotification("ARTIQ EYNİSİ MÖVUDDUR !!!", "XƏTA !!!");
            } else {
                IncomeCategory incomeCategory = new IncomeCategory();
                incomeCategory.setCategory(newİncomeCategory);
                incomeCategory.setId(dao.getİncomeCategoryById(selectedCategory));

                if (dao.updateIncomeCategory(incomeCategory)) {
                    incomeNewCategoryTxt.setText("");
                    Notification.callNotification("UĞURLA YENİLƏNDİ !!!", "UĞURLU ƏMƏLİYYAT !!!");
                    incomeListView.getSelectionModel().clearSelection();
                    incomeListView.getItems().clear();
                    fillInIncomeCategoryListView();
                } else {
                    Notification.callNotification("YENİLƏNMƏDİ !!!", "XƏTA !!!");
                }
            }
        }
    }

    @FXML
    private void deleteIncomeOnAction(ActionEvent event) {
        if (selectedCategory.equalsIgnoreCase("")) {
            Notification.callNotification("GƏLİR NÖVÜ SEÇİN !!!", "XƏTA !!!");
        } else {
            int incomeCategoryId = dao.find_IncomeCategoryId_ByName(selectedCategory, UserInfo.userId);

            IncomeCategory incomeCategory = new IncomeCategory();
            incomeCategory.setId(incomeCategoryId);
            if (dao.deleteIncomeCategory(incomeCategory)) {
                incomeListView.getItems().clear();
                fillInIncomeCategoryListView();
                incomeNewCategoryTxt.setText("");
                selectedCategory = "";
                Notification.callNotification("UĞURLA SİLİNDİ !!!", "UĞURLU ƏMƏLİYYAT !!!");
            } else {
                Notification.callNotification("SİLİNMƏDİ !!!", "XƏTA !!!");
            }
        }
    }

    @FXML
    private void moreInfoIncomeOnAction(ActionEvent event) {
        try {
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Ətraflı Məlumat");
            stage.getIcons().add(new Image("/com/balance/images/download.jpg"));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/balance/report/incomeReport.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void expenseListViewOnMousePressed(MouseEvent event) {
        selectedCategori = expenseListView.getSelectionModel().getSelectedItem();
        Tooltip.install(expenseListView, new Tooltip("XƏRCLƏRİNİZ"));
        expenseNewCategoryTxt.setText(selectedCategori);
    }

    @FXML
    private void newExpenseOnAction(ActionEvent event) {
        try {
            if (selectedCategori.trim().equalsIgnoreCase("")) {
                Notification.callNotification("HƏR HANSI BİR XƏRC NÖVÜ SEÇİN !!!", "XƏTA !!!");
            } else {
                Stage stage1 = (Stage) incomeNewCategoryBtn.getScene().getWindow();
                stage1.close();
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Yeni Xərc");
                stage.getIcons().add(new Image("/com/balance/images/download.jpg"));
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/balance/expense/expense1.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();

                ExpenseController expenseController = loader.getController();
                expenseController.setExpenseCategoryLabelText(selectedCategori);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void updateExpenseOnAction(ActionEvent event) {
        String newExpenseCategory = expenseNewCategoryTxt.getText();
        if (newExpenseCategory.trim().equalsIgnoreCase("")) {
            Notification.callNotification("XƏRCİN ADINI SEÇİN !!!", "XƏTA !!!");
        } else {
            boolean isExist = incomeCategoryIsCheckInList(expenseListView.getItems(), newExpenseCategory);
            if (isExist) {
                Notification.callNotification("ARTIQ EYNİSİ MÖVUDDUR !!!", "XƏTA !!!");
            } else {
                ExpenseCategory expenseCategory = new ExpenseCategory();
                expenseCategory.setCategory(newExpenseCategory);
                expenseCategory.setId(dao.getExpenseCategoryById(selectedCategori));

                if (dao.updateExpenseCategory(expenseCategory)) {
                    Notification.callNotification("UĞURLA YENİLƏNDİ !!!", "UĞURLU ƏMƏLİYYAT");
                    expenseListView.getSelectionModel().clearSelection();
                    expenseListView.getItems().clear();
                    fillInExpenseCategoryListView();
                    expenseNewCategoryTxt.setText("");
                } else {
                    Notification.callNotification("YENİLƏNMƏDİ !!!", "XƏTA !!!");
                }
            }
        }
    }

    @FXML
    private void deleteExpenseOnAction(ActionEvent event) {
        if (selectedCategori.equalsIgnoreCase("")) {
            Notification.callNotification("XƏRC NÖVÜ SEÇİN !!!", "XƏTA !!!");
        } else {
            int expenseCategoryId = dao.find_ExpenseCategoryId_ByName(selectedCategori, UserInfo.userId);

            ExpenseCategory expenseCategory = new ExpenseCategory();
            expenseCategory.setId(expenseCategoryId);

            if (dao.deleteExpenseCategory(expenseCategory)) {
                expenseListView.getItems().clear();
                fillInExpenseCategoryListView();
                expenseNewCategoryTxt.setText("");
                selectedCategori = "";
                Notification.callNotification("UĞURLA SİLİNDİ !!!", "UĞURLU ƏMƏLİİYAT");
            } else {
                Notification.callNotification("SİLİNMƏDİ !!!", "XƏTA !!!");
            }
        }
    }

    @FXML
    private void moreInfoExpenseOnAction(ActionEvent event) {
        try {
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Ətraflı Məlumat");
            stage.getIcons().add(new Image("/com/balance/images/download.jpg"));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/balance/report/expenseReport.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void planListViewOnMousePressed(MouseEvent event) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        Tooltip.install(planListView, new Tooltip("PLANINIZ"));
        planReportListView.getItems().clear();

        int plan_id = dao.getPlanIdByName(planListView.getSelectionModel().getSelectedItem());
        Plan plan = dao.getPlanById(plan_id);

        beginDateLbl.setText("" + plan.getBegin_date().substring(0, 10));
        endDateLbl.setText("" + plan.getEnd_date().substring(0, 10));
        sumAmountLbl.setText("" + plan.getAmount());

        List<PlanDetails> detailses = dao.getPlanDetailsByPlanId(plan_id);

        for (int i = 0; i < detailses.size(); i++) {
            HBox hBox = new HBox();

            String category = detailses.get(i).getCategory();

            double ayrilmisAmount = detailses.get(i).getAmount();

            Label ayrilmisLabel = new Label("KATEQORIYANIN ADI :       " + category + "                AYRILMIŞ MƏBLƏĞ :        " + ayrilmisAmount + "          ");

            ayrilmisLabel.setMaxWidth(450);

            ayrilmisLabel.setMinWidth(420);

            int exp_cat_id = dao.getExpenseCategoryId_byCategory(detailses.get(i).getCategory());

            double xerclenenAmount = dao.findSumAmountExpense(exp_cat_id, plan.getBegin_date().substring(0, 10), plan.getEnd_date().substring(0, 10));

            Label xerclenenLabel = new Label("        XƏRCLƏNƏN MƏBLƏĞ :     " + xerclenenAmount);

            xerclenenLabel.setMaxWidth(250);

            xerclenenLabel.setMinWidth(220);

            double deyer = xerclenenAmount / ayrilmisAmount;

            if (deyer > 1) {
                deyer = 1;
            }
            if (deyer < 0) {
                deyer = 0;
            }

            int faiz = (int) ((xerclenenAmount / ayrilmisAmount) * 100);

            Label faizLabel = new Label("      FAİZ : " + faiz + "%");

            ProgressBar progressBar = new ProgressBar(deyer);

            LocalDate beginDate = LocalDate.parse(plan.getBegin_date().substring(0, 10), formatter);
            LocalDate endDate = LocalDate.parse(plan.getEnd_date().substring(0, 10), formatter);

            int ayrilmisVaxtDate = endDate.compareTo(beginDate);

            int xerclenenVaxt = LocalDate.now().compareTo(beginDate);

            double birGunUcunAyrilmisMebleg = ayrilmisAmount / ayrilmisVaxtDate;
            double birGunUcunXercleneMebleg = xerclenenAmount / xerclenenVaxt;

            String temp = "";
            if (birGunUcunAyrilmisMebleg < birGunUcunXercleneMebleg) {
                temp = "PİS";
            }

            if (birGunUcunAyrilmisMebleg > birGunUcunXercleneMebleg) {
                temp = "YAXŞI";
            }

            if (birGunUcunAyrilmisMebleg == birGunUcunXercleneMebleg) {
                temp = "ORTA";
            }
            Label tempLabel = new Label("                " + temp);

            hBox.getChildren().add(ayrilmisLabel);

            hBox.getChildren().add(progressBar);

            hBox.getChildren().add(xerclenenLabel);

            hBox.getChildren().add(faizLabel);

            hBox.getChildren().add(tempLabel);

            planReportListView.getItems().add(hBox);
        }
    }

    @FXML
    private void newPlanOnAciton(ActionEvent event) {
        try {
            Stage stage1 = (Stage) newPlanBtn.getScene().getWindow();
            stage1.close();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("YENİ PLAN");
            stage.getIcons().add(new Image("/com/balance/images/download.jpg"));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/balance/plan/newPlan1.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadBalance() {
        balanceLbl2.setText("" + (dao.sumIncomeAmount() - dao.sumExpenseAmount()));
    }

    private void fillInIncomeCategoryListView() {
        incomeListView.getItems().clear();
        incomeListView.getItems().addAll(dao.getIncomeCategories(UserInfo.userId));
    }

    boolean incomeCategoryIsCheckInList(List<String> list, String str) {
        boolean result = false;
        for (String name : list) {
            if (name.equalsIgnoreCase(str)) {
                result = true;
                break;
            }
        }
        return result;
    }

    boolean expenseCategoryIsCheckInList(List<String> list1, String str1) {
        boolean result = false;
        for (String name : list1) {
            if (name.equalsIgnoreCase(str1)) {
                result = true;
                break;
            }
        }
        return result;
    }

    @FXML
    private void expenseNewCategoryOnAction(ActionEvent event) {
        String expenseCategoryName = expenseNewCategoryTxt.getText().trim();
        if (expenseCategoryName.equalsIgnoreCase("")) {
            Notification.callNotification("XƏRC ADI BOŞ OLA BİLMƏZ !!!", "XƏTA !!!");
        } else {
            List<String> expenseCategoryNameList = new ArrayList<String>();
            expenseCategoryNameList = expenseListView.getItems();
            boolean result = expenseCategoryIsCheckInList(expenseCategoryNameList, expenseCategoryName);
            if (result) {
                Notification.callNotification("ARTIQ EYNİSİ MÖVUDDUR !!!", "XƏTA !!!");
            } else {
                ExpenseCategory expenseCategory = new ExpenseCategory();
                User user = new User();
                user.setId(UserInfo.userId);
                expenseCategory.setUser(user);
                expenseCategory.setCategory(expenseCategoryName);
                if (dao.addExpenseCategory(expenseCategory)) {
                    Notification.callNotification("KATEQORİYA UĞURLA ƏLAVƏ OLUNDU !!!", "UĞURLU ƏMƏLİYYAT !!!");
                    expenseListView.getItems().clear();
                    fillInExpenseCategoryListView();
                    expenseNewCategoryTxt.setText("");
                } else {
                    Notification.callNotification("KATEQORİYA ƏLAVƏ OLUNMADI !!!", "XƏTA !!!");
                }
            }
        }
    }

    private void fillInExpenseCategoryListView() {
        expenseListView.getItems().clear();
        expenseListView.getItems().addAll(dao.getExpenseCategories(UserInfo.userId));
    }

    @FXML
    private void logOutBtnOnAction(ActionEvent event) {
        try {
            ImageIcon icon = new ImageIcon();
            int response = JOptionPane.showConfirmDialog(null, "ÇIXMAQ İSTƏDYİNİZDƏN ƏMİNSİNİZ ?", "TƏSDİQLƏ", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, icon);

            if (response == JOptionPane.YES_OPTION) {
                Stage stage1 = (Stage) logOutBtn.getScene().getWindow();
                stage1.close();

                Stage stage = new Stage();
                stage.setTitle("Qeydiyyat Səhifəsi");
                stage.getIcons().add(new Image("/com/balance/images/download.jpg"));
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/balance/login/login.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadPlans() {
        planListView.getItems().clear();
        planListView.getItems().addAll(dao.getPlans());
    }

    @FXML
    private void incomeNewCategoryBtnOnMousePressed(MouseEvent event) {
        Tooltip.install(incomeNewCategoryBtn, new Tooltip("YENİ GƏLİR ƏLAVƏ ETMƏK ÜÇÜN"));
    }

    @FXML
    private void newIncomeBtnOnMousePressed(MouseEvent event) {
        Tooltip.install(newIncomeBtn, new Tooltip("GƏLİRİ ƏTRAFLI ADLANDIRMAQ ÜÇÜN"));
    }

    @FXML
    private void updateIncomeBtnOnMousePressed(MouseEvent event) {
        Tooltip.install(updateIncomeBtn, new Tooltip("GƏLİRİ YENİLƏMƏK ÜÇÜN"));
    }

    @FXML
    private void deleteIncomeBtnOnMousePressed(MouseEvent event) {
        Tooltip.install(deleteIncomeBtn, new Tooltip("GƏLİRİ SİLMƏK ÜÇÜN"));
    }

    @FXML
    private void moreInfoIncomeBtnOnMousePressed(MouseEvent event) {
        Tooltip.install(moreInfoIncomeBtn, new Tooltip("GƏLİRLƏRİNİZ HAQQDA ƏTRAFLI MƏLUMAT"));
    }

    @FXML
    private void incomeNewCategoryTxtOnMousePressed(MouseEvent event) {
        Tooltip.install(incomeNewCategoryTxt, new Tooltip("GƏLİRİN ADINI DAXİL ETMƏK ÜÇÜN"));
    }

    @FXML
    private void expenseNewCategoryBtnOnMousePressed(MouseEvent event) {
        Tooltip.install(expenseNewCategoryBtn, new Tooltip("XƏRCLƏRİNİZ"));
    }

    @FXML
    private void expenseNewCategoryTxtOnMousePressed(MouseEvent event) {
        Tooltip.install(expenseNewCategoryTxt, new Tooltip("XƏRCİN ADINI DAXİL ETMƏK ÜÇÜN"));
    }

    @FXML
    private void newExpenseBtnOnMousePressed(MouseEvent event) {
        Tooltip.install(newExpenseBtn, new Tooltip("XƏRCİ ƏTRAFLI ADLANDIRMAQ ÜÇÜN"));
    }

    @FXML
    private void updateExpenseBtnOnMousePressed(MouseEvent event) {
        Tooltip.install(updateExpenseBtn, new Tooltip("GƏLİRİN YENİLƏMƏK ÜÇÜN"));
    }

    @FXML
    private void deleteExpenseBtnOnMousePressed(MouseEvent event) {
        Tooltip.install(deleteExpenseBtn, new Tooltip("GƏLİRİ SİLMƏK ÜÇÜN"));
    }

    @FXML
    private void moreInfoExpenseBtnOnMousePressed(MouseEvent event) {
        Tooltip.install(moreInfoExpenseBtn, new Tooltip("XƏRCİNİZ HAQQDA ƏTRAFLI MƏLUMAT"));
    }

    @FXML
    private void newPlanBtnOnMousePressed(MouseEvent event) {
        Tooltip.install(newPlanBtn, new Tooltip("YENİ PLAN ƏLAVƏ ETMƏK ÜÇÜN"));
    }

    @FXML
    private void incomeOnMouseReleased(MouseEvent event) {

        ObservableList<Income> incomeList = dao.fillIncomePieChart();

        ObservableList<PieChart.Data> dataList = FXCollections.observableArrayList();

        for (int i = 0; i < incomeList.size(); i++) {

            PieChart.Data data = new PieChart.Data(incomeList.get(i).getNote(), incomeList.get(i).getAmount());
            dataList.add(data);

        }
        reportPieChart.setData(dataList);
    }

    @FXML
    private void expenseOnMouseReleased(MouseEvent event) {
        ObservableList<Expense> expenseList = dao.fillExpensePieChart();

        ObservableList<PieChart.Data> dataList = FXCollections.observableArrayList();

        for (int i = 0; i < expenseList.size(); i++) {

            PieChart.Data data = new PieChart.Data(expenseList.get(i).getNote(), expenseList.get(i).getAmount());
            dataList.add(data);

        }
        reportPieChart.setData(dataList);
    }

}
