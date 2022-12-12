package com.spingas.account.springboot.services;

import com.spingas.account.springboot.dao.UserDao;
import com.spingas.account.springboot.entity.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServices {
    @Autowired
    UserDao dao;

    public List<User> findAll() {
        return dao.findAll();
    }

    public List<User> auto(User user) {
        Specification<User> spc = new Specification<User>() {
            List<Predicate> predicates = new ArrayList<>();

            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                if (user.getId() != null) predicates.add(criteriaBuilder.equal(root.get("id"), user.getId()));
                if (user.getName() != null) predicates.add(criteriaBuilder.equal(root.get("name"), user.getName()));
                if (user.getIcon() != null) predicates.add(criteriaBuilder.equal(root.get("icon"), user.getIcon()));
                if (user.getPhone() != null) predicates.add(criteriaBuilder.equal(root.get("phone"), user.getPhone()));
                if (user.getEmail() != null) predicates.add(criteriaBuilder.equal(root.get("email"), user.getEmail()));
                if (user.getPwd() !=null) predicates.add(criteriaBuilder.equal(root.get("pwd"),user.getPwd()));
                if (user.getSubscription()!=null) predicates.add(criteriaBuilder.equal(root.get("subscription"),user.getSubscription()));
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return dao.findAll(spc);
    }

    public User add(User user) {
        return dao.save(user);
    }

    public boolean update(User user) {
        dao.save(user);
        return true;
    }

    public boolean del(User user) {
        dao.delete(user);
        return true;
    }
}
