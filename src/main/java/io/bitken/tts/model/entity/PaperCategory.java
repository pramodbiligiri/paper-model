package io.bitken.tts.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "paper_category")
public class PaperCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_paper_category")
    @SequenceGenerator(name = "seq_paper_category", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name="paper_id", nullable=false)
    private PaperData paperData;

//    @Column(name = "paper_id", nullable = false)
//    private Long paperId;

    private String category;

    public Long getId() {
        return id;
    }

    public PaperData getPaperData() {
        return paperData;
    }

//    public Long getPaperId() {
//        return paperId;
//    }

    public String getCategory() {
        return category;
    }

    public PaperCategory setPaperData(PaperData paperData) {
        this.paperData = paperData;
        return this;
    }

    public PaperCategory setCategory(String category) {
        this.category = category;
        return this;
    }
}
