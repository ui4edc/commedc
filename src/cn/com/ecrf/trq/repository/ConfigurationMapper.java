package cn.com.ecrf.trq.repository;

import cn.com.ecrf.trq.model.SystemConfiguration;

public interface ConfigurationMapper {

	SystemConfiguration getConfiguration(String key);

}
