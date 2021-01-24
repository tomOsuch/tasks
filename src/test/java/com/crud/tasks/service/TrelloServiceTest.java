package com.crud.tasks.service;

import com.crud.tasks.client.TrelloClient;
import com.crud.tasks.dto.CreatedTrelloCardDto;
import com.crud.tasks.dto.TrelloBoardDto;
import com.crud.tasks.dto.TrelloCardDto;
import com.crud.tasks.dto.TrelloListDto;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TrelloServiceTest {

    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private TrelloClient trelloClient;

    @Test
    public void testFetchTrelloBoards() {
        //Given
        List<TrelloListDto> trelloListDto = List.of(new TrelloListDto("1", "test_name", false));
        List<TrelloBoardDto> trelloBoardDtoList = List.of(new TrelloBoardDto("test_id", "test_name", trelloListDto));

        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoardDtoList);
        //When
        List<TrelloBoardDto> resultTrelloBoardList = trelloService.fetchTrelloBoards();
        //Then
        assertEquals(1, resultTrelloBoardList.size());
    }

    @Test
    public void testCreateNullTrelloCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("test_cart_name", "test_card_description", "top", "test_idList");
        CreatedTrelloCardDto createdTrelloCardDto = null;

        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(createdTrelloCardDto);
        //When
        CreatedTrelloCardDto resultCreatedTrelloCardDto = trelloService.createTrelloCard(trelloCardDto);
        //Then
        assertNull(resultCreatedTrelloCardDto);
    }
}
