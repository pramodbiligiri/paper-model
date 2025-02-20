package io.bitken.tts.model.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "email_sub")
public class EmailSubscriber {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_email_sub")
    @SequenceGenerator(name = "seq_email_sub", allocationSize = 1)
    private Long id;

    @Column(name="email_id")
    private String emailId;

    @Column(name = "create_time")
    private java.sql.Timestamp createTime;

    public EmailSubscriber setEmailId(String emailId) {
        this.emailId = emailId;
        return this;
    }

    public EmailSubscriber setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
        return this;
    }

    public Long getId() {
        return id;
    }

    public String getEmailId() {
        return emailId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }
}
