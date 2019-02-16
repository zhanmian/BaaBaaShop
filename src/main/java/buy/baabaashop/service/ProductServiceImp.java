package buy.baabaashop.service;

import buy.baabaashop.common.CommonException;
import buy.baabaashop.common.PaginationRequestParam;
import buy.baabaashop.common.PaginationResultData;
import buy.baabaashop.common.ResultData;
import buy.baabaashop.dao.ProductDao;
import buy.baabaashop.entity.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static buy.baabaashop.controller.CustomerController.getRequestBodyAsString;

@Service
public class ProductServiceImp implements ProductService {

    @Resource
    public ProductDao productDao;

    @Override
    public PaginationResultData<ProductCategory> selectProductCategoryList(PaginationRequestParam param){

        Integer totalRecord = productDao.selectProductCategoryTotalRecord();

        PaginationResultData<ProductCategory> resultData = new PaginationResultData<>();
        resultData.setTotalRecord(totalRecord);

        List<ProductCategory> list = productDao.selectProductCategoryList(param);
        resultData.setList(list);

        return resultData;
    }

    @Override
    @Transactional
    public ResultData addProductCategory(ProductCategory productCategory){
        ResultData resultData = new ResultData();
        try{
            productDao.addProductCategory(productCategory);
            resultData.setMessage("添加分类成功");
        }catch(CommonException c){
            c.printStackTrace();
            throw new CommonException(c.getCode(), c.getMessage());
        }catch(Exception e){
            e.printStackTrace();
            throw new CommonException();
        }
        return resultData;
    }

    @Override
    public ResultData updateProductCategory(ProductCategory productCategory){
        ResultData resultData = new ResultData();
        try{
            productDao.updateProductCategory(productCategory);
            resultData.setMessage("更新分类成功");
        }catch(CommonException c){
            c.printStackTrace();
            throw new CommonException(c.getCode(), c.getMessage());
        }catch(Exception e){
            e.printStackTrace();
            throw new CommonException();
        }
        return resultData;
    }

    @Override
    public ProductCategory getProductCategoryById(ProductCategory productCategory){
        return productDao.selectProductCategoryById(productCategory);
    }

    @Override
    public PaginationResultData<ProductAttribute> selectProductAttributeCategory(PaginationRequestParam param){

        Integer totalRecord = productDao.selectProductAttributeCategoryTotalRecord();

        PaginationResultData<ProductAttribute> resultData = new PaginationResultData<>();
        resultData.setTotalRecord(totalRecord);

        List<ProductAttribute> list = productDao.selectProductAttributeCategory(param);
        resultData.setList(list);

        return resultData;
    }

    @Override
    @Transactional
    public ResultData addProductAttributeCategory(ProductAttribute productAttribute){
        ResultData resultData = new ResultData();
        try{
            productDao.addProductAttributeCategory(productAttribute);
            resultData.setMessage("添加属性分类成功");
        }catch(CommonException c){
            c.printStackTrace();
            throw new CommonException(c.getCode(), c.getMessage());
        }catch(Exception e){
            e.printStackTrace();
            throw new CommonException();
        }
        return resultData;
    }

    @Override
    @Transactional
    public ResultData updateProductAttributeCategory(ProductAttribute productAttribute){
        ResultData resultData = new ResultData();
        try{
            productDao.updateProductAttributeCategory(productAttribute);
            resultData.setMessage("更新属性分类成功");
        }catch(CommonException c){
            c.printStackTrace();
            throw new CommonException(c.getCode(), c.getMessage());
        }catch(Exception e){
            e.printStackTrace();
            throw new CommonException();
        }
        return resultData;
    }

    @Override
    public PaginationResultData<ProductAttribute> selectProductAttribute(PaginationRequestParam param){
        Integer totalRecord = productDao.selectProductAttributeTotalRecord(param);

        PaginationResultData<ProductAttribute> resultData = new PaginationResultData<ProductAttribute>();
        resultData.setTotalRecord(totalRecord);

        List<ProductAttribute> list = productDao.selectProductAttribute(param);
        resultData.setList(list);

        return resultData;
    }

