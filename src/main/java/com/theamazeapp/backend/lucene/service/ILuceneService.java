package com.theamazeapp.backend.lucene.service;

import java.util.Collection;
import java.util.List;

import org.apache.lucene.search.Query;

import com.theamazeapp.backend.lucene.model.BaseLuceneObject;

public interface ILuceneService {
	
	
	void  createDocuments(Collection<? extends BaseLuceneObject> docs);
	void  updateDocuments(Collection<? extends BaseLuceneObject> docs);
	<T extends BaseLuceneObject> void  updateDocument(T doc);
	<T extends BaseLuceneObject> void  createDocument(T doc);
	List<String> getTopDocs(Query q, int limit);

}
