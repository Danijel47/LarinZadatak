import java.sql.*;
import java.util.ArrayList;

public class SqlConnection {

    String dbName;
    String userName;
    String password;

    Connection conn = null;
    Statement statement = null;

    public SqlConnection(String dbName, String userName, String password) {
        this.dbName = dbName;
        this.userName = userName;
        this.password = password;
    }

    public boolean connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+dbName, userName, password);
            System.out.println("Connected");
            return true;
        } catch (Exception e) {
            System.out.println("Connection error");
            return false;
        }
    }

    public void disconnect() {
        try {
            conn.close();
            System.out.println("Disconnected");
        } catch (Exception e) {
            System.out.println("Disconnect Error");
        }
    }

    public ArrayList<ArrayList<String>> getData(String query) {
        ArrayList<ArrayList<String>> getDataQuery = new ArrayList<ArrayList<String>>();

        try {
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                ArrayList<String> column = new ArrayList<>();
                int columnCount = resultSet.getMetaData().getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    column.add(resultSet.getString(i));
                }
                getDataQuery.add(column);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return getDataQuery;

    }

    public boolean setData(String query) {
        int updateQuery = 0;
        System.out.println(query);
        try {
            statement = conn.createStatement();
            updateQuery = statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e);
        }
        if (updateQuery == 0) {
            return false;
        } else {
            return true;
        }
    }


}
