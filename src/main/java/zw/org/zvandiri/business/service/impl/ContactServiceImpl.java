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

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import zw.org.zvandiri.business.domain.Contact;
import zw.org.zvandiri.business.domain.InvestigationTest;
import zw.org.zvandiri.business.domain.Patient;
import zw.org.zvandiri.business.domain.User;
import zw.org.zvandiri.business.domain.util.TestType;
import zw.org.zvandiri.business.repo.ContactRepo;
import zw.org.zvandiri.business.service.ContactService;
import zw.org.zvandiri.business.service.InvestigationTestService;
import zw.org.zvandiri.business.service.UserService;
import zw.org.zvandiri.business.util.UUIDGen;

/**
 *
 * @author Judge Muzinda
 */
@Repository
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class ContactServiceImpl implements ContactService {

	@Resource
	private ContactRepo contactRepo;
	@Resource
	private UserService userService;
	@Resource
	private InvestigationTestService investigationTestService;
	private final Logger LOG = Logger.getLogger(ContactServiceImpl.class);

	@Override
	public List<Contact> getAll() {
		return contactRepo.findByAllContacts();
	}

	@Override
	public Contact get(String id) {
		if (id == null) {
			throw new IllegalStateException("Item to be does not exist :" + id);
		}

		return contactRepo.findById(id).get();
	}

	@Override
	public void delete(Contact t) {
		if (t.getId() == null) {
			throw new IllegalStateException("Item to be deleted is in an inconsistent state");
		}
		contactRepo.delete(t);
	}

	@Override
	public List<Contact> getPageable() {
		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
																		// Tools | Templates.
	}

	@Override
	@Transactional
	public Contact save(Contact t) {

		if (t.getId() == null || StringUtils.isBlank(t.getId())) {
			try {
				t.setId(UUIDGen.generateUUID());
				t.setCreatedBy(userService.getCurrentUser());
				t.setDateCreated(new Date());
				return contactRepo.save(t);
			} catch (Exception e) {
				return null;
			}
		}
		t.setModifiedBy(userService.getCurrentUser());
		t.setDateModified(new Date());
		return contactRepo.save(t);
	}

	@Override
	public Boolean checkDuplicate(Contact current, Contact old) {
		throw new UnsupportedOperationException("No relevant");
	}

	@Override
	public List<Contact> getByPatient(Patient patient) {
		// patient.getContacts();
		return contactRepo.findByPatient(patient);
	}

	@Override
	public List<Contact> findByPatientAndContactDate(Patient patient, Date start, Date end) {
		return contactRepo.findByPatientAndContactDateBetween(patient, start, end);
	}

	@Override
	public List<Contact> findByReferredPersonAndOpen(User referredPerson) {
		// return
		// contactRepo.findByReferredPersonAndOpenOrderByContactDateDesc(referredPerson,
		// Boolean.FALSE);
		return null;
	}

	@Override
	public Contact findLatestContact(Patient patient) {
		for (Contact contact : contactRepo.findTop1ByPatientOrderByContactDateDesc(patient)) {
			return contact;
		}
		return null;
	}

	@Override
	@Transactional
	public void saveContactDTO(Contact contact) {

		save(contact);
		if (contact.getViralLoad() != null && (contact.getViralLoad().getPatient() != null
				&& StringUtils.isNotBlank(contact.getViralLoad().getPatient().getId()))) {
			InvestigationTest viralLoad = contact.getViralLoad();

			if (viralLoad.getResult() == null) {
				viralLoad.setTestType(TestType.VIRAL_LOAD);
				investigationTestService.save(viralLoad);
			}
		}
		if (contact.getCd4Count() != null && (contact.getCd4Count().getPatient() != null
				&& StringUtils.isNotBlank(contact.getCd4Count().getPatient().getId()))) {
			InvestigationTest cd4Count = contact.getCd4Count();

			if (cd4Count.getResult() == null) {
				cd4Count.setTestType(TestType.CD4_COUNT);
				investigationTestService.save(cd4Count);
			}
		}

	}

	@Override
	public List<Contact> findByDistrictInGivenTime(Date start, Date end, String district) {
		return contactRepo.findByDistrictInGivenTime(start, end,district);
	}

	@Override
	public List<Contact> findByFacilityInGivenTime(Date start, Date end, String facility) {
		return contactRepo.findByFacilityInGivenTime(start,end,facility);
	}

}
