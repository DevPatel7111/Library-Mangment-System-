package org.dev.JBDL_Lec14.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 30)
    private String name ;

   @Column(nullable = false,unique = true)
    private String phoneNo;


   @Column(nullable = false,unique = true)
   private String email;


   private String address;

   @CreationTimestamp
    private Date creationDate;

   @UpdateTimestamp
    private Date updateDate;

   @Enumerated(value = EnumType.STRING)
   private UserType userType;

   @Enumerated
    private UserStatus userStatus;

   @OneToMany(mappedBy="user")
   private List<Book> booklist ;

   @OneToMany(mappedBy = "user")
   @JsonIgnoreProperties(value = {"user", "book"})
   private List<Transition> txnlist;








}
