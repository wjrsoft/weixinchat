package com.wonder.core.dao;

import java.util.List;

import com.wonder.core.schema.Emp;


public interface EmpDAO{
    int deleteByPrimaryKey(Integer empno);

    int insert(Emp record);

    int insertSelective(Emp record);

    Emp selectByPrimaryKey(short id);

    int updateByPrimaryKeySelective(Emp record);

    int updateByPrimaryKey(Emp record);
    
    public List<Emp> findAll();
    
    List<Emp> findByEmp(Emp record);
}