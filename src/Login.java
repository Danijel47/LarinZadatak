import java.util.ArrayList;
import java.util.Vector;

public class Login {

    private int id;
    private String loginName;
    private String password;

    public Login() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Vector<Login> getLogin(SqlConnection sqlConnection) {

        ArrayList<ArrayList<String>> loginList = sqlConnection.getData("SELECT * FROM counter_login");
        Vector<Login> listOfAllLogin = new Vector<>();

        for (int i = 0; i < loginList.size(); i++) {
            ArrayList<String> loginResult = loginList.get(i);
            Login newLogin = new Login();
            for (int j = 0; j < loginResult.size(); j++) {
                newLogin.setId(Integer.valueOf(loginResult.get(0)));
                newLogin.setLoginName(loginResult.get(1));
                newLogin.setPassword(loginResult.get(2));
            }
            listOfAllLogin.add(newLogin);
        }
        return listOfAllLogin;
    }

    public String setLogin(SqlConnection sqlConnection, Login login) {
        System.out.println(login.printLogin());
        Boolean isLoginAdded = sqlConnection.setData("INSERT INTO counter_login(login_name, password) VALUES ('" + login.getLoginName() + "','" + login.getPassword() + "')");
        if (isLoginAdded){
            return "Login is added";
        }else{
            return "Login hasn't been added";
        }
    }

    public String printLogin(){
        return "ID: "+getId()+" Login name: "+getLoginName()+" Password: "+getPassword();
    }

}
