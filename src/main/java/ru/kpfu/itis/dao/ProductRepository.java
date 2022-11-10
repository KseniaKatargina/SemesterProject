package ru.kpfu.itis.dao;

import ru.kpfu.itis.exceptions.DBException;
import ru.kpfu.itis.model.Product;

import java.util.ArrayList;

public interface ProductRepository {
    ArrayList<Product> getAllProducts() throws DBException;
    ArrayList<Long> getListProductsID(long userID, long listID) throws DBException;
    Product getProducts(long prodID) throws DBException;
    void removeProductFromList(Long productID, Long listID) throws DBException;
}
