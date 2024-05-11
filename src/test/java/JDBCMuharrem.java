import org.testng.annotations.Test;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class JDBCMuharrem extends JDBCParentClass {

    @Test
    public void Test13() throws SQLException {
        DBConnectionOpen();
        String query = "SELECT emp_no, salary FROM salaries WHERE (emp_no, from_date) IN (SELECT emp_no, MAX(from_date) FROM salaries GROUP BY emp_no)";
        ResultSet rs = sorguEkrani.executeQuery(query);
        System.out.println("Test 13: Her çalışanın en son maaşlarını bul");
        while (rs.next()) {
            int empNo = rs.getInt("emp_no");
            int salary = rs.getInt("salary");
            System.out.println("Employee Number: " + empNo + ", Salary: " + salary);
        }
        DBConnectionClose();
    }

    @Test
    public void Test14() throws SQLException {
        DBConnectionOpen();
        String query = "SELECT e.first_name, e.last_name, MAX(s.salary) AS highest_salary FROM employees e INNER JOIN dept_emp de ON e.emp_no = de.emp_no INNER JOIN departments d ON de.dept_no = d.dept_no INNER JOIN salaries s ON e.emp_no = s.emp_no WHERE d.dept_name = 'Sales' GROUP BY e.first_name, e.last_name ORDER BY highest_salary DESC LIMIT 1";
        ResultSet rs = sorguEkrani.executeQuery(query);
        System.out.println("Test 14: 'Satış' departmanındaki en yüksek maaşı olan çalışan");
        if (rs.next()) {
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            int highestSalary = rs.getInt("highest_salary");
            System.out.println("First Name: " + firstName + ", Last Name: " + lastName + ", Highest Salary: " + highestSalary);
        }
        DBConnectionClose();
    }

    @Test
    public void Test15() throws SQLException {
        DBConnectionOpen();
        String query = "SELECT e.first_name, e.last_name, AVG(s.salary) AS average_salary FROM employees e JOIN dept_emp de ON e.emp_no = de.emp_no JOIN departments d ON de.dept_no = d.dept_no JOIN salaries s ON e.emp_no = s.emp_no WHERE d.dept_name = 'Research' GROUP BY e.first_name, e.last_name ORDER BY average_salary DESC LIMIT 1";
        ResultSet rs = sorguEkrani.executeQuery(query);
        System.out.println("Test 15: Araştırma Departmanındaki Ortalama En Yüksek Maaşlı Çalışan");
        if (rs.next()) {
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            double averageSalary = rs.getDouble("average_salary");
            System.out.println("First Name: " + firstName + ", Last Name: " + lastName + ", Average Salary: " + averageSalary);
        }
        DBConnectionClose();
    }

    @Test
    public void Test16() throws SQLException {
        DBConnectionOpen();
        String query = "SELECT d.dept_name, e.first_name, e.last_name, MAX(s.salary) AS peak_salary FROM departments d JOIN employees e ON d.dept_no = e.dept_no JOIN salaries s ON e.emp_no = s.emp_no GROUP BY d.dept_name ORDER BY peak_salary DESC";
        ResultSet rs = sorguEkrani.executeQuery(query);
        System.out.println("Test 16: Her departman için kaydedilmiş en yüksek tek maaş");
        while (rs.next()) {
            String deptName = rs.getString("dept_name");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            int peakSalary = rs.getInt("peak_salary");
            System.out.println("Dept Name: " + deptName + ", First Name: " + firstName + ", Last Name: " + lastName + ", Peak Salary: " + peakSalary);
        }
        DBConnectionClose();
    }

    @Test
    public void Test17() throws SQLException {
        DBConnectionOpen();
        String query = "SELECT d.dept_name, e.first_name, e.last_name, AVG(s.salary) AS average_salary FROM departments d JOIN employees e ON d.dept_no = e.dept_no JOIN salaries s ON e.emp_no = s.emp_no GROUP BY d.dept_name, e.emp_no ORDER BY average_salary DESC";
        ResultSet rs = sorguEkrani.executeQuery(query);
        System.out.println("Test 17: Her departmandaki en yüksek ortalama maaşa sahip çalışanlar");
        while (rs.next()) {
            String deptName = rs.getString("dept_name");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            double averageSalary = rs.getDouble("average_salary");
            System.out.println("Dept Name: " + deptName + ", First Name: " + firstName + ", Last Name: " + lastName + ", Average Salary: " + averageSalary);
        }
        DBConnectionClose();
    }

    @Test
    public void Test18() throws SQLException {
        DBConnectionOpen();
        String query = "SELECT first_name, last_name, hire_date FROM employees WHERE hire_date < '1990-01-01' ORDER BY first_name, last_name";
        ResultSet rs = sorguEkrani.executeQuery(query);
        System.out.println("Test 18: 1990-01-01 tarihinden önce işe alınan tüm çalışanların adlarını, soyadlarını ve işe alınma tarihlerini listele");
        while (rs.next()) {
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            Date hireDate = rs.getDate("hire_date");
            System.out.println("First Name: " + firstName + ", Last Name: " + lastName + ", Hire Date: " + hireDate);
        }
        DBConnectionClose();
    }

    @Test
    public void Test19() throws SQLException {
        DBConnectionOpen();
        String query = "SELECT first_name, last_name, hire_date FROM employees WHERE hire_date BETWEEN '1985-01-01' AND '1989-12-31' ORDER BY hire_date";
        ResultSet rs = sorguEkrani.executeQuery(query);
        System.out.println("Test 19: 1985-01-01 ile 1989-12-31 tarihleri arasında işe alınan tüm çalışanların adlarını, soyadlarını ve işe alınma tarihlerini listele");
        while (rs.next()) {
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            Date hireDate = rs.getDate("hire_date");
            System.out.println("First Name: " + firstName + ", Last Name: " + lastName + ", Hire Date: " + hireDate);
        }
        DBConnectionClose();
    }

    @Test
    public void Test20() throws SQLException {
        DBConnectionOpen();
        String query = "SELECT e.first_name, e.last_name, e.hire_date, s.salary FROM employees e JOIN salaries s ON e.emp_no = s.emp_no JOIN departments d ON e.dept_no = d.dept_no WHERE e.hire_date BETWEEN '1985-01-01' AND '1989-12-31' AND d.dept_name = 'Sales' ORDER BY s.salary DESC";
        ResultSet rs = sorguEkrani.executeQuery(query);
        System.out.println("Test 20: 1985-01-01 ile 1989-12-31 tarihleri arasında işe alınan Satış departmanındaki tüm çalışanların adlarını, soyadlarını, işe alınma tarihlerini ve maaşlarını listele");
        while (rs.next()) {
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            Date hireDate = rs.getDate("hire_date");
            int salary = rs.getInt("salary");
            System.out.println("First Name: " + firstName + ", Last Name: " + lastName + ", Hire Date: " + hireDate + ", Salary: " + salary);
        }
        DBConnectionClose();
    }
}

