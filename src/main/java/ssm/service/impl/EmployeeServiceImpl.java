package ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssm.mapper.EmployeeMapper;
import ssm.pojo.Employee;
import ssm.service.EmployeeService;

import java.util.List;

@Service
@Transactional//添加事务
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public PageInfo<Employee> getEmployeeList(Integer pageNum) {
        PageHelper.startPage(pageNum, 8);
        List<Employee> employeeList = employeeMapper.getEmployeeList();
        PageInfo<Employee> page = new PageInfo<>(employeeList, 4);
        return page;
    }
    @Override
    public Employee getEmployeeById(Integer id) {
        Employee employee = employeeMapper.getEmployeeById(id);
        return employee;
    }

    @Override
    public void updateEmployeeById(Employee employee,Integer id) {
        employeeMapper.updateEmployeeById(employee,id);
    }

    @Override
    public void save(Employee employee) {
        employeeMapper.insertEmployee(employee);
    }

    @Override
    public void deleteEmployee(Integer id) {
        employeeMapper.deleteEmployeeById(id);
    }

    @Override
    public void deleteChecked(Integer[] empIds) {
        employeeMapper.deleteCheckedById(empIds);
    }
}
