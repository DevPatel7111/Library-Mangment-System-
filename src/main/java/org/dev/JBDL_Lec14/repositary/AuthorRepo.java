package org.dev.JBDL_Lec14.repositary;

import org.dev.JBDL_Lec14.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AuthorRepo extends JpaRepository<Author, Integer> {
    //
//    native query
    @Query(value = "SELECT * FROM author WHERE email = :email", nativeQuery = true)
    Author getAuthorByEmail(@Param("email") String email);
}
