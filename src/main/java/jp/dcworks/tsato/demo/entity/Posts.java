package jp.dcworks.tsato.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

/**
 * 投稿Entityクラス.
 *
 * @author tomo-sato
 */
@Data
@Entity
@Table(name = "posts")
public class Posts implements Serializable {
	/** ID */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/** ユーザーID */
	@Column(name = "users_id")
	private Long usersId;

	/** タイトル */
	@Column(name = "title", nullable = false)
	private String title;

	/** 本文 */
	@Column(name = "body", nullable = false)
	private String body;

	@ManyToOne
	@JoinColumn(name="users_id", insertable=false, updatable=false)
	private Users users;
}
