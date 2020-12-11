package com.crud.tasks.client;

import com.crud.tasks.config.TrelloConfig;
import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class TrelloClientTest {

    @InjectMocks
    private TrelloClient trelloClient;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private TrelloConfig trelloConfig;

    @Before("")
    void beforeAll() {
        when(trelloConfig.getTrelloApiEndpoint()).thenReturn("http://test.com");
        when(trelloConfig.getTrelloAppKey()).thenReturn("test");
        when(trelloConfig.getTrelloToken()).thenReturn("test");
        when(trelloConfig.getTrelloUsername()).thenReturn("test");
    }

    @Test
    void shouldFetchTrelloBoards() throws URISyntaxException {
        //Given
        TrelloBoardDto[] trelloBoards = new TrelloBoardDto[1];
        trelloBoards[0] = new TrelloBoardDto("test_id", "test_board", new ArrayList<>());

        URI uri = new URI("http://test.com/members/test/boards?key=test&token=test&fields=name,id&lists=all");
        when(restTemplate.getForObject(uri,TrelloBoardDto[].class)).thenReturn(trelloBoards);

        //When
        List<TrelloBoardDto> fetchedTrelloBoards = trelloClient.getTrelloBoards();

        //Then
        assertEquals(1,fetchedTrelloBoards.size());
        assertEquals("test_id",fetchedTrelloBoards.get(0).getId());
        assertEquals("test_board",fetchedTrelloBoards.get(0).getName());
        assertEquals(new ArrayList<>(),fetchedTrelloBoards.get(0).getLists());
    }

    @Test
    void shouldCreateCard() throws URISyntaxException {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto(
                "Test task",
                "Test Description",
                "top",
                "test_id"
        );
        URI uri = new URI("http://test.com/cards?key=test&token=test&name=Test%20task&desc=Test%20Description&pos=top&idList=test_id");

        CreatedTrelloCard createdTrelloCard = new CreatedTrelloCard(
                "1",
                "Test task",
                "http://test.com",
                null
        );

        when(restTemplate.postForObject(uri,null,CreatedTrelloCard.class)).thenReturn(createdTrelloCard);

        //When
        CreatedTrelloCard newCard = trelloClient.createNewCard(trelloCardDto);

        //Then
        assertEquals("1",newCard.getId());
        assertEquals("Test task", newCard.getName());
        assertEquals("http://test.com",newCard.getShortUrl());
        assertNull(newCard.getBadges());
    }

    @Test
    void shouldReturnEmptyList() throws URISyntaxException {
        //Given
        String url = "http://test.com/members/test/boards?key=test&token=test&fields=name,id&lists=all";
        URI uri = new URI(url);
        when(restTemplate.getForObject(uri,TrelloBoardDto[].class)).thenReturn(null);

        //When
        List<TrelloBoardDto> fetchedTrelloBoards = trelloClient.getTrelloBoards();

        //Then
        assertEquals(0,fetchedTrelloBoards.size());
        assertEquals(Collections.emptyList(),fetchedTrelloBoards);
    }
}