package cn.com.ecrf.trq.repository;

import java.util.List;
import java.util.Map;

import cn.com.ecrf.trq.model.Organization;
import cn.com.ecrf.trq.model.Role;
import cn.com.ecrf.trq.model.User;


public interface OrganizationMapper {

	int insertOrganization(Organization organization);
	
	int updateOrganization(Organization organization);
	
	int deleteOrganizationById(Integer id);
	
	Role getOrganizationById(Integer id);

	Role findOrganizationByName(String organizationName);

	List<Organization> findOrganizations(Map<String, Object> condition);
}