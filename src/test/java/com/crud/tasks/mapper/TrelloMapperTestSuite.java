package com.crud.tasks.mapper;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.dto.TrelloBoardDto;
import com.crud.tasks.dto.TrelloCardDto;
import com.crud.tasks.dto.TrelloListDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TrelloMapperTestSuite {

    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    public void testMapToBoards() {
        //Given
        List<TrelloBoardDto> trelloBoardDtoList = List.of(new TrelloBoardDto("test_id","test_board",new ArrayList<>()));
        //When
        TrelloBoard trelloBoard = trelloMapper.mapToBoards(trelloBoardDtoList).get(0);
        //Then
        assertEquals(trelloBoardDtoList.get(0).getId(), trelloBoard.getId());
        assertEquals(trelloBoardDtoList.get(0).getName(), trelloBoard.getName());
        assertEquals(trelloBoardDtoList.get(0).getLists().size(),trelloBoard.getLists().size());
    }

    @Test
    public void testMapToBoardsDto() {
        //Given
        List<TrelloBoard> trelloBoardList = List.of(new TrelloBoard("test_id","test_board",new ArrayList<>()));
        //When
        TrelloBoardDto resultTrelloBoardDto = trelloMapper.mapToBoardsDto(trelloBoardList).get(0);
        //Then
        assertEquals(trelloBoardList.get(0).getId(), resultTrelloBoardDto.getId());
        assertEquals(trelloBoardList.get(0).getName(), resultTrelloBoardDto.getName());
        assertEquals(trelloBoardList.get(0).getLists().size(), resultTrelloBoardDto.getLists().size());
    }

    @Test
    public void testMapToList() {
        //Given
        List<TrelloListDto> trelloListsDto = List.of(new TrelloListDto("1", "list_name", false));
        //When
        TrelloList resultTrelloList = trelloMapper.mapToList(trelloListsDto).get(0);
        //Then
        assertEquals(trelloListsDto.get(0).getId(), resultTrelloList.getId());
        assertEquals(trelloListsDto.get(0).getName(), resultTrelloList.getName());
        assertEquals(trelloListsDto.get(0).isClosed(), resultTrelloList.isClosed());
    }

    @Test
    public void testMapToListDto() {
        //Given
        List<TrelloList> trelloLists = List.of(new TrelloList("1", "list_name", false));
        //When
        TrelloListDto resultTrelloListDto = trelloMapper.mapToListDto(trelloLists).get(0);
        //Then
        assertEquals(trelloLists.get(0).getId(), resultTrelloListDto.getId());
        assertEquals(trelloLists.get(0).getName(), resultTrelloListDto.getName());
        assertEquals(trelloLists.get(0).isClosed(), resultTrelloListDto.isClosed());
    }

    @Test
    public void testMapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("card_name", "test_description", "pos_test", "1");
        //When
        TrelloCardDto resultTrelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        //Then
        assertEquals(trelloCard.getName(), resultTrelloCardDto.getName());
        assertEquals(trelloCard.getDescription(), resultTrelloCardDto.getDescription());
        assertEquals(trelloCard.getPos(), resultTrelloCardDto.getPos());
        assertEquals(trelloCard.getListId(), resultTrelloCardDto.getListId());
    }

    @Test
    public void testMapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("card_name", "test_description", "pos_test", "1");
        //When
        TrelloCard resultTrelloCard = trelloMapper.mapToCard(trelloCardDto);
        //Then
        assertEquals(trelloCardDto.getName(), resultTrelloCard.getName());
        assertEquals(trelloCardDto.getDescription(), resultTrelloCard.getDescription());
        assertEquals(trelloCardDto.getPos(), resultTrelloCard.getPos());
        assertEquals(trelloCardDto.getListId(), resultTrelloCard.getListId());
    }
}
