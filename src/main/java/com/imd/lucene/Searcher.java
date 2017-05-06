package com.imd.lucene;

import com.imd.entity.CareerInfo;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * 检索
 * Created by jinyao on 2017/4/17.
 */
@Service
public class Searcher {

    private static final String searchField = "title";
    private static final int queryNum = 50;

    public List<CareerInfo> dimQuery(String keyWord)
            throws ParseException, IOException {
        QueryParser parser = new QueryParser(searchField, new SmartChineseAnalyzer());
        Query query = parser.parse(keyWord);
        IndexSearcher indexSearcher = this.initIndexSearcher();

        List<CareerInfo> careerInfos = new ArrayList<>();
        TopDocs topDocs = indexSearcher.search(query, queryNum);
        for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
            Document document = indexSearcher.doc(scoreDoc.doc);
            CareerInfo careerInfo = new CareerInfo(
                    Integer.parseInt(document.get("id")), null, document.get("title"),
                    null, null, null, null,
                    null, null, document.get("school")
            );
            careerInfos.add(careerInfo);
        }
        return careerInfos;
    }

    /**
     * 初始化 IndexSearcher
     *
     * @return
     * @throws IOException
     */
    private IndexSearcher initIndexSearcher() throws IOException {
        String dirPath = this.getDirectory();
        FSDirectory fsDirectory = FSDirectory.open(Paths.get(dirPath));
        IndexReader reader = DirectoryReader.open(fsDirectory);
        IndexSearcher indexSearcher = new IndexSearcher(reader);
        return indexSearcher;
    }

    private String getDirectory() throws IOException {
        URL url = this.getClass().getResource("/");
        return url.getFile() + "index/";
    }

}
