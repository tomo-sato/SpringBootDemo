package jp.dcworks.tsato.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jp.dcworks.tsato.demo.entity.User;
import jp.dcworks.tsato.demo.repository.UserRepository;

/**
 * ユーザーサービスクラス.
 *
 * @author tomo-sato
 */
@Service
public class UserService {

	private UserRepository repository;

	public UserService(UserRepository repository) {
		this.repository = repository;
	}

	public Page<User> findAll(Pageable pageable) {
		Page<User> userList = repository.findAll(pageable);
		return userList;
	}

	public Page<User> findAll(Integer pageNo, Integer pageSize, String sortBy) {
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<User> userList = repository.findAll(pageable);
		return userList;
	}

}
