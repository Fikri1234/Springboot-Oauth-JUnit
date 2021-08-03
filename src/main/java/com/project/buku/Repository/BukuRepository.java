/**
 * 
 */
package com.project.buku.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.buku.Entity.Buku;

/**
 * @author Fikri
 *
 */

@Repository
public interface BukuRepository extends JpaRepository<Buku, Long> {

}
