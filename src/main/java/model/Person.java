package main.java.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PERSON")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    private String name;
    private String mail;
    private String city;

    @Column(unique = true)
    //TODO : ajouter un index sur le username en BDD
    private String username;
    private String password;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Message> messages = new ArrayList<Message>();

    /**
     * Correspond aux personnes qui nous suivent
     */
    @ManyToMany(cascade = {CascadeType.ALL})
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "FOLLOW",
            joinColumns = {@JoinColumn(name = "id")},
            inverseJoinColumns = {@JoinColumn(name = "id_PERSON")})
    private List<Person> followers = new ArrayList<Person>();

    /**
     * Correspond aux personnes que l'on suit
     */
    @ManyToMany(mappedBy = "followers")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Person> follows = new ArrayList<Person>();

    /**
     * Liste des gazouillis des autres que l'on partage
     */
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "SHARE",
            joinColumns = {@JoinColumn(name = "id")},
            inverseJoinColumns = {@JoinColumn(name = "id_MESSAGE")})
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Message> messagesShared = new ArrayList<Message>();


    public Person(String firstName, String name, String mail, String username, String password) {
        this.firstName = firstName;
        this.name = name;
        this.mail = mail;
        this.username = username;
        this.password = password;
    }

    public Person() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public Message getMessage(int index) {
        return this.messages.get(index);
    }

    public void addMessage(Message message) {
        this.messages.add(message);
    }

    public List<Person> getFollowers() {
        return followers;
    }

    public void setFollowers(List<Person> followers) {
        this.followers = followers;
    }

    public Person getFollower(int index) {
        return this.followers.get(index);
    }

    public void addFollowers(Person follower) {
        this.followers.add(follower);
    }

    public List<Person> getFollows() {
        return follows;
    }

    public void setFollows(List<Person> follows) {
        this.follows = follows;
    }

    public Person getFollow(int index) {
        return this.follows.get(index);
    }

    public void addFollow(Person follow) {
        this.follows.add(follow);
    }

    public List<Message> getMessagesShared() {
        return messagesShared;
    }

    public void setMessagesShared(List<Message> messagesShared) {
        this.messagesShared = messagesShared;
    }

    public Message getMessageShared(int index) {
        return this.messagesShared.get(index);
    }

    public void addMessageShared(Message messageShared) {
        this.messagesShared.add(messageShared);
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}