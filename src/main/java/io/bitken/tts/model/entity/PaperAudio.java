package io.bitken.tts.model.entity;

import io.bitken.tts.model.entity.converter.BlobConverter;
import io.bitken.tts.model.entity.converter.IAudioFile;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "paper_audio")
public class PaperAudio {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_paper_audio")
    @SequenceGenerator(name = "seq_paper_audio", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paper_id" , updatable = false, nullable = false)
    private PaperData paperData;

    @Convert(converter = BlobConverter.class)
    @Column(name = "audio")
    private IAudioFile audio;

    @Column(name = "duration")
    private Integer duration; // Seconds

    @Column(name = "create_time")
    private Timestamp createTime; // Seconds

    public PaperAudio() {
    }

    public Long getId() {
        return id;
    }

    public PaperData getPaperData() {
        return paperData;
    }

    public IAudioFile getAudio() {
        return audio;
    }

    // Duration in seconds
    public Integer getDuration() {
        return duration;
    }

    public void setPaper(PaperData paperData) {
        this.paperData = paperData;
    }

    public void setAudio(IAudioFile audio) {
        this.audio = audio;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}