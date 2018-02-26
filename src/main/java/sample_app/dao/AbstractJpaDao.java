package sample_app.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import sample_app.persistence.PersistenceFacade;

public abstract class AbstractJpaDao<K, E> {

	protected final Class<E> entityClass;

	protected AbstractJpaDao(final Class<E> entityClass) {
		this.entityClass = entityClass;
	}

	protected EntityManager getEntityManager() {
		return PersistenceFacade.getEntityManager();
	}

	/**
	 * Persists entity into DB
	 *
	 * @param entity
	 */
	public void create(final E entity) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();

		em.persist(entity);

		em.getTransaction().commit();
		em.close();
	}

	/**
	 * Updates entity into DB
	 *
	 * @param entity
	 * @return entity
	 */
	public E update(final E entity) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();

		E merge = em.merge(entity);

		em.getTransaction().commit();
		em.close();

		return merge;
	}

	/**
	 * Deletes entity from DB
	 *
	 * @param entity
	 */
	public void delete(String id) {
		String entityName = entityClass.getSimpleName();
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		em.createQuery("DELETE FROM " + entityName + " g where g.id='" + id + "'").executeUpdate();
		em.getTransaction().commit();
		em.close();
	}

	/**
	 * Deletes entities from DB
	 *
	 * @param entities
	 */
	public void delete(final List<E> entities) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();

		for (E e : entities) {
			em.remove(e);
		}

		em.getTransaction().commit();
		em.close();
	}

	public void deleteAll() {
		String entity = entityClass.getSimpleName();
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		em.createQuery("DELETE FROM " + entity + " g").executeUpdate();
		em.getTransaction().commit();
		em.close();
	};

	/**
	 * Finds entity by Id
	 *
	 * @param id
	 * @return entity
	 */
	public E findById(final K id) {
		EntityManager em = getEntityManager();
		E found = em.find(entityClass, id);

		return found;
	}

	/**
	 * Finds all entities
	 *
	 * @return all entities
	 */
	public List<E> findAll() {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<E> cq = cb.createQuery(entityClass);
		cq.select(cq.from(entityClass));
		final TypedQuery<E> query = em.createQuery(cq);
		List<E> resultList = query.getResultList();

		return resultList;
	}
}
