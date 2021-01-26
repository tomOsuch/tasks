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
        TrelloList trelloList = new TrelloList("1", "Trello_list", false);
        List<TrelloList> trelloLists = List.of(trelloList);

        TrelloBoard trelloBoard = new TrelloBoard("1", "Trello_board_test", trelloLists);
        List<TrelloBoard> trelloBoards = List.of(trelloBoard);
        //When
        List<TrelloBoard> validTrelloBoard = trelloValidator.validateTrelloBoards(trelloBoards);
        //Then
        assertEquals(1, validTrelloBoard.size());
    }
}
