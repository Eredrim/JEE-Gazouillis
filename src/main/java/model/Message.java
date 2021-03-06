package main.java.model;

import main.java.dao.KeywordDAO;
import main.java.utils.HtmlEscape;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@Table(name = "MESSAGE")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String content;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "is_published")
    private Boolean isPublished;

    @ManyToOne
    @JoinColumn(name = "id_PERSON")
    private Person person;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "HAVE",
            joinColumns = {@JoinColumn(name = "id")},
            inverseJoinColumns = {@JoinColumn(name = "id_keyword")})
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Keyword> keywords = new ArrayList<Keyword>();

    /**
     * Personnes qui ont partagé ce message
     */
    @ManyToMany(mappedBy = "messagesShared", cascade = {CascadeType.ALL})
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Person> sharers = new ArrayList<Person>();

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
        this.content = HtmlEscape.escapeHtml(content);
        this.parseKeywords(content);
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
        person.addMessage(this);
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
        sharer.addMessageShared(this);
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    private void parseKeywords(String content) {
        Pattern pattern = Pattern.compile("#(\\w+)\\b");
        Matcher matcher = pattern.matcher(content);

        KeywordDAO keywordDAO = new KeywordDAO();
        this.keywords = new ArrayList<>();

        while (matcher.find()) {
            String word = matcher.group(1);
            Keyword keyword = keywordDAO.findByWord(word);

            if (keyword == null) {
                keyword = new Keyword(word);
                keywordDAO.insert(keyword);
            }

            this.keywords.add(keyword);
        }
    }

    @Override
    public boolean equals(Object object)
    {
        if (object != null && object instanceof Message)
        {
            return (this.id == ((Message) object).getId());
        }

        return false;
    }

    @Override
    public int hashCode() {
        return this.id;
    }
}
