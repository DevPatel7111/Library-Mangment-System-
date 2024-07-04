package org.dev.JBDL_Lec14.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.dev.JBDL_Lec14.dto.UserRequest;
import org.dev.JBDL_Lec14.model.Operator;
import org.dev.JBDL_Lec14.model.User;
import org.dev.JBDL_Lec14.model.UserFilterType;
import org.dev.JBDL_Lec14.model.UserType;
import org.dev.JBDL_Lec14.repositary.UserRepositary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private static final Log logger = LogFactory.getLog(UserService.class);
//    private static final Log logger = LogFactory.getLog(UserService.class);
//private static final Class<UserService> log = UserService.class;
    @Autowired
    private UserRepositary userRepositary;

 @PersistenceContext
 private EntityManager em;
    public User addStudent(UserRequest userRequest) {
        User user = userRequest.toUser();
        user.setUserType(UserType.STUDENT);
  return  userRepositary.save(user);
    }

    public List<User> filter(String filterBy, String opertor, String value) {
        String[] filters = filterBy.split(",");
        String[] operators = opertor.split(",");
        String[] values = value.split(",");
        StringBuilder query = new StringBuilder();
        query.append("select * from user where ");
//        return null;
        for(int i = 0 ; i< operators.length; i++){
            UserFilterType userFilterType = UserFilterType.valueOf(filters[i]);
            Operator operator1 = Operator.valueOf(operators[i]);
            String finalValue = values[i];
            query = query.append(userFilterType).
                    append(operator1.getValue()).
                    append("'").append(finalValue).
                    append("'").append(" and ");
        }
        logger.info("query is :"+query.substring(0,query.length()-4));
        Query query1 = em.createNativeQuery(query.substring(0, query.length()-4), User.class);
        return ((Query) query1).getResultList();

    }


    public User getStudentByPhoneNo(String userPhoneNumber) {
        return userRepositary.findByphoneNoAndUserType(userPhoneNumber , UserType.STUDENT);
    }
}


