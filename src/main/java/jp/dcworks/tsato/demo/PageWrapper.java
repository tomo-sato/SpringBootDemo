package jp.dcworks.tsato.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import lombok.Getter;

@Getter
public class PageWrapper<T> {
	public static final int MAX_PAGE_ITEM_DISPLAY = 5;

	private Page<T> page;
	private List<PageItem> items;
	private int currentNumber;
	private String url;
	private boolean isListContinue;

	public PageWrapper(Page<T> page, String url) {
		this.page = page;
		this.url = url;
		items = new ArrayList<PageItem>();

		currentNumber = page.getNumber() + 1;

		// 表示するページアイテムの開始と、表示個数。
		int start = 1;
		int size = MAX_PAGE_ITEM_DISPLAY;
		// 省略表示の有無。
		this.isListContinue = false;

		// ページ数が、定数のページ数以下の場合。
		if (page.getTotalPages() <= MAX_PAGE_ITEM_DISPLAY){
			size = page.getTotalPages();
		}
		// ページ数が、定数のページ数より大きい場合。
		else {
			// 現在ページにより開始と、表示個数の制御。

			// currentNumberが、1 ～ 中央未満。（※前の方。）
			if (currentNumber <= MAX_PAGE_ITEM_DISPLAY - MAX_PAGE_ITEM_DISPLAY / 2) {
				// 省略表示ON。
				this.isListContinue = true;

			}
			// currentNumberが、n ～ 中央以上かつ、最大ページ表示時。（※後の方。）
			else if (currentNumber >= page.getTotalPages() - MAX_PAGE_ITEM_DISPLAY / 2) {
				start = page.getTotalPages() - MAX_PAGE_ITEM_DISPLAY + 1;

			}
			// currentNumberが、n ～ 中央以上。（※真ん中。）
			else {
				start = currentNumber - MAX_PAGE_ITEM_DISPLAY / 2;

				// 省略表示ON。
				this.isListContinue = true;
			}
		}

		// 表示するページアイテムをリストに追加。
		for (int i = 0; i < size; i++) {
			items.add(new PageItem((start + i), ((start + i) == currentNumber)));
		}
	}

	public boolean isListContinue() {
		return this.isListContinue;
	}

	public List<T> getContent() {
		return page.getContent();
	}

	public int getSize() {
		return page.getSize();
	}

	public int getTotalPages() {
		return page.getTotalPages();
	}

	public boolean isFirstPage() {
		return page.isFirst();
	}

	public boolean isLastPage() {
		return page.isLast();
	}

	public boolean isHasPreviousPage() {
		return page.hasPrevious();
	}

	public boolean isHasNextPage() {
		return page.hasNext();
	}

	@Getter
	public class PageItem {
		private int number;
		private boolean current;

		public PageItem(int number, boolean current) {
			this.number = number;
			this.current = current;
		}

		public boolean isCurrent() {
			return this.current;
		}
	}
}
