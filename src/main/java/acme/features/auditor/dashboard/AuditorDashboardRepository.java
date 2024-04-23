/*
 * AdministratorDashboardRepository.java
 *
 * Copyright (C) 2012-2024 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.auditor.dashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;

@Repository
public interface AuditorDashboardRepository extends AbstractRepository {

	@Query("select count (ca) from CodeAudit ca where ca.type = acme.entities.codeAudits.Type.Static and ca.draftMode=false")
	Integer totalNumberOfStaticCodeAudit(int id);

	@Query("select count (ca) from CodeAudit ca where ca.type = acme.entities.codeAudits.Type.Dynamic and ca.draftMode=false")
	Integer totalNumberOfDynamicCodeAudit(int id);

	@Query("select avg( select count(ar) from AuditRecord ar where a.id= :id and ar.codeAudit.draftMode = false) from Auditor a")
	Double averageNumberOfAuditRecords(int id);

	@Query("select avg( select count(ar) from AuditRecord ar where a.id= :id and ar.codeAudit.draftMode = false) from Auditor a")
	Double deviationNumberOfAuditRecords(int id);

	@Query("select min( select count(ar) from AuditRecord ar where a.id= :id and ar.codeAudit.draftMode = false) from Auditor a")
	Double minimumNumberOfAuditRecords(int id);

	@Query("select max( select count(ar) from AuditRecord ar where a.id= :id and ar.codeAudit.draftMode = false) from Auditor a")
	Double maximumNumberOfAuditRecords(int id);

	@Query("select avg( select count(ar) from AuditRecord ar where a.id= :id and ar.codeAudit.draftMode = false) from Auditor a")
	Double averageNumberOfPeriod(int id);

	@Query("select avg( select count(ar) from AuditRecord ar where a.id= :id and ar.codeAudit.draftMode = false) from Auditor a")
	Double deviationNumberOfPeriod(int id);

	@Query("select min( select count(ar.periodEnd - ar.periodStart) from AuditRecord ar where a.id= :id and ar.codeAudit.draftMode = false) from Auditor a")
	Double minimumNumberOfPeriod(int id);

	@Query("select max( select count(ar.periodEnd - ar.periodStart) from AuditRecord ar where a.id= :id and ar.codeAudit.draftMode = false) from Auditor a")
	Double maximumNumberOfPeriod(int id);

}
