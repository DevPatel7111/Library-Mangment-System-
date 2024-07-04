package org.dev.JBDL_Lec14.service;

import org.dev.JBDL_Lec14.model.Author;
import org.dev.JBDL_Lec14.repositary.AuthorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
  @Autowired
  private AuthorRepo authorRepo;

    public Author getauthordata(String email) {
 return    authorRepo.getAuthorByEmail(email);
    }
}
