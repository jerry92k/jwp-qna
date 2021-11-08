package qna.domain;

import qna.NotFoundException;
import qna.UnAuthorizedException;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Answer extends BaseTimeEntity {

	@Id
	@GeneratedValue
	private Long id;
	private String contents;
	private boolean deleted;
	private Long questionId;
	private Long writerId;

	protected Answer() {
	}

	public Answer(User writer, Question question, String contents) {
		this(null, writer, question, contents);
	}

	public Answer(Long id, User writer, Question question, String contents) {
		if (Objects.isNull(writer)) {
			throw new UnAuthorizedException();
		}

		if (Objects.isNull(question)) {
			throw new NotFoundException();
		}

		this.id = id;
		this.contents = contents;
		this.deleted = false;
		this.writerId = writer.getId();
		this.questionId = question.getId();
	}

	public boolean isOwner(User writer) {
		return this.writerId.equals(writer.getId());
	}

	public void toQuestion(Question question) {
		this.questionId = question.getId();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getWriterId() {
		return writerId;
	}

	public void setWriterId(Long writerId) {
		this.writerId = writerId;
	}

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Answer answer = (Answer)o;
		return isDeleted() == answer.isDeleted() && Objects.equals(getId(), answer.getId())
			&& Objects.equals(getContents(), answer.getContents()) && Objects.equals(getQuestionId(),
			answer.getQuestionId()) && Objects.equals(getWriterId(), answer.getWriterId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getContents(), isDeleted(), getQuestionId(), getWriterId());
	}

	@Override
	public String toString() {
		return "Answer{" +
			"id=" + id +
			", contents='" + contents + '\'' +
			", deleted=" + deleted +
			", questionId=" + questionId +
			", writerId=" + writerId +
			'}';
	}
}
