/**
 * 
 */
package com.project.buku.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.buku.Entity.MUser;

/**
 * @author Fikri
 *
 */

@Repository
public interface MUserRepository extends JpaRepository<MUser, Long> {
	
	Optional<MUser> findByUsername(String username);

}
