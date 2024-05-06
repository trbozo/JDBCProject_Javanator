import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCParent_e {
    public static Connection baglanti;
    public static Statement sorguEkrani;

    public static void DBConnectionOpen()
    {
        String url = "jdbc:mysql://db-technostudy.ckr1jisflxpv.us-east-1.rds.amazonaws.com/employees";
        String username = "root";
        String password = "'\"-LhCB'.%k[4S]z";

        try {
            baglanti = DriverManager.getConnection(url, username, password);
            sorguEkrani = baglanti.createStatement();
        }
        catch(Exception ex)
        {
            System.out.println("ex.getMessage() = " + ex.getMessage());
        }
    }

    public static void DBConnectionClose()
    {
        try {
            baglanti.close();
        } catch (SQLException e) {
            System.out.println("ex.getMessage() = " + e.getMessage());
        }
    }
    public static Connection link;
    public static Statement queryScreen;

    public static List<List<String>> getListData(String query) {
        List<List<String>> table = new ArrayList<>();

        try {
            DBConnectionOpen();
            ResultSet rs = queryScreen.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();

            while (rs.next()) {

                ArrayList<String> row = new ArrayList<>();
                for (int i = 1; i <= rsmd.getColumnCount(); i++)
                    row.add(rs.getString(i));


                table.add(row);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            DBConnectionClose();
        }

        return table;
    }
}