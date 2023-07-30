package io.github.saviomisael.services;

import io.github.saviomisael.exceptions.ResourceNotFoundException;
import io.github.saviomisael.models.File;
import io.github.saviomisael.repositories.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileService {
    private FileRepository repository;

    @Autowired
    public FileService(FileRepository repository) {
        this.repository = repository;
    }

    public void saveFile(File file) {
        this.repository.save(file);
    }

    public File getFileById(int id) {
        return this.repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("File not found."));
    }
}
