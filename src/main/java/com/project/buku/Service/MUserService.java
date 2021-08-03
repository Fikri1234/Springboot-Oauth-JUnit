/**
 * 
 */
package com.project.buku.Service;

import java.util.List;
import java.util.Optional;

import com.project.buku.Entity.MUser;

/**
 * @author Fikri
 *
 */
public interface MUserService {
	
	Optional<MUser> findById(Long id);
	Optional<MUser> findByUsername(String username);
	void save(MUser entity);
	void update(MUser entity);
    void deleteById(Long id);
    void deleteAll();
    List<MUser> findAll();

}
