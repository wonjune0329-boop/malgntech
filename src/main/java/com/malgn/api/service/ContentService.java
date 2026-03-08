package com.malgn.api.service;

import com.malgn.api.dto.ContentRequest;
import com.malgn.api.dto.ContentResponse;
import com.malgn.api.entity.Content;
import com.malgn.api.repository.ContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ContentService {

    private final ContentRepository contentRepository;

    public ContentResponse create(ContentRequest request, String username) {
        Content content = Content.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .viewCount(0L)
                .createdDate(LocalDateTime.now())
                .createdBy(username)
                .lastModifiedDate(LocalDateTime.now())
                .lastModifiedBy(username)
                .build();

        Content saved = contentRepository.save(content);
        return ContentResponse.from(saved);
    }

    public Page<ContentResponse> getAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));

        return contentRepository.findAll(pageRequest)
                .map(ContentResponse::from);
    }

    public ContentResponse getById(Long id) {
        Content content = contentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 콘텐츠가 없습니다."));

        content.setViewCount(content.getViewCount() + 1);
        Content updated = contentRepository.save(content);

        return ContentResponse.from(updated);
    }

    public ContentResponse update(Long id, ContentRequest request, String username, boolean isAdmin) {
        Content content = contentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 콘텐츠가 없습니다."));

        if (!content.getCreatedBy().equals(username) && !isAdmin) {
            throw new RuntimeException("수정 권한이 없습니다.");
        }

        content.setTitle(request.getTitle());
        content.setDescription(request.getDescription());
        content.setLastModifiedDate(LocalDateTime.now());
        content.setLastModifiedBy(username);

        return ContentResponse.from(contentRepository.save(content));
    }

    public void delete(Long id, String username, boolean isAdmin) {
        Content content = contentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 콘텐츠가 없습니다."));

        if (!content.getCreatedBy().equals(username) && !isAdmin) {
            throw new RuntimeException("삭제 권한이 없습니다.");
        }

        contentRepository.delete(content);
    }

}