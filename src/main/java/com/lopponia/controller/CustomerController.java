package com.lopponia.controller;

import com.lopponia.bean.Result;
import com.lopponia.utils.Page;
import com.lopponia.bean.BaseDict;
import com.lopponia.bean.Customer;
import com.lopponia.bean.User;
import com.lopponia.service.BaseDictService;
import com.lopponia.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private BaseDictService baseDictService;
    //客户来源
    @Value("${customer.from.type}")
    private String FROM_TYPE;
    //客户所属行业
    @Value("${customer.industry.type}")
    private String INDUSTRY_TYPE;
    //客户级别
    @Value("${customer.level.type}")
    private String LEVEL_TYPE;

    /*
    根据客户来源查询
    根据客户所属行业查询
    根据客户级别查询
    */
    @GetMapping("/customers")
    // @RequestParam仅支持formData格式
    public Result list(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer rows,
                       @RequestParam(defaultValue = "") String custName,
                       @RequestParam(defaultValue = "") String custSource,
                       @RequestParam(defaultValue = "") String custIndustry,
                       @RequestParam(defaultValue = "") String custLevel) {
        Page<Customer> customers = customerService.findCustomerList(page, rows, custName, custSource, custIndustry, custLevel);
        //客户来源
        List<BaseDict> fromType = baseDictService.findBaseDictByTypeCode(FROM_TYPE);
        //客户所属行业
        List<BaseDict> industryType = baseDictService.findBaseDictByTypeCode(INDUSTRY_TYPE);
        //客户级别
        List<BaseDict> levelType = baseDictService.findBaseDictByTypeCode(LEVEL_TYPE);
        Result result = new Result();
        System.out.println(customers.toString());
        List<Object> data = new ArrayList<>();
        data.add(customers);
        data.add(fromType);
        data.add(industryType);
        data.add(levelType);
        result.setData(data);
        return result;
    }

    @PutMapping("/customer")
    public Result customerCreate(@RequestParam String custName,
                                 @RequestParam String custSource,
                                 @RequestParam String custIndustry,
                                 @RequestParam String custLevel,
                                 @RequestParam String linkman,
                                 @RequestParam String phone,
                                 @RequestParam String mobile,
                                 @RequestParam String zipcode,
                                 @RequestParam String address) {
        //获取Session中的当前用户
//        User user = (User) session.getAttribute("USER_SESSION");
        Customer customer = new Customer();
        User user = new User();
        //将当前用户id存储在客户对象中
        System.out.println(custName);
        System.out.println(custSource);
        System.out.println(custIndustry);
        System.out.println(custLevel);
        System.out.println(linkman);
        System.out.println(phone);
        System.out.println(mobile);
        System.out.println(zipcode);
        System.out.println(address);
        customer.setCust_create_id(6);
        customer.setCust_name(custName);
        customer.setCust_source(custSource);
        customer.setCust_industry(custIndustry);
        customer.setCust_level(custLevel);
        customer.setCust_linkman(linkman);
        customer.setCust_phone(phone);
        customer.setCust_mobile(mobile);
        customer.setCust_zipcode(zipcode);
        customer.setCust_address(address);
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        customer.setCust_createtime(timestamp);
        int rows = customerService.createCustomer(customer);
        Result result = new Result();
        if (rows > 0) {
            result.setMessage("添加成功");
            return result;
        } else {
            result.setMessage("添加失败");
            return result;
        }
    }

    @GetMapping("/customer/{id}")
    public Customer getCustomerById(@PathVariable("id") Integer id) {
        return customerService.getCustomerById(id);
    }

    //    @ResponseBody
    @PostMapping("/customer")
    public Result customerUpdate(Customer customer) {
        int rows = customerService.updateCustomer(customer);
        Result result = new Result();
        if (rows > 0) {
            result.setMessage("删除成功");
            return result;
        } else {
            result.setMessage("删除失败");
            return result;
        }
    }

    @DeleteMapping("/customer/{id}")
    public String customerDelete(@PathVariable("id") Integer id) {
        int rows = customerService.delete(id);
        if (rows > 0) {
            return "OK";
        } else {
            return "FAIL";
        }
    }
}