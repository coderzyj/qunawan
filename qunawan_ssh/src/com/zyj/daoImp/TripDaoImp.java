package com.zyj.daoImp;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.zyj.dao.TripDao;
import com.zyj.entity.Trip;
import com.zyj.form.SearchForm;
@Repository
public class TripDaoImp implements TripDao{
	
	private SessionFactory sessionFacotry;
	
	@Resource(name="sessionFactory")
	public void setSessionFacotry(SessionFactory sessionFacotry) {
		this.sessionFacotry = sessionFacotry;
	}

	public Session getSession(){
		return sessionFacotry.getCurrentSession();
	}

	@Override
	public List<Trip> getAllTripByCondition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Trip> getPageTripByCondition(Integer start, Integer maxCount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Trip> getPageTripsByType(int id, int start, int maxCount) {
		// TODO Auto-generated method stub
		String hql="from Trip t where t.sequence.id=?";
		return (List<Trip>)getSession().createQuery(hql).setParameter(0, id).setFirstResult(start).setMaxResults(maxCount).list();
	}

	@Override
	public Trip getTripById(int id) {
		// TODO Auto-generated method stub
		String hql="from Trip t where t.id=?";
		
		return (Trip)getSession().createQuery(hql).setParameter(0, id).uniqueResult();
	}

	@Override
	public void updateScore(Trip trip) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Trip> getAllTripByCondition(SearchForm vo) {
		// TODO Auto-generated method stub
		return getPageTripByCondition(vo, null, null);
	}

	@Override
	public List<Trip> getPageTripByCondition(SearchForm vo, Integer start,
			Integer maxCount) {
		// TODO Auto-generated method stub
		String hql = getBaseSql(vo);
		if(start!=null&&maxCount!=null){
			return (List<Trip>)getSession().createQuery(hql).setFirstResult((start-1)*maxCount).setMaxResults(maxCount).list();
		}
		else
		{
			return (List<Trip>)getSession().createQuery(hql).list();
		}
	}
	private String getBaseSql(SearchForm vo) 
	{
		String sql="select distinct t FROM Trip t WHERE t.isOk=1";
		// 如果产品类型不为空，添加查询条件
				if (vo.getType_id() != null) {
					sql += " AND t.sequence.id=" + vo.getType_id();
				}

			// 如果出发地不为空，添加查询条件
				List<Integer> startList = vo.getStart_place_id_list();
				if (startList != null && !startList.isEmpty()) {
					sql += " AND (t.city=" + startList.get(0);
					for (int i = 1; i < startList.size(); i++) {
						sql += " OR t.city=" + startList.get(i);
					}
					sql += ")";
				}

				// 如果交通工具不为空，添加查询条件
				if (vo.getTraffic() != null) {
					sql += " AND t.traffic LIKE '%" + vo.getTraffic() + "%'";
				}

				// 如果出行天数不为空，添加查询条件
				if (vo.getTime() != null) {
					sql += " AND t.time=" + vo.getTime();
				}
				
				// 如果搜索关键字不为空，添加查询条件
				if (vo.getSearch_key() != null) {
					sql += " AND (t.id IN (" + "SELECT tot.trip FROM Themeontrip tot WHERE tot.theme.id IN ("
							+ "SELECT th.id FROM Theme th WHERE th.name LIKE '%" + vo.getSearch_key() + "%'))" + "OR t.id IN ("
							+ "SELECT pot.trip FROM Placeontrip pot WHERE pot.place IN ("
							+ "SELECT p.id FROM Place p WHERE p.name LIKE '%" + vo.getSearch_key() + "%'))" + "OR t.title LIKE '%"
							+ vo.getSearch_key() + "%' " + "OR t.STitle LIKE '%" + vo.getSearch_key() + "%')";
					//sql+="AND t.STitle LIKE '%" + vo.getSearch_key() + "%'";
				}
				
				// 如果景点地区不为空，添加查询条件
				List<Integer> placeList = vo.getPlace_id_list();
				if (placeList != null && !placeList.isEmpty()) {
					sql += " AND t.id IN (SELECT pt.trip.id FROM Placeontrip pt WHERE (pt.place.id=" + placeList.get(0);
					for (int i = 1; i < placeList.size(); i++) {
						sql += " OR pt.place.id=" + placeList.get(i);
					}
					sql += "))";
				}

				// 如果主题不为空，添加查询条件
				List<Integer> themeList = vo.getTheme_id_list();
				if (themeList != null && !themeList.isEmpty()) {
					sql += " AND t.id IN (SELECT tt.trip FROM Themeontrip tt WHERE (tt.theme.id=" + themeList.get(0);
					for (int i = 1; i < themeList.size(); i++) {
						sql += " OR tt.theme=" + themeList.get(i);
					}
					sql += "))";
				}

				// 如果最低价格不为空，添加查询条件
				if (vo.getMin_price() != null) {
					sql += " AND t.id IN (SELECT p.trip FROM Price p where p.price>=" + vo.getMin_price() + ")";
				}

				// 如果最高价格不为空，添加查询条件
				if (vo.getMax_price() != null) {
					sql += " AND t.id IN (SELECT p.trip FROM Price p where p.price<=" + vo.getMax_price() + ")";
				}
				
				// 如果好评&价格排序都为空，采用默认排序规则
				if (vo.getGood_rate_sort() == null && vo.getPrice_sort() == null)
					sql += " ORDER BY t.goodRate desc,min_price asc";

				// 当前以价格为主排序规则进行排序
				if (vo.getCur_sort_str() != null && "price".equals(vo.getCur_sort_str())) {
					if (vo.getGood_rate_sort() != null)
						sql += " ORDER BY min_price " + vo.getPrice_sort() + ",t.goodRate " + vo.getGood_rate_sort();
					else
						sql += " ORDER BY min_price " + vo.getPrice_sort() + ",t.goodRate desc";
				}

				// 当前以好评率为主排序规则进行排序
				if (vo.getCur_sort_str() != null && "comment".equals(vo.getCur_sort_str())) {
					if (vo.getPrice_sort() != null)
						sql += " ORDER BY t.goodRate " + vo.getGood_rate_sort() + ",min_price " + vo.getPrice_sort();
					else
						sql += " ORDER BY t.goodRate " + vo.getGood_rate_sort() + ",min_price asc";
				}

		return sql;
	}
}
