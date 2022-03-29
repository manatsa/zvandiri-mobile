/*
 * Copyright 2016 Judge Muzinda.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package zw.org.zvandiri.business.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import zw.org.zvandiri.business.domain.CatDetail;
import zw.org.zvandiri.business.domain.Patient;
import zw.org.zvandiri.business.domain.User;
import zw.org.zvandiri.business.domain.UserRole;
import zw.org.zvandiri.business.domain.util.UserType;
import zw.org.zvandiri.business.repo.CatDetailRepo;
import zw.org.zvandiri.business.service.CatDetailService;
import zw.org.zvandiri.business.service.UserRoleService;
import zw.org.zvandiri.business.service.UserService;
import zw.org.zvandiri.business.util.UUIDGen;
import zw.org.zvandiri.business.util.dto.NameIdDTO;

/**
 *
 * @author Judge Muzinda
 */
@Repository
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class CatDetailServiceImpl implements CatDetailService {

	private static final Logger LOGGER = Logger.getLogger(CatDetailServiceImpl.class);
	@Resource
	private CatDetailRepo catDetailRepo;
	@Resource
	private UserService userService;
	@Resource
	private UserRoleService userRoleService;

	@Override
	public List<CatDetail> getAll() {
		return catDetailRepo.findByActive(Boolean.TRUE);
	}

	@Override
	public CatDetail get(String id) {
		if (id == null) {
			throw new IllegalStateException("Item to be does not exist :" + id);
		}
		return catDetailRepo.findById(id).get();
	}

	@Override
	public void delete(CatDetail t) {
		if (t.getId() == null) {
			throw new IllegalStateException("Item to be deleted is in an inconsistent state");
		}
		t.setActive(Boolean.FALSE);
		t.setDeleted(true);
		catDetailRepo.save(t);
	}

	@Override
	public List<CatDetail> getPageable() {
		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
																		// Tools | Templates.
	}

	@Override
	public CatDetail getByEmail(String email) {
		CatDetail catDetail=catDetailRepo.findByEmail(email);
		return catDetail;
	}

	@Override
	@Transactional
	public CatDetail save(CatDetail t) {
		if (t.getId() == null) {
			t.setId(UUIDGen.generateUUID());
			t.setCreatedBy(userService.getCurrentUser());
			t.setDateCreated(new Date());
			t.setEmail(t.getUserName());
			userService.save(createUser(t));
			return catDetailRepo.save(t);
		}
		t.setModifiedBy(userService.getCurrentUser());
		t.setDateModified(new Date());
		return catDetailRepo.save(t);
	}

	@Override
	public Boolean checkDuplicate(CatDetail current, CatDetail old) {
		if (current.getId() != null) {
			if (!current.getUserName().equals(old.getUserName())) {
				if (userService.findByUserName(current.getUserName()) != null) {
					return true;
				}
			}
		} else if (current.getId() == null) {
			if (userService.findByUserName(current.getUserName()) != null) {
				return true;
			}
			if (getByPatient(current.getPatient()) != null) {
				return true;
			}
		}
		return false;
	}

	@Override
	public CatDetail getByPatient(Patient patient) {
		return catDetailRepo.findByPatient(patient);
	}

	@Override
	public List<NameIdDTO> getCatPatients(CatDetail catDetail) {

		return catDetailRepo.getFacilityPatients(catDetail.getPrimaryClinic(), true, false);
	}

	private User createUser(CatDetail catDetail) {
		User user = new User();
		user.setPassword(catDetail.getPassword());
		user.setUserName(catDetail.getUserName());
		user.setUserType(UserType.CATS);
		user.setFirstName(catDetail.getPatient().getFirstName());
		user.setLastName(catDetail.getPatient().getLastName());
		user.setGender(catDetail.getPatient().getGender());
		Set<UserRole> userRoles = new HashSet<>();
		userRoles.add(userRoleService.getByName("ROLE_MOBILE"));
		user.setUserRoles(userRoles);
		return user;
	}

}
