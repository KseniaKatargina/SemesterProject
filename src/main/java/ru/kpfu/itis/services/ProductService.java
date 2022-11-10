package ru.kpfu.itis.services;

import ru.kpfu.itis.model.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class ProductService {
    public void addProducts (ArrayList<Product> products, HttpServletRequest request, HttpServletResponse response){
        request.getSession().setAttribute("products" , products);
    }

}
