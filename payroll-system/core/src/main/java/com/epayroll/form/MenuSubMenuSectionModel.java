package com.epayroll.form;

import java.util.Arrays;

import com.epayroll.constant.Menu;
import com.epayroll.constant.Section;
import com.epayroll.constant.SubMenu;

public class MenuSubMenuSectionModel {

	private Menu menu;
	private SubMenu subMenu;
	private Section[] defaultSections;
	private Section[] selectedSections;

	public MenuSubMenuSectionModel() {
	}

	public MenuSubMenuSectionModel(Menu menu, SubMenu subMenu, Section[] defaultSections,
			Section[] selectedSections) {
		super();
		this.menu = menu;
		this.subMenu = subMenu;
		this.defaultSections = defaultSections;
		this.selectedSections = selectedSections;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public SubMenu getSubMenu() {
		return subMenu;
	}

	public void setSubMenu(SubMenu subMenu) {
		this.subMenu = subMenu;
	}

	public Section[] getDefaultSections() {
		return defaultSections;
	}

	public void setDefaultSections(Section[] defaultSections) {
		this.defaultSections = defaultSections;
	}

	public Section[] getSelectedSections() {
		return selectedSections;
	}

	public void setSelectedSections(Section[] selectedSections) {
		this.selectedSections = selectedSections;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MenuSubMenuSectionModel [menu=");
		builder.append(menu);
		builder.append(", subMenu=");
		builder.append(subMenu);
		builder.append(", defaultSections=");
		builder.append(Arrays.toString(defaultSections));
		builder.append(", selectedSections=");
		builder.append(Arrays.toString(selectedSections));
		builder.append("]");
		return builder.toString();
	}

}
