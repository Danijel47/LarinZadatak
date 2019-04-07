import java.util.ArrayList;
import java.util.Vector;

public class Register {

    private int id;
    private String loginName;
    private String password;

    public Register() {
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

    public Vector<Register> getLogin(SqlConnection sqlConnection) {

        ArrayList<ArrayList<String>> loginList = sqlConnection.getData("SELECT * FROM counter_login");
        Vector<Register> listOfAllRegister = new Vector<>();

        for (int i = 0; i < loginList.size(); i++) {
            ArrayList<String> loginResult = loginList.get(i);
            Register newRegister = new Register();
            for (int j = 0; j < loginResult.size(); j++) {
                newRegister.setId(Integer.valueOf(loginResult.get(0)));
                newRegister.setLoginName(loginResult.get(1));
                newRegister.setPassword(loginResult.get(2));
            }
            listOfAllRegister.add(newRegister);
        }
        return listOfAllRegister;
    }

    public String setLogin(SqlConnection sqlConnection, Register register) {
        System.out.println(register.printLogin());
        Boolean isLoginAdded = sqlConnection.setData("INSERT INTO counter_login(login_name, password) VALUES ('" + register.getLoginName() + "','" + register.getPassword() + "')");
        if (isLoginAdded){
            return "Register is added";
        }else{
            return "Register hasn't been added";
        }
    }

    public String printLogin(){
        return "ID: "+getId()+" Register name: "+getLoginName()+" Password: "+getPassword();
    }

}
