import java.sql.*;


public class Main {
    public static void main(String[] args){

        String driver = "org.mariadb.jdbc.Driver";
        String url = "jdbc:mariadb://127.0.0.1:3306/mydb";
        String user = "root";
        String pwd = "tmdals123";
        Connection conn;

        try {

            Class.forName(driver);
            conn = DriverManager.getConnection(url,user,pwd);

            insert(conn,"insert into book values(101, 'DBP 실습','강원대학교',35000)");
            insert(conn,"insert into book values(102, 'JDBC 실습','강원대학교',28000)");
            insert(conn,"insert into book values(103, 'DBP 프로젝트','강원대학교',25000)");

            select(conn,"select * from book where bookid > 100");

            update(conn,"update book set bookname = '데이터베이스 실습', price = 30000 where bookid = 101");
            update(conn,"update book set bookname = 'JDBC 프로그래밍', price = 20000 where bookid = 102");
            update(conn,"update book set bookname = '데이터베이스프로그래밍 프로젝트', price = 15000 where bookid = 103");

            select(conn,"select * from book where bookid > 100");

            delete(conn,"delete from book where bookid > 100");

            conn.close();

        } catch (ClassNotFoundException | SQLException exception){
            exception.printStackTrace();
        }
    }

    protected static void select(Connection conn, String sql) {
        try {

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                System.out.println("Bookid : " + rs.getInt("bookid") +
                        ", Bookname : " + rs.getString("bookname") +
                        ", Publisher : " + rs.getString("publisher") +
                        ", Price : " + rs.getInt("price"));
            }

            stmt.close();

        } catch (SQLException exception){
            exception.printStackTrace();
        }
    }

    protected static void insert(Connection conn, String sql){
        try{
            Statement stmt = conn.createStatement();

            int count = stmt.executeUpdate(sql);
            System.out.println(count + " row inserted");

            stmt.close();

        }catch (SQLException exception){
            exception.printStackTrace();
        }
    }

    protected static void delete(Connection conn, String sql){
        try{
            Statement stmt = conn.createStatement();
            int count = stmt.executeUpdate(sql);
            System.out.println(count + " row deleted");

            stmt.close();

        }catch (SQLException exception){
            exception.printStackTrace();
        }
    }

    protected static void update(Connection conn, String sql){
        try{
            Statement stmt = conn.createStatement();
            int count = stmt.executeUpdate(sql);
            System.out.println(count + " row updated");

            stmt.close();

        }catch (SQLException exception){
            exception.printStackTrace();
        }
    }

}
