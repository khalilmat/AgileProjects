package io.agileintelligence.ppmtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.agileintelligence.ppmtool.domain.Project;
import io.agileintelligence.ppmtool.exceptions.ProjectIdException;
import io.agileintelligence.ppmtool.repositories.ProjectRepository;

@Service
public class ProjectService {
    
    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project) {
        try{
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        }catch (Exception e){
            throw new ProjectIdException("Project Id '"+project.getProjectIdentifier().toUpperCase()+"' already exists");
        }

    }

    public Project findProjectByIdentifier(String projectId) {
        
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());

        if (project == null) {
            throw new ProjectIdException("Project Id '"+projectId+"' does not exist");
        }
        
        return project;
    }

    /*This wires the findsAllProjects from the repository layer
      It returns findAll of type ProjectRepository 
      After this something has to be setup in the project controller
      to get the route setup to find all proejcts*/
      
    public Iterable<Project> findAllProjects(){
        return projectRepository.findAll();

    }

    /* create a method to delete a project by passing the projectid to 
       the findAllProjects methods defined above to find it then
       error handle it if the project id does not exist then
       delete project contents if it exists. Next we need to 
       create a handler in the project controller with an address to find it and 
       delete it by passing project id info*/

    public void deleteProjectByIdentifier(String projectId) {
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());

        if (project == null) {
            throw new ProjectIdException("Cannot delete Project with ID '"+projectId+"'. This project does not exist");
        }

        projectRepository.delete(project);
    }

}
