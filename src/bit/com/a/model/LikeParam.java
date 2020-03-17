package bit.com.a.model;

import java.io.Serializable;

public class LikeParam implements Serializable {
	private int seq;	// 게시글 seq
	private String id;	// session id
	
	public LikeParam() {
	}

	public LikeParam(int seq, String id) {
		super();
		this.seq = seq;
		this.id = id;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "LikeParam [seq=" + seq + ", id=" + id + "]";
	}
}
