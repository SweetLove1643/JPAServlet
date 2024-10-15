package vn.iostar.services;

import java.util.List;

import vn.iostar.entity.Category;

public interface ICategoryService {
	
	int count();

	List<Category> findAll(int page, int pagesize);

	List<Category> searchByName(String catname);

	List<Category> finAll();

	Category findByCategoryname(String name) throws Exception;

	Category findById(int id);

	void delete(int id) throws Exception;

	void update(Category category);

	void insert(Category category);
}
