package com.example;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeRepository repo;

//    @GetMapping({"/list","/"})
//    public ModelAndView getAllEmployees(){
//        ModelAndView mav=new ModelAndView("list-employees");
//        mav.addObject("employees",repo.findAll());
//        return mav;
//    }
    @GetMapping({"/list","/"})
    public String getAllEmployees(Model model){
        model.addAttribute("employees",repo.findAll());
        return "list-employees";
    }
    @GetMapping("/addEmployeeForm")
    public String getAll(Model model){
        Employee employee=new Employee();
        model.addAttribute("employee",employee);
        return "add-employee-form";
    }
    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute Employee employee){
        repo.save(employee);
        return "redirect:/list";
    }
    @GetMapping("/showUpdateForm")
    public String showUpdateForm(@RequestParam Integer employeeId,Model model){
       model.addAttribute("employee", repo.getReferenceById(employeeId));
        return "add-employee-form";
    }
    @GetMapping("/deleteEmployee")
    public String deleteEmployee(@RequestParam Integer employeeId){
        repo.deleteById(employeeId);
        return "redirect:/list";
    }
}
