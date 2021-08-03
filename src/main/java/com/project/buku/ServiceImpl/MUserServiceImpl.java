/**
 * 
 */
package com.project.buku.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.buku.Entity.MUser;
import com.project.buku.Repository.MUserRepository;
import com.project.buku.Service.MUserService;

/**
 * @author Fikri
 *
 */

@Service
public class MUserServiceImpl implements MUserService {

	@Autowired
	private MUserRepository mUserRepository;

	@Override
	public Optional<MUser> findById(Long id) {
		// TODO Auto-generated method stub
		return mUserRepository.findById(id);
	}

	@Override
	public Optional<MUser> findByUsername(String username) {
		// TODO Auto-generated method stub
		return mUserRepository.findByUsername(username);
	}

	@Override
	public void save(MUser entity) {
		// TODO Auto-generated method stub
		mUserRepository.save(entity);
	}

	@Override
	public void update(MUser entity) {
		// TODO Auto-generated method stub
		save(entity);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		mUserRepository.deleteById(id);
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		mUserRepository.deleteAll();
	}

	@Override
	public List<MUser> findAll() {
		// TODO Auto-generated method stub
		return mUserRepository.findAll();
	}
}
