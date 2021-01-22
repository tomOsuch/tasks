package com.crud.tasks.dto;

import com.crud.tasks.dto.TrelloDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AttachmentsByTypeDto {

    @JsonProperty("trello")
    private TrelloDto trello;
}