    @Override
    @Transactional
    public ResultData addProductAttribute(ProductAttribute productAttribute){
        ResultData resultData = new ResultData();
        String inputList = productAttribute.getInputList();
        if(inputList != null){
            //使用trim()去除首尾空格，replaceAll()替换空格符、回车符、换行符、制表符为逗号
            String newInputList = inputList.trim().replaceAll("[\\t\\n\\r\\s]", ",");
            productAttribute.setInputList(newInputList);
        }
        try{
            productDao.addProductAttribute(productAttribute);
            Integer type = productAttribute.getType();
            Integer categoryId = productAttribute.getId();
            ProductAttribute param = productDao.selectProductAttributeCategoryById(categoryId);
            //根据type判断是0（规格属性）还是1（参数属性）来增加计数
            if(type==0){
                param.setAttributeCount(param.getAttributeCount()+1);
            }else if(type ==1){
                param.setParamCount(param.getParamCount()+1);
            }
            productDao.updateProductAttributeCategory(param);
            resultData.setMessage("新建商品属性成功");

        }catch(CommonException c){
            c.printStackTrace();
            throw new CommonException(c.getCode(), c.getMessage());
        }catch(Exception e){
            e.printStackTrace();
            throw new CommonException();
        }
        return resultData;
    }

    @Override
    public ProductAttribute getProductAttributeById(ProductAttribute productAttribute){
        return productDao.selectProductAttributeById(productAttribute);
    }

    @Override
    @Transactional
    public ResultData updateProductAttribute(ProductAttribute productAttribute){
        ResultData resultData = new ResultData();
        try{
            String inputList = productAttribute.getInputList();
            if(inputList != null){
                //使用trim()去除首尾空格，replaceAll()替换空格符、回车符、换行符、制表符为逗号
                String newInputList = inputList.trim().replaceAll("[\\n\\t\\r\\s]", ",");
                productAttribute.setInputList(newInputList);
            }
            productDao.updateProductAttribute(productAttribute);
            resultData.setMessage("修改商品属性成功");
        }catch(CommonException c){
            c.printStackTrace();
            throw new CommonException(c.getCode(), c.getMessage());
        }catch(Exception e){
            e.printStackTrace();
            throw new CommonException();
        }
        return resultData;
    }

    @Override
    @Transactional
    public ResultData addProduct(ProductParam productParam){
        ResultData resultData = new ResultData();
        try{
            productDao.addProduct(productParam);

            List<ProductSku> skuStockList = productParam.getSkuStockList();
            if(skuStockList != null && skuStockList.size() > 0){
                for(ProductSku productSku : skuStockList){
                    productSku.setProductId(productParam.getId());
                    productDao.addProductSku(productSku);
                }
            }

            List<ProductAttribute> productAttributeValueList = productParam.getProductAttributeValueList();
            if(productAttributeValueList != null && productAttributeValueList.size() > 0){
                for(ProductAttribute productAttribute : productAttributeValueList){
                    productAttribute.setProductId(productParam.getId());
                    productDao.addAttributeValue(productAttribute);
                }
            }
            resultData.setCode(1111);
            resultData.setMessage("添加商品成功");

        }catch(CommonException c){
            c.printStackTrace();
            throw new CommonException(c.getCode(), c.getMessage());
        }catch(Exception e){
            e.printStackTrace();
            throw new CommonException();
        }
        return resultData;
    }

    @Override
    @Transactional
    public ResultData updateProduct(ProductParam productParam){
        ResultData resultData = new ResultData();
        try{
            productDao.updateProduct(productParam);

            List<ProductSku> skuStockList = productParam.getSkuStockList();
            if(skuStockList != null && skuStockList.size() > 0){
                for(ProductSku productSku : skuStockList){
                    productSku.setProductId(productParam.getId());
                    productDao.updateProductSku(productSku);
                }
            }

            List<ProductAttribute> productAttributeValueList = productParam.getProductAttributeValueList();
            if(productAttributeValueList != null && productAttributeValueList.size() > 0){
                for(ProductAttribute productAttribute : productAttributeValueList){
                    productAttribute.setProductId(productParam.getId());
                    productDao.updateAttributeValue(productAttribute);
                }
            }
            resultData.setCode(1111);
            resultData.setMessage("更新商品成功");

        }catch(CommonException c){
            c.printStackTrace();
            throw new CommonException(c.getCode(), c.getMessage());
        }catch(Exception e){
            e.printStackTrace();
            throw new CommonException();
        }
        return resultData;
    }

