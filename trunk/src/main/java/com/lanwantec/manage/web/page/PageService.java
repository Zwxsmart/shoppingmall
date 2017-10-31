package com.lanwantec.manage.web.page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.lanwantec.manage.entity.PageResponse;

@Service
public class PageService {

	@Autowired
	private PageDao pageDao;
	
	@Value("${brand.imgPath}")
	private String imgPath = "";

	/** 根据信息类型获取信息内容 */
	public String getContent(int infoType) {
		String content = "";
		List<Map<String, Object>> info = pageDao.getInfoByType(infoType, 1, 1);
		if (info != null && info.size() > 0) {
			content = info.get(0).get("content").toString();
		}
		return content;
	}

	/** 集团动态 */
	public Map<String, Object> groupDynamics(int page, int size) {
		Map<String, Object> map = new HashMap<>(5);
		map.put("page", page);
		map.put("size", size);
		map.put("imgPath", imgPath);
		map.put("total", pageDao.getToatlByInfoType(2)); // 根据类型获取信息总数
		map.put("data", pageDao.getInfoByType(2, page, size)); // infoType=2
		return map;
	}

	/** 企业荣誉 */
	public List<Map<String, Object>> companyHonor() {
		return pageDao.getInfoByType(4); // infoType=4
	}

	/** 根据id获取信息 */
	public Map<String, Object> getInfoById(int id) {
		return pageDao.getInfoById(id);
	}

	/** 精选品牌或其它品牌列表 */
	public List<Map<String, Object>> getBrandList(boolean isSelection, int mallId) {
		return pageDao.getBrandList(isSelection, mallId);
	}

	/** 根据品牌编号获取品牌详情 */
	public Map<String, Object> getBrandByBrandNo(String brandNo) {
		return pageDao.getBrandByBrandNo(brandNo);
	}

	/** 根据模块编号获取模块信息 */
	public List<Map<String, Object>> getModulesByModuleNo(String moduleNo) {
		return pageDao.getModulesByModuleNo(moduleNo);
	}

	/** 场馆列表 */
	public List<Map<String, Object>> getPavilions() {
		return pageDao.getPavilions();
	}

	/** 根据品牌编号获取联系方式 */
	public Map<String, Object> getContactByBrandNo(String brandNo) {
		return pageDao.getContactByBrandNo(brandNo);
	}

	/** 样板间列表 */
	public Map<String, Object> getModelList(String style, String theme, String area, String part, int page, int size) {
		PageResponse pageResponse = new PageResponse();
		pageResponse.setPageNum(page);
		pageResponse.setPageSize(size);
		pageResponse.setTotalCounts(pageDao.getTotalOfModels(style, theme, area, part));
		List<Map<String, Object>> data = pageDao.getModelList(style, theme, area, part, page, size);
		if (data != null && data.size() > 0) {
			for (Map<String, Object> map : data) {
				map.put("picUrl", imgPath + map.get("picUrl"));
				map.put("types", pageDao.getTypesByCaseId(map.get("caseId").toString()));
			}
		}
		pageResponse.setData(data);
		
		Map<String, Object> map = new HashMap<>(1);
		map.put("pageResponse", pageResponse);
		return map;
	}

	/** 获取样板间的分类数据 */
	public Map<String, Object> getTypes() {
		// style风格、theme主题、area区域、part局部
		Map<String, Object> map = new HashMap<>(4);
		map.put("style", pageDao.getTypesByField("style"));
		map.put("theme", pageDao.getTypesByField("theme"));
		map.put("area", pageDao.getTypesByField("area"));
		map.put("part", pageDao.getTypesByField("part"));
		return map;
	}

	/** 样板间详情 */
	public Map<String, Object> getModelDetailById(String caseId) {
		Map<String, Object> model = pageDao.getModelDetailById(caseId);
		if (model != null) {
			model.put("types", pageDao.getTypesByCaseId(caseId));
			model.put("goodsList", pageDao.getGoodsListOfModel(caseId));
		}
		return model;
	}

	/** 门店列表数据接口(分页) */
	public Map<String, Object> getStoreList(int mallId, int page, int size) {
		PageResponse pageResponse = new PageResponse();
		pageResponse.setPageNum(page);
		pageResponse.setPageSize(size);
		pageResponse.setTotalCounts(pageDao.getTotalOfStores(mallId));
		List<Map<String, Object>> data = pageDao.getStoreList(mallId, page, size);
		if (data != null && data.size() > 0) {
			for (Map<String, Object> map : data) {
				map.put("showUrl", imgPath + map.get("showUrl"));
			}
		}
		pageResponse.setData(data);
		
		Map<String, Object> map = new HashMap<>(1);
		map.put("pageResponse", pageResponse);
		return map;
	}

	/** 热门活动列表 */
	public Map<String, Object> getActivityList(int infoType, int page, int size) {
		PageResponse pageResponse = new PageResponse();
		pageResponse.setPageNum(page);
		pageResponse.setPageSize(size);
		pageResponse.setTotalCounts(pageDao.getToatlByInfoType(12));
		List<Map<String, Object>> data = pageDao.getInfoByType(infoType, page, size);
		if (data != null && data.size() > 0) {
			for (Map<String, Object> map : data) {
				map.put("picTitle", imgPath + map.get("picTitle"));
			}
		}
		pageResponse.setData(data);
		
		Map<String, Object> map = new HashMap<>(1);
		map.put("pageResponse", pageResponse);
		return map;
	}

	/** 联盟国际营销活动列表 */
	public Map<String, Object> getCampaignList(int page, int size) {
		PageResponse pageResponse = new PageResponse();
		pageResponse.setPageNum(page);
		pageResponse.setPageSize(size);
		pageResponse.setTotalCounts(pageDao.getToatlCampaignList());
		List<Map<String, Object>> data = pageDao.getCampaignList(page, size);
		if (data != null && data.size() > 0) {
			for (Map<String, Object> map : data) {
				map.put("picTitle", imgPath + map.get("picTitle"));
			}
		}
		pageResponse.setData(data);
		
		Map<String, Object> map = new HashMap<>(1);
		map.put("pageResponse", pageResponse);
		return map;
	}
	
	/** 根据id获取营销活动详情 */
	public Map<String, Object> getCampaignById(int id) {
		return pageDao.getCampaignById(id);
	}

	/** 获取联盟国际的二维码链接 */
	public String getQrCode() {
		List<Map<String, Object>> modules = pageDao.getModulesByModuleNo("3"); // 联盟国际二维码 moduleNo="3"
		String qrCode = "";
		if (modules != null && modules.size() > 0) {
			Map<String, Object> module = modules.get(0);
			Object object = module.get("picUrl");
			if (object != null && !"".equals(object)) {
				qrCode = imgPath + object;
			}
		}
		return qrCode;
	}

	public List<Map<String, Object>> queryHonor() {
		return pageDao.queryHonor();
	}
}
