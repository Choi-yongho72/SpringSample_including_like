package bit.com.a.service;

import java.util.List;

import bit.com.a.model.BbsDto;
import bit.com.a.model.BbsParam;
import bit.com.a.model.GetLikeParam;
import bit.com.a.model.LikeParam;

public interface BbsService {

	public List<BbsDto> getBbsList(BbsParam param);
	
	public int getBbsCount(BbsParam param);
	
	public boolean writeBbs(BbsDto bbs);
	
	public BbsDto getBbs(int seq);
	
	public void updateBbs(BbsDto bbs);
	
	public void deleteBbs(int seq);

	public int like(LikeParam likeParam);

	public GetLikeParam getLike(int seq);
}
