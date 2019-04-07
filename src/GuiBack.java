import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

public class GuiBack implements ActionListener {

    private JTextField firstNameTextField, lastNameTextField, counterCountTextField, counterNumberTextField, registerNameLoginTextField;
    private JList allCounterList;
    private JButton buttonEnter, buttonRegister, buttonLogin;
    private Product product;
    private Register register;
    private SqlConnection sqlConnection;
    private JPanel panelWest, panelEast, panelLogin;
    private JPasswordField passwordField;

    public GuiBack(JTextField firstNameTextField, JTextField lastNameTextField, JTextField counterCountTextField, JTextField counterNumberTextField, JTextField registerNameLoginTextField, JList allCounterList, JButton buttonEnter, JButton buttonRegister, JButton buttonLogin, JPanel panelWest, JPanel panelEast, JPanel panelLogin, JPasswordField passwordField) {
        this.firstNameTextField = firstNameTextField;
        this.lastNameTextField = lastNameTextField;
        this.counterCountTextField = counterCountTextField;
        this.counterNumberTextField = counterNumberTextField;
        this.registerNameLoginTextField = registerNameLoginTextField;
        this.allCounterList = allCounterList;
        this.buttonEnter = buttonEnter;
        this.buttonRegister = buttonRegister;
        this.buttonLogin = buttonLogin;
        this.panelWest = panelWest;
        this.panelEast = panelEast;
        this.panelLogin = panelLogin;
        this.passwordField = passwordField;
    }

    public void buttonFrontRegisterGui(){

    }

    public void buttonRegisterGui(){
        register = new Register();
        sqlConnection = new SqlConnection("counter", "root","");
        setLogin();
        Vector<Register> allRegisterListVector = register.getLogin(sqlConnection);
        DefaultListModel list = new DefaultListModel();
        for (int i = 0; i < allRegisterListVector.size(); i++){
            list.addElement(allRegisterListVector.get(i).printLogin());
        }

        panelLogin.setVisible(false);
        panelEast.setVisible(true);
        panelWest.setVisible(true);
    }

    public void buttonEnterGui() {
        product = new Product();
        sqlConnection = new SqlConnection("counter", "root", "");
        setProduct();
        Vector<Product> allProductListVector = product.getProduct(sqlConnection);
        DefaultListModel list = new DefaultListModel();
        for (int i = 0; i < allProductListVector.size(); i++){
            list.addElement(allProductListVector.get(i).printProduct());
        }
        allCounterList.setModel(list);
    }

    public void setProduct(){
        product.setFirstName(firstNameTextField.getText());
        product.setLastName(lastNameTextField.getText());
        product.setCounterCode(Integer.valueOf(counterNumberTextField.getText()));
        product.setCounterCount(Double.valueOf(counterCountTextField.getText()));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        product.setTime(now.format(formatter));
        sqlConnection.connect();
        product.setProduct(sqlConnection, product);

    }

    public void setLogin(){
        register.setLoginName(registerNameLoginTextField.getText());
        register.setPassword(passwordField.getText());
        sqlConnection.connect();
        register.setLogin(sqlConnection, register);
    }





    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if(src == buttonRegister){
            buttonRegisterGui();
        }else if (src == buttonEnter){
            buttonEnterGui();
        }
        sqlConnection.disconnect();
    }
}
