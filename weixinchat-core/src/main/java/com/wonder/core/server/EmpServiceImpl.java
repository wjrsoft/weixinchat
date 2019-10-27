package com.wonder.core.server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wonder.core.dao.EmpDAO;
import com.wonder.core.schema.Emp;


@Service("empService")
public class EmpServiceImpl implements EmpService {
	public EmpDAO empDAO;
	public EmpDAO getempDAO() {
		return empDAO;
	}


	@Autowired
	public void setempDAO(EmpDAO empDAO) {
		this.empDAO = empDAO;
	}
	
	public Emp getEmpById(short id) {
		return empDAO.selectByPrimaryKey(id);
	}
	
	

	public int insert(Emp record) {
		// TODO Auto-generated method stub
		return empDAO.insert(record);
	}

	public int insertSelective(Emp record) {
		// TODO Auto-generated method stub
		return empDAO.insertSelective(record);
	}

	public Emp selectByPrimaryKey(Short empno) {
		// TODO Auto-generated method stub
		return empDAO.selectByPrimaryKey(empno);
	}

	public int updateByPrimaryKeySelective(Emp record) {
		// TODO Auto-generated method stub
		return empDAO.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(Emp record) {
		// TODO Auto-generated method stub
		return empDAO.updateByPrimaryKey(record);
	}


	public List<Emp> findAll() {
		 
		return empDAO.findAll();
	}


	public List<Emp> findByEmp(Emp record) {
		// TODO Auto-generated method stub
		return empDAO.findByEmp( record);
	}

    

	

}
