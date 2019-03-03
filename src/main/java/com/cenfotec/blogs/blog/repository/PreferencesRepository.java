package com.cenfotec.blogs.blog.repository;

import com.cenfotec.blogs.blog.domain.Preferences;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PreferencesRepository extends JpaRepository<Preferences, Long> {

    @Query("SELECT preference FROM Preferences preference WHERE preference.name LIKE :name% ")
    List<Preferences> findSimilarPreferences (@Param("name") String name);
}
