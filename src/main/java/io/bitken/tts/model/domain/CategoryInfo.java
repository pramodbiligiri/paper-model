package io.bitken.tts.model.domain;

import java.util.Optional;

/* Maps category names and labels to Arxiv strings */
public enum CategoryInfo {

    AI("ai", "Artificial Intelligence", new String[]{"cs.AI"}),
    ARCH("arch", "Architecture", new String[]{"cs.AR"}),
    DB("db", "Databases", new String[]{"cs.DB"}),
    DC("dist", "Distributed Systems", new String[]{"cs.DC"}),
    NI("net", "Networking", new String[]{"cs.NI"}),
    OS("os", "Operating Systems", new String[]{"cs.OS"}),
    PL("pl", "Programming Languages", new String[]{"cs.PL"});

    private String[] arxivCats;
    private String descriptiveLabel;
    private String dbTag;

    CategoryInfo(String dbTag, String descriptiveLabel, String[] arxivCats) {
        this.dbTag = dbTag;
        this.descriptiveLabel = descriptiveLabel;
        this.arxivCats = arxivCats;
    }

    public String[] getArxivCats() {
        return arxivCats;
    }

    public String getDescriptiveLabel() {
        return descriptiveLabel;
    }

    public String getDbTag() {
        return dbTag;
    }

    public static Optional<CategoryInfo> fromDbTag(String dbTag) {
        for (CategoryInfo cat : CategoryInfo.values()) {
            if (cat.dbTag.equalsIgnoreCase(dbTag)) {
                return Optional.of(cat);
            }
        }

        return Optional.empty();
    }
}
