import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GuiBack implements ActionListener {

    private JTextField firstNameTextField, lastNameTextField, counterCountTextField, counterNumberTextField,
            registerNameLoginTextField, loginNameTextField;
    private JList allCounterList;
    private JButton buttonEnter, buttonRegister, buttonLogin, buttonFrontLogin, buttonFrontRegister;
    private Product product;
    private Register register;
    private SqlConnection sqlConnection;
    private JPanel panelWest;
    private JPanel panelEast;
    private JPanel panelLogin;
    private JPanel panelRegister;
    private JPanel panelFront;
    private JPasswordField registerPassword, loginPassword;

    public GuiBack(JTextField firstNameTextField, JTextField lastNameTextField, JTextField counterCountTextField,
                   JTextField counterNumberTextField, JTextField registerNameLoginTextField, JTextField loginNameTextField, JList allCounterList,
                   JButton buttonEnter, JButton buttonRegister, JButton buttonLogin, JButton buttonFrontLogin,
                   JButton buttonFrontRegister, JPanel panelWest, JPanel panelEast, JPanel panelLogin,
                   JPanel panelRegister, JPanel panelFront, JPasswordField registerPassword, JPasswordField loginPassword) {
        this.firstNameTextField = firstNameTextField;
        this.lastNameTextField = lastNameTextField;
        this.counterCountTextField = counterCountTextField;
        this.counterNumberTextField = counterNumberTextField;
        this.registerNameLoginTextField = registerNameLoginTextField;
        this.allCounterList = allCounterList;
        this.buttonEnter = buttonEnter;
        this.buttonRegister = buttonRegister;
        this.buttonLogin = buttonLogin;
        this.buttonFrontLogin = buttonFrontLogin;
        this.buttonFrontRegister = buttonFrontRegister;
        this.loginNameTextField = loginNameTextField;
        this.panelWest = panelWest;
        this.panelEast = panelEast;
        this.panelLogin = panelLogin;
        this.panelRegister = panelRegister;
        this.panelFront = panelFront;
        this.registerPassword = registerPassword;
        this.loginPassword = loginPassword;
    }

    public void buttonFrontRegisterGui() {
        panelFront.setVisible(false);
        panelRegister.setVisible(true);
    }

    public void buttonFrontLoginGui() {
        panelFront.setVisible(false);
        panelLogin.setVisible(true);
    }

    public void buttonRegisterGui() {
        register = new Register();
        sqlConnection = new SqlConnection("counter", "root", "");
        setRegister();
        Vector<Register> allRegisterListVector = register.getLogin(sqlConnection);
        DefaultListModel list = new DefaultListModel();
        for (int i = 0; i < allRegisterListVector.size(); i++) {
            list.addElement(allRegisterListVector.get(i).printLogin());
        }

        panelFront.setVisible(true);
        panelRegister.setVisible(false);
    }

    public void buttonEnterGui() {
        product = new Product();
        sqlConnection = new SqlConnection("counter", "root", "");
        setProduct();
        Vector<Product> allProductListVector = product.getProduct(sqlConnection);
        DefaultListModel list = new DefaultListModel();
        for (int i = 0; i < allProductListVector.size(); i++) {
            list.addElement(allProductListVector.get(i).printProduct());
        }
        allCounterList.setModel(list);
    }

    public void setProduct() {
        JFrame frame = new JFrame();

        Pattern patternCounterNumber = Pattern.compile("^(?=.*[0-9]).*$");
        Matcher matcherCounterNumber = patternCounterNumber.matcher(Character.getName(product.getCounterCode()));

        boolean isCounterNumberValid = matcherCounterNumber.matches();
        if (isCounterNumberValid) {

        } else {
            JOptionPane.showMessageDialog(frame, "Enter only digits");
        }

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

    public void setRegister() {
        JFrame frame = new JFrame();

        register.setLoginName(registerNameLoginTextField.getText());
        register.setPassword(registerPassword.getText());
        sqlConnection.connect();
        register.setLogin(sqlConnection, register);

        Pattern patternPassword = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).*$");
        Matcher matcherPassword = patternPassword.matcher(register.getPassword());

        boolean isPasswordValid = matcherPassword.matches();

        if (isPasswordValid) {
            JOptionPane.showMessageDialog(frame, "U have been registered");
        } else {
            JOptionPane.showMessageDialog(frame, "Enter your password with one uppercase and one number");
        }
    }

    public void buttonLoginGui() {
        PreparedStatement preparedStatement;
        Connection connection;
        JFrame frame = new JFrame();
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/counter",
                    "root", "");
            preparedStatement = connection.prepareStatement("SELECT login_name,password FROM counter_login WHERE login_name =? AND password =?");
            preparedStatement.setString(1, loginNameTextField.getText());
            preparedStatement.setString(2, String.valueOf(loginPassword.getText()));
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                panelRegister.setVisible(false);
                panelLogin.setVisible(false);
                panelFront.setVisible(false);
                panelWest.setVisible(true);
                panelEast.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(frame, "Wrong name or password");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == buttonRegister) {
            buttonRegisterGui();
        } else if (src == buttonEnter) {
            buttonEnterGui();
        } else if (src == buttonFrontRegister) {
            buttonFrontRegisterGui();
        } else if (src == buttonFrontLogin) {
            buttonFrontLoginGui();
        } else if (src == buttonLogin) {
            buttonLoginGui();
        }
    }
}
