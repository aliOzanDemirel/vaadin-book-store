package app.domain;

import app.common.Awards;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private String id;
    private String name;
    private String author;
    private String originalLanguage;
    private String pageSize;
    private LocalDate publishedDate;
    private Awards awardWon;

}
