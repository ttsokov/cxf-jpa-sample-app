package sample_app.initialization;

import java.util.List;

import sample_app.dao.AbstractJpaDao;

public class DataImport<K, E> {

	public void importFromJSON(List<E> objects, AbstractJpaDao<K, E> dao) {
		dao.deleteAll();
		for (E d : objects) {
			dao.create(d);
		}
	}
}
