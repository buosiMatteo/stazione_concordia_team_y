package it.euris.stazioneconcordia.controller;

import it.euris.stazioneconcordia.data.dto.*;
import it.euris.stazioneconcordia.data.model.Card;
import it.euris.stazioneconcordia.data.model.Labels;
import it.euris.stazioneconcordia.data.model.Lists;
import it.euris.stazioneconcordia.data.model.User;
import it.euris.stazioneconcordia.service.*;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/from-trello")
public class TrelloController {

    private BoardService boardService;

    private ListsService listsService;

    private CardService cardService;

    private LabelsService labelsService;

    private UserService userService;


    @GetMapping("/sync")
    public void getInfoFromTrello(@RequestParam String idBoard,@RequestParam String username, @RequestParam String key, @RequestParam String token){
       getBoardFromTrello(idBoard, key, token);
       getUserByUsername(username, key, token);
       getLabelsFromTrelloBoard(idBoard, key, token);
       ListsDTO[] listDTOs = getListsFromTrelloBoard(idBoard, key, token);
        for (ListsDTO listDTO: listDTOs) {
            getCardsFromTrelloList(listDTO.getId(), key, token);
        }
    }

    public BoardDTO getBoardFromTrello(@RequestParam String idBoard, @RequestParam String key, @RequestParam String token) {
        return boardService.getBoardFromTrello(idBoard, key, token).toDto();
    }

    public ListsDTO[] getListsFromTrelloBoard(@RequestParam String idBoard, @RequestParam String key, @RequestParam String token) {
        Lists[] lists = listsService.getListFromTrelloBoard(idBoard, key, token);
        ListsDTO[] listsDTOs = new ListsDTO[lists.length];
        for (int i = 0; i < lists.length; i++) {
            listsDTOs[i] = lists[i].toDto();
        }
        return listsDTOs;
    }

    public CardDTO[] getCardsFromTrelloList(@RequestParam String idList, @RequestParam String key, @RequestParam String token) {
        Card[] cards = cardService.getCardsFromTrelloList(idList, key, token);
        CardDTO[] cardDTOs = new CardDTO[cards.length];
        for (int i = 0; i < cards.length; i++) {
            cardDTOs[i] = cards[i].toDto();
        }
        return cardDTOs;
    }

    public UserDTO getUserByUsername(@RequestParam String username, @RequestParam String key, @RequestParam String token) {
        User user = userService.getUserFromTrello(username, key, token);
        return user.toDto();
    }


    public LabelsDTO[] getLabelsFromTrelloBoard(@RequestParam String idBoard, @RequestParam String key, @RequestParam String token) {
        Labels[] labels = labelsService.getLabelsFromTrelloBoard(idBoard, key, token);
        LabelsDTO[] labelsDTOs = new LabelsDTO[labels.length];
        for (int i = 0; i < labels.length; i++) {
            labelsDTOs[i] = labels[i].toDto();
        }
        return labelsDTOs;
    }
}