    @Override
    public PaginationResultData<Product> getProductList(PaginationRequestParam param){
        PaginationResultData<Product> resultData = new PaginationResultData<>();
        Integer totalRecord = productDao.selectProductTotalRecord();
        resultData.setTotalRecord(totalRecord);
        List<Product> list = productDao.selectProductList(param);
        resultData.setList(list);
        return resultData;
    }


    @Override
    public Object getSkuDetails(Integer productId){
        //获得sku值列表，以及商品货号和商品分类编号
        List<ProductSku> skuList = productDao.selectSkuDetails(productId);
//        Product product = productDao.selectProductCode(productId);
//        //获得属性列表作为表格列名动态加载
//        ProductAttribute productAttribute = new ProductAttribute();
//        productAttribute.setCategoryId(product.getCategoryId());
//        productAttribute.setType(0);
//        List<ProductAttribute> list = productDao.selectProductAttributeByCategoryId(productAttribute);
//        List<String> attributeList = new ArrayList<>();
//        for(ProductAttribute p : list){
//            attributeList.add(p.getAttributeName());
//        }
//        Map<String, Object> map = new HashMap<>();
//        map.put("skuList", skuList);
//        map.put("productCode", product.getProductCode());
//        map.put("attributeList", attributeList);
        return skuList;
    }

    @Override
    @Transactional
    public ResultData deleteProductCategory(Integer categoryId){
        ResultData resultData = new ResultData();
        try{
            //删除商品分类
            productDao.deleteProductCategory(categoryId);
            //删除商品分类的子类
            productDao.deleteProductSubcategory(categoryId);
            resultData.setMessage("成功删除所选的商品分类");
        }catch(CommonException c){
            c.printStackTrace();
            throw new CommonException(c.getCode(), c.getMessage());
        }catch(Exception e){
            e.printStackTrace();
            throw new CommonException();
        }
        return resultData;
    }

    @Override
    @Transactional
    public ResultData deleteProduct(Integer productId){
        ResultData resultData = new ResultData();
        try{
            //删除商品
            productDao.deleteProduct(productId);
            //删除SKU
            productDao.deleteSku(productId);
            //删除添加商品时手动添加的商品属性
            productDao.deleteProductAttributeValue(productId);
            resultData.setMessage("成功删除所选的商品");
        }catch(CommonException c){
            c.printStackTrace();
            throw new CommonException(c.getCode(), c.getMessage());
        }catch(Exception e){
            e.printStackTrace();
            throw new CommonException();
        }
        return resultData;
    }

    @Override
    @Transactional
    public ResultData deleteProductAttributeCategory(Integer categoryId){
        ResultData resultData = new ResultData();
        try{
            //删除商品属性分类
            productDao.deleteProductAttributeCategory(categoryId);
            //删除从属于该商品属性分类的所有商品属性
            productDao.deleteProductAttributeByCategoryId(categoryId);
            resultData.setMessage("成功删除所选的商品属性分类");
        }catch(CommonException c){
            c.printStackTrace();
            throw new CommonException(c.getCode(), c.getMessage());
        }catch(Exception e){
            e.printStackTrace();
            throw new CommonException();
        }
        return resultData;
    }

    @Override
    @Transactional
    public ResultData deleteProductAttribute(ProductAttribute productAttribute){
        ResultData resultData = new ResultData();
        try{
            productDao.deleteProductAttribute(productAttribute);
            Integer type = productAttribute.getType();
            Integer categoryId = productAttribute.getCategoryId();
            ProductAttribute param = productDao.selectProductAttributeCategoryById(categoryId);
            //根据type判断是0（规格属性）还是1（参数属性）来增加计数
            if(type==0){
                param.setAttributeCount(param.getAttributeCount()-1);
            }else if(type ==1){
                param.setParamCount(param.getParamCount()-1);
            }
            productDao.updateProductAttributeCategory(param);
            resultData.setMessage("成功删除所选的商品属性");
        }catch(CommonException c){
            c.printStackTrace();
            throw new CommonException(c.getCode(), c.getMessage());
        }catch(Exception e){
            e.printStackTrace();
            throw new CommonException();
        }
        return resultData;
    }

    @Override
    public List<ProductCategoryWithChildrenItem> getProductCategoryWithChildren(){
        return productDao.selectProductCategoryWithChildren();
    }

}


