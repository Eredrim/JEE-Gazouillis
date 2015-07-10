package main.java.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "keyword")
public class Keyword {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(unique = true)
    private String word;

    @ManyToMany(mappedBy = "keywords")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Message> messages = new ArrayList<Message>();

    public Keyword(String word) {
        this.word = word;
    }

    public Keyword() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> tours) {
        this.messages = tours;
    }

    public Message getMessage(int index) {
        return this.messages.get(index);
    }

    public void addMessage(Message tour) {
        this.messages.add(tour);
    }
}