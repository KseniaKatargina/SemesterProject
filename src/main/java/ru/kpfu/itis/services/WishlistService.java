package ru.kpfu.itis.services;

import ru.kpfu.itis.model.Product;
import ru.kpfu.itis.model.Wishlist;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class WishlistService {
    public void addWishlists (ArrayList<Wishlist> wishlists, HttpServletRequest request, HttpServletResponse response){
        request.getSession().setAttribute("wishlists" , wishlists);
    }

    public void addTitle(String title, HttpServletRequest request, HttpServletResponse response){
        request.getSession().setAttribute("title" , title);
    }

    public void addID(long id , HttpServletRequest request, HttpServletResponse response){
        request.getSession().setAttribute("listID", id);
    }

}
