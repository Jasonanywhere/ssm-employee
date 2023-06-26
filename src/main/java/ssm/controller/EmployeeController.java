package ssm.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ssm.pojo.Employee;
import ssm.service.EmployeeService;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @RequestMapping(value = "/employee/page/{pageNum}", method = RequestMethod.GET)
    public String getEmployeeList(Model model, @PathVariable("pageNum") Integer pageNum){
        PageInfo<Employee> page = employeeService.getEmployeeList(pageNum);
        model.addAttribute("page", page);
        return "employee_list";
    }

    //转到更新信息页面
    @RequestMapping(value = "/update/{id}/{pageNum}",method = RequestMethod.GET)
    public String toUpdate(@PathVariable("id") Integer id,Model model,@PathVariable("pageNum") Integer pageNum){
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee",employee);
        model.addAttribute("pageNum",pageNum);
        return "update";
    }

    //更新信息
    @RequestMapping(value = "/employee/update/{id}/{pageNum}",method = RequestMethod.PUT)
    public String updateEmployeeById(@PathVariable("id") Integer id,Employee employee,@PathVariable("pageNum") Integer pageNum){
        employeeService.updateEmployeeById(employee,id);
        return "redirect:/employee/page/"+pageNum;
    }

    //跳转到添加信息的页面
    @RequestMapping(value = "/insert/{lastPage}",method = RequestMethod.GET)
    public String toInsert(@PathVariable("lastPage") Integer lastPage,Model model){
        model.addAttribute("lastPage",lastPage);
        return "insert";
    }

    //添加信息
    @RequestMapping(value = "/employee/insert/{lastPage}",method = RequestMethod.POST)
    public String insertEmployee(Employee employee,@PathVariable("lastPage") Integer lastPage){
        PageInfo<Employee> page = employeeService.getEmployeeList(lastPage);
        //如果最后一页的数据已经满了，那么就往后加一页
        if(page.getSize()==page.getPageSize()){
            lastPage++;
        }
        employeeService.save(employee);
        return "redirect:/employee/page/"+lastPage;
    }

    //删除信息
    @RequestMapping(value = "/delete/{id}/{pageNum}",method = RequestMethod.GET)
    public String deleteEmployee(@PathVariable("id") Integer id,@PathVariable("pageNum") Integer pageNum){
        employeeService.deleteEmployee(id);
        return "redirect:/employee/page/"+pageNum;
    }

    //删除选中人员的信息
    @RequestMapping(value = "/deleteChecked/{pageNum}",method = RequestMethod.DELETE)
    public String deleteCheckedEmployee(@RequestParam("empIds") Integer[] empIds,@PathVariable("pageNum") Integer pageNum){
        employeeService.deleteChecked(empIds);
        //如果该页数据被删除清空，则跳转到前一页,需要在删除操作结束之后进行判断
        PageInfo<Employee> page = employeeService.getEmployeeList(pageNum);
        if (page.getSize()==0){
            pageNum--;
        }
        return "redirect:/employee/page/"+pageNum;
    }

    //跳转到查询结果页面
    @RequestMapping(value = "/search/{pageNum}",method = RequestMethod.GET)
    public String toSearch(Employee employee,Model model,@PathVariable("pageNum") Integer pageNum){
        int Empty;//查询后，用来标记是否获取到了信息

        PageInfo<Employee> page = employeeService.getEmployeeByName(pageNum,employee.getEmpName());
        List<Employee> list = page.getList();
        if (list.isEmpty()){
            Empty=0;
            model.addAttribute("Empty",Empty);
        }else {
            Empty=1;
            model.addAttribute("Empty",Empty);
        }

        model.addAttribute("empName",employee.getEmpName());
        model.addAttribute("page",page);
        return "search_employee_list";
    }

    //在查询结果页面实现翻页
    @RequestMapping(value = "/search/{empName}/{pageNum}",method = RequestMethod.GET)
    public String searchEmployee(@PathVariable("empName") String empName,Model model,@PathVariable("pageNum") Integer pageNum){
        int Empty=1;
        PageInfo<Employee> page = employeeService.getEmployeeByName(pageNum,empName);
        model.addAttribute("Empty",Empty);
        model.addAttribute("empName",empName);
        model.addAttribute("page",page);
        return "search_employee_list";
    }
}
