import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

public class JDBC extends  JDBCParent_e {
    @Test
    public void Test6() throws SQLException {

        DBConnectionOpen();
        ResultSet rs=sorguEkrani.executeQuery("SELECT e.*, s.salary\n" +
                "FROM employees e\n" +
                "JOIN dept_emp de ON e.emp_no = de.emp_no\n" +
                "JOIN departments d ON de.dept_no = d.dept_no\n" +
                "JOIN salaries s ON e.emp_no = s.emp_no\n" +
                "WHERE d.dept_name = 'Sales' AND s.salary > 70000;");

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

    @Test
    public void Test7() throws SQLException {
        DBConnectionOpen();
 
        List<List<String>> returnedData = getListData("SELECT e.*\n" +
                "FROM employees e\n" +
                "INNER JOIN salaries s ON e.emp_no = s.emp_no\n" +
                "WHERE s.salary BETWEEN 50000 AND 100000;");

        for (List<String> row : returnedData) {
            for (String columns : row) {
                System.out.printf("%-10s", columns);
            }
            System.out.println();
        }
        DBConnectionClose();

    }
    @Test
    public void Test8() throws SQLException {

        DBConnectionOpen();
        ResultSet rs = sorguEkrani.executeQuery("SELECT de.dept_no, AVG(s.salary) AS average_salary\n" +
                "FROM dept_emp de\n" +
                "JOIN salaries s ON de.emp_no = s.emp_no\n" +
                "GROUP BY de.dept_no;");

        ResultSetMetaData rsmd = rs.getMetaData();

        System.out.println("Calculated By Department Number: \n ");
        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            System.out.printf("%-10s", rsmd.getColumnName(i));
        }
        System.out.println();

        while (rs.next()) {
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                System.out.printf("%-10s", rs.getString(i));
            }
            System.out.println();
        }

        System.out.println("\nCalculated By Department Name: \n ");
        ResultSet rs2 = sorguEkrani.executeQuery("SELECT dept_name, AVG(salary) AS avg_salary\n" +
                "FROM departments\n" +
                "JOIN dept_emp ON departments.dept_no = dept_emp.dept_no\n" +
                "JOIN salaries ON dept_emp.emp_no = salaries.emp_no\n" +
                "GROUP BY departments.dept_no;");

        ResultSetMetaData rsmd2 = rs2.getMetaData();

        for (int i = 1; i <= rsmd2.getColumnCount(); i++) {
            System.out.printf("%-20s", rsmd2.getColumnName(i));
        }
        System.out.println();

        while (rs2.next()) {
            for (int i = 1; i <= rsmd2.getColumnCount(); i++) {
                System.out.printf("%-20s", rs2.getString(i));
            }
            System.out.println();
        }
        DBConnectionClose();
    }

    @Test
    public void Test9() throws SQLException {

        DBConnectionOpen();
        ResultSet rs = sorguEkrani.executeQuery("SELECT d.dept_no, d.dept_name, AVG(s.salary) AS average_salary\n" +
                "FROM employees e\n" +
                "JOIN dept_emp de ON e.emp_no = de.emp_no\n" +
                "JOIN salaries s ON e.emp_no = s.emp_no\n" +
                "JOIN departments d ON de.dept_no = d.dept_no\n" +
                "GROUP BY d.dept_no, d.dept_name;");

        ResultSetMetaData rsmd = rs.getMetaData();

        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            System.out.printf("%-20s", rsmd.getColumnName(i));
        }
        System.out.println();

        while (rs.next()) {
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                System.out.printf("%-20s", rs.getString(i));
            }
            System.out.println();
        }
        DBConnectionClose();
    }

    @Test
    public void Test10() throws SQLException {

        DBConnectionOpen();
        ResultSet rs = sorguEkrani.executeQuery("SELECT emp_no, salary, from_date, to_date\n" +
                "FROM salaries\n" +
                "WHERE emp_no = '10102'\n" +
                "ORDER BY from_date;");
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