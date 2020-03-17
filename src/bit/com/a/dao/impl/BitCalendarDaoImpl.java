package bit.com.a.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bit.com.a.dao.BitCalendarDao;
import bit.com.a.model.CalendarDto;

@Repository
public class BitCalendarDaoImpl implements BitCalendarDao {
	@Autowired
	SqlSessionTemplate sqlSession;
	
	private String ns = "Calendar.";

	@Override
	public List<CalendarDto> getCalendarList(CalendarDto cal) {
		return sqlSession.selectList(ns + "getCalendar", cal);
	}
}
