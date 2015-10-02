/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epayroll.dao;

import java.util.List;

import com.epayroll.entity.Person;
import com.epayroll.entity.Resource;

public interface ResourceDao extends GenericDao<Resource, Long> {

	public List<Resource> findResourcesByCreator(Person creator);

	public List<Resource> findResourcesByModifier(Person modifier);
}
