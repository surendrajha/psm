package com.epayroll.form;

import com.epayroll.constant.Application;
import com.epayroll.constant.Menu;
import com.epayroll.constant.PermissionType;
import com.epayroll.constant.Section;
import com.epayroll.constant.SubMenu;

public class ManagePermission {

	private Application application;
	private Menu menu;
	private SubMenu subMenu;
	private Section section;
	private PermissionType[] permissionTypes;

	public ManagePermission() {
	}

	public ManagePermission(Application application, Menu menu, SubMenu subMenu, Section section,
			PermissionType[] permissionTypes) {
		super();
		this.application = application;
		this.menu = menu;
		this.subMenu = subMenu;
		this.section = section;
		this.permissionTypes = permissionTypes;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
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

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public PermissionType[] getPermissionTypes() {
		return permissionTypes;
	}

	public void setPermissionTypes(PermissionType[] permissionTypes) {
		this.permissionTypes = permissionTypes;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ManagePermission [application=");
		builder.append(application);
		builder.append(", menu=");
		builder.append(menu);
		builder.append(", subMenu=");
		builder.append(subMenu);
		builder.append(", section=");
		builder.append(section);
		builder.append(", permissionTypes=");
		builder.append(permissionTypes);
		builder.append("]");
		return builder.toString();
	}
}
