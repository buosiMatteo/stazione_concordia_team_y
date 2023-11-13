package it.euris.stazioneconcordia.service;

import it.euris.stazioneconcordia.data.model.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User insert(User user);

    User update(User user);

    Boolean deleteById(Long idUser);

    User findById(Long idUser);


    User getUserByUserName(String userName);

    User getUserByIdTrelloFromDb(String idTrello);
}
