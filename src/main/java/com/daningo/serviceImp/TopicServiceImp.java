package com.daningo.serviceImp;

import com.daningo.entity.Topic;
import com.daningo.mappers.TopicMapper;
import com.daningo.service.UIService;
import com.google.inject.Inject;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class TopicServiceImp implements UIService<Topic> {
    @Inject
    private SqlSession sqlSession;
    @Inject
    private TopicMapper topicMapper;

    @Override
    public List<Topic> getAll(Topic model) {
        return null;
    }

    @Override
    public void update(Topic model) {

    }

    @Override
    public Topic add(Topic topic) {
        this.topicMapper.createTopic(topic);
        return null;
    }

    public void addTopicUser(int topicID, int userID, String type) {
        System.out.println(topicID + " " + userID + " " + type);
        this.topicMapper.createTopicUser(topicID, userID, type);
    }

    @Override
    public Topic get(Topic model) {
        return null;
    }

    public Topic get(int userID, int topicID) {
        Topic topic = this.topicMapper.getTopic(userID, topicID);
        topic.setUserTopic(this.topicMapper.getTopicUser(userID, topic.getTopicID()));
        topic.setDescription(this.topicMapper.getTopicDescription(topicID));
        return topic;
    }

    @Override
    public List<Topic> getAll(int id) {
        List<Topic> topics = this.topicMapper.getTopics(id);
        for(Topic topic:topics) {
            topic.setUserTopic(this.topicMapper.getTopicUser(id ,topic.getTopicID()));
        }
        return topics;
    }


    @Override
    public List<Topic> getAll() {
        return null;
    }


}
