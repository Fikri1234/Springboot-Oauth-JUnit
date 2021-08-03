/**
 * 
 */
package com.project.buku.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.project.buku.Entity.Buku;

/**
 * @author Fikri
 *
 */
public interface BukuService {
	
	Optional<Buku> findById(Long id);
	void save(Buku entity);
	void update(Buku entity);
    void deleteById(Long id);
    void deleteAll();
    List<Buku> findAll();
    Page<Buku> findPaginated(int page, int size);

}
