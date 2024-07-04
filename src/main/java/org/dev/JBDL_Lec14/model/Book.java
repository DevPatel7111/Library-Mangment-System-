package org.dev.JBDL_Lec14.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString
@Builder
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(length=30)
  private String title;



  @Column(unique=true)
  private String bookNo;

  @Column
  private BookType bookType;

  private Integer securityAmount;



    @CreationTimestamp
    private Date creationDate;

    @UpdateTimestamp
    private Date updateDate;

    @ManyToOne
    @JoinColumn
    private User user ;

  @ManyToOne
  @JoinColumn

  private Author author;

  @OneToMany(mappedBy = "book")
  private List<Transition> txnlist;



}
// user can have multiple books
