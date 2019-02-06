package cabmgmt.persistence.dao;

import java.util.List;
import java.util.Optional;

public interface BaseDAO<T, T1 extends Exception> {

	/**
	 * 
	 * @param entity
	 * @return
	 * @throws T1
	 */
	T create(T entity) throws T1;

	/**
	 * IMPORTANT: as no on delete cascade feature supported by mongo, the reference document deletion must be manually enforce
	 * @param entity
	 * @throws T1
	 */
	void delete(T entity) throws T1;

	/**
	 * 
	 * @return
	 * @throws T1
	 */
	List<T> findAll() throws T1;

	/**
	 * 
	 * @param entityId
	 * @return
	 * @throws T1
	 */
	Optional<T> get(String entityId) throws T1;

	/**
	 * 
	 * @param entity
	 * @return
	 * @throws T1
	 */
	T update(T entity) throws T1;
}
