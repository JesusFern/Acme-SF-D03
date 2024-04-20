
package acme.features.developer.trainingModules;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.projects.Project;
import acme.entities.trainingModule.TrainingModule;
import acme.roles.Developer;

@Repository
public interface DeveloperTrainingModuleRepository extends AbstractRepository {

	@Query("select t from TrainingModule t where t.id = :id")
	TrainingModule findOneTrainingModuleById(int id);

	@Query("select t from TrainingModule t where t.developer.id = :developerId")
	Collection<TrainingModule> findAllTrainingModulesByDeveloperId(int developerId);

	@Query("select d from Developer d where d.id = :id")
	Developer findOneDeveloperById(int id);

	@Query("select p from Project p where p.id = :projectId")
	Project findOneProjectById(int projectId);

	@Query("select p from Project p")
	Collection<Project> findAllProjects();

	@Query("select t from TrainingModule t")
	Collection<TrainingModule> findAllTrainingModules();

}
