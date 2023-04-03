package com.yautumn.service.shop.impl;

import com.yautumn.common.entity.shop.ShopCommodityInformation;
import com.yautumn.common.utils.BatchUtils;
import com.yautumn.common.utils.DateUtils;
import com.yautumn.common.utils.ExcelUtils;
import com.yautumn.common.utils.PageBeanUtil;
import com.yautumn.dao.shop.ShopCommodityInformationMapper;
import com.yautumn.param.request.common.PageParam;
import com.yautumn.param.request.shop.ShopCommodityParam;
import com.yautumn.param.response.ExceptionsEnum;
import com.yautumn.service.shop.ShopCommodityInfoService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class ShopCommodityInfoServiceImpl implements ShopCommodityInfoService {

    @Autowired
    private ShopCommodityInformationMapper shopCommodityInformationMapper;

    @Autowired
    private BatchUtils batchUtils;

    @Value("${fileUploadPath}")
    private String fileUploadPath;

    @Value("${redisprefix}")
    private String redisprefix;

    @Autowired
    private RedissonClient redissonClient;

    /**
     * 插入商品信息
     * @param shopCommodityParam
     * @return
     */
    @Override
    public String insert(ShopCommodityParam shopCommodityParam) {

        ShopCommodityInformation commodityInfo = this.selectByCondition(shopCommodityParam.getShopId(),shopCommodityParam.getProductBrand(),shopCommodityParam.getProductType(),shopCommodityParam.getProductSpecific());
        if (null != commodityInfo){
            RBucket<ShopCommodityInformation> rBucket = this.getRBucket(redisprefix + "commodity-" + commodityInfo.getId());
            rBucket.set(commodityInfo);
            return ExceptionsEnum.SHOP_COMMODITY_IS_EXIST.name;
        }
        ShopCommodityInformation shopCommodityInformation = new ShopCommodityInformation();
        BeanUtils.copyProperties(shopCommodityParam,shopCommodityInformation);
        shopCommodityInformation.setStatus("1");
        shopCommodityInformation.setCreatetime(DateUtils.dateTimeToString(new Date()));
        int i = shopCommodityInformationMapper.insert(shopCommodityInformation);
        RBucket<ShopCommodityInformation> rBucket = this.getRBucket(redisprefix + shopCommodityInformation.getId());
        if (i == 1){
            rBucket.set(shopCommodityInformation);
            return ExceptionsEnum.SUCCESS.name;
        }else {
            return ExceptionsEnum.SHOP_COMMODITY_INSERT_ERROR.name;
        }
    }

    /**
     * 根据id删除商品信息
     * @param shopCommodityId
     * @return
     */
    @Override
    public String delShopCommodityByID(int shopCommodityId) {
        RBucket<ShopCommodityInformation> rBucket = this.getRBucket(redisprefix + "commodity-" +shopCommodityId);
        if (isNull(shopCommodityId)) {
            return ExceptionsEnum.SHOP_COMMODITY_IS_NOT_EXIST.name;
        }
        int i = shopCommodityInformationMapper.deleteByPrimaryKey(shopCommodityId);
        if (i == 1) {
            rBucket.delete();
            return ExceptionsEnum.SUCCESS.name;
        } else {
            return ExceptionsEnum.SHOP_COMMODITY_DELETE_ERROR.name;
        }
    }

    /**
     * 更新商品信息
     * @param shopCommodityParam
     * @return
     */
    @Override
    public String updateCommdityByID(ShopCommodityParam shopCommodityParam) {
        if(this.isNull(shopCommodityParam.getId())){
            return ExceptionsEnum.SHOP_COMMODITY_IS_NOT_EXIST.name;
        }
        RBucket<ShopCommodityInformation> rBucket = this.getRBucket(redisprefix + "commodity-" +shopCommodityParam.getId());
        ShopCommodityInformation shopCommodityInformation = rBucket.get();

        if (null == shopCommodityInformation){
            shopCommodityInformation = this.findShopCommodityByID(shopCommodityParam.getId());
            if (shopCommodityInformation != null){
                rBucket.set(shopCommodityInformation);
            }
            return ExceptionsEnum.SHOP_COMMODITY_IS_NOT_EXIST.name;
        }

        BeanUtils.copyProperties(shopCommodityParam,shopCommodityInformation);
        shopCommodityInformation.setUpdatetime(DateUtils.dateTimeToString(new Date()));
        int i = shopCommodityInformationMapper.updateByPrimaryKey(shopCommodityInformation);
        if (i == 1){
            rBucket.set(shopCommodityInformation);
            return ExceptionsEnum.SUCCESS.name;
        }else {
            return ExceptionsEnum.SHOP_COMMODITY_UPDATE_ERROR.name;
        }
    }

    /**
     * 分页查询商品信息
     * @param pageParam
     * @return
     */
    @Override
    public PageBeanUtil findCommdityAll(PageParam pageParam) {
        int currentPage = pageParam.getCurrentPage();
        int pageSize = pageParam.getPageSize();
        int totalCount = pageParam.getTotalCount();
        PageBeanUtil pageBeanUtil = new PageBeanUtil(currentPage,pageSize,totalCount);
        List<ShopCommodityInformation> shopCommodityInformations = shopCommodityInformationMapper.findCommodityAll(pageBeanUtil.getStart(),pageBeanUtil.getEnd());
        pageBeanUtil.setList(shopCommodityInformations);
        return pageBeanUtil;
    }


    /**
     * 根据id查询商品信息
     * @param shopCommodityId
     * @return
     */
    @Override
    public ShopCommodityInformation findShopCommodityByID(int shopCommodityId) {
        RBucket<ShopCommodityInformation> rBucket = this.getRBucket(redisprefix + "commodity-" +shopCommodityId);
        ShopCommodityInformation shopCommodityInformation = rBucket.get();
        if (null == shopCommodityInformation){
            shopCommodityInformation = shopCommodityInformationMapper.selectByPrimaryKey(shopCommodityId);
            if (null != shopCommodityInformation){
                rBucket.set(shopCommodityInformation,30,TimeUnit.SECONDS);
            }
        }
        return shopCommodityInformation;
    }

    @Override
    public int countCommoditys(int shopId) {
        int num = shopCommodityInformationMapper.selectCount(shopId);
        return num;
    }

    @Override
    @Transactional
    public String batchInsert(List<ShopCommodityParam> shopCommodityParams) {
        List<ShopCommodityInformation> shopCommodityInformations = new ArrayList<ShopCommodityInformation>();
        shopCommodityParams.forEach(shopCommodityParam -> {
            ShopCommodityInformation commodityInformation = this.selectByCondition(shopCommodityParam.getShopId(),shopCommodityParam.getProductBrand(),shopCommodityParam.getProductType(),shopCommodityParam.getProductSpecific());
            if (null != commodityInformation){
                return;
            }
            ShopCommodityInformation shopCommodityInformation = new ShopCommodityInformation();
            BeanUtils.copyProperties(shopCommodityParam,shopCommodityInformation);
            shopCommodityInformation.setStatus("1");
            shopCommodityInformation.setCreatetime(DateUtils.dateTimeToString(new Date()));
            shopCommodityInformations.add(shopCommodityInformation);
        });
        int i = batchUtils.batchUpdateOrInsert(shopCommodityInformations,ShopCommodityInformationMapper.class,(item,hopCommodityInformationMapper)->shopCommodityInformationMapper.insert(item));
        if (i == 1){
            return ExceptionsEnum.SUCCESS.name;
        }else {
            return ExceptionsEnum.SHOP_COMMODITY_BATCH_INSERT_ERROR.name;
        }
    }

    @Override
    @Transactional
    public String batchUpdate(List<ShopCommodityParam> shopCommodityParams) {
        List<ShopCommodityInformation> shopCommodityInformations = new ArrayList<ShopCommodityInformation>();
        shopCommodityParams.forEach(shopCommodityParam -> {
            ShopCommodityInformation shopCommodityInformation = new ShopCommodityInformation();
            BeanUtils.copyProperties(shopCommodityParam,shopCommodityInformation);
            shopCommodityInformation.setCreatetime(DateUtils.dateTimeToString(new Date()));
            shopCommodityInformations.add(shopCommodityInformation);
        });
        int i = batchUtils.batchUpdateOrInsert(shopCommodityInformations,ShopCommodityInformationMapper.class,(item,hopCommodityInformationMapper)->shopCommodityInformationMapper.updateByPrimaryKey(item));
        if (i == 1){
            return ExceptionsEnum.SUCCESS.name;
        }else {
            return ExceptionsEnum.SHOP_COMMODITY_BATCH_UPDATE_ERROR.name;
        }
    }

    @Override
    public String analysisExcel(MultipartFile multipartFile, int shopId){
        List excelList = null;
        try {
            excelList = this.excelToList(multipartFile,shopId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<Map<String,String>> mapList = this.excelListToMap(excelList);
        List<ShopCommodityParam> shopCommodityParams = this.listToParam(mapList,shopId);
        String msg = this.batchInsert(shopCommodityParams);
        return msg;
    }

    private ShopCommodityInformation selectByCondition(int shopId,String productBrand,String productType,String productSpecific){
        return shopCommodityInformationMapper.selectByCondition(shopId,productBrand,productType,productSpecific);
    }

    private List<ShopCommodityParam> listToParam(List<Map<String,String>> list,int shopId){
        List<ShopCommodityParam> shopCommodityParams = new ArrayList<>();
        for (int i= 0; i < list.size(); i++) {
            ShopCommodityParam param = new ShopCommodityParam();
            Map<String,String> map = list.get(i);
            param.setShopId(shopId);
            param.setProductBrand(map.get("product_brand"));
            param.setProductName(map.get("product_name"));
            param.setProductType(map.get("product_type"));
            param.setProductSpecific(map.get("product_specific"));
            param.setProductUnit(map.get("product_unit"));
            param.setRemark(map.get("remark"));
            shopCommodityParams.add(param);
        }
        return shopCommodityParams;
    }

    private List excelToList(MultipartFile multipartFile, int shopId) throws FileNotFoundException,IOException{
        List<ShopCommodityInformation> commodityInformations =new ArrayList<>();
        String url = this.upload(multipartFile,shopId);
        File file = new File(url);
        List excelList = new ArrayList();

        FileInputStream fis = new FileInputStream(file);
        Workbook wb = ExcelUtils.getWork(url,new FileInputStream(url));
        Sheet sheet = wb.getSheetAt(0);
        Row row = null;
        int lastRowNum = sheet.getLastRowNum()+1;
        for (int y = 0; y < lastRowNum; y++) {
            List rowList = new ArrayList();
            row = sheet.getRow(y);
            if (null != row) {
                //获取每一列值
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    Cell cell = row.getCell(j);
                    String value = ExcelUtils.getCellValue(cell);
                    rowList.add(value);
                }
            }
            excelList.add(rowList);
        }
        wb.close();
        fis.close();

        return excelList;
    }

    private List<Map<String,String>> excelListToMap(List excelList){
        List list = (List) excelList.get(1);
        List<Map<String,String>> mapList = new ArrayList<>();
        for (int i = 2; i < excelList.size(); i++) {
            List rowList = (List) excelList.get(i);
            Map cellMap = new HashMap();
            for (int j = 0; j < rowList.size(); j++) {
                cellMap.put(list.get(j),rowList.get(j));
            }
            mapList.add(cellMap);
        }
        return mapList;
    }

    private String upload(MultipartFile file,int shopId){
        // 1、获取文件的原始文件名, 通过原始文件名获取文件后缀 例如：abc.jpg
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String uploadPath = fileUploadPath+"/"+String.valueOf(shopId) + "/";

        // 2、重新生成文件名，防止文件名称重复造成文件覆盖
        String fileName = DateUtils.dateFormatToString(new Date()) + suffix;

        // 3、创建一个目录对象
        File dir = new File(uploadPath);
        //判断当前目录是否存在
        if (!dir.exists()) {
            //目录不存在，需要创建
            dir.mkdirs();
        }

        try {
            // 4、将临时文件转存到指定位置
            file.transferTo(new File(uploadPath + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return uploadPath + fileName;
    }

    private boolean isNull(int shopCommodityId){
        boolean flag = false;
        ShopCommodityInformation shopCommodityInformation = this.selectCommodityByRedis(shopCommodityId);
        if (shopCommodityInformation == null){
            flag = true;
        }
        return flag;
    }

    private ShopCommodityInformation selectCommodityByRedis(int shopCommodityId){
        RBucket<ShopCommodityInformation> rBucket = redissonClient.getBucket(redisprefix + "commodity-" +shopCommodityId);
        ShopCommodityInformation shopCommodityInformation = rBucket.get();
        if (shopCommodityInformation == null) {
            synchronized (ShopCommodityInfoServiceImpl.class) {
                shopCommodityInformation = rBucket.get();
                if (null == shopCommodityInformation) {
                    shopCommodityInformation = this.findShopCommodityByID(shopCommodityId);
                }
            }
        }
        rBucket.set(shopCommodityInformation,60,TimeUnit.SECONDS);
        return shopCommodityInformation;
    }

    private RBucket<ShopCommodityInformation> getRBucket(String key){
        return redissonClient.getBucket(key);
    }

}
