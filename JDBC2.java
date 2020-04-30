import java.sql.*;

public class JDBC2 {
    public static void main(String[] args) {

        String driver = "org.mariadb.jdbc.Driver";
        String url = "jdbc:mariadb://127.0.0.1:3306/jdbc";
        String user = "root";
        String pwd = "tmdals123";
        Connection conn;


        try{

            Class.forName(driver);
            conn = DriverManager.getConnection(url,user,pwd);

            createTable(conn, "create table SuperOrders as " +
                    "select bookname, name, orderdate, saleprice, publisher, price " +
                    "from Orders " +
                    "left join Customer on Orders.custid = Customer.custid " +
                    "left join Book on Orders.bookid = Book.bookid");

            select(conn, "select * from SuperOrders where name = '장미란'");

            conn.close();

        }catch (ClassNotFoundException | SQLException exception){
            exception.printStackTrace();
        }

    }

    protected static void createTable(Connection conn, String sql){
        try{
            Statement stmt = conn.createStatement();

            int count = stmt.executeUpdate(sql);

            System.out.println("Table create, " + count + " row inserted");

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    protected static void select(Connection conn, String sql) {
        try {

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                System.out.println("Bookname : " + rs.getString("bookname") +
                        ", Name : " + rs.getString("name") +
                        ", Orderdate : " + rs.getDate("orderdate") +
                        ", Salesprice : " + rs.getInt("saleprice") +
                        ", Publisher : " + rs.getString("publisher") +
                        ", Price : " + rs.getInt("price"));
            }

            stmt.close();

        } catch (SQLException exception){
            exception.printStackTrace();
        }
    }
}
