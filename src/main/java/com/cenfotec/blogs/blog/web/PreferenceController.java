package com.cenfotec.blogs.blog.web;

import com.cenfotec.blogs.blog.domain.Preferences;
import com.cenfotec.blogs.blog.domain.User;
import com.cenfotec.blogs.blog.service.PreferencesService;
import javassist.NotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/preference")
public class PreferenceController {

    private PreferencesService preferencesService;

    public PreferenceController(PreferencesService preferencesService) {
        this.preferencesService = preferencesService;
    }

    @GetMapping("/typeahead")
    public List<Preferences> getPreferences(@RequestParam("searchTerm") String searchTerm ) throws NotFoundException {
        return preferencesService.findLike(searchTerm);
    }
}
