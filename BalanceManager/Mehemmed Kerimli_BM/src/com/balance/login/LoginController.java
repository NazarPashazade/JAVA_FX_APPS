package com.balance.login;

import com.balance.DAO.DAO;
import com.balance.DAO.DAOImpl;
import com.balance.model.User;
import com.balance.util.UserInfo;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class LoginController implements Initializable {

    DAO dao = new DAOImpl();
    public String username = "";
    public String password = "";
    public int userId = 0;

    @FXML
    private TextField userameTF;
    @FXML
    private Button loginBtn;
    @FXML
    private Label infoLbl1;
    @FXML
    private Label infoLbl2;
    @FXML
    private PasswordField passwordPF;
    @FXML
    private Button creatAccountBtn;
    @FXML
    private Button deleteAccountBtn;
    @FXML
    private ImageView loginImageView;
    @FXML
    private ImageView loginImageView2;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loginImageView.setImage(new Image("/com/balance/images/sekil.jpg"));
    }

    @FXML
    private void loginBtnOnAction(ActionEvent event) {
        userInfo();
        if (userameTF.getText().trim().equalsIgnoreCase("") || passwordPF.getText().trim().equalsIgnoreCase("")) {
            if (userameTF.getText().trim().equalsIgnoreCase("")) {
                message("ZƏHMƏT OLMASA İSTİFADƏÇİ ADI DAXİL EDİN");
            }
            if (passwordPF.getText().trim().equalsIgnoreCase("")) {
                message("ZƏHMƏT OLMASA ŞİFRƏ DAXİL EDİN");
            }
            if (userameTF.getText().trim().equalsIgnoreCase("") && passwordPF.getText().equalsIgnoreCase("")) {
                message("ZƏHMƏT OLMASA İSTİFADƏÇİ ADI VƏ ŞİFRƏ DAXİL EDİN");
            }
        } else {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            boolean isExist = dao.checkUser(user);
            if (isExist) {
                userId = dao.find_UserId_ByUsernameAndPassword(user);
                UserInfo.userId = userId;
                try {
                    Stage stage1 = (Stage) loginBtn.getScene().getWindow();
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
                message("BELƏ BİR İSTİFADƏÇİ MÖVCUD DEYİL !!!");
            }
        }

    }

    @FXML
    private void creatAccountOnAction(ActionEvent event) {
        userInfo();
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        if (userameTF.getText().trim().equalsIgnoreCase("") || passwordPF.getText().trim().equalsIgnoreCase("")) {
            if (userameTF.getText().trim().equalsIgnoreCase("")) {
                message("ZƏHMƏT OLMASA İSTİFADƏÇİ ADI DAXİL EDİN");
            }
            if (passwordPF.getText().trim().equalsIgnoreCase("")) {
                message("ZƏHMƏT OLMASA ŞİFRƏ DAXİL EDİN");
            }
            if (userameTF.getText().trim().equalsIgnoreCase("") && passwordPF.getText().equalsIgnoreCase("")) {
                message("ZƏHMƏT OLMASA İSTİFADƏÇİ ADI VƏ ŞİFRƏ DAXİL EDİN");
            }
        } else {
            boolean isExist = dao.checkUser(user);
            if (isExist) {
                message("BELƏ BİR HESAB ARTIQ MÖVCUDDUR !!!");
            } else {
                if (dao.creatAccount(user)) {
                    message("HESAB UĞURLA YARADILDI !!!");
                } else {
                    message("HESAB YARADILMADI !!!");
                }
            }
        }
    }

    @FXML
    private void deleteAccountOnAction(ActionEvent event) {
        userInfo();
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userId = dao.find_UserId_ByUsernameAndPassword(user);
        if (dao.deleteAccount1(userId)) {
            if (userameTF.getText().trim().equalsIgnoreCase("") || passwordPF.getText().trim().equalsIgnoreCase("")) {
                if (userameTF.getText().trim().equalsIgnoreCase("")) {
                    message("ZƏHMƏT OLMASA İSTİFADƏÇİ ADI DAXİL EDİN");
                }
                if (passwordPF.getText().trim().equalsIgnoreCase("")) {
                    message("ZƏHMƏT OLMASA ŞİFRƏ DAXİL EDİN");
                }
                if (userameTF.getText().trim().equalsIgnoreCase("") && passwordPF.getText().equalsIgnoreCase("")) {
                    message("ZƏHMƏT OLMASA İSTİFADƏÇİ ADI VƏ ŞİFRƏ DAXİL EDİN");
                }
            } else if (userId == 0) {
                message("HESAB SİLİNMƏDİ BELƏ BİR HESAB MÖVCUD DEYİL !!!");
            } else {
                ImageIcon icon = new ImageIcon();
                int response = JOptionPane.showConfirmDialog(null, "HESABINIZI SİLMƏK İSTƏDİYİNİZDƏN ƏMİNSİNİZ ?", "TƏSDİQLƏ", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, icon);
                if (response == JOptionPane.YES_OPTION) {
                    message("HESAB UĞURLA SİLİNDİ !!!");
                }

            }
        } else {
            message("HESAB SİLİNMƏDİ !!!");

        }
    }

    @FXML
    private void loginMousePressed(MouseEvent event) {
        Tooltip.install(loginBtn, new Tooltip("ƏSAS SƏHİFƏNİ AÇMAQ ÜÇÜN"));

    }

    @FXML
    private void creatAccountMousePressed(MouseEvent event) {
        Tooltip.install(creatAccountBtn, new Tooltip("YENİ HESAB YARATMAQ ÜÇÜN"));
    }

    @FXML
    private void deleteMousePressed(MouseEvent event) {
        Tooltip.install(deleteAccountBtn, new Tooltip("MÖVCUD HESABI SİLMƏK ÜÇÜN"));
    }

    public void userInfo() {
        username = userameTF.getText().trim();
        password = passwordPF.getText().trim();
    }

    public void message(String message) {
        infoLbl2.setText(message);
    }

}
