package bit.com.a.model;

import java.io.Serializable;

public class GetLikeParam implements Serializable {
	private int likeCount;
	private int dislike;
	
	public GetLikeParam() {
	}

	public GetLikeParam(int likeCount, int dislike) {
		super();
		this.likeCount = likeCount;
		this.dislike = dislike;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public int getDislike() {
		return dislike;
	}

	public void setDislike(int dislike) {
		this.dislike = dislike;
	}

	@Override
	public String toString() {
		return "GetLikeParam [likeCount=" + likeCount + ", dislike=" + dislike + "]";
	}
}
