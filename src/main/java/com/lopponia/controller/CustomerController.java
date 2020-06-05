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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
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

    //客户列表,分页处理
    @GetMapping("/customers")
    public String list(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer rows,
                       String custName, String custSource, String custIndustry, String custLevel, Model model) {
        Page<Customer> customers = customerService.findCustomerList(page, rows, custName, custSource, custIndustry, custLevel);
        model.addAttribute("page", customers);
        //客户来源
        List<BaseDict> fromType = baseDictService.findBaseDictByTypeCode(FROM_TYPE);
        //客户所属行业
        List<BaseDict> industryType = baseDictService.findBaseDictByTypeCode(INDUSTRY_TYPE);
        //客户级别
        List<BaseDict> levelType = baseDictService.findBaseDictByTypeCode(LEVEL_TYPE);
        //添加参数
//        model.addAttribute("fromType", fromType);
//        model.addAttribute("industryType", industryType);
//        model.addAttribute("levelType", levelType);
//        model.addAttribute("custName", custName);
//        model.addAttribute("custSource", custSource);
//        model.addAttribute("custIndustry", custIndustry);
//        model.addAttribute("custLevel", custLevel);
        System.out.println(fromType);
        System.out.println(industryType);
        System.out.println(levelType);
        System.out.println(custName);
        System.out.println(custSource);
        System.out.println(custIndustry);
        System.out.println(custLevel);
        return "customer";
    }

    @PutMapping("/customer")
    public String customerCreate(Customer customer, HttpSession session) {
        //获取Session中的当前用户
//        User user = (User) session.getAttribute("USER_SESSION");
        User user = new User();
        //将当前用户id存储在客户对象中
        customer.setCust_create_id(user.getUser_id());
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        customer.setCust_createtime(timestamp);
        int rows = customerService.createCustomer(customer);
        if (rows > 0) {
            return "OK";
        } else {
            return "FAIL";
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