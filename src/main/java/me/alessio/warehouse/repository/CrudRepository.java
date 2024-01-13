package me.alessio.warehouse.repository;

import java.util.List;
import java.util.Map;

public interface CrudRepository<T,ID> {

	Map<String,String> findById(ID id);

	List<Map<String,String>> findAll();

	boolean insert(T entity);
	
	boolean update(T entity);
	
	boolean delete(T entity);
}
