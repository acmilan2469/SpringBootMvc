package mn.milan.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import mn.milan.exception.EmployeeNotFound;
import mn.milan.model.Employee;
import mn.milan.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("")
    public String none(){
        return "home";
    }

    @GetMapping("/registerEmployee")
    public String registerEmployee(){
        return "registerEmployee";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Employee employee, Model model){
        service.save(employee);
        Long id = service.save(employee).getId();
        String message = "Record with id : '"+ id +"' is saved successfully !";
        model.addAttribute("message", message);
        return "registerEmployee";
    }

    @GetMapping("/edit")
    public String edit(Model model, RedirectAttributes redirectAttributes, @RequestParam Long id){
        String page = null;
        try{
            Employee employee = service.findById(id);
            model.addAttribute("employee", employee);
            page = "updateEmployee";
        }catch (EmployeeNotFound employeeNotFound){
            employeeNotFound.printStackTrace();
            redirectAttributes.addAttribute(employeeNotFound.getMessage());
            return "redirect:findAll";
        }
        return page;
    }

    @GetMapping("/findAll")
    public String findAll(@RequestParam(value = "message", required = false) String message, Model model){
        List<Employee> employees = service.findAll();
        model.addAttribute("list", employees);
        model.addAttribute("message", message);
        return "allEmployeeListPage";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Long id, RedirectAttributes redirectAttributes){
        try {
            service.delete(id);
            redirectAttributes.addAttribute("Employee with id " + id + " is removed.");
        }catch (EmployeeNotFound employeeNotFound){
            employeeNotFound.printStackTrace();
            redirectAttributes.addAttribute(employeeNotFound.getMessage());
        }
        return "redirect:findAll";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Employee employee, RedirectAttributes redirectAttributes){
        service.update(employee);
        Long id = employee.getId();
        redirectAttributes.addAttribute("message", "Employee with id "+ id + " is successfully updated.");
        return "redirect:findAll";
    }

}
