package com.theamazeapp.backend.lucene.service;

import java.util.Collection;

import com.theamazeapp.backend.lucene.model.BaseLuceneObject;

public interface ILuceneSyncService {

	void syncIndexes(Collection<? extends BaseLuceneObject> docs);
	void triggerUpdateEvent(Collection<? extends BaseLuceneObject> docs);
	
}
