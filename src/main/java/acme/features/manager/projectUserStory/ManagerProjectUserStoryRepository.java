
package acme.features.manager.projectUserStory;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.projects.Project;
import acme.entities.projects.ProjectUserStory;
import acme.entities.projects.UserStory;

@Repository
public interface ManagerProjectUserStoryRepository extends AbstractRepository {

	@Query("select pu from ProjectUserStory pu where pu.project.manager.id = :managerId")
	Collection<ProjectUserStory> findManyProjectUserStoriesByManagerId(int managerId);

	@Query("select p from Project p where p.id = :id")
	Project findOneProjectById(int id);

	@Query("select u from UserStory u where u.id = :id")
	UserStory findOneUserStoryById(int id);

	@Query("select p from Project p where p.manager.id = :managerId")
	Collection<Project> findManyProjectsByManagerId(int managerId);

	@Query("select u from UserStory u where u.manager.id = :managerId")
	Collection<UserStory> findManyUserStoriesByManagerId(int managerId);

}
