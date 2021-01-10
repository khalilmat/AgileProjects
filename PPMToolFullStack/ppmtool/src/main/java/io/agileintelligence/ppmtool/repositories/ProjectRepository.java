package io.agileintelligence.ppmtool.repositories;

import javax.validation.OverridesAttribute;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.agileintelligence.ppmtool.domain.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {
    
    Project findByProjectIdentifier(String projectIdentifier);

    /*This is a method to find all products defined in the repository layer
      When this is defined, it needs to be wired up in the serivce layer*/
    @Override
    Iterable<Project> findAll();

}
