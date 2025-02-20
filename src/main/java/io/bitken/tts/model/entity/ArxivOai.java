package io.bitken.tts.model.entity;

import com.vladmihalcea.hibernate.type.json.JsonType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import javax.persistence.*;
import java.util.Map;

@TypeDefs({
    @TypeDef(name = "json", typeClass = JsonType.class)
})
@Entity
@Table(name = "arxiv_oai")
public class ArxivOai {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_arxiv_oai")
    @SequenceGenerator(name = "seq_arxiv_oai", allocationSize = 1)
    private Long id;

    private String oaiXml;

    @Type(type = "json")
    @Column(name="src", columnDefinition = "jsonb")
    private Source src;

    @Column(name="batch_id")
    private Long batchId;

    public Long getId() {
        return id;
    }

    public String getOaiXml() {
        return oaiXml;
    }

    public Source getSrc() {
        return src;
    }

    public Long getBatchId() {
        return batchId;
    }

    public void setOaiXml(String oaiXml) {
        this.oaiXml = oaiXml;
    }

    public void setSrc(Source src) {
        this.src = src;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public static class Source {
        private String url;
        private Map<String, String> params;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public Map<String, String> getParams() {
            return params;
        }

        public void setParams(Map<String, String> params) {
            this.params = params;
        }
    }
}
