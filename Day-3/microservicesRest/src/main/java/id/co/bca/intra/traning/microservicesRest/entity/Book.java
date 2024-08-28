package id.co.bca.intra.traning.microservicesRest.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "book")
public class Book {
    @Id
    @JsonProperty("id")
    private long id;
    @JsonProperty("judul")
    private String judul;
    @JsonProperty("penulis")
    private String penulis;
    @JsonProperty("tahun_terbit")
    private Integer tahunTerbit;
}
