package jp.dcworks.tsato.demo.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

/**
 * ユーザーEntityクラス.
 *
 * @author tomo-sato
 */
@Data
@Entity
@Table(name = "users")
public class Users implements Serializable {
	/** ID */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/** 名前 */
	@Column(name = "name", nullable = false)
	private String name;

	/** 年齢 */
	@Column(name = "age", nullable = false)
	private Integer age;

	@OneToMany(mappedBy="users", cascade=CascadeType.ALL)
	private List<Posts> postList;
}
