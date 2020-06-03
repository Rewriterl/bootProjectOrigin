package com.lopponia.dao;

import com.lopponia.bean.Customer;

import java.util.List;

public interface CustomerDao {
    //客户列表
    public List<Customer> selectCustomerList(Customer customer);
    //客户数
    public Integer selectCustomerListCount(Customer customer);
    public int createCustomer(Customer customer);
    public Customer getCustomerById(Integer id);
    public int updateCustomer(Customer customer);
    public int delete(Integer id);
}
