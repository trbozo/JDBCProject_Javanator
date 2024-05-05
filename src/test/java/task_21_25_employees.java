import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class task_21_25_employees extends JDBCParentClass {
    @Test
    public void Test21() throws SQLException {

        DBConnectionOpen();
        System.out.printf("%-20s%-20s%-20s%n", "Male Count:", "Female Count:", "Total Employees:");

        // a
        Statement queryScreen;
        ResultSet rs1 = sorguEkrani.executeQuery("SELECT COUNT(gender) AS Male_Count FROM employees WHERE gender = 'M'; ");
        rs1.next();
        System.out.printf("%-20s", rs1.getString("Male_Count"));

        // b
        ResultSet rs2 = sorguEkrani.executeQuery("SELECT COUNT(gender) AS Female_Count FROM employees WHERE gender = 'F';");
        rs2.next();
        System.out.printf("%-20s", rs2.getString("Female_Count"));

        // c
        ResultSet rs3 = sorguEkrani.executeQuery("SELECT COUNT(emp_no) AS Total_Employees FROM employees; ");
        rs3.next();
        System.out.printf("%-20s", rs3.getString("Total_Employees"));
        System.out.println();

        // d
        ResultSet rs4 = sorguEkrani.executeQuery("SELECT gender, COUNT(*) AS count FROM employees GROUP BY gender;");
        while (rs4.next()) {
            String gender = rs4.getString("gender");
            String count = rs4.getString("count");
            System.out.printf("\nGender: %-11s Count: %-11s\n", gender, count);
        }
        JDBCParent.DBConnectionClose();
    }

    @Test
    public void Test22() {

        DBConnectionOpen();
        List<List<String>> returnedData1 = getListData("select count(distinct first_name)as miktar   \n" +
                "from employees");

        System.out.println("Unique First Names Numbers");
        for (List<String> row : returnedData1) {
            for (String columns : row) {
                System.out.printf("%-5s",columns);
            }
            System.out.println();
        }


        List<List<String>> returnedData2 = getListData("select count(distinct dept_name) as miktar\n" +
                "from departments");

        System.out.println("The Number Of Distinct Department Names");
        for (List<String> row : returnedData2) {
            for (String columns : row) {
                System.out.printf("%-5s",columns);
            }
            System.out.println();
        }
        JDBCParent.DBConnectionClose();
    }


    @Test
    public void Test23() throws SQLException {

        DBConnectionOpen();
        ResultSet rs = sorguEkrani.executeQuery("SELECT de.dept_no, COUNT(*) AS employee_count\n" +
                "FROM dept_emp de\n" +
                "GROUP BY de.dept_no;");
        ResultSetMetaData rsmd = rs.getMetaData();

        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            System.out.print(((ResultSetMetaData) rsmd).getColumnName(i) + "\t");
        }
        System.out.println();
        while (rs.next()) {
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                System.out.print(rs.getString(i) + "\t");
            }
            System.out.println();
        }
        JDBCParent.DBConnectionClose();
    }

    @Test
    public void Test24() {

        DBConnectionOpen();
        List<List<String>> returnedData = getListData("select  first_name,last_name,hire_date as iseAlınmaTarihi \n" +
                "from employees\n" +
                "where hire_date between '1985-01-01'and '1990-02-21'\n" +
                "order by iseAlınmaTarihi limit 100");

        for (List<String> row : returnedData) {
            for (String columns : row) {
                System.out.printf("%-15s|",columns);
            }
            System.out.println();
        }
        JDBCParent.DBConnectionClose();
    }

    @Test
    public void Test25() throws SQLException {

        DBConnectionOpen();
        ResultSet rs = sorguEkrani.executeQuery("SELECT *\n" +
                "FROM employees\n" +
                "WHERE first_name = 'Annemarie' AND last_name = 'Redmiles';");
        ResultSetMetaData rsmd = rs.getMetaData();

        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            System.out.print(rsmd.getColumnName(i) + "\t");
        }
        System.out.println();
        while (rs.next()) {
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                System.out.print(rs.getString(i) + "\t");
            }
            System.out.println();
        }
        DBConnectionClose();
    }

}

//    public static List<List<String>> getListData(String query) {
//        List<List<String>> table = new ArrayList<>();
//
//        try {
//            DBConnectionOpen();
//            ResultSet rs = queryScreen.executeQuery(query);
//            ResultSetMetaData rsmd = rs.getMetaData();
//
//            while (rs.next()) {
//
//                ArrayList<String> row = new ArrayList<>();
//                for (int i = 1; i <= rsmd.getColumnCount(); i++)
//                    row.add(rs.getString(i));
//
//
//                table.add(row);
//            }
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//        } finally {
//            DBConnectionClose();
//        }
//
//        return table;

// JDBCParentClass a eklenecek