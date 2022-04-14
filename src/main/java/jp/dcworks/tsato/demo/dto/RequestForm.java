package jp.dcworks.tsato.demo.dto;


import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Range;

import lombok.Data;

@Data
public class RequestForm {

	/** 数値入力（※「String」で定義しないと数値以外の場合、「Integer=null」や、「int=0」になる。エラーでリダイレクトしたときフィルイン出来ない。） */
	@NotBlank(message="必須です。入力してください。")
	@Range(min=10, max=9999, message="10～9999の範囲で入力してください。")
	private String num;
}
