package io.bitken.tts.model.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "feedback")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_feedback")
    @SequenceGenerator(name = "seq_feedback", allocationSize = 1)
    private Long id;

    @Column(name="data")
    private String data;

    @Column(name = "create_time")
    private java.sql.Timestamp createTime;

    public Feedback setData(String data) {
        this.data = data;
        return this;
    }

    public Feedback setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
        return this;
    }

    public Long getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }
}