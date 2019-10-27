package com.wonder.core.server;

import java.util.List;

import com.wonder.core.schema.Emp;


public interface EmpService {
	public Emp getEmpById(short id);

	int insert(Emp record);

	int insertSelective(Emp record);

	Emp selectByPrimaryKey(Short empno);

	int updateByPrimaryKeySelective(Emp record);

	int updateByPrimaryKey(Emp record);
	
	public List<Emp> findAll();
	
	List<Emp> findByEmp(Emp record);
}
