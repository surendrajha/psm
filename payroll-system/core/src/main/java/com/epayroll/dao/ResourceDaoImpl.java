/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epayroll.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.epayroll.entity.Person;
import com.epayroll.entity.Resource;

@Repository
public class ResourceDaoImpl extends GenericDaoImpl<Resource, Long> implements ResourceDao {

	@Override
	@SuppressWarnings("unchecked")
	public List<Resource> findResourcesByCreator(Person creator) {
		return getSession().createQuery("from Resource r where r.creator = :creator")
				.setParameter("creator", creator).list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Resource> findResourcesByModifier(Person modifier) {
		return getSession().createQuery("from Resource r where r.modifier = :modifier")
				.setParameter("modifier", modifier).list();
	}
}
