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
        List<TrelloBoardDto> trelloBoardDtoList = List.of(new TrelloBoardDto("test_id", "test_board", new ArrayList<>()));
        //When
        List<TrelloBoard> resultTrelloBoard = trelloMapper.mapToBoards(trelloBoardDtoList);
        //Then
        assertEquals(trelloBoardDtoList.get(0).getId(), resultTrelloBoard.get(0).getId());
        assertEquals(trelloBoardDtoList.get(0).getName(), resultTrelloBoard.get(0).getName());
        assertEquals(trelloBoardDtoList.get(0).getLists().size(),resultTrelloBoard.get(0).getLists().size());
        assertEquals(trelloBoardDtoList.size(), resultTrelloBoard.size());
    }

    @Test
    public void testMapToBoardsDto() {
        //Given
        List<TrelloBoard> trelloBoardList = List.of(new TrelloBoard("test_id", "test_board", new ArrayList<>()));
        //When
        List<TrelloBoardDto> resultTrelloBoardDto = trelloMapper.mapToBoardsDto(trelloBoardList);
        //Then
        assertEquals(trelloBoardList.get(0).getId(), resultTrelloBoardDto.get(0).getId());
        assertEquals(trelloBoardList.get(0).getName(), resultTrelloBoardDto.get(0).getName());
        assertEquals(trelloBoardList.get(0).getLists().size(), resultTrelloBoardDto.get(0).getLists().size());
        assertEquals(trelloBoardList.size(), resultTrelloBoardDto.size());
    }

    @Test
    public void testMapToList() {
        //Given
        List<TrelloListDto> trelloListsDto = List.of(new TrelloListDto("1", "list_name", false));
        //When
        List<TrelloList> resultTrelloList = trelloMapper.mapToList(trelloListsDto);
        //Then
        assertEquals(trelloListsDto.get(0).getId(), resultTrelloList.get(0).getId());
        assertEquals(trelloListsDto.get(0).getName(), resultTrelloList.get(0).getName());
        assertEquals(trelloListsDto.get(0).isClosed(), resultTrelloList.get(0).isClosed());
        assertEquals(trelloListsDto.size(), resultTrelloList.size());
    }

    @Test
    public void testMapToListDto() {
        //Given
        List<TrelloList> trelloLists = List.of(new TrelloList("1", "list_name", false));
        //When
        List<TrelloListDto> resultTrelloListDto = trelloMapper.mapToListDto(trelloLists);
        //Then
        assertEquals(trelloLists.get(0).getId(), resultTrelloListDto.get(0).getId());
        assertEquals(trelloLists.get(0).getName(), resultTrelloListDto.get(0).getName());
        assertEquals(trelloLists.get(0).isClosed(), resultTrelloListDto.get(0).isClosed());
        assertEquals(trelloLists.size(), resultTrelloListDto.size());
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
