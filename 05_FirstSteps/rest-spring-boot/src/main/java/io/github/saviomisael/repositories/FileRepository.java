package io.github.saviomisael.repositories;

import io.github.saviomisael.models.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Integer> {
}
