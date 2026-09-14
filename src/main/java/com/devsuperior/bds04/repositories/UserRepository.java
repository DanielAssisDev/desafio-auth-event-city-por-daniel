package com.devsuperior.bds04.repositories;

import com.devsuperior.bds04.entities.User;
import com.devsuperior.bds04.projections.UserDetailsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(nativeQuery = true, value = """
            SELECT u.email Username, u.password Password, r.id RoleId, r.authority Authority
            FROM tb_user u INNER JOIN tb_user_role ur ON u.id=ur.user_id
            INNER JOIN tb_role r ON ur.role_id=r.id
            WHERE u.email= :email
            """)
    List<UserDetailsProjection> searchUserByUsernameAndRole(String email);
}
