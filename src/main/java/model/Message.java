package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "MESSAGE")
public class Message {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String content;

    @Column(name = "is_published")
    private Boolean isPublished;

    @ManyToOne
    private Person person;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="HAVE",
            joinColumns={@JoinColumn(name="id")},
            inverseJoinColumns={@JoinColumn(name="id_keyword")})
    private List<Keyword> keywords = new ArrayList<Keyword>();

    /**
     * Personnes qui ont partagé ce message
     */
    @ManyToMany(mappedBy = "messagesShared")
    private List<Person> sharers = new ArrayList<Person>();

    public Message(Integer id, String content, Boolean isPublished, Person person) {
        this.id = id;
        this.content = content;
        this.isPublished = isPublished;
        this.person = person;
    }

    public Message() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getIsPublished() {
        return isPublished;
    }

    public void setIsPublished(Boolean isPublished) {
        this.isPublished = isPublished;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<Keyword> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<Keyword> keywords) {
        this.keywords = keywords;
    }
    
    public Keyword getKeyword(int index) {
        return this.keywords.get(index);
    }

    public void addKeyword(Keyword keyword) {
        this.keywords.add(keyword);
    }
    
    public List<Person> getSharers() {
        return sharers;
    }

    public void setSharers(List<Person> sharers) {
        this.sharers = sharers;
    }

    public Person getSharer(int index) {
        return this.sharers.get(index);
    }

    public void addSharer(Person sharer) {
        this.sharers.add(sharer);
    }
}
