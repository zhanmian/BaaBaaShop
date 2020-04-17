package buy.baabaashop.controller;

import buy.baabaashop.common.PaginationRequestParam;
import buy.baabaashop.common.PaginationResultData;
import buy.baabaashop.common.Result;
import buy.baabaashop.common.ResultData;
import buy.baabaashop.entity.*;
import buy.baabaashop.entity.cms.CmsProductCategoryParam;
import buy.baabaashop.entity.cms.ProductAttributeRequestParam;
import buy.baabaashop.entity.cms.ProductCategoryWithChildrenItem;
import buy.baabaashop.entity.cms.ProductParam;
import buy.baabaashop.service.ProductService;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdminController {

    @Value("${storage.location.root}")
    private String storageLocationRoot;
    @Value("${storage.location.image}")
    private String storageLocationImage;

    @Value("${baseUrl}")
    private String baseUrl;

    @ModelAttribute
    public void populateModel(Model model) {
        model.addAttribute("baseUrl", baseUrl);
    }

    @Resource
    public ProductService productService;

    //添加商品
    @RequestMapping(value = "add_product")
    @ResponseBody
    public Result addProduct(@RequestBody ProductParam productParam){
        return productService.addProduct(productParam);
    }

    //根据ID获取商品详情
    @RequestMapping(value = "get_product_detail/{id}")
    @ResponseBody
    public ProductParam getProductDetail(@PathVariable Integer id){
        return productService.getProductDetail(id);
    }

    //更新商品
    @RequestMapping(value = "update_product")
    @ResponseBody
    public Result updateProduct(@RequestBody ProductParam productParam){
        return productService.updateProduct(productParam);
    }

    //获取商品列表
    @RequestMapping(value = "product_list")
    @ResponseBody
    public PaginationResultData<Product> getProductList(
            @RequestBody PaginationRequestParam param){
        return productService.getProductList(param);
    }

    @RequestMapping(value = "get_sku_details")
    @ResponseBody
    public Object getSkuDetails(@RequestBody ProductSku productSku){
        Map<String, Object> map = new HashMap<>();
        map.put("draw", 0);
        map.put("data", productService.getSkuDetails(productSku.getProductId()));
        return map;
    }

    //获取商品分类列表
    @RequestMapping(value = "product_category")
    @ResponseBody
    public PaginationResultData<ProductCategory> getProductCategoryList(
            @RequestBody CmsProductCategoryParam param){
        return productService.selectProductCategoryList(param);
    }

    //添加商品分类
    @RequestMapping(value = "add_product_category")
    @ResponseBody
    public ResultData addProductCategory(@RequestBody ProductCategory productCategory){
        return productService.addProductCategory(productCategory);
    }

    //更新商品分类
    @RequestMapping(value = "update_product_category")
    @ResponseBody
    public ResultData updateProductCategory(@RequestBody ProductCategory productCategory){
        return productService.updateProductCategory(productCategory);
    }

    //获取商品属性分类列表
    @RequestMapping(value = "product_attribute_category")
    @ResponseBody
    public PaginationResultData<ProductAttribute> getProductAttributeCategory(
            @RequestBody PaginationRequestParam param){
        return productService.selectProductAttributeCategory(param);
    }

    //根据ID查找商品分类
    @RequestMapping(value = "get_product_category_by_id")
    @ResponseBody
    public ProductCategory getProductCategoryById(@RequestBody ProductCategory productCategory){
        return productService.getProductCategoryById(productCategory);
    }

    //添加商品属性分类
    @RequestMapping(value = "add_product_attribute_category")
    @ResponseBody
    public ResultData addProductAttributeCategory(
            @RequestBody ProductAttribute productAttribute){
        return productService.addProductAttributeCategory(productAttribute);
    }

    //更新商品属性分类
    @RequestMapping(value = "update_product_attribute_category")
    @ResponseBody
    public ResultData updateProductAttributeCategory(
            @RequestBody ProductAttribute productAttribute){
        return productService.updateProductAttributeCategory(productAttribute);
    }

    //获取商品属性列表
    @RequestMapping(value = "product_attribute")
    @ResponseBody
    public PaginationResultData<ProductAttribute> getProductAttribute(
            @RequestBody ProductAttributeRequestParam param){
        return productService.selectProductAttribute(param);
    }

    //添加商品属性
    @RequestMapping(value = "add_product_attribute")
    @ResponseBody
    public ResultData addProductAttribute(@RequestBody ProductAttribute productAttribute){
        return productService.addProductAttribute(productAttribute);
    }

    //根据ID查找商品属性
    @RequestMapping(value = "get_product_attribute_by_id")
    @ResponseBody
    public ProductAttribute getProductAttributeById(
            @RequestBody ProductAttribute productAttribute){
        return productService.getProductAttributeById(productAttribute);
    }

    //更新商品属性
    @RequestMapping(value = "update_product_attribute")
    @ResponseBody
    public ResultData updateProductAttribute(@RequestBody ProductAttribute productAttribute){
        return productService.updateProductAttribute(productAttribute);
    }

    //删除商品分类
    @RequestMapping(value = "delete_product_category/{categoryId}")
    @ResponseBody
    public ResultData deleteProductCategory(@PathVariable Integer categoryId){
        return productService.deleteProductCategory(categoryId);
    }

    //删除商品
    @RequestMapping(value = "delete_product")
    @ResponseBody
    public ResultData deleteProduct(@RequestBody Product product){
        return productService.deleteProduct(product.getId());
    }

    //删除商品属性分类
    @RequestMapping(value = "delete_product_attribute_category")
    @ResponseBody
    public ResultData deleteProductAttributeCategory(@RequestBody ProductAttribute productAttribute){
        return productService.deleteProductAttributeCategory(productAttribute.getCategoryId());
    }

    //删除商品属性
    @RequestMapping(value = "delete_product_attribute")
    @ResponseBody
    public ResultData deleteProductAttribute(@RequestBody ProductAttribute productAttribute){
        return productService.deleteProductAttribute(productAttribute);
    }

    //查询所有商品父分类和其子类
    @RequestMapping(value = "get_product_category_with_children")
    @ResponseBody
    public List<ProductCategoryWithChildrenItem> getProductCategoryWithChildren(){
        return productService.getProductCategoryWithChildren();
    }

    //上传图片
    @RequestMapping(value = "/upload_picture")
    @ResponseBody
    public ResultData upload(@RequestPart("file") MultipartFile image) throws IOException {

        String fileName = image.getOriginalFilename();
        image.transferTo(new File(storageLocationRoot + storageLocationImage + "/" + fileName));

        //压缩图片用于找到图片路径
        String filePath = storageLocationRoot + storageLocationImage + "/" + fileName;

        //压缩图片
        Thumbnails.of(filePath)
                .scale(1f)
                .outputQuality(0.9f)
                .toFile(filePath);

        //用于存进数据库
        String path = storageLocationImage + "/" + fileName;

        Map<String, Object> map = new HashMap<>();
        map.put("filePath", path);

        Object o = new Object();
        o.equals(map);

        ResultData resultData = new ResultData();
        resultData.setData(map);
        resultData.setCode(1);
        return resultData;
    }

    //加载图片
    @RequestMapping(value = "/downloadImage")
    public void downloadImage(HttpServletResponse response, String filePath) throws IOException {

        File f = new File(storageLocationRoot + filePath);
        String fileName = f.getName();
        String ext = StringUtils.substringAfter(fileName, ".");
        FileInputStream fis = new FileInputStream(f);
        BufferedInputStream bis = new BufferedInputStream(fis);
        response.setContentType("image/" + ext);
        BufferedOutputStream output = new BufferedOutputStream(response.getOutputStream());
        for (int data; (data = bis.read()) > -1;) {
            output.write(data);
        }
        fis.close();
        output.close();
    }
}
