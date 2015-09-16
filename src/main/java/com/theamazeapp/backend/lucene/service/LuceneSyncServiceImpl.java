package com.theamazeapp.backend.lucene.service;

import java.util.Collection;

//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.theamazeapp.backend.lucene.model.BaseLuceneObject;


@Service
public class LuceneSyncServiceImpl implements ILuceneSyncService {
	
	@Autowired
	ILuceneService luceneService;
	 
	@Override
	//Todo if its possible to send list of object in rabbit mq
	public void syncIndexes(Collection<? extends BaseLuceneObject> docs) {
		luceneService.updateDocuments(docs);
		
	}

	@Override
	public void triggerUpdateEvent(Collection<? extends BaseLuceneObject> docs) {
		
	}

}
