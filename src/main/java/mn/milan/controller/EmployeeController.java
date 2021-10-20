package mn.milan.controller;

import mn.milan.model.Employee;
import mn.milan.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/registerEmployee")
    public String registerEmployee(){
        return "registerEmployee";
    }

    @PostMapping("/save")
    public Employee save(@ModelAttribute Employee employee, Model model){
        service.save(employee);
        Long id = service.save(employee).getId();
        String message = "Record with id : '"+id+"' is saved successfully !";
        model.addAttribute("message", message);
        return "registerInvoicePage";
    }
}
