package com.college.service.assignment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.entity.assignment.Notes;
import com.college.repository.assignment.NotesRepository;

@Service
public class NotesService {

	@Autowired
    private NotesRepository repo;

    public void saveNotes(Notes notes){
        repo.save(notes);
    }

    public List<Notes> getNotes(String department,String year){
        return repo.findByDepartmentAndYear(department,year);
    }
}
