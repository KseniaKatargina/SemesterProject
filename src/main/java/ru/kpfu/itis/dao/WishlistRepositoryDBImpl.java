package ru.kpfu.itis.dao;

import ru.kpfu.itis.exceptions.DBException;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.model.Wishlist;
import ru.kpfu.itis.util.ConnectionProvider;

import java.sql.*;
import java.util.ArrayList;

public class WishlistRepositoryDBImpl implements WishlistRepository{
    private ConnectionProvider connectionProvider;

    public WishlistRepositoryDBImpl(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    //language=SQL
    private static final String SQL_SELECT_USERS_LISTS = "select * from  wishlists where user_id = (?)";

    //language=SQL
    private static final String SQL_ADD_LIST = "insert into wishlists (user_id,title) values (?,?)";

    //language=SQL
    private static final String SQL_SELECT_TITLE = "select title from wishlists where id = (?)";

    //language=SQL
    private static final String SQL_SELECT_PRODUCT = "select * from wishlists_entry where list_id = (?) and product_id = (?)";

    //language=SQL
    private static final String SQL_INSERT_INTO_LISTS_ENTRY = "insert into wishlists_entry(list_id, product_id)  values (?,?)";

    //language=SQL
    private static final String SQL_DELETE_LIST = "delete from wishlists where id = (?)";

    //language=SQL
    private static final  String SQL_UPDATE_TITLE = "update wishlists set title = (?) where id = (?) and user_id = (?)";

    @Override
    public ArrayList<Wishlist> getUsersLists(User user) throws DBException {
        ArrayList<Wishlist> wishlists = new ArrayList<>();
        try (PreparedStatement statement = this.connectionProvider.getConnection().prepareStatement(SQL_SELECT_USERS_LISTS)) {
            statement.setLong(1, user.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                int userId = resultSet.getInt(2);
                String title = resultSet.getString(3);
                Wishlist wishlist = new Wishlist(id,userId,title);
                wishlists.add(wishlist);
            }
        } catch (SQLException e) {
            throw new DBException("Can't select lists" , e);
        }
        return wishlists;
    }

    @Override
    public void addList(Wishlist wishlist) throws DBException {
        try (PreparedStatement statement = this.connectionProvider.getConnection().prepareStatement(SQL_ADD_LIST, Statement.RETURN_GENERATED_KEYS)) {

            statement.setLong(1,wishlist.getUser_id());
            statement.setString(2,wishlist.getTitle());

            int affectedRows = statement.executeUpdate();

            if (affectedRows != 1) {
                throw new DBException("Can't add list");
            }

            ResultSet generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next()) {
                wishlist.setId(generatedKeys.getLong("id"));
            } else {
                throw new DBException("Can't obtain generated key");
            }

        } catch (SQLException | DBException e) {
            throw new DBException("Can't add list", e);
        }
    }


    @Override
    public String getTitle(long id) throws DBException {
        String title = null;
        try (PreparedStatement statement = this.connectionProvider.getConnection().prepareStatement(SQL_SELECT_TITLE)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                title = resultSet.getString(1);
            }
        }
        catch (SQLException e) {
            throw new DBException("Can't get title");
        }
        return title;
    }

    @Override
    public boolean isProductInList(Wishlist wishlist, long prodID) throws DBException {
        boolean isProductExist;
        try(PreparedStatement ps = this.connectionProvider.getConnection().prepareStatement(SQL_SELECT_PRODUCT)) {
            ps.setLong(1, wishlist.getId());
            ps.setLong(2,prodID);
            ResultSet result = ps.executeQuery();
            isProductExist = result.next();
        }catch (SQLException e) {
            throw new DBException("Can't find list" , e);
        }
        return isProductExist;
    }

    @Override
    public void addProductInListEntry(long listID, long prodID) throws DBException {
        try (PreparedStatement statement = this.connectionProvider.getConnection().prepareStatement(SQL_INSERT_INTO_LISTS_ENTRY, Statement.RETURN_GENERATED_KEYS)) {

            statement.setLong(1,listID);
            statement.setLong(2,prodID);


            int affectedRows = statement.executeUpdate();

            if (affectedRows != 1) {
                throw new DBException("Can't add product");
            }

        } catch (SQLException | DBException e) {
            throw new DBException("Can't add product", e);
        }
    }

    @Override
    public void deleteList(long listID) throws DBException {
        try (PreparedStatement statement = this.connectionProvider.getConnection().prepareStatement(SQL_DELETE_LIST)) {
            statement.setLong(1, listID);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new DBException("Can't delete list", e);
        }
    }

    @Override
    public void updateTitle(String title, long listID, long userID) throws DBException {
        try (PreparedStatement statement = this.connectionProvider.getConnection().prepareStatement(SQL_UPDATE_TITLE)) {
            statement.setString(1, title);
            statement.setLong(2,listID);
            statement.setLong(3,userID);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new DBException("Can't update title" , e);
        }
    }
}
