import org.testng.annotations.Test;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBC_Statement extends JDBCParent {

    @Test
    public void Test11() throws SQLException {

        DBConnectionOpen();
        ResultSet rs = sorguEkrani.executeQuery("select emp_no, salary, to_date " +
                "from salaries " +
                "where emp_no = '10102' " +
                "order by to_date");

        while (rs.next()) {
            int empNo = rs.getInt("emp_no");
            int salary = rs.getInt("salary");
            Date toDate = rs.getDate("to_date");

            System.out.println("Employee Number: " + empNo +
                    ", Salary: " + salary +
                    ", To Date: " + toDate);
        }
        DBConnectionClose();
    }

    @Test
    public void Test12() {
        String sorgu = "select * from employees.employees " +
                "left join employees.salaries ON employees.emp_no=employees.salaries.emp_no " +
                "order by salary desc " +
                "limit 1 ";
        List<List<String>> donenTablo = getListData(sorgu);
        //System.out.println(donenTablo);
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
    public void Test13() throws SQLException {
        DBConnectionOpen();
        ResultSet rs = sorguEkrani.executeQuery("SELECT emp_no, salary, to_date " +
                "FROM salaries " +
                "WHERE (emp_no, to_date) IN (SELECT emp_no, MAX(to_date) FROM salaries GROUP BY emp_no)");

        while (rs.next()) {
            int empNo = rs.getInt("emp_no");
            int salary = rs.getInt("salary");

            System.out.println("Employee Number: " + empNo +
                    ", Salary: " + salary);
        }

        DBConnectionClose();
    }

    @Test
    public void Test14() throws SQLException {

        DBConnectionOpen();

        ResultSet rs = sorguEkrani.executeQuery("SELECT e.first_name, e.last_name, MAX(s.salary) AS highest_salary " +
                "FROM employees e " +
                "INNER JOIN dept_emp de ON e.emp_no = de.emp_no " +
                "INNER JOIN departments d ON de.dept_no = d.dept_no " +
                "INNER JOIN salaries s ON e.emp_no = s.emp_no " +
                "WHERE d.dept_name = 'Sales' " +
                "GROUP BY e.first_name, e.last_name " +
                "ORDER BY highest_salary DESC " +
                "LIMIT 1");

        while (rs.next()) {

            String firstname = rs.getString("first_name");
            String lastname = rs.getString("last_name");
            int highestSalary = rs.getInt("highest_salary");

            System.out.println("firstname: " + firstname + ",lastname: " + lastname +
                    ",highestSalary: " + highestSalary);
        }


        DBConnectionClose();
    }


    @Test
    public void Test15() throws SQLException {

        DBConnectionOpen();
        ResultSet rs= sorguEkrani.executeQuery("select employees.employees.first_name,employees.employees.last_name,avg(employees.salaries.salary) as average_salary " +
                "from employees.employees " +
                "left join employees.dept_emp ON employees.emp_no=dept_emp.emp_no " +
                "left join employees.departments ON dept_emp.dept_no=departments.dept_no " +
                "left join employees.salaries ON employees.emp_no=employees.salaries.emp_no " +
                "where employees.departments.dept_name= 'Research' " +
                "group by employees.first_name, employees.last_name " +
                "order by average_salary desc " +
                "limit 1");

        if(rs.next()){
            String firstname= rs.getString("first_name");
            String lastname= rs.getString("last_name");
            double avgSalary= rs.getDouble("average_salary");

            System.out.println("firstname: " + firstname + ",lastname: " + lastname +
                    ",avgSalary: " + avgSalary);
        }


        DBConnectionClose();
    }

}





