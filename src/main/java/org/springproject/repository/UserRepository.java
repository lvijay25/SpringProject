package org.springproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springproject.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
