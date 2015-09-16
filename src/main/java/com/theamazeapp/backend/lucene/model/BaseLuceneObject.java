package com.theamazeapp.backend.lucene.model;

import org.apache.lucene.document.Document;

public interface BaseLuceneObject {
	
	 Document toLuceneDocument();

}
