/**
 * 
 */
package com.project.buku.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.buku.Entity.Buku;
import com.project.buku.Repository.BukuRepository;
import com.project.buku.Service.BukuService;

/**
 * @author Fikri
 *
 */

@Service
public class BukuServiceImpl implements BukuService {
	
	@Autowired
	private BukuRepository bukuRepository;

	@Override
	public Optional<Buku> findById(Long id) {
		// TODO Auto-generated method stub
		return bukuRepository.findById(id);
	}

	@Override
	public void save(Buku entity) {
		// TODO Auto-generated method stub
		bukuRepository.save(entity);
	}

	@Override
	public void update(Buku entity) {
		// TODO Auto-generated method stub
		save(entity);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		bukuRepository.deleteById(id);
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		bukuRepository.deleteAll();
	}

	@Override
	public List<Buku> findAll() {
		// TODO Auto-generated method stub
		return bukuRepository.findAll();
	}

	@Override
	public Page<Buku> findPaginated(int page, int size) {
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(page, size);
		return bukuRepository.findAll(pageable);
	}

}
