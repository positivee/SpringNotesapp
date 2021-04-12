package pl.biernacki.notesapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.biernacki.notesapp.entity.Note;

public interface NoteRepository extends JpaRepository<Note, Long> {

}
