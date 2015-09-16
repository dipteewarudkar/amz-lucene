package com.theamazeapp.backend.lucene.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.theamazeapp.backend.lucene.model.BaseLuceneObject;

@Service
public class LuceneService  implements ILuceneService{
	
	IndexWriter indexWriter;
	
	public LuceneService(IndexWriter indexWriter){
		this.indexWriter=indexWriter;
	}

	Logger logger = LoggerFactory.getLogger(getClass());


	

	
	@Override
	public <T extends BaseLuceneObject> void createDocument(T doc) {
		try{
			indexWriter.addDocument(doc.toLuceneDocument());
			indexWriter.commit();
			logger.info("Created {} documents in lucene");
		}catch(IOException io){
			logger.error("Error while creating  documents in lucene:{}",io);
		}
	}
	
	
	@Override
	public void createDocuments(Collection<? extends BaseLuceneObject> docs) {
		try{
			
			List<Document> documents = docs.stream().map(doc->{
				Document d = doc.toLuceneDocument();
				return d;
			}).collect(Collectors.toList());
			
			indexWriter.addDocuments(documents);
			indexWriter.commit();
			logger.info("Created {} documents in lucene" + documents.size());
		}catch(IOException io){
			logger.error("Error while creating  documents in lucene:{}",io);
		}
	}

	@Override
	public void updateDocuments(Collection<? extends BaseLuceneObject> docs) {
		try{
			List<Document> documents = new ArrayList<Document>();

			List<Term> terms = docs.stream().map(doc->{

				Document d = doc.toLuceneDocument();
				documents.add(d);
				Term term = new Term("id", d.get("id"));
				return term;
			}).collect(Collectors.toList());

			indexWriter.deleteDocuments(terms.toArray(new Term[0]));
			indexWriter.addDocuments(documents);
			indexWriter.commit();
			logger.info("Updated {} documents in lucene" , documents.size());

		}catch(IOException io){
			logger.error("Error while updating  in lucene ",io);
		}
	}


	@Override
	public <T extends BaseLuceneObject> void updateDocument(T doc) {
		try{
			Document d = doc.toLuceneDocument();
			Term term = new Term("id", d.get("id"));

			indexWriter.deleteDocuments(term);
			indexWriter.addDocument(d);
			indexWriter.commit();


		}catch(IOException io){
			logger.error("Error while updating  in lucene ",io);
		}

	}

	@Override
	public List<String> getTopDocs(Query q, int limit) {
		// TODO Auto-generated method stub
		return null;
	}



	

}
