import javax.swing.*;
import java.awt.*;

public class Gui extends JFrame {

    JTextField firstNameTextField, lastNameTextField, counterCountTextField, counterNumberTextField, registerNameLoginTextField, loginNameTextField;
    JList allCounterList;
    JButton buttonEnter, buttonRegister, buttonLogin, buttonFrontLogin, buttonFrontRegister;
    JPasswordField registerPassword, loginPassword;

    public Gui() {

        JFrame frame = new JFrame("Larin zadatak");

        firstNameTextField = new JTextField(20);
        lastNameTextField = new JTextField(20);
        counterCountTextField = new JTextField(20);
        counterNumberTextField = new JTextField(20);
        registerNameLoginTextField = new JTextField(20);
        loginNameTextField = new JTextField(20);

        registerPassword = new JPasswordField(20);
        loginPassword = new JPasswordField(20);

        JLabel firstNameLabel = new JLabel("First Name:");
        JLabel lastNameLabel = new JLabel("Last Name:");
        JLabel counterNumberLabel = new JLabel("Counter Number::");
        JLabel counterCountLabel = new JLabel("Counter count:");
        JLabel loginNameLabel = new JLabel("Enter new name:");
        JLabel loginPasswordLabel = new JLabel("Enter new password");
        JLabel registerNameLabel = new JLabel("Enter name:");
        JLabel registerPasswordLabel = new JLabel("Enter password:");

        buttonEnter = new JButton("Enter");
        buttonRegister = new JButton("Register");
        buttonLogin = new JButton("Login");
        buttonFrontRegister = new JButton("Register");
        buttonFrontLogin = new JButton("Login");

        allCounterList = new JList();

        setSize(1800, 900);
        setLayout(new BorderLayout());

        JPanel panelFront = new JPanel();
        JPanel panelWest = new JPanel();
        JPanel panelEast = new JPanel();
        JPanel panelLogin = new JPanel();
        JPanel panelRegister = new JPanel();

        panelFront.add(buttonFrontRegister);
        panelFront.add(buttonFrontLogin);


        panelRegister.add(loginNameLabel);
        panelRegister.add(registerNameLoginTextField);
        panelRegister.add(loginPasswordLabel);
        panelRegister.add(registerPassword);
        panelRegister.add(buttonRegister);

        panelLogin.add(registerNameLabel);
        panelLogin.add(loginNameTextField);
        panelLogin.add(registerPasswordLabel);
        panelLogin.add(loginPassword);
        panelLogin.add(buttonLogin);

        panelFront.setVisible(true);
        panelEast.setVisible(false);
        panelWest.setVisible(false);
        panelLogin.setVisible(false);
        panelRegister.setVisible(false);

        panelWest.add(firstNameLabel);
        panelWest.add(firstNameTextField);

        panelWest.add(lastNameLabel);
        panelWest.add(lastNameTextField);

        panelWest.add(counterNumberLabel);
        panelWest.add(counterCountTextField);

        panelWest.add(counterCountLabel);
        panelWest.add(counterNumberTextField);

        panelWest.add(buttonEnter);

        panelEast.add(allCounterList);

        add(panelLogin, BorderLayout.LINE_START);

        add(panelRegister, BorderLayout.LINE_END);

        add(panelWest, BorderLayout.PAGE_START);

        add(panelEast, BorderLayout.PAGE_END);

        add(panelFront, BorderLayout.CENTER);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GuiBack guiBack = new GuiBack(firstNameTextField, lastNameTextField, counterNumberTextField, counterCountTextField, registerNameLoginTextField, loginNameTextField, allCounterList, buttonEnter, buttonRegister, buttonLogin, buttonFrontLogin, buttonFrontRegister, panelWest, panelEast, panelLogin, panelRegister, panelFront, registerPassword, loginPassword);
        buttonEnter.addActionListener(guiBack);
        buttonRegister.addActionListener(guiBack);
        buttonLogin.addActionListener(guiBack);
        buttonFrontRegister.addActionListener(guiBack);
        buttonFrontLogin.addActionListener(guiBack);
    }


}
