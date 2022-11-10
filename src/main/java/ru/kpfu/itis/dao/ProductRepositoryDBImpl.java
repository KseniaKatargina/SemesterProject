package ru.kpfu.itis.dao;

import ru.kpfu.itis.exceptions.DBException;
import ru.kpfu.itis.model.Product;
import ru.kpfu.itis.util.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductRepositoryDBImpl implements ProductRepository{
    private ConnectionProvider connectionProvider;

    public ProductRepositoryDBImpl(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    //language=SQL
    private static final String SQL_SELECT_ALL_PRODUCTS = "select * from products";

    //language=SQL
    private static final String SQL_SELECT_ALL_PRODUCTS_IN_LIST = "select product_id from wishlists_entry where list_id = (select id from wishlists where user_id = (?) and id= (?));";

    //language=SQL
    private  static final String SQL_SELECT_PRODUCTS = "select * from products where id = (?)";

    //language=SQL
    private  static final String SQL_DELETE_PRODUCT = "delete from wishlists_entry where product_id = (?) and list_id = (?)";

    @Override
    public ArrayList<Product> getAllProducts() throws DBException {
        ArrayList<Product> products = new ArrayList<>();
        try (PreparedStatement ps = this.connectionProvider.getConnection().prepareStatement(SQL_SELECT_ALL_PRODUCTS)) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String img = resultSet.getString(2);
                String text = resultSet.getString(3);
                Product product = new Product(id,img,text);
                products.add(product);
            }
        } catch (SQLException e) {
            throw new DBException("Can't get all products" , e);
        }
        return products;
    }

    @Override
    public ArrayList<Long> getListProductsID(long userID, long listID) throws DBException {
        ArrayList<Long> products = new ArrayList<>();
        try (PreparedStatement ps = this.connectionProvider.getConnection().prepareStatement(SQL_SELECT_ALL_PRODUCTS_IN_LIST)) {
            ps.setLong(1, userID);
            ps.setLong(2, listID);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                long id = resultSet.getInt(1);
                products.add(id);
            }
        } catch (SQLException e) {
            throw new DBException("Can't get product's ID", e);
        }
        return products;
    }

    @Override
    public Product getProducts(long prodID) throws DBException {
        Product product = new Product();
        try (PreparedStatement ps = this.connectionProvider.getConnection().prepareStatement(SQL_SELECT_PRODUCTS)) {
            ps.setLong(1,prodID);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String img = resultSet.getString(2);
                String text = resultSet.getString(3);
                product = new Product(id,img,text);
            }
        } catch (SQLException e) {
            throw new DBException("Can't get product", e);
        }
        return product;
    }

    @Override
    public void removeProductFromList(Long productID, Long listID) throws DBException {
        try (PreparedStatement ps = this.connectionProvider.getConnection().prepareStatement(SQL_DELETE_PRODUCT)) {
            ps.setLong(1,productID);
            ps.setLong(2,listID);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DBException("Can't remove product", e);
        }
    }
}
