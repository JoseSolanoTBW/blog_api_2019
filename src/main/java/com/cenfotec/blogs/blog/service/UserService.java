package com.cenfotec.blogs.blog.service;

import com.cenfotec.blogs.blog.domain.Preferences;
import com.cenfotec.blogs.blog.domain.User;
import com.cenfotec.blogs.blog.repository.UserRepository;
import com.cenfotec.blogs.blog.utils.Status;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class UserService {

    private UserRepository userRepository;
    private PreferencesService preferencesService;

    public UserService(UserRepository userRepository, PreferencesService preferencesService) {
        this.preferencesService = preferencesService;
        this.userRepository = userRepository;
    }

    public User save(User user) {
        user.setStatus(Status.ACTIVE.getValue());

        Set<Preferences> userPreferences = new HashSet<>();

        if (!user.getPreferences().isEmpty()){
            user.getPreferences().forEach(
                    p -> {
                        p.setName(p.getName().toLowerCase());

                        if(p.getId() != null)
                            userPreferences.add(preferencesService.findById(p));
                        else{
                            Preferences newPref = new Preferences();
                            newPref.setName(p.getName());
                            userPreferences.add(newPref);
                        }
                    }
            );
        }

        user.setPreferences(userPreferences);

        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User getUser (String userNickName) throws NotFoundException {

        Optional<User> user = userRepository.findByNickName(userNickName);

        if(!user.isPresent()) {
            throw new NotFoundException("Couldn't find user");
        }
        return user.get();
    }

    public User findById(Long id) throws NotFoundException {

        Optional<User> user = userRepository.findById(id);

        if(!user.isPresent()) {
            throw new NotFoundException("Couldn't find user");
        }
        return user.get();
    }

}
