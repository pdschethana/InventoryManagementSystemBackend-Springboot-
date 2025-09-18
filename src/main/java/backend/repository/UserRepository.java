/*package backend.repository;

import backend.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Long> {

    // Custom finder method (no @Override here!)
    Optional<UserModel> findByEmail(String email);
    boolean existByEmail (String email);
}*/


package backend.repository;

import backend.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByEmail(String email);
    boolean existsByEmail(String email); // ✅ correct name
}



