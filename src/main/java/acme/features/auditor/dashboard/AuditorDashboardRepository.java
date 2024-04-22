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

	@Query("select count (ca) from CodeAudit ca where ca.type = acme.entities.codeAudits.Type.Static")
	Integer totalNumberOfStaticCodeAudit();

	@Query("select count (ca) from CodeAudit ca where ca.type = acme.entities.codeAudits.Type.Dynamic")
	Integer totalNumberOfDynamicCodeAudit();

	//	@Query("select avg(ar) from (select count(ar) from AuditRecord ar) ar")
	//	Double averageNumberOfAuditRecords();

	//	@Query("select stdev(select count(ar) from AuditRecord ar")
	//	Double deviationNumberOfAuditRecords();
	//
	//	@Query("select min(select count(ar) from AuditRecord ar")
	//	Double minimumNumberOfAuditRecords();
	//
	//	@Query("select avg(ar) from (select count(ar) from AuditRecord ar) ar")
	//	Double maximumNumberOfAuditRecords();
	//
	//	@Query("select avg(ar) from (select count(ar) from AuditRecord ar) ar")
	//	Double averageNumberOfPeriod();
	//
	//	@Query("select avg(ar) from (select count(ar) from AuditRecord ar) ar")
	//	Double deviationNumberOfPeriod();
	//
	//	@Query("select avg(ar) from (select count(ar) from AuditRecord ar) ar")
	//	Double minimumNumberOfPeriod();
	//
	//	@Query("select avg(ar) from (select count(ar) from AuditRecord ar) ar")
	//	Double maximumNumberOfPeriod();

}
