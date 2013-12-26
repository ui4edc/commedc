package cn.com.ecrf.trq.utils;

import java.util.UUID;

public class UUIDGenerator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String uuid = UUID.randomUUID().toString();
		System.out.println("uuid="+uuid+",len:"+uuid.length());
	}

}
