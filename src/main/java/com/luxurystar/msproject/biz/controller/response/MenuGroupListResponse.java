package com.luxurystar.msproject.biz.controller.response;

import java.util.List;

import com.luxurystar.msproject.biz.entity.Menu;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuGroupListResponse {

	private String name;
	private List<MenuResponse> menuResponseList;

	public void setMenuList(List<Menu> menuList) {
		this.menuResponseList = menuList.stream()
			.filter(Menu::isShowFlag)
			.map(MenuResponse::from)
			.toList();
	}

	@Getter
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	static class MenuResponse {
		private String name;
		private Long price;

		public static MenuResponse from(Menu menu) {
			return new MenuResponse(menu.getName(), menu.getPrice());
		}
	}
}
