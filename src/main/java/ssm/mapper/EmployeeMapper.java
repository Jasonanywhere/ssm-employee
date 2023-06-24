package ssm.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import ssm.pojo.Employee;

import java.util.List;

@Repository
public interface EmployeeMapper {
    List<Employee> getEmployeeList();

    Employee getEmployeeById(@Param("id") Integer id);

    void updateEmployeeById(@Param("employee") Employee employee,@Param("id") Integer id);

    void insertEmployee(@Param("employee") Employee employee);

    void deleteEmployeeById(@Param("id") Integer id);

    void deleteCheckedById(@Param("empIds") Integer[] empIds);

    List<Employee> getEmployeeListByName(@Param("empName") String empName);
}
