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
import java.util.*;

@Service
public class ShopCommodityInfoServiceImpl implements ShopCommodityInfoService {

    @Autowired
    private ShopCommodityInformationMapper shopCommodityInformationMapper;

    @Autowired
    private BatchUtils batchUtils;

    @Value("${fileUploadPath}")
    private String fileUploadPath;

    /**
     * 插入商品信息
     * @param shopCommodityParam
     * @return
     */
    @Override
    public String insert(ShopCommodityParam shopCommodityParam) {
        ShopCommodityInformation shopCommodityInformation = new ShopCommodityInformation();
        BeanUtils.copyProperties(shopCommodityParam,shopCommodityInformation);
        shopCommodityInformation.setCreatetime(DateUtils.dateTimeToString(new Date()));
        int i = shopCommodityInformationMapper.insert(shopCommodityInformation);
        if (i == 1){
            return ExceptionsEnum.SUCCESS.name;
        }else {
            return ExceptionsEnum.SHOP_INSERT_ERROR.name;
        }
    }

    /**
     * 根据id删除商品信息
     * @param shopCommodityId
     * @return
     */
    @Override
    public String delShopCommodityByID(int shopCommodityId) {
        if (isNull(shopCommodityId)) {
            return "商品信息不存在";
        }
        int i = shopCommodityInformationMapper.deleteByPrimaryKey(shopCommodityId);
        if (i == 1) {
            return ExceptionsEnum.SUCCESS.name;
        } else {
            return ExceptionsEnum.SHOP_DELETE_ERROR.name;
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
            return ExceptionsEnum.SHOP_IS_NOT_EXIST.name;
        }
        ShopCommodityInformation shopCommodityInformation = this.findShopCommodityByID(shopCommodityParam.getId());
        BeanUtils.copyProperties(shopCommodityParam,shopCommodityInformation);
        shopCommodityInformation.setUpdatetime(DateUtils.dateTimeToString(new Date()));
        int i = shopCommodityInformationMapper.updateByPrimaryKey(shopCommodityInformation);
        if (i == 1){
            return ExceptionsEnum.SUCCESS.name;
        }else {
            return ExceptionsEnum.SHOP_UPDATE_ERROR.name;
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
        ShopCommodityInformation shopCommodityInformation = shopCommodityInformationMapper.selectByPrimaryKey(shopCommodityId);
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
            ShopCommodityInformation shopCommodityInformation = new ShopCommodityInformation();
            BeanUtils.copyProperties(shopCommodityParam,shopCommodityInformation);
            shopCommodityInformation.setCreatetime(DateUtils.dateTimeToString(new Date()));
            shopCommodityInformations.add(shopCommodityInformation);
        });
        int i = batchUtils.batchUpdateOrInsert(shopCommodityInformations,ShopCommodityInformationMapper.class,(item,hopCommodityInformationMapper)->shopCommodityInformationMapper.insert(item));
        if (i == 1){
            return ExceptionsEnum.SUCCESS.name;
        }else {
            return ExceptionsEnum.SHOP_BATCH_INSERT_ERROR.name;
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
            return ExceptionsEnum.SHOP_BATCH_UPDATE_ERROR.name;
        }
    }

    @Override
    public List<Map> analysisExcel(MultipartFile multipartFile, int shopId) {
        List excelList = this.excelToList(multipartFile,shopId);
        List<Map> mapList = this.excelListToMap(excelList);
        return mapList;
    }

    private List excelToList(MultipartFile multipartFile, int shopId){
        List<ShopCommodityInformation> commodityInformations =new ArrayList<>();
        String url = this.upload(multipartFile,shopId);
        File file = new File(url);
        List excelList = new ArrayList();
        try {
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
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return excelList;
    }

    private List<Map> excelListToMap(List excelList){
        List list = (List) excelList.get(1);
        List<Map> mapList = new ArrayList<>();
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

        // 2、重新生成文件名，防止文件名称重复造成文件覆盖
        String fileName = String.valueOf(shopId) + "-" + DateUtils.dateFormatToString(new Date()) + suffix;

        // 3、创建一个目录对象
        File dir = new File(fileUploadPath);
        //判断当前目录是否存在
        if (!dir.exists()) {
            //目录不存在，需要创建
            dir.mkdirs();
        }

        try {
            // 4、将临时文件转存到指定位置
            file.transferTo(new File(fileUploadPath + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileUploadPath + fileName;
    }

    private boolean isNull(int shopCommodityId){
        boolean flag = false;
        ShopCommodityInformation shopCommodityInformation = this.findShopCommodityByID(shopCommodityId);
        if (null == shopCommodityInformation){
            flag = true;
        }
        return flag;
    }

}
