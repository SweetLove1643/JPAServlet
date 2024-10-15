package vn.iostar.dao.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import vn.iostar.configs.JPAConfig;
import vn.iostar.dao.ICategoryDao;
import vn.iostar.entity.Category;

public class CategoryDao implements ICategoryDao {
	@Override
	public void insert(Category category) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.persist(category);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

	@Override
	public void update(Category category) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.merge(category);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

	@Override
	public void delete(int id) throws Exception {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			Category cate = enma.find(Category.class, id);
			if (cate != null) {
				enma.remove(cate);
			} else {
				throw new Exception("Không tìm thấy");
			}
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

	@Override
	public Category findById(int id) {
		EntityManager enma = JPAConfig.getEntityManager();
		Category cate = enma.find(Category.class, id);

		return cate;
	}

	@Override
	public Category findByCategoryname(String name) throws Exception {

		EntityManager enma = JPAConfig.getEntityManager();
		String jpql = "SELECT c FROM categories c WHERE c.Categoryname =:catename";
		try {
			TypedQuery<Category> query = enma.createQuery(jpql, Category.class);
			query.setParameter("catename", name);
			Category category = query.getSingleResult();
			if (category == null) {
				throw new Exception("Category Name đã tồn tại");
			}
			return category;
		} finally {
			enma.close();
		}
	}
	
	@Override
	public List<Category> finAll(){
		EntityManager enma = JPAConfig.getEntityManager();
		 TypedQuery<Category> query= enma.createNamedQuery("Category.findAll", Category.class);
		 return query.getResultList();	 
	}
	
	
	 @Override
	public List<Category> searchByName(String catname) {
	 EntityManager enma = JPAConfig.getEntityManager();
	 String jpql = "SELECT c FROM categories c WHERE c.Categoryname like :catname";
	 TypedQuery<Category> query= enma.createQuery(jpql, Category.class);
	 query.setParameter("catename", "%" + catname + "%");
	 return query.getResultList();
	 }

	 
	 
	 @Override
	public List<Category> findAll(int page, int pagesize) {
	 EntityManager enma = JPAConfig.getEntityManager();
	 TypedQuery<Category> query= enma.createNamedQuery("Category.findAll", Category.class);
	 query.setFirstResult(page*pagesize);
	 query.setMaxResults(pagesize);
	 return query.getResultList();
	 }

	 
	 
	 @Override
	public int count() {
	 EntityManager enma = JPAConfig.getEntityManager();
	 String jpql = "SELECT count(c) FROM categories c";
	 Query query = enma.createQuery(jpql);
	 return ((Long)query.getSingleResult()).intValue();
	 }

}
