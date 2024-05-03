import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBC_Statements extends JDBCParent {

    @Test
    public void Test1() {

        String sorgu = "SELECT * " +
                "FROM employees " +
                "INNER JOIN dept_emp ON employees.emp_no = dept_emp.emp_no " +
                "WHERE dept_no = 'D001'";
        List<List<String>> donenTablo = getListData(sorgu);
        for (List<String> satir : donenTablo) {
            for (String kolon : satir)
                System.out.print(kolon + "\t");

            System.out.println();
        }


    }

    public List<List<String>> getListData(String sorgu) {
        List<List<String>> tablo = new ArrayList<>();

        try {
            DBConnectionOpen();
            ResultSet rs = sorguEkrani.executeQuery(sorgu);
            ResultSetMetaData rsmd = rs.getMetaData();

            ArrayList<String> kolonSatiri = new ArrayList<>();
            for (int i = 1; i <= rsmd.getColumnCount(); i++)
                kolonSatiri.add(rsmd.getColumnName(i));
            tablo.add(kolonSatiri);

            while (rs.next()) {

                ArrayList<String> satir = new ArrayList<>();
                for (int i = 1; i <= rsmd.getColumnCount(); i++)
                    satir.add(rs.getString(i));

                tablo.add(satir);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            DBConnectionClose();
        }

        return tablo;
    }

    @Test
    public void Test2() {

        String sorgu = "SELECT employees.* " +
                "FROM employees " +
                "INNER JOIN dept_emp ON employees.emp_no = dept_emp.emp_no " +
                "INNER JOIN departments ON dept_emp.dept_no = departments.dept_no " +
                "WHERE departments.dept_name = 'Human Resources';";
        List<List<String>> donenTablo = getListData(sorgu);
        for (List<String> satir : donenTablo) {
            for (String kolon : satir)
                System.out.print(kolon + "\t");

            System.out.println();
        }

    }

    @Test
    public void Test3() throws SQLException {
        DBConnectionOpen();

        ResultSet rs=sorguEkrani.executeQuery("SELECT AVG(salary) AS average_salary " +
                "FROM salaries");

        if (rs.next()) {
            double avgSalary = rs.getDouble("average_salary");
            System.out.println("avgSalary = " + avgSalary);
        } else {
            System.out.println("Herhangi bir sonuç bulunamadı.");

        }

DBConnectionClose();
    }

    @Test
    public void Test4() throws SQLException {

        DBConnectionOpen();

        ResultSet rs=sorguEkrani.executeQuery("SELECT AVG(s.salary) AS average_salary " +
                "FROM employees e " +
                "INNER JOIN salaries s ON e.emp_no = s.emp_no " +
                "WHERE e.gender = 'F'");

        if (rs.next()) {
            double avgSalary = rs.getDouble("average_salary");
            System.out.println("avgSalary = " + avgSalary);
        } else {
            System.out.println("Herhangi bir sonuç bulunamadı.");

        }

        DBConnectionClose();
    }

    @Test
    public void Test5() throws SQLException {
        DBConnectionOpen();

        ResultSet rs=sorguEkrani.executeQuery("SELECT AVG(s.salary) AS average_salary " +
                "FROM employees e " +
                "INNER JOIN salaries s ON e.emp_no = s.emp_no " +
                "WHERE e.gender = 'M';");

        if (rs.next()) {
            double avgSalary = rs.getDouble("average_salary");
            System.out.println("avgSalary = " + avgSalary);
        } else {
            System.out.println("Herhangi bir sonuç bulunamadı.");

        }

        DBConnectionClose();

    }
}
