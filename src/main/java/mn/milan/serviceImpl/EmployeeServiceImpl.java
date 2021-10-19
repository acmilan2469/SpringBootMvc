package mn.milan.serviceImpl;

import mn.milan.exception.EmployeeNotFound;
import mn.milan.model.Employee;
import mn.milan.repo.EmployeeRepo;
import mn.milan.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepo repo;

    public EmployeeServiceImpl(EmployeeRepo repo) {
        this.repo = repo;
    }

    @Override
    public Employee save(Employee employee) {
        return repo.save(employee)  ;
    }

    @Override
    public void update(Employee employee) {
        repo.save(employee);
    }

    @Override
    public Employee findById(Long id) {
        Optional<Employee> optional = repo.findById(id);
        if (optional.isPresent()){
            return optional.get();
        }else {
            throw new EmployeeNotFound("Employee with id " + id + " is not found");
        }
    }

    @Override
    public List<Employee> findAll() {
        return repo.findAll();
    }

    @Override
    public void delete(Long id) {
        repo.delete(findById(id));
    }
}
