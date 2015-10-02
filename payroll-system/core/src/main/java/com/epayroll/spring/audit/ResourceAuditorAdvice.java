package com.epayroll.spring.audit;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.epayroll.dao.GenericDao;
import com.epayroll.entity.Resource;

@Aspect
@Component
public class ResourceAuditorAdvice {

	private static final String RESOURCE_FIELD = "resource";
	private List<Class<?>> resources = new ArrayList<Class<?>>();
	private List<Class<?>> nonResources = new ArrayList<Class<?>>();

	public ResourceAuditorAdvice() {
	}

	@Transactional
	@Before(value = "execution(public * com.epayroll.dao.GenericDao.create(*))||execution(public * com.epayroll.dao.GenericDao.update(*))")
	public void before(JoinPoint point) throws Throwable {

		Object obj = point.getArgs()[0];
		if (isResource(obj) && (point.getTarget() instanceof GenericDao)) {
			Resource resource = (Resource) PropertyUtils.getProperty(obj, RESOURCE_FIELD);
			if (resource == null) {
				resource = new Resource();
			}
			resource.setUpdationDate(Calendar.getInstance());
			// resource.setModifier(AuthorizationUtil.getLoginUser().getPerson());
			resource.setModifier(null);

			if (point.getSignature().getName().startsWith("create")) {
				resource.setCreationDate(Calendar.getInstance());
				// resource.setCreator(AuthorizationUtil.getLoginUser().getPerson());
				resource.setModifier(null);
			}
			PropertyUtils.setProperty(obj, RESOURCE_FIELD, resource);
		}
	}

	private boolean isResource(Object obj) {
		Class<?> clazz = obj.getClass();
		if (resources.contains(clazz)) {
			return true;
		}
		if (nonResources.contains(clazz)) {
			return false;
		}
		for (Field field : clazz.getDeclaredFields()) {
			if (field.getName().equalsIgnoreCase(RESOURCE_FIELD)) {
				resources.add(clazz);
				return true;
			}
		}
		nonResources.add(clazz);
		return false;
	}

}
