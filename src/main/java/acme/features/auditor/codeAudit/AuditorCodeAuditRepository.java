
package acme.features.auditor.codeAudit;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.codeAudits.CodeAudit;
import acme.entities.projects.Project;
import acme.roles.Auditor;

@Repository
public interface AuditorCodeAuditRepository extends AbstractRepository {

	@Query("select ca from CodeAudit ca where ca.id = :id")
	CodeAudit findOneCodeAuditById(int id);

	@Query("select ca from CodeAudit ca where ca.auditor.id = :auditorId")
	Collection<CodeAudit> findManyCodeAuditByAuditorId(int auditorId);

	@Query("select a from Auditor a where a.id = :id")
	Auditor findOneAuditorById(int id);

	@Query("select p from Project p where p.id = :projectId")
	Project findOneProjectById(int projectId);

	@Query("select p from Project p where p.id not in (select ca.project.id from CodeAudit ca where ca.auditor.id = :auditorId)")
	Collection<Project> findManyAvailableProjectByAuditorId(int auditorId);

}
