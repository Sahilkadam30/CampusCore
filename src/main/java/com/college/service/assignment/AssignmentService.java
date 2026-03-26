package com.college.service.assignment;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.dto.CompletedStudentDTO;
import com.college.entity.assignment.Assignment;
import com.college.entity.assignment.AssignmentCompletion;
import com.college.entity.login.Student;
import com.college.repository.assignment.AssignmentCompletionRepository;
import com.college.repository.assignment.AssignmentRepository;
import com.college.repository.notify.StudentRepository;

@Service
public class AssignmentService {

	@Autowired
    private AssignmentRepository repo;

    @Autowired
    private AssignmentCompletionRepository completionRepo;
    
    @Autowired
    private StudentRepository studentRepo;


    public List<Assignment> getRecentAssignments(String department, String year){

        return repo.findTop2ByDepartmentAndYearOrderByIdDesc(department, year);
    }
    public List<Assignment> getAllAssignments(){
        return repo.findAll();
    }
    
    public void saveAssignment(Assignment assignment){
        repo.save(assignment);
    }
    public void deleteAssignment(Long id) {
        repo.deleteById(id);
    }
    public List<Assignment> getAssignments(String department,String year){
        return repo.findByDepartmentAndYear(department,year);
    }

    public boolean isCompleted(Long assignmentId, Long prn){
        return completionRepo.existsByAssignmentIdAndPrn(assignmentId, prn);
    }

    public void completeAssignment(Long assignmentId,Long prn){

        if(!completionRepo.existsByAssignmentIdAndPrn(assignmentId,prn)){

            AssignmentCompletion completion = new AssignmentCompletion();
            completion.setAssignmentId(assignmentId);
            completion.setPrn(prn);

            completionRepo.save(completion);

            Assignment assignment = repo.findById(assignmentId).get();
            assignment.setCompletedCount(
                    completionRepo.countByAssignmentId(assignmentId)
            );

            repo.save(assignment);
        }
    }
    
    public List<CompletedStudentDTO> getCompletedStudents(Long assignmentId){

        List<AssignmentCompletion> completions =
                completionRepo.findByAssignmentId(assignmentId);

        List<CompletedStudentDTO> list = new ArrayList<>();

        for(AssignmentCompletion c : completions){

            Student s = studentRepo.findByPrn(c.getPrn()).orElse(null);

            if(s != null){
                list.add(new CompletedStudentDTO(
                        s.getPrn(),
                        s.getFirstName(),
                        s.getDepartment(),
                        s.getYear()
                ));
            }
        }

        return list;
    }
}
