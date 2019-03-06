package com.cenfotec.blogs.blog.service;

import com.cenfotec.blogs.blog.domain.Preferences;
import com.cenfotec.blogs.blog.repository.PreferencesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PreferencesService {

    private PreferencesRepository preferencesRepository;

    public PreferencesService(PreferencesRepository preferencesRepository) {
        this.preferencesRepository = preferencesRepository;
    }

    @Transactional(readOnly = true)
    public List<Preferences> findAll() {
        return preferencesRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Preferences> findLike(String name) {
        return preferencesRepository.findSimilarPreferences(name);
    }

    @Transactional(readOnly = true)
    public Preferences findById(Preferences p) {

        Optional<Preferences> pref =  preferencesRepository.findById(p.getId());

        if(pref.isPresent()){
            return pref.get();
        }

        Preferences result = new Preferences();
        result.setName(p.getName());
        return result;
    }

    public Preferences save(Preferences preferences) {
        return preferencesRepository.save(preferences);
    }

}
