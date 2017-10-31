package com.lanwantec.manage.manage.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class StoreService {
    @Autowired
    private StoreDao storeDao;

    public List<Map<String, Object>> queryByData(Integer pageNumber, Integer pageSize, String brandName, String floor, String storeStyle, String startTime, String endTime){
        return (List<Map<String, Object>>) storeDao.queryByData(pageNumber, pageSize, brandName, floor, storeStyle, startTime,endTime);
    }

    public Integer countByData(String brandName, String floor, String storeStyle, String startTime, String endTime) {
        return (Integer) storeDao.countByData(brandName, floor, storeStyle, startTime,endTime);
    }

    public Map<String, Object> queryById(String userNo) {
        return storeDao.queryById(userNo);
    }

    public Integer status(String status, String userNo) {
        return storeDao.status(status, userNo);
    }

    public List<Map<String, Object>> querAllMall() {
        return storeDao.queryAllMall();
    }

    public List<Map<String, Object>> querAllBrand() {
        return storeDao.queryAllBrand();
    }

    public Integer updateBrand(String bsid,String brand, String storeId) {
        return storeDao.updateBrand(bsid, brand,storeId);
    }

    public Integer updateMall(String smid,String mall, String storeId, String floor, String doorplate) {
        return storeDao.updateMall(smid,mall, storeId, floor, doorplate);
    }

    public Integer update(String storeId, String name, String principal, String contacter,String contactPhone, String storePhone, String doorplate, String openingHours, String address, String detailAddress, String longitude, String latitude,String provideService, String showUrl) {
        return storeDao.update(storeId,name,  principal, contacter,contactPhone, storePhone,doorplate,openingHours, address,detailAddress,longitude,latitude,provideService,showUrl);
    }

    public List<Map<String, Object>> queryStyles() {
        return storeDao.queryStyles();
    }

    public Integer insert(String name, String principal, String contacter,String contactPhone, String storePhone, String provideService, String address, String detailAddress, String openingHours) {
        return storeDao.insert(name,  principal, contacter,contactPhone, storePhone,provideService, address,detailAddress,openingHours);
    }
}
