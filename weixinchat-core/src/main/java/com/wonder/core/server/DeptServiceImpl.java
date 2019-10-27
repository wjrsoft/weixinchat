package com.wonder.core.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wonder.core.dao.DeptDAO;
import com.wonder.core.schema.Dept;


@Service("deptService")
public class DeptServiceImpl implements DeptService {
	public DeptDAO deptDAO;

	public DeptDAO getdeptDAO() {
		return deptDAO;
	}

	@Autowired
	public void setdeptDAO(DeptDAO deptDAO) {
		this.deptDAO = deptDAO;
	}

	public int deleteByPrimaryKey(Integer deptno) {
		// TODO Auto-generated method stub
		return deptDAO.deleteByPrimaryKey(deptno);
	}

	public int insert(Dept record) {
		// TODO Auto-generated method stub
		return deptDAO.insert(record);
	}

	public int insertSelective(Dept record) {
		// TODO Auto-generated method stub
		return deptDAO.insertSelective(record);
	}

	public Dept selectByPrimaryKey(Integer deptno) {
		// TODO Auto-generated method stub
		return deptDAO.selectByPrimaryKey(deptno);
	}

	public int updateByPrimaryKeySelective(Dept record) {
		// TODO Auto-generated method stub
		return deptDAO.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(Dept record) {
		// TODO Auto-generated method stub
		return deptDAO.updateByPrimaryKey(record);
	}

}
