package io.bitken.tts.model.entity.stats;

import javax.persistence.*;

@Entity
@Table(name = "listen_stat")
public class ListenStat {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_listen_stat")
    @SequenceGenerator(name = "seq_listen_stat", allocationSize = 1)
    private Long id;

    @Column(name="session_id")
    private final String session;

    @Column(name="paper_id")
    private Long paperId;

    @Column(name="src")
    private String src;

    @Column(name="current")
    private int current;

    @Column(name="total")
    private int total;

    public ListenStat(String session, long paperId, String src, int current, int total) {
        this.session = session;
        this.paperId = paperId;
        this.src = src;
        this.current = current;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public String getSession() {
        return session;
    }

    public Long getPaperId() {
        return paperId;
    }

    public String getSrc() {
        return src;
    }

    public int getCurrent() {
        return current;
    }

    public int getTotal() {
        return total;
    }

}
