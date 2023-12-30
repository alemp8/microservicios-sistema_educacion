package microservicio.commons.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public class CommonServiceImpl<E, R extends CrudRepository<E,Long>> implements CommonService<E> {
	
	@Autowired
	public R repositorio;

	@Override
	@Transactional(readOnly = true)
	public Iterable<E> findAll() {
		
		return repositorio.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<E> findById(Long id) {
		
		return repositorio.findById(id);
	}

	@Override
	public E save(E entity) {
		
		return repositorio.save(entity);
	}

	@Override
	public void deleteById(Long id) {
		repositorio.deleteById(id);
		
	}

}
