package com.company.enroller.persistence;

import com.company.enroller.model.Meeting;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component("meetingService")
public class MeetingService {

    DatabaseConnector connector;

    public MeetingService() {
        connector = DatabaseConnector.getInstance();
    }

    public Collection<Meeting> getAll() {
        String hql = "FROM Meeting";
        Query query = connector.getSession().createQuery(hql);
        return query.list();
    }


    public Meeting findById(long id) {
        String hql = "FROM Meeting as meeting where meeting.id =" + id;
        Query query = connector.getSession().createQuery(hql);
        return (Meeting) query.uniqueResult();
    }

    public void createMeeting(Meeting meeting) {
        Transaction transaction = connector.getSession().beginTransaction();
        connector.getSession().save(meeting);
        transaction.commit();
    }
}
