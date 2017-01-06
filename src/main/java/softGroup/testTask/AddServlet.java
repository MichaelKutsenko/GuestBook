package softGroup.testTask;

import softGroup.testTask.objects.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.Date;

public class AddServlet extends HttpServlet {
//    static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/mydb";
    static final String DB_CONNECTION = "jdbc:mysql://gi6kn64hu98hy0b6.chr7pe7iynqr.eu-west-1.rds.amazonaws.com/wogkrtduick0q3la";

//    static final String MYSQL_PATH = "jdbc:mysql://localhost:3306";
    static final String DB_USER = "tevupndx9bm5zn59";
    static final String DB_PASSWORD = "uzx1mz9sc4xwgb65";

    static Connection conn;
    static PreparedStatement preparedStatment;

    static boolean isDBChecked;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String fistName = getString(request, "firstName");

        String lastName = getString(request, "lastName");

        Calendar calendar = new GregorianCalendar(Integer.parseInt(request.getParameter("year")),
                Integer.parseInt(request.getParameter("month")),
                Integer.parseInt(request.getParameter("day")));
        Date bithdate = calendar.getTime();

        String phone = request.getParameter("phone");

        String email = request.getParameter("email").toLowerCase();

        String country = request.getParameter("country");

        String city = getString(request, "city");

        try {
            if (!isDBChecked) {
                createDB();
                isDBChecked = true;
            }

            addPersonToDB(fistName, lastName, bithdate, phone, email, country, city);
            List<Person> persons = getUsers();

            request.setAttribute("persons", persons);
            request.getRequestDispatcher("/WEB-INF/pages/result.jsp").forward(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();

            request.setAttribute("message", "ooops.. .something's wrong. try again later.");
            request.getRequestDispatcher("/WEB-INF/pages/index.jsp").forward(request, response);
        }
    }

    private String getString(HttpServletRequest request, String param) {
        StringBuilder stringBuilder = new StringBuilder();

        String string = request.getParameter(param).trim().toLowerCase();
        stringBuilder.append(Character.toUpperCase(string.charAt(0)))
                .append(string.substring(1));
        string = stringBuilder.toString();

        return string;
    }

    private void addPersonToDB(String fistName, String lastName, Date bithdate, String phone, String email,
                               String country, String city) throws SQLException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);

            preparedStatment = conn.prepareStatement("INSERT INTO Users " +
                    "(first_name, lastt_name, bithdate, phone, email, country, city) VALUES(?, ?, ?, ?, ?, ?, ?)");

            preparedStatment.setString(1, fistName);
            preparedStatment.setString(2, lastName);
            preparedStatment.setDate(3, new java.sql.Date(bithdate.getTime()));
            preparedStatment.setString(4, phone);
            preparedStatment.setString(5, email);
            preparedStatment.setString(6, country);
            preparedStatment.setString(7, city);
            preparedStatment.executeUpdate();
        } finally {
            if (preparedStatment != null) preparedStatment.close();
            if (conn != null) conn.close();
        }
    }

    public List<Person> getUsers() throws SQLException, ClassNotFoundException {

        List<Person> users = new ArrayList<>();

        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);

            preparedStatment = conn.prepareStatement("SELECT * FROM Users");

            rs = preparedStatment.executeQuery();

            while (rs.next()) {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("lastt_name");
                Date bithdate = rs.getDate("bithdate");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String country = rs.getString("country");
                String city = rs.getString("city");

                users.add(new Person(firstName, lastName, bithdate, phone, email, country, city));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (preparedStatment != null) preparedStatment.close();
            if (conn != null) conn.close();
        }

        return users;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if (!isDBChecked) {
                createDB();
                isDBChecked = true;
            }

            List<Person> persons = getUsers();

            request.setAttribute("persons", persons);
            request.getRequestDispatcher("/WEB-INF/pages/result.jsp").forward(request, response);
        } catch (SQLException | ClassNotFoundException | ArithmeticException e) {
            e.printStackTrace();

            request.setAttribute("message", "ooops.. .something's wrong. try again later.");
            request.getRequestDispatcher("/WEB-INF/pages/index.jsp").forward(request, response);
        }
    }

    private void createDB() throws ClassNotFoundException, SQLException, IOException {

        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(
                DB_CONNECTION, DB_USER, DB_PASSWORD);

        try {
            String delimiter = ";";
            Scanner scanner = new Scanner(new File(getServletContext().getRealPath("script.sql"))).useDelimiter(delimiter);


            Statement statement = null;
            while (scanner.hasNext()) {

                String rawStatement = scanner.next() + delimiter;
                try {
                    statement = conn.createStatement();
                    statement.execute(rawStatement);
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    if (statement != null) statement.close();
                }
            }
        } finally {
            if (conn != null) conn.close();
        }
    }
}

