package ssm.service;

import com.github.pagehelper.PageInfo;
import ssm.pojo.Employee;

import java.util.List;

public interface EmployeeService {
    PageInfo<Employee> getEmployeeList(Integer pageNum);

    Employee getEmployeeById(Integer id);

    void updateEmployeeById(Employee employee,Integer id);

    void save(Employee employee);

    void deleteEmployee(Integer id);

    void deleteChecked(Integer[] empIds);
}
