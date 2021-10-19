package mn.milan.service;

import mn.milan.model.Employee;

import java.util.List;

public interface EmployeeService {

    public Employee save(Employee employee);
    public void update(Employee employee);
    public Employee findById(Long id);
    public List<Employee> findAll();
    public void delete(Long id);

}
