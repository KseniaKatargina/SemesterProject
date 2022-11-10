package ru.kpfu.itis.dao;

import ru.kpfu.itis.exceptions.DBException;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.services.GetYearsService;
import ru.kpfu.itis.util.ConnectionProvider;

import java.sql.*;

public class UserRepositoryDBImpl {
    private ConnectionProvider connectionProvider;

    public UserRepositoryDBImpl(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    //language=SQL
    private static final String SQL_INSERT_USER = "insert into users(username, email, password, birthday) " +
            "values (?,?, ?, ?)";

    //language=SQL
    private static final String SQL_FIND_USER_BY_EMAIL = "select * from users where email = ?";

    //language=SQL
    private static final String SQL_FIND_REGISTER_USER = "select * from users where email=(?) and password=(?)";

    //language=SQL
    private final static String SQL_UPDATE_USER = "update users set username = (?), email = (?), password = (?), birthday = (?) where id = (?)";

    public void saveUser(User user) throws DBException {

        try ( PreparedStatement statement = this.connectionProvider.getConnection().prepareStatement(SQL_INSERT_USER, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1,user.getUsername());
            statement.setString(2,user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getBirthday());


            int affectedRows = statement.executeUpdate();

            if (affectedRows != 1) {
                throw new DBException("Can't save user");
            }

            ResultSet generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next()) {
                user.setId(generatedKeys.getLong("id"));
            } else {
                throw new DBException("Can't obtain generated key");
            }

        } catch (DBException | SQLException e) {
            throw new DBException("Can't save user", e);
        }
    }

    public boolean findByEmail(String email) throws DBException {
        boolean isAlreadyLogin;
        try(PreparedStatement ps = this.connectionProvider.getConnection().prepareStatement(SQL_FIND_USER_BY_EMAIL)) {
            ps.setString(1,email);
            ResultSet result = ps.executeQuery();
            isAlreadyLogin = result.next();
        }catch (SQLException e) {
            throw new DBException("Can't find user", e);
        }
        return isAlreadyLogin;
    }

    public boolean isLogin(User user) throws DBException {
        boolean isLogin;
        try(PreparedStatement ps = this.connectionProvider.getConnection().prepareStatement(SQL_FIND_REGISTER_USER)) {
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ResultSet result = ps.executeQuery();
            isLogin = result.next();
        }catch (SQLException e) {
            throw new DBException("User doesn't exist", e);
        }
        return isLogin;
    }

    public User userByEmail(String email) throws DBException {
        User user = null;
        try(PreparedStatement ps = this.connectionProvider.getConnection().prepareStatement(SQL_FIND_USER_BY_EMAIL)) {
            ps.setString(1,email);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                Long id = result.getLong(1);
                String username = result.getString(2);
                String userEmail = result.getString(3);
                String password = result.getString(4);
                String birthday = result.getString(5);
                int years = GetYearsService.getYears(birthday);
                user = new User(id,username,userEmail,password, birthday, years);
            }
        }catch (SQLException e) {
            throw new DBException("Can't create user" , e);
        }
        return user;
    }

    public void updateUser(User user) throws DBException {
        try (PreparedStatement statement = this.connectionProvider.getConnection().prepareStatement(SQL_UPDATE_USER)) {
            statement.setString(1,user.getUsername());
            statement.setString(2,user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getBirthday());
            statement.setLong(5,user.getId());
            int affectedRows = statement.executeUpdate();

            if (affectedRows != 1) {
                throw new DBException("Can't update user");
            }
        } catch (SQLException | DBException e) {
            throw new DBException("Can't update user" , e);
        }
    }
}
