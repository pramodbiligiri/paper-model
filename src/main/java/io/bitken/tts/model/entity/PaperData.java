package io.bitken.tts.model.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "paper_data")
public class PaperData {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq_paper_data")
    @SequenceGenerator(name = "seq_paper_data", allocationSize = 1)
    private Long id;

    private String arxivId;

    @Column(name="title", columnDefinition = "text")
    private String title;

    @Column(name="abstract", columnDefinition = "text")
    private String abstractt;

    @Column(name="link")
    private String link;

    @Column(name="authors", columnDefinition = "text")
    private String authors;

    @Column(name = "pubdate")
    private java.sql.Timestamp pubDate;

    @OneToMany(mappedBy = "paperData", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PaperAudio> audio = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "paper_id")
    private List<PaperCategory> categories = new ArrayList<>();

    public PaperData() {
    }

    @Override
    public String toString() {
        return String.format(
            "PaperData[id=%d, arxivId='%s']", id, arxivId
        );
    }

    public Long getId() {
        return id;
    }

    public String getArxivId() {
        return arxivId;
    }

    public String getTitle() {
        return title;
    }

    public String getAbstractt() {
        return abstractt;
    }

    public String getLink() {
        return link;
    }

    public String getAuthors() {
        return authors;
    }

    public Timestamp getPubDate() {
        return pubDate;
    }

    public List<PaperAudio> getAudio() {
        return audio;
    }

    public List<PaperCategory> getCategories() {
        return categories;
    }

    public void setArxivId(String arxivId) {
        this.arxivId = arxivId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAbstractt(String abstractt) {
        this.abstractt = abstractt;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public void setPubDate(Timestamp pubDate) {
        this.pubDate = pubDate;
    }

    public void setAudio(List<PaperAudio> audio) {
        this.audio = audio;
    }

    public void setAudio(PaperAudio audio) {
        this.audio.add(audio);
    }

    public void setCategories(List<PaperCategory> categories) {
        for (PaperCategory cat : categories) {
            cat.setPaperData(this);
        }

        this.categories = categories;
    }

    public void addCategory(PaperCategory category) {
        category.setPaperData(this);
        this.categories.add(category);
    }
}
