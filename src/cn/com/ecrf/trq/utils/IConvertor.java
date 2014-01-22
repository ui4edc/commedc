package cn.com.ecrf.trq.utils;

public interface IConvertor<T,V> {
	
	public V convertFromModelToView(T model);
	public T convertFromViewToModel(V view);
}
