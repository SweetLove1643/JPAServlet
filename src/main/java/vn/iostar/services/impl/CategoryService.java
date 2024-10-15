package vn.iostar.services.impl;

import java.util.List;

import vn.iostar.dao.ICategoryDao;
import vn.iostar.dao.impl.CategoryDao;
import vn.iostar.entity.Category;
import vn.iostar.services.ICategoryService;

public class CategoryService implements ICategoryService{
	public ICategoryDao cateDao = new CategoryDao();
	@Override
	public int count() {
		return cateDao.count();
	}

	@Override
	public List<Category> findAll(int page, int pagesize) {
		
		return cateDao.findAll(page,pagesize);
	}

	@Override
	public List<Category> searchByName(String catname) {
		return cateDao.searchByName(catname);
	}

	@Override
	public List<Category> finAll() {
		return cateDao.finAll();
	}

	@Override
	public Category findByCategoryname(String name){
		try {
			return cateDao.findByCategoryname(name);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Category findById(int id) {
		try {
			return cateDao.findById(id);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void delete(int id) throws Exception {
		try {
			cateDao.delete(id);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Category category) {
		try {
			Category cate = this.findById(category.getCategoryId());
			if(cate != null) {
				cateDao.update(category);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insert(Category category) {
		Category cate = this.findByCategoryname(category.getCategoryname());
		if(cate == null) {
			cateDao.insert(category);
		}
		
	}
	
}
