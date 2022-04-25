package jp.dcworks.tsato.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
}
