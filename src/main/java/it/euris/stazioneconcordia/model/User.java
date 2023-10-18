package it.euris.stazioneconcordia.model;

import it.euris.stazioneconcordia.data.dto.UserDTO;
import it.euris.stazioneconcordia.data.dto.archetype.Dto;
import it.euris.stazioneconcordia.data.dto.archetype.Model;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static it.euris.stazioneconcordia.utility.DataConversionUtils.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User implements Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "bio")
    private String bio;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "email")
    private String email;

    @Column(name = "status")
    @Builder.Default
    private Boolean status=false;

    @Builder.Default
    @OneToMany(mappedBy = "idUser", fetch = FetchType.EAGER)
    private List<Card> cards = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy="idUser", fetch = FetchType.EAGER)
    private List<Comment> commentList = new ArrayList<>();

    @Override
    public UserDTO toDto() {
        return UserDTO.builder()
                .id(numberToString(id))
                .fullName(fullName)
                .bio(bio)
                .avatarUrl(avatarUrl)
                .email(email)
                .status(booleanToString(status))
                .build();
    }
}
