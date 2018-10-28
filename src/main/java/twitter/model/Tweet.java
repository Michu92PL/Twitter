package twitter.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Tweet {

	@Id
	@GeneratedValue
	private Integer id;
	private String message;
	@Transient
	private String author;

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Tweet tweet = (Tweet) o;

		if (message != null ? !message.equals(tweet.message) : tweet.message != null) return false;
		return author != null ? author.equals(tweet.author) : tweet.author == null;
	}

	@Override
	public int hashCode() {
		int result = message != null ? message.hashCode() : 0;
		result = 31 * result + (author != null ? author.hashCode() : 0);
		return result;
	}

	public Tweet() {
	}

	public Tweet(String message) {
		this.message = message;
	}


	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return "Tweet{" +
				"id=" + id +
				", message='" + message + '\'' +
				'}';
	}
}
