package org.sda.bms.model;

import javax.persistence.*;

@Entity
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "score")
    private String score;
    @Column(name = "comment")
    private String comment;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    public Review() {
    }

    public Review(String score, String comment) {
        this.score = score;
        this.comment = comment;
    }

    public Review(int score, String comment) {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", score='" + score + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
