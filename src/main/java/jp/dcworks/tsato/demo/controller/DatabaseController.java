package jp.dcworks.tsato.demo.controller;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import javax.persistence.Column;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jp.dcworks.tsato.demo.PageWrapper;
import jp.dcworks.tsato.demo.entity.User;
import jp.dcworks.tsato.demo.service.UserService;
import lombok.extern.log4j.Log4j2;

/**
 * データベース検証の為のコントローラ.
 *
 * @author tomo-sato
 */
@Log4j2
@Controller
@RequestMapping("/db")
public class DatabaseController {
	private static final String DEFAULT_PAGE = "0";

	@Autowired
	private UserService userService;

	@RequestMapping(value="", method=RequestMethod.GET)
	public String index(Pageable pageable, Model model) {
		log.info("index");

		Page<User> userPage = userService.findAll(pageable);
		model.addAttribute("users", userPage);
		model.addAttribute("page", userPage);
		model.addAttribute("words", userPage.getContent());
		model.addAttribute("url", "");

		return "db/index";
	}

	/**
	 * ※クエリパラメータ名はBacklogと、
	 * {@link org.springframework.data.domain.PageRequest#of(int page, int size, Sort sort)}参照。
	 *
	 * ※Requestパラメータが変換できないと「400 Bad Request」になるので、Stringで受け付けて、変換できなかったらデフォルトに置き換える。
	 *
	 * ※TODO {@code page}や、{@code size}は、プロパティファイルなどで管理したい。
	 *
	 * @param page ページ番号（0始まり、変換できなかったら0で。）
	 * @param size ページサイズ（limit、変換できなかったら25で。）
	 * @param sort カラム名（存在しないカラムの場合、500 Internal Server Errorになる。存在しなかったらidで。）
	 * @param order 文字列指定"true".昇順/"false".降順
	 * （※Booleanにすると、変換できない値の場合400 Bad Requestになる。変換できなかったら昇順で。）
	 * @param model
	 * @return
	 */
	@GetMapping(value="test2")
	public String getAllEmployees(
			@RequestParam(defaultValue = DEFAULT_PAGE) String page,
			@RequestParam(defaultValue = "5") String size,
			@RequestParam(defaultValue = "id") String sort,
			@RequestParam(defaultValue = "true") String order,
			Model model) {

		// とりあえず、parseInt。要エラー処理。
		int ipage = Integer.parseInt(page);
		int isize = Integer.parseInt(size);
		boolean isOrderAsc = BooleanUtils.toBoolean(order);

		// TODO カラムが存在するかどうかチェック
		// TODO カラム名じゃなく、プロパティのようだ。。
//		String sortColumn = isColumnExist(User.class, sort) ? sort : "id";
		String sortColumn = sort;

		Page<User> userPage = userService.findAll(ipage, isize, sortColumn, isOrderAsc);
		PageWrapper<User> pager = new PageWrapper<User>(userPage, "test2");

		model.addAttribute("users", userPage);
		model.addAttribute("page", userPage);
		model.addAttribute("pager", pager);

		model.addAttribute("sort", sortColumn);
		model.addAttribute("order", order);
		model.addAttribute("words", userPage.getContent());
		model.addAttribute("url", "");

		return "db/index2";
	}


	private static boolean isColumnExist(Class<User> clazz, String columnName) {
		boolean isColumnExist = false;

		Field[] fieldList = clazz.getDeclaredFields();
		for (Field field : fieldList) {
			// フィールドの属性（修飾子）を取得
			int mod = field.getModifiers();

			// 静的要素は除外
			if (Modifier.isStatic(mod)) {
				continue;
			}

			Column column = field.getAnnotation(Column.class);
			if (column != null) {
				String name = column.name();
				if (name != null) {
					if (name.equals(columnName)) {
						isColumnExist = true;
						break;
					}
				}
			}

		}
		return isColumnExist;
	}
}
