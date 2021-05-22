package io.javabrains.springbootstarter.topic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TopicService {
	
	private List<Topic> topics = new ArrayList<>(Arrays.asList(
			new Topic("Spring", "123", "Spring Course"),
			new Topic("Spr", "java", " Course"),
			new Topic("Spring", "123", "Spring Course"),
			new Topic("Spring", "123", "Spring Course"))) ;
	
	
	public Topic getTopicById(String id) {
		return topics.stream().filter(t -> t.getId().equals(id)).findFirst().get();
	}
	
	public List<Topic> getAllTopics(){
		return topics;
	}

	public void addTopic(Topic topic) {
		// TODO Auto-generated method stub
		topics.add(topic);
	}

	public void updateTopic(Topic topic, String id) {
		// TODO Auto-generated method stub
		//System.out.println("came to service function"+id);
		for(int i =0; i<topics.size();i++)
		{
			
			Topic t = topics.get(i);
			//System.out.println(t.getId()== id);
			if(t.getId().equals(id))
			{
				//System.out.println("found");
				topics.set(i, topic);
				return;
			}
		}
		
	}

	public void deleteTopic(String id) {
		// TODO Auto-generated method stub
		for(int i = 0; i<topics.size();i++)
		{
			Topic t = topics.get(i);
			if(t.getId().equals(id))
			{
				topics.remove(i);
				return;
			}
		}
	}
}
