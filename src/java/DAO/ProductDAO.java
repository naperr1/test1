/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import static DAO.DBConnect.connection;
import Model.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fattt
 */
public class ProductDAO extends DBConnect{
//    Get All Product
    public List<Product> getAllProduct(){
        List<Product> list = new ArrayList<>();
        String sql = "select * from product";
        
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("categoryId"),
                        rs.getString("image"),
                        rs.getInt("quantity"),
                        rs.getDouble("price"),
                        rs.getString("description")
                );
                list.add(product);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
//    public static void main(String[] args) {
//        ProductDAO hsd = new ProductDAO();
//        List<Product> list = hsd.getAllProduct();
//        System.out.println(list.get(0).getName());
//    }
    
//    Add product
    public void addProduct(Product p){
        String sql = "insert into product values(?,?,?,?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, p.getId());
            st.setString(2, p.getName());
            st.setString(3, p.getCategoryId());
            st.setString(4, p.getImage());
            st.setInt(5, p.getQuatity());
            st.setDouble(6, p.getPrice());
            st.setString(7, p.getDescription());
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
//    Get Product By Id
    public Product getProductById(int id){
        String sql = "select * from product where id=?";
        
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                Product p = new Product(
                 rs.getInt("id"),
                rs.getString("name"),
                rs.getString("categoryId"),
                rs.getString("image"),
                rs.getInt("quantity"),
                rs.getDouble("price"),
                rs.getString("description")    
                );
                return p;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
//    Delete product
    public void deleteProduct(int id){
        String sql = "DELETE FROM product WHERE id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
//    Update product
    public void updateProduct(Product p){
        String sql = "UPDATE product SET "
            + "name = ?,"
            + "categoryId = ?,"
            + "image = ?,"
            + "quantity = ?,"
            + "price = ?,"
            + "description = ?"
            + "WHERE id = ?;";
        
        try {
        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1, p.getName());
        st.setString(2, p.getCategoryId());
        st.setString(3, p.getImage());
        st.setInt(4, p.getQuatity());
        st.setDouble(5, p.getPrice());
        st.setString(6, p.getDescription());
        st.setInt(7, p.getId());

        st.executeUpdate();
    } catch (Exception e) {
        // Xử lý ngoại lệ (exception) nếu có
        e.printStackTrace();
    }
    }
}
