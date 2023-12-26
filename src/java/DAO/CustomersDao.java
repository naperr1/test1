/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import static DAO.DBConnect.connection;
import Model.Category;
import Model.Customers;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fattt
 */
public class CustomersDao extends DBConnect{
    //    Get All Customer
    public List<Customers> getAllCustomer() {
        List<Customers> list = new ArrayList<>();
        String sql = "select * from user";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Customers customer = new Customers(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("fullname"),
                        rs.getString("address"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getInt("isAdmin")
                        
                );
                list.add(customer);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
//    public static void main(String[] args) {
//        CustomersDao hsd = new CustomersDao();
//        List<Customers> list = hsd.getAllCustomer();
//        System.out.println(list.get(0).getUsername());
//    }
    
//    Add user
    public void addUser(Customers c){
        String sql = "insert into user values(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, c.getId());
            st.setString(2, c.getUsername());
            st.setString(3, c.getPassword());
            st.setString(4, c.getFullname());
            st.setString(5, c.getAddress());
            st.setString(6, c.getEmail());
            st.setString(7, c.getPhone());
            st.setInt(8, c.getIsAdmin());
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
//    Find user by id
    public Customers getCustomerById(int id){
        String sql = "select * from user where id=?";
        
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                Customers c = new Customers(
                  rs.getInt("id"),
                  rs.getString("username"),
                  rs.getString("password"),
                  rs.getString("fullname"),
                  rs.getString("address"),
                  rs.getString("email"),
                  rs.getString("phone"),
                  rs.getInt("isAdmin")
                );
                return c;
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    //    Delete a custormer
    public void deleteCustomer(int id){
        String sql = "DELETE FROM user WHERE id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }
    
//    Update category
    public void updateCustomer(Customers c){
        
        
        String sql = "UPDATE user SET "
                            + "id = ?,"
                            + "username = ?,"
                            + "password = ?,"
                            + "fullname = ?,"
                            + "address = ?,"
                            + "email = ?,"
                            + "phone = ?,"
                            + "isAdmin = ? "
                            + "WHERE id = ?;";
        
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, c.getId());
            st.setString(2, c.getUsername());
            st.setString(3, c.getPassword());
            st.setString(4, c.getFullname());
            st.setString(5, c.getAddress());
            st.setString(6, c.getEmail());
            st.setString(7, c.getPhone());
            st.setInt(8, c.getIsAdmin());
            st.setInt(9, c.getId());
            st.executeUpdate();
        } catch (Exception e) {
        }
    }
}
