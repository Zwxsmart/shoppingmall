package com.lanwantec.manage.web.page;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class PageDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/** 根据信息类型获取分页信息 */
	public List<Map<String, Object>> getInfoByType(int infoType, int page, int size) {
		String sql = "SELECT * FROM tbl_info WHERE infoType=? AND isValid=1 "
				+ "ORDER BY sortIndex DESC,id DESC LIMIT ?,?";
		return jdbcTemplate.queryForList(sql, infoType, (page - 1) * size, size);
	}

	/** 根据信息类型获取所有信息，未分页 */
	public List<Map<String, Object>> getInfoByType(int infoType) {
		String sql = "SELECT * FROM tbl_info WHERE infoType=? AND isValid=1 "
				+ "ORDER BY sortIndex DESC,id DESC";
		return jdbcTemplate.queryForList(sql, infoType);
	}

	/** 根据类型获取信息总数 */
	public int getToatlByInfoType(int infoType) {
		String sql = "SELECT COUNT(1) FROM tbl_info WHERE infoType=? AND isValid=1";
		return jdbcTemplate.queryForObject(sql, Integer.class, infoType);
	}

	/** 根据id获取信息 */
	public Map<String, Object> getInfoById(int id) {
		String sql = "SELECT * FROM tbl_info WHERE id=? AND isValid=1";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, id);
		return list.size() > 0 ? list.get(0) : null;
	}

	/** 根据品牌编号获取品牌详情 */
	public Map<String, Object> getBrandByBrandNo(String brandNo) {
		String sql = "SELECT * FROM tbl_brand WHERE brandNo=? AND isValid=1";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, brandNo);
		return list.size() > 0 ? list.get(0) : null;
	}

	/** 根据模块编号获取模块信息 */
	public List<Map<String, Object>> getModulesByModuleNo(String moduleNo) {
		String sql = "SELECT * FROM tbl_headerInfo WHERE moduleNo=? AND isValid=1";
		return jdbcTemplate.queryForList(sql, moduleNo);
	}

	/** 场馆列表 */
	public List<Map<String, Object>> getPavilions() {
		return jdbcTemplate.queryForList("SELECT * FROM tbl_mall WHERE mallId>0 AND isValid=1");
	}

	/** 精选品牌或其它品牌列表 */
	public List<Map<String, Object>> getBrandList(boolean isSelection, int mallId) {
		String in = " IN ";
		if (!isSelection) {
			in = " NOT IN ";
		}
		String sql = "SELECT * FROM tbl_brand WHERE isValid=1 AND brandNo " + in
				+ " (SELECT brandNo FROM tbl_hot_brand_to_mall WHERE mallId=?)";
		return jdbcTemplate.queryForList(sql, mallId);
	}

	/** 根据品牌编号获取联系方式 */
	public Map<String, Object> getContactByBrandNo(String brandNo) {
		String sql = "SELECT a.storePhone,CONCAT(b.floor,'') floor "
				+ "FROM tbl_store a "
				+ "JOIN tbl_store_to_mall b ON b.storeId=a.storeId AND b.isValid=1 "
				+ "JOIN tbl_brand_to_store c ON c.brandNo=? AND c.isValid=1 "
				+ "WHERE a.storeId=c.storeId AND a.isValid=1 LIMIT 1";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, brandNo);
		return list.size() > 0 ? list.get(0) : null;
	}

	/** 统计样板间总数 */
	public int getTotalOfModels(String style, String theme, String area, String part) {
		String subWhere = getSubWhere(style, theme, area, part);
		String sql = "SELECT COUNT(1) FROM tbl_case WHERE isValid=1 AND caseId IN "
				+ "(SELECT DISTINCT caseId FROM tbl_case_extend WHERE isValid=1 " + subWhere + ")";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	/** 获取样板间列表 */
	public List<Map<String, Object>> getModelList(String style, String theme, String area, String part, int page, int size) {
		String subWhere = getSubWhere(style, theme, area, part);
		String sql = "SELECT * FROM tbl_case WHERE isValid=1 AND caseId IN "
				+ "(SELECT DISTINCT caseId FROM tbl_case_extend WHERE isValid=1 " + subWhere + ") "
				+ "ORDER BY caseId LIMIT ?,?";
		return jdbcTemplate.queryForList(sql, (page - 1) * size, size);
	}

	/** 查询条件封装 */
	private String getSubWhere(String style, String theme, String area, String part) {
		String subWhere = "";
		if (style != null && !"".equals(style)) {
			subWhere = subWhere + " AND caseId=ANY(SELECT caseId FROM tbl_case_extend WHERE isValid=1 AND caseTypeNo='" + style + "') ";
		}
		if (theme != null && !"".equals(theme)) {
			subWhere = subWhere + " AND caseId=ANY(SELECT caseId FROM tbl_case_extend WHERE isValid=1 AND caseTypeNo='" + theme + "') ";
		}
		if (area != null && !"".equals(area)) {
			subWhere = subWhere + " AND caseId=ANY(SELECT caseId FROM tbl_case_extend WHERE isValid=1 AND caseTypeNo='" + area + "') ";
		}
		if (part != null && !"".equals(part)) {
			subWhere = subWhere + " AND caseId=ANY(SELECT caseId FROM tbl_case_extend WHERE isValid=1 AND caseTypeNo='" + part + "') ";
		}
		return subWhere;
	}

	/** 根据caseId获取其所有类型 */
	public List<Map<String, Object>> getTypesByCaseId(String caseId) {
		String sql = "SELECT caseType FROM tbl_case_extend WHERE caseId=? AND isValid=1";
		return jdbcTemplate.queryForList(sql, caseId);
	}

	/** 根据caseTypeField获取样板间的分类数据 */
	public List<Map<String, Object>> getTypesByField(String field) {
		String sql = "SELECT DISTINCT caseTypeNo,caseType FROM tbl_case_extend "
				+ "WHERE caseTypeField=? AND isValid=1 ORDER BY caseTypeNo ";
		return jdbcTemplate.queryForList(sql, field);
	}

	/** 样板间详情 */
	public Map<String, Object> getModelDetailById(String caseId) {
		String sql = "SELECT * FROM tbl_case WHERE caseId=? AND isValid=1";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, caseId);
		return list.size() > 0 ? list.get(0) : null;
	}

	/** 样板间下的单品信息 */
	public List<Map<String, Object>> getGoodsListOfModel(String caseId) {
		String sql = "SELECT * FROM tbl_goods a JOIN tbl_case_goods b ON b.caseId=? AND b.isValid=1 "
				+ "WHERE a.goodsId=b.goodsId";
		return jdbcTemplate.queryForList(sql, caseId);
	}

	/** 统计门店总数 */
	public int getTotalOfStores(int mallId) {
		String sql = "SELECT COUNT(1) "
				+ "FROM tbl_store a "
				+ "JOIN tbl_store_to_mall b ON b.isValid=1 AND b.mallId=? "
				+ "WHERE a.storeId=b.storeId";
		return jdbcTemplate.queryForObject(sql, Integer.class, mallId);
	}

	/** 门店列表 */
	public List<Map<String, Object>> getStoreList(int mallId, int page, int size) {
		String sql = "SELECT a.*,CONCAT(b.floor,'') floor "
				+ "FROM tbl_store a "
				+ "JOIN tbl_store_to_mall b ON b.isValid=1 AND b.mallId=? "
				+ "WHERE a.storeId=b.storeId "
				+ "LIMIT ?,?";
		return jdbcTemplate.queryForList(sql, mallId, (page - 1) * size, size);
	}

	public List<Map<String, Object>> queryHonor() {
		String sql = "SELECT * FROM tbl_info WHERE infoType=? AND isValid=1 "
				+ "ORDER BY createDate asc";
		return jdbcTemplate.queryForList(sql, 4);
	}

	/** 统计营销活动总数 */
	public int getToatlCampaignList() {
		return jdbcTemplate.queryForObject("SELECT COUNT(1) FROM tbl_campaign WHERE isValid=1", Integer.class);
	}

	/** 联盟国际营销活动列表 */
	public List<Map<String, Object>> getCampaignList(int page, int size) {
		String sql = "SELECT *,(CASE WHEN finishDate<=NOW() THEN TRUE ELSE FALSE END) isFinish "
				+ "FROM tbl_campaign WHERE isValid=1 ORDER BY createDate DESC LIMIT ?,?";
		return jdbcTemplate.queryForList(sql, (page - 1) * size, size);
	}

	/** 根据id获取营销活动详情 */
	public Map<String, Object> getCampaignById(int id) {
		String sql = "SELECT * FROM tbl_campaign WHERE id=? AND isValid=1";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, id);
		return list.size() > 0 ? list.get(0) : null;
	}

}
