package jp.dcworks.tsato.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import jp.dcworks.tsato.demo.entity.User;

/**
 * ユーザー関連リポジトリインターフェース.
 *
 * @author tomo-sato
 */
@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

	public Page<User> findAll(Pageable pageable);
}
