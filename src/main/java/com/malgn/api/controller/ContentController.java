package com.malgn.api.controller;

import com.malgn.api.dto.ContentRequest;
import com.malgn.api.dto.ContentResponse;
import com.malgn.api.service.ContentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contents")
@RequiredArgsConstructor
public class ContentController {

    private final ContentService contentService;

    @PostMapping
    public ContentResponse create(@RequestBody ContentRequest request,
                                  HttpServletRequest httpServletRequest) {

        String username = (String) httpServletRequest.getAttribute("username");
        if (username == null) {
            throw new RuntimeException("인증 정보가 없습니다.");
        }

        return contentService.create(request, username);
    }

    @GetMapping
    public Page<ContentResponse> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return contentService.getAll(page, size);
    }

    @GetMapping("/{id}")
    public ContentResponse getById(@PathVariable Long id) {
        return contentService.getById(id);
    }

    @PutMapping("/{id}")
    public ContentResponse update(@PathVariable Long id,
                                  @RequestBody ContentRequest request,
                                  HttpServletRequest httpServletRequest) {

        String username = (String) httpServletRequest.getAttribute("username");
        String role = (String) httpServletRequest.getAttribute("role");

        boolean isAdmin = "ADMIN".equals(role);

        return contentService.update(id, request, username, isAdmin);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id,
                       HttpServletRequest httpServletRequest) {

        String username = (String) httpServletRequest.getAttribute("username");
        String role = (String) httpServletRequest.getAttribute("role");

        boolean isAdmin = "ADMIN".equals(role);

        contentService.delete(id, username, isAdmin);
    }
}