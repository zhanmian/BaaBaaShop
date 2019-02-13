package buy.baabaashop.service;

import buy.baabaashop.common.CommonException;
import buy.baabaashop.common.PaginationRequestParam;
import buy.baabaashop.common.PaginationResultData;
import buy.baabaashop.common.ResultData;
import buy.baabaashop.dao.ProductDao;
import buy.baabaashop.entity.Product;
import buy.baabaashop.entity.ProductAttribute;
import buy.baabaashop.entity.ProductSku;
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
    public PaginationResultData<Product> selectProductCategoryList(PaginationRequestParam param){

        Integer totalRecord = productDao.selectProductCategoryTotalRecord();

        PaginationResultData<Product> resultData = new PaginationResultData<Product>();
        resultData.setTotalRecord(totalRecord);

        List<Product> list = productDao.selectProductCategoryList(param);
        resultData.setList(list);

        return resultData;
    }

    @Override
    public List<Product> selectAllParentProductCategory(){
        return productDao.selectAllParentProductCategory();
    }

    @Override
    @Transactional
    public ResultData addProductCategory(Product product){
        ResultData resultData = new ResultData();
        try{
            productDao.addProductCategory(product);
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
    public PaginationResultData<Product> selectProductAttributeCategory(PaginationRequestParam param){

        Integer totalRecord = productDao.selectProductAttributeCategoryTotalRecord();

        PaginationResultData<Product> resultData = new PaginationResultData<Product>();
        resultData.setTotalRecord(totalRecord);

        List<Product> list = productDao.selectProductAttributeCategory(param);
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
            Integer categoryId = productAttribute.getCategoryId();
            //根据type判断是0（规格属性）还是1（参数属性）来增加计数
            if(type==0){
                Integer attributeCount = productDao.selectProductAttributeCategoryAttributeCount(categoryId);
                productAttribute.setAttributeCount(attributeCount+1);
                productDao.updateProductAttributeCategoryAttributeCount(productAttribute);
            }else if(type ==1){
                Integer paramCount = productDao.selectProductAttributeCategoryParamCount(categoryId);
                productAttribute.setParamCount(paramCount+1);
                productDao.updateProductAttributeCategoryParamCount(productAttribute);
            }
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
    public ResultData addProduct(HttpServletRequest request){
        ResultData resultData = new ResultData();
        try{
            //从request获取JSON数据
            String jsonString = getRequestBodyAsString(request);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonTree = objectMapper.readTree(jsonString);

            int categoryId = jsonTree.get("categoryId").asInt();
            int attributeCategoryId = jsonTree.get("attributeCategoryId").asInt();
            String productCode = jsonTree.get("productCode").asText();
            Double productPrice = jsonTree.get("productPrice").asDouble();
            String productName = jsonTree.get("productName").asText();
            String description = jsonTree.get("description").asText();
            String picture = jsonTree.get("picture").asText();

            Product product = new Product();
            product.setCategoryId(categoryId);
            product.setProductCode(productCode);
            product.setProductPrice(productPrice);
            product.setAttributeCategoryId(attributeCategoryId);
            product.setProductName(productName);
            product.setDescription(description);
            product.setPicture(picture);

            productDao.addProduct(product);

            JsonNode skuListTree = jsonTree.get("skuList");
            for(JsonNode nodes : skuListTree){
                for(JsonNode node : nodes) {
                    ProductSku productSku = new ProductSku();
                    int price = node.get("price").asInt();
                    int stock = node.get("stock").asInt();
                    String skuCode = node.get("skuCode").asText();
                    String spec1 = node.get("spec1").asText();
                    String spec2 = node.get("spec2").asText();
                    if(node.size()==6){
                        String spec3 = node.get("spec3").asText();
                        productSku.setSpec3(spec3);
                    }
                    productSku.setSpec1(spec1);
                    productSku.setSpec2(spec2);
                    productSku.setProductId(product.getId());
                    productSku.setSkuCode(skuCode);
                    productSku.setSkuPrice(price);
                    productSku.setSkuStock(stock);
                    productDao.addProductSku(productSku);
                }
            }
            //手动添加的属性写入数据库
            JsonNode addAttrVal = jsonTree.get("addAttributeValue");
            for(JsonNode attribute : addAttrVal){
                ProductAttribute productAttribute = new ProductAttribute();
                String attributeName = attribute.get("attributeName").asText();
                Integer attributeId = attribute.get("attributeId").asInt();
                productAttribute.setAttributeName(attributeName);
                productAttribute.setId(attributeId);
                productAttribute.setProductId(product.getId());
                productDao.addAttributeValue(productAttribute);
            }
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
    public ResultData deleteProductAttribute(Integer attributeId){
        ResultData resultData = new ResultData();
        try{
            productDao.deleteProductAttribute(attributeId);
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
}


