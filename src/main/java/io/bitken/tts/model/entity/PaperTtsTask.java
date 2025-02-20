package io.bitken.tts.model.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "paper_tts_task")
public class PaperTtsTask {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_paper_tts_task")
    @SequenceGenerator(name = "seq_paper_tts_task", allocationSize = 1)
    private Long id;

    private Long paperId;

    private Long paperAudioId;

    private int status;

    private Timestamp startTime;
    private Timestamp endTime;

    public PaperTtsTask() {
    }

    public Long getId() {
        return id;
    }

    public Long getPaperId() {
        return paperId;
    }

    public Long getPaperAudioId() {
        return paperAudioId;
    }

    public int getStatus() {
        return status;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setPaperId(Long paperId) {
        this.paperId = paperId;
    }

    public void setPaperAudioId(Long paperAudioId) {
        this.paperAudioId = paperAudioId;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }
}
