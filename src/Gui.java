import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Gui extends JFrame {

    JTextField firstNameTextField, lastNameTextField, counterCountTextField, counterNumberTextField, nameLoginTextField;
    JList allCounterList;
    JButton buttonEnter, buttonLogin;
    JPasswordField passwordField;

    public Gui(){

        JFrame frame = new JFrame("Larin zadatak");

        firstNameTextField = new JTextField(20);
        lastNameTextField = new JTextField(20);
        counterCountTextField = new JTextField(20);
        counterNumberTextField = new JTextField(20);
        nameLoginTextField = new JTextField(20);

        passwordField = new JPasswordField(20);

        JLabel firstNameLabel = new JLabel("First Name:");
        JLabel lastNameLabel = new JLabel("Last Name:");
        JLabel counterNumberLabel = new JLabel("Counter Number::");
        JLabel counterCountLabel = new JLabel("Counter count:");
        JLabel loginName = new JLabel("Enter login name:");
        JLabel loginPassword = new JLabel("Enter password");

        buttonEnter = new JButton("Enter");
        buttonLogin = new JButton("Login");

        allCounterList = new JList();

        setSize(1000,1000);
        setLayout(new BorderLayout());

        JPanel panelWest = new JPanel();
        JPanel panelEast = new JPanel();
        JPanel panelLogin = new JPanel();

        panelLogin.add(loginName);
        panelLogin.add(nameLoginTextField);
        panelLogin.add(loginPassword);
        panelLogin.add(passwordField);
        panelLogin.add(buttonLogin);

        panelEast.setVisible(false);
        panelWest.setVisible(false);

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

        add(panelLogin,BorderLayout.CENTER);

        panelLogin.setLayout(new FlowLayout());

        add(panelWest, BorderLayout.WEST);

        panelEast.setLayout(new GridLayout(1,1));

        add(panelEast, BorderLayout.EAST);

        panelWest.setLayout(new GridLayout(8,1));

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GuiBack guiBack = new GuiBack(firstNameTextField, lastNameTextField, counterNumberTextField, counterCountTextField, nameLoginTextField,allCounterList, buttonEnter, buttonLogin, panelWest, panelEast, panelLogin, passwordField);
        buttonEnter.addActionListener(guiBack);
        buttonLogin.addActionListener(guiBack);
    }


    

}
