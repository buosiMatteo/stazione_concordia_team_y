package it.euris.stazioneconcordia.data.dto;

import it.euris.stazioneconcordia.data.dto.archetype.Dto;
import it.euris.stazioneconcordia.data.dto.archetype.Model;
import it.euris.stazioneconcordia.model.Card;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static it.euris.stazioneconcordia.utility.DataConversionUtils.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardDTO implements Dto {

    private String id;
    private String idList;
    private String name;
    private String position;
    private String priority;
    private String description;
    private String closed;
    private String expirationDate;
    private String dateLastActivity;

    @Override
    public Card toModel() {
        return Card
                .builder()
                .id(stringToLong(id))
                .idList(stringToLong(idList))
                .name(name)
                .position(stringToLong(position))
                .priority(stringToPriority(priority))
                .descriptions(description)
                .closed(stringToBoolean(closed))
                .expirationDate(stringToLocalDateTime(expirationDate))
                .dateLastActivity(stringToLocalDateTime(dateLastActivity))
                .build();
    }
}