package common.entity;

import org.apache.lucene.analysis.core.KeywordTokenizerFactory;
import org.apache.lucene.analysis.core.StopFilterFactory;
import org.apache.lucene.analysis.miscellaneous.WordDelimiterFilterFactory;
import org.apache.lucene.analysis.ngram.EdgeNGramFilterFactory;
import org.apache.lucene.analysis.ngram.NGramFilterFactory;
import org.apache.lucene.analysis.pattern.PatternReplaceFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.apache.lucene.analysis.tr.ApostropheFilterFactory;
import org.apache.lucene.analysis.tr.TurkishLowerCaseFilterFactory;
import org.hibernate.search.annotations.*;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.io.Serializable;

@MappedSuperclass
@AnalyzerDefs({
    @AnalyzerDef(name = "autocompleteEdgeAnalyzer",
        tokenizer = @TokenizerDef(factory = KeywordTokenizerFactory.class),
        filters = {
            @TokenFilterDef(factory = ApostropheFilterFactory.class),
            @TokenFilterDef(factory = PatternReplaceFilterFactory.class, params = {
                @Parameter(name = "pattern",value = "([^a-zA-Z0-9\\.])"),
                @Parameter(name = "replacement", value = " "),
                @Parameter(name = "replace", value = "all") }),
            @TokenFilterDef(factory = TurkishLowerCaseFilterFactory.class),
            @TokenFilterDef(factory = StopFilterFactory.class),
            @TokenFilterDef(factory = EdgeNGramFilterFactory.class, params = {
                @Parameter(name = "minGramSize", value = "2"),
                @Parameter(name = "maxGramSize", value = "50") }) }),
    @AnalyzerDef(name = "autocompleteNGramAnalyzer",
        tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
        filters = {
            @TokenFilterDef(factory = ApostropheFilterFactory.class),
            @TokenFilterDef(factory = WordDelimiterFilterFactory.class),
            @TokenFilterDef(factory = TurkishLowerCaseFilterFactory.class),
            @TokenFilterDef(factory = NGramFilterFactory.class, params = {
                @Parameter(name = "minGramSize", value = "3"),
                @Parameter(name = "maxGramSize", value = "5") }),
            @TokenFilterDef(factory = PatternReplaceFilterFactory.class, params = {
                @Parameter(name = "pattern",value = "([^a-zA-Z0-9\\.])"),
                @Parameter(name = "replacement", value = " "),
                @Parameter(name = "replace", value = "all") })
        }),
    @AnalyzerDef(name = "standardAnalyzer",
        tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
        filters = {
            @TokenFilterDef(factory = ApostropheFilterFactory.class),
            @TokenFilterDef(factory = WordDelimiterFilterFactory.class),
            @TokenFilterDef(factory = TurkishLowerCaseFilterFactory.class),
            @TokenFilterDef(factory = PatternReplaceFilterFactory.class, params = {
                @Parameter(name = "pattern", value = "([^a-zA-Z0-9\\.])"),
                @Parameter(name = "replacement", value = " "),
                @Parameter(name = "replace", value = "all") })
        })
})
public abstract class AbstractVersionedEntity extends AbstractEntity implements Serializable {
    @Version
    @Column(name = "VERSION", nullable = false)
    protected Long version;
}