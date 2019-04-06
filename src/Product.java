import java.util.ArrayList;
import java.util.Vector;

public class Product {

    private int id;
    private String firstName;
    private String lastName;
    private int counterCode;
    private double counterCount;
    private String time;

    public Product() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getCounterCode() {
        return counterCode;
    }

    public void setCounterCode(int counterCode) {
        this.counterCode = counterCode;
    }

    public double getCounterCount() {
        return counterCount;
    }

    public void setCounterCount(double counterCount) {
        this.counterCount = counterCount;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public Vector<Product> getProduct(SqlConnection sqlConnection) {

        ArrayList<ArrayList<String>> productList = sqlConnection.getData("SELECT * FROM counter_table");
        Vector<Product> listOfAllProduct = new Vector<>();

        for (int i = 0; i < productList.size(); i++) {
            ArrayList<String> productResult = productList.get(i);
            Product newProduct = new Product();
            for (int j = 0; j < productResult.size(); j++) {
                newProduct.setId(Integer.valueOf(productResult.get(0)));
                newProduct.setFirstName(productResult.get(1));
                newProduct.setLastName(productResult.get(2));
                newProduct.setCounterCode(Integer.valueOf(productResult.get(3)));
                newProduct.setCounterCount(Double.valueOf(productResult.get(4)));
                newProduct.setTime(productResult.get(5));
            }
            listOfAllProduct.add(newProduct);
        }
        return listOfAllProduct;
    }


    public String setProduct(SqlConnection sqlConnection, Product product) {
        System.out.println(product.printProduct());
        Boolean isProductAdded = sqlConnection.setData("INSERT INTO counter_table(first_name, last_name,counter_number, counter_count, time) VALUES ('" + product.getFirstName() + "','" + product.getLastName() + "','" + product.getCounterCode() + "','" + product.getCounterCount() + "','" + product.getTime() + "')");
        if (isProductAdded) {
            return "Info has been added";
        } else
            return "Info hasn't been added";
    }

    public String printProduct() {
        return "Id: " + getId() + " First Name: " + getFirstName() + " Last Name: " + getLastName() + " Counter Number: " + getCounterCode() + " Counter Counter: " + getCounterCount() + " Time: " + getTime();
    }

}
