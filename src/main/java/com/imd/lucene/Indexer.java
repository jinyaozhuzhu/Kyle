package com.imd.lucene;

import com.imd.entity.CareerInfo;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;

/**
 * 建立索引
 * Created by jinyao on 2017/4/17.
 */
@Service
public class Indexer {

    private Document document;

    private Directory directory;

    /**
     * 建立索引
     *
     * @throws Exception
     */
    public void indexWriter(List<CareerInfo> careerInfos) throws Exception {

        directory = this.getDirectory();
        IndexWriter writer = this.getWriter(directory);

        for (CareerInfo careerInfo : careerInfos) {
            document = new Document();
            String id = String.valueOf(careerInfo.getId());
            StringBuilder sb = new StringBuilder();
            sb.append(careerInfo.getSchool());
            sb.append(careerInfo.getTitle());
            sb.append(careerInfo.getContent());

            document.add(new StringField("id", id, Field.Store.YES));
            document.add(new TextField("content",sb.toString(), Field.Store.YES));
            writer.addDocument(document);
        }
        System.out.println("一共写入" + writer.numDocs());
        writer.close();
    }


    /**
     * 获取 Directory 实例
     *
     * @return
     * @throws IOException
     */
    private Directory getDirectory() throws IOException {
        URL url = this.getClass().getResource("/");
        File root_path = new File(url.getFile());
        this.deleteDir(root_path);
        String filePath = url.getFile() + "index/";
        File index = new File(filePath);
        index.mkdir();
        return FSDirectory.open(Paths.get(filePath));
    }

    /**
     * 删除目录
     *
     * @param file
     * @return
     */
    private boolean deleteDir(File file) {
        String[] dirList = file.list();
        for (String dir : dirList) {
            if ("index".equals(dir)) {
                File index = new File(file, "index/");
                File[] files = index.listFiles();
                for (File subFile : files) {
                    subFile.delete();
                }
                index.delete();
            }
        }
        return file.exists();
    }

    /**
     * 获取 IndexWriter 实例
     *
     * @param dir
     * @return
     * @throws Exception
     */
    private IndexWriter getWriter(Directory dir) throws Exception {
        SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();
        IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
        IndexWriter writer = new IndexWriter(dir, iwc);
        return writer;
    }

}
