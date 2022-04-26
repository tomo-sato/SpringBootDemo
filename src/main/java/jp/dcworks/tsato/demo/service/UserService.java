package jp.dcworks.tsato.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
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

	/**
	 * ●●テーブルの情報を取得する.
	 *
	 * @param page ページ番号
	 * @param size ページサイズ
	 * @param sort ソート対象のカラム
	 * @param isOrderAsc 昇順、降順（true.asc/false.desc）
	 * @return ●●テーブルの情報を返す。
	 */
	public Page<User> findAll(Integer page, Integer size, String sort, boolean isOrderAsc) {

		Direction direction = isOrderAsc ? Direction.ASC : Direction.DESC;
		List<Order> orderList = new ArrayList<Order>();

		// ID以外であれば、追加で設定。
		if (sort != null && !"id".equals(sort)) {
			orderList.add(new Order(direction, sort));
		}
		// IDはデフォルト設定。
		orderList.add(new Order(direction, "id"));

		Pageable pageable = PageRequest.of(page, size, Sort.by(orderList));
		Page<User> userList = repository.findAll(pageable);
		return userList;
	}

}
