package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TrelloValidatorTest {

    @Autowired
    private TrelloValidator trelloValidator;

    @Test
    public void testValidatorTrelloBoards() {
        //Given
        TrelloList trelloList = new TrelloList("1", "Trello_list_name", false);
        List<TrelloList> trelloLists = List.of(trelloList);

        TrelloBoard trelloBoard = new TrelloBoard("1", "Trello_board_test", trelloLists);
        List<TrelloBoard> trelloBoards = List.of(trelloBoard);
        //When
        List<TrelloBoard> validTrelloBoard = trelloValidator.validateTrelloBoards(trelloBoards);
        //Then
        assertEquals(1, validTrelloBoard.size());
    }

    @Test
    public void testNotValidatorTrelloBoards() {
        //Given
        List<TrelloList> trelloLists = List.of(new TrelloList("1", "my_list", false));
        List<TrelloBoard> trelloBoards = List.of(new TrelloBoard("1", "test", trelloLists));
        //When
        List<TrelloBoard> validBoards = trelloValidator.validateTrelloBoards(trelloBoards);
        //Then
        assertEquals(0, validBoards.size());
    }

}
