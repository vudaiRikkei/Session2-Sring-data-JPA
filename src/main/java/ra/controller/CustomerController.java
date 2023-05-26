package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ra.model.Customer;
import ra.service.ICustomerService;

import java.util.List;

@Controller
@RequestMapping({"/customerController","/"})
public class CustomerController {
    @Autowired
    private ICustomerService customerService;
    @GetMapping({"/list",""})
    public String getList(ModelMap model){
        List<Customer> list = customerService.findAll();
        model.put("name","hùng");
        String str = (String) model.get("name");
        System.out.println(str);
        model.addAttribute("list",list);
        return "index";
    }
    @GetMapping("/delete/{id}")
    public  String delete(@PathVariable("id") Long id){
        customerService.remove(id);
        return "redirect:/";
    }
    @GetMapping("/add")
    public ModelAndView add(){
        return new ModelAndView("add","cus",new Customer());
    }
    @PostMapping("/create")
    public  String create(@ModelAttribute("cus") Customer c){
        customerService.save(c);
        return "redirect:/";
    }
    @GetMapping("/edit/{id}")
    public ModelAndView add(@PathVariable("id")Long id){
        return new ModelAndView("edit","cus",customerService.findById(id));
    }
    @PostMapping("/update")
    public  String update(@ModelAttribute("cus") Customer c){
        customerService.save(c);
        return "redirect:/";
    }
}
