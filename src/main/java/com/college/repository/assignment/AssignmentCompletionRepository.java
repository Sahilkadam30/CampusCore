package com.college.repository.assignment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.college.entity.assignment.AssignmentCompletion;

@Repository
public interface AssignmentCompletionRepository extends JpaRepository<AssignmentCompletion,Long>{

	boolean existsByAssignmentIdAndPrn(Long assignmentId,Long prn);

    int countByAssignmentId(Long assignmentId);
    List<AssignmentCompletion> findByAssignmentId(Long assignmentId);
}
