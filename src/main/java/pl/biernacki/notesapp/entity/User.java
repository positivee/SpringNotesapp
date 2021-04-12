package pl.biernacki.notesapp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;


   @Email(message = "Nie poprawny format maila")
    @NotBlank(message = "Nie może być pustym polem")
   @Size(min = 6,max = 20,message = "Pole ma mieć miedzy 6 a 20 znaków")
    private String email;


    @Column(unique = true)
    @NotBlank(message = "Nie może być pustym polem")
   @Size(min = 3,max = 20,message = "Pole ma mieć miedzy 3 a 20 znaków")
    private String username;


    @NotBlank(message = "Nie może być pustym polem")
   @Size(min = 4,max = 100, message = "Pole ma mieć miedzy 4 a 100 znaków")
    private String password;

    @OneToMany(cascade=CascadeType.ALL, orphanRemoval = true,  fetch = FetchType.LAZY, mappedBy = "users")
    private List<Note> notesList = new ArrayList<>();



}
