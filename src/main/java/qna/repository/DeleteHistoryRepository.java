package qna.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import qna.domain.ContentType;
import qna.domain.DeleteHistory;

public interface DeleteHistoryRepository extends JpaRepository<DeleteHistory, Long> {
	List<DeleteHistory> findByContentIdAndContentType(Long contentId, ContentType contentType);
}
