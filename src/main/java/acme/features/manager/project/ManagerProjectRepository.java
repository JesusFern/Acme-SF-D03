
package acme.features.manager.project;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.projects.Project;
import acme.roles.Manager;

@Repository
public interface ManagerProjectRepository extends AbstractRepository {

	@Query("select p from Project p where p.id = :id")
	Project findOneProjectById(int id);

	@Query("select p from Project p where p.manager.id = :managerId")
	Collection<Project> findManyProjectsByManagerId(int managerId);

	@Query("select m from Manager m where m.id = :id")
	Manager findOneManagerById(int id);

	@Query("select p from Project p where p.code = :code")
	Project findOneProjectByCode(String code);

	//	@Query("select u from UserStory u where u.project.id = :projectId")
	//	Collection<UserStory> findManyUserStoriesByProjectId(int projectId);
}
