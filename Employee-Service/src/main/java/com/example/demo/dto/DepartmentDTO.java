package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {
	
	
	public DepartmentDTO(long l, String string) {
		// TODO Auto-generated constructor stub
	}
	private Long id;
	private String departmentName;
	private String departmentDescription;
	private String departmentCode;
	
	
	
	
	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public String getDepartmentName() {
		return departmentName;
	}




	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}




	public String getDepartmentDescription() {
		return departmentDescription;
	}




	public void setDepartmentDescription(String departmentDescription) {
		this.departmentDescription = departmentDescription;
	}




	public String getDepartmentCode() {
		return departmentCode;
	}




	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}




	public Object getName() {
		// TODO Auto-generated method stub
		return getName();
	}


}
