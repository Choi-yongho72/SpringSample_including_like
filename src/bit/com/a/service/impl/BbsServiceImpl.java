package bit.com.a.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bit.com.a.dao.BbsDao;
import bit.com.a.model.BbsDto;
import bit.com.a.model.BbsParam;
import bit.com.a.model.GetLikeParam;
import bit.com.a.model.LikeParam;
import bit.com.a.service.BbsService;

@Service
public class BbsServiceImpl implements BbsService {

	@Autowired
	BbsDao bbsDao; // = new BbsDaoImpl();

	@Override
	public List<BbsDto> getBbsList(BbsParam param) {		
		return bbsDao.getBbsList(param);
	}
	
	@Override
	public int getBbsCount(BbsParam param) {
		// TODO Auto-generated method stub
		return bbsDao.getBbsCount(param);		
	}



	@Override
	public boolean writeBbs(BbsDto bbs) {		
		return bbsDao.writeBbs(bbs);		
	}
	
	@Override
	public BbsDto getBbs(int seq) {		
		return bbsDao.getBbs(seq);		
	}
	
	@Override
	public void updateBbs(BbsDto bbs) {
		bbsDao.updateBbs(bbs);
	}
	
	@Override
	public void deleteBbs(int seq) {
		bbsDao.deleteBbs(seq);		
	}

	@Override
	public int like(LikeParam likeParam) {
		return bbsDao.like(likeParam);
	}

	@Override
	public GetLikeParam getLike(int seq) {
		
		return bbsDao.getLike(seq);
	}
}








