package it.euris.stazioneconcordia.model;

import it.euris.stazioneconcordia.data.dto.CardDTO;
import it.euris.stazioneconcordia.data.dto.archetype.Dto;
import it.euris.stazioneconcordia.data.dto.archetype.Model;
import it.euris.stazioneconcordia.enums.Priority;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static it.euris.stazioneconcordia.utility.DataConversionUtils.*;
import static it.euris.stazioneconcordia.utility.DataConversionUtils.stringToLocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "card")
public class Card implements Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "position")
    private Long position;

    @Column(name = "priority")
    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Column(name = "description")
    private String description;

    @Column(name = "id_list")
    private String idList;

    @Column(name = "closed")
    @Builder.Default
    private Boolean closed = false;

    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;

    @Column(name = "date_last_activity")
    private LocalDateTime dateLastActivity;

    @Column(name = "id_user")
    private Long idUser;

    @Builder.Default
    @OneToMany(mappedBy = "idCard", fetch = FetchType.EAGER)
    private List<Comment> commentList = new ArrayList<>();

    @Override
    public CardDTO toDto() {
        return CardDTO.builder()
                .id(numberToString(id))
                .idList(numberToString(idList))
                .name(name)
                .poition(numberToString(position))
                .priority(priorityToString(priority))
                .description(description)
                .closed(booleanToString(closed))
                .expirationDate(localDateTimeToString(expirationDate))
                .dateLastActivity(localDateTimeToString(dateLastActivity))
                .build();
    }
}
