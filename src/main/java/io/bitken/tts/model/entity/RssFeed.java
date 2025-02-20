package io.bitken.tts.model.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "rss_feed")
public class RssFeed {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_rss_feed")
    @SequenceGenerator(name = "seq_rss_feed", allocationSize = 1)
    private Long id;

    @Column(name = "create_time")
    private java.sql.Timestamp createTime;

    @Column(name="content")
    private String content;

    @Column(name="selector")
    private String selector;

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSelector() {
        return selector;
    }

    public void setSelector(String selector) {
        this.selector = selector;
    }
}
