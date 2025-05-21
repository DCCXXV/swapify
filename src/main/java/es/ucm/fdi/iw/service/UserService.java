package es.ucm.fdi.iw.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.ucm.fdi.iw.model.Swap;
import es.ucm.fdi.iw.model.User;
import es.ucm.fdi.iw.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User.Transfer registerUser(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("Email is already registered");
        }

        user.setDeleted(false);
        user.setRoles("USER");

        User registeredUser = userRepository.save(user);

        return registeredUser.toTransfer();
    }

    public List<User.Transfer> getAllUsers() {
        return userRepository.findByUsernameNot("a")
                .stream()
                .map(User::toTransfer)
                .collect(Collectors.toList());
    }

    public List<User.Transfer> getUsersByKeyword(String keyword) {
        return userRepository.findByUsernameContainingIgnoreCase(keyword)
                .stream()
                .map(User::toTransfer)
                .collect(Collectors.toList());
    }

    public List<User.Transfer> getUsersByKeywordWithoutUser(String keyword, User user) {
        return userRepository.findByUsernameContainingIgnoreCaseAndUsernameNot(keyword, user.getUsername())
                .stream()
                .map(User::toTransfer)
                .collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public User getUsersByID(long id) {
        return userRepository.findById(id);
    }

    public User getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return user;
        } else {
            return null;
        }
    }

    public Page<User> findUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findAll(pageable);
    }

    public Page<User> findUsers(int page, int size, long currentUserId) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findByUsernameNotAndIdNot("a", currentUserId, pageable);
    }

    public boolean findEmail(String email) {
        User u = userRepository.findByEmail(email);
        if (u == null) {
            return false;
        }
        return true;
    }

    public boolean findFirstname(String firstName) {
        User u = userRepository.findByFirstNameIgnoreCase(firstName);
        if (u == null) {
            return false;
        }
        return true;
    }

    public List<User.Transfer> searchUsers(
            String keyword,
            User me,
            boolean filterUsers,
            boolean username,
            boolean userdesc,
            boolean currentSkills,
            boolean desiredSkills) {
        if (!filterUsers) {
            return List.of();
        }

        List<User> candidates = userRepository
                .findByUsernameContainingIgnoreCaseAndUsernameNot(
                        keyword, me.getUsername());

        return candidates.stream()
                .filter(u -> {
                    String kw = keyword.toLowerCase();
                    boolean ok = true;
                    if (username) {
                        ok &= u.getUsername().toLowerCase().contains(kw);
                    }
                    if (userdesc) {
                        ok &= u.getDescription().toLowerCase().contains(kw);
                    }
                    if (currentSkills) {
                        ok &= u.getCurrentSkills().stream()
                                .anyMatch(s -> s.toString().toLowerCase().contains(kw));
                    }
                    if (desiredSkills) {
                        ok &= u.getDesiredSkills().stream()
                                .anyMatch(s -> s.toString().toLowerCase().contains(kw));
                    }
                    return ok;
                })
                .map(User::toTransfer)
                .collect(Collectors.toList());
    }
}
