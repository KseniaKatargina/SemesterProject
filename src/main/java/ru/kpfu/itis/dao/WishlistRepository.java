package ru.kpfu.itis.dao;

import ru.kpfu.itis.exceptions.DBException;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.model.Wishlist;

import java.util.ArrayList;

public interface WishlistRepository {
    ArrayList<Wishlist> getUsersLists(User user) throws DBException;
    void addList(Wishlist wishlist) throws DBException;
    String getTitle(long id) throws DBException;
    boolean isProductInList(Wishlist wishlist, long prodID) throws DBException;
    void addProductInListEntry(long listID, long prodID) throws DBException;
    void deleteList(long listID) throws DBException;
    void updateTitle(String title, long listID, long userID) throws DBException;
}
