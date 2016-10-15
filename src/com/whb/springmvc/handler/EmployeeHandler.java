package com.whb.springmvc.handler;

import com.whb.springmvc.dao.DepartmentDao;
import com.whb.springmvc.dao.EmployeeDao;
import com.whb.springmvc.entry.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * employeehandler
 *
 * @author Administrator
 * @create 2016 10 10 22:41
 */
@Controller
public class EmployeeHandler {
	@Autowired
	private EmployeeDao employeeDao;
	@RequestMapping("/emps")
	public  String list(Map<String,Object> map){
		map.put("employees",employeeDao.getAll());

		return "list";
	}
	@Autowired
	private  DepartmentDao departmentDao;

	@RequestMapping(value = "/emp" ,method = RequestMethod.GET)
	public  String input(Map<String,Object> map){
		map.put("departments",departmentDao.getDepartments());
		map.put("employee",new Employee());
		return "input";
	}

	@RequestMapping(value = "/emp",method = RequestMethod.POST)
	public  String save(Employee employee){
        employeeDao.save(employee);
		return "redirect:/emps";
	}
	@RequestMapping(value = "/emp/{id}",method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") Integer id ){
		employeeDao.delete(id);
		return "redirect:/emps";
	}
	@RequestMapping(value = "/emp/{id}",method = RequestMethod.GET)
	public  String input(@PathVariable("id") Integer id,Map<String,Object> map){
		map.put("employee",employeeDao.get(id));
		map.put("departments",departmentDao.getDepartments());
		return "input";
	}
	@RequestMapping(value = "/emp" ,method = RequestMethod.PUT)
	public  String update(Employee employee){
		employeeDao.save(employee);
		return "redirect:/emps";
	}
	@ModelAttribute
	public  void getEmployee(@RequestParam(value = "id",required = false) Integer id,
	                         Map<String ,Object> map){
		if( id != null){
			map.put("employee",employeeDao.get(id));

		}
	}

}
