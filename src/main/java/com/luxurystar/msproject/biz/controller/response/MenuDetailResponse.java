package com.luxurystar.msproject.biz.controller.response;

import java.util.List;

import com.luxurystar.msproject.biz.entity.MenuOptionGroup;
import com.luxurystar.msproject.biz.entity.Option;
import com.luxurystar.msproject.biz.entity.OptionGroup;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuDetailResponse {

	private String name;
	private Long price;
	private List<OptionGroupResponse> optionGroupResponseList;

	public void setMenuOptionGroupList(List<MenuOptionGroup> menuOptionGroupList) {
		this.optionGroupResponseList = menuOptionGroupList.stream()
			.map(OptionGroupResponse::from)
			.toList();
	}

	@Getter
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	static class OptionGroupResponse {
		private String name;
		private List<OptionResponse> optionResponseList;

		public static OptionGroupResponse from(MenuOptionGroup menuOptionGroup) {
			OptionGroup optionGroup = menuOptionGroup.getOptionGroup();
			return new OptionGroupResponse(optionGroup.getName(), optionGroup.getOptionList()
				.stream()
				.map(OptionResponse::from)
				.toList());
		}

		@Getter
		@AllArgsConstructor(access = AccessLevel.PRIVATE)
		static class OptionResponse {
			private String name;
			private Long price;

			public static OptionResponse from(Option option) {
				return new OptionResponse(option.getName(), option.getPrice());
			}
		}
	}
}
