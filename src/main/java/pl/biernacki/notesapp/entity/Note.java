package pl.biernacki.notesapp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @DateTimeFormat
    private Date date;


    @NotBlank(message = "Puste pole")
    @Size(min = 5,max = 100, message = "Pole ma mieć miedzy 5 a 100 znaków")
    private String title;
    @NotBlank(message = "Puste pole")
    @Size(min = 10,max = 255, message = "Pole ma mieć miedzy 10 a 255 znaków")
    private String description;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User users;
}
