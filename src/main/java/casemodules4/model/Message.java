package casemodules4.model;

import javax.persistence.*;

@Entity
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMessage;

    @ManyToOne
    @JoinColumn(name = "id_user_send")
    private User userSend;

    @ManyToOne
    @JoinColumn(name = "id_user_receive")
    private User userReceive;

    private String content;

    public User getUserReceive() {
        return userReceive;
    }

    public User getUserSend() {
        return userSend;
    }

    public Message() {
    }

    public Message(Long idMessage, User userSend, User userReceive, String content) {
        this.idMessage = idMessage;
        this.userSend = userSend;
        this.userReceive = userReceive;
        this.content = content;
    }

    public Long getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(Long idMessage) {
        this.idMessage = idMessage;
    }

    public void setUserSend(User userSend) {
        this.userSend = userSend;
    }

    public void setUserReceive(User userReceive) {
        this.userReceive = userReceive;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
