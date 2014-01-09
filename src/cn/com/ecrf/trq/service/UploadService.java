package cn.com.ecrf.trq.service;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.ecrf.trq.repository.UploadMapper;

@Service
public class UploadService {

	@Autowired
	private UploadMapper uploadMapper;
	
	public void insertImage(String fileName, InputStream in){
		
	}
}
