package twitter.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Tweet {

	@Id
	@GeneratedValue
	private Integer id;
	private String message;

	public Tweet() {
	}

	public Tweet(String message) {
		this.message = message;
	}


	public String getMessage() {
		return message;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Tweet tweet = (Tweet) o;

		if (id != null ? !id.equals(tweet.id) : tweet.id != null) return false;
		return message != null ? message.equals(tweet.message) : tweet.message == null;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (message != null ? message.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Tweet{" +
				"id=" + id +
				", message='" + message + '\'' +
				'}';
	}
}
