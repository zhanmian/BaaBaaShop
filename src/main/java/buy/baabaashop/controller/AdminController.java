package buy.baabaashop.controller;

import buy.baabaashop.common.PaginationRequestParam;
import buy.baabaashop.common.PaginationResultData;
import buy.baabaashop.common.ResultData;
import buy.baabaashop.dao.ProductDao;
import buy.baabaashop.entity.Product;
import buy.baabaashop.entity.ProductAttribute;
import buy.baabaashop.entity.ProductSku;
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
import java.util.ArrayList;
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

    @Resource
    public ProductDao productDao;

    @RequestMapping(value = "")
    public String home(){
        return "online_shop/index";
    }

    //添加商品
    @RequestMapping(value = "/add_product")
    @ResponseBody
    public ResultData addProduct(HttpServletRequest request){
        return productService.addProduct(request);
    }

    //获取商品列表
    @RequestMapping(value = "product_list")
    @ResponseBody
    public PaginationResultData<Product> getProductList(
            HttpServletRequest request, PaginationRequestParam param){
        return productService.getProductList(param);
    }

    @RequestMapping(value = "get_sku_details")
    @ResponseBody
    public Object getSkuDetails(HttpServletRequest request, ProductSku productSku){
        Map<String, Object> map = new HashMap<>();
        map.put("draw", 0);
        map.put("data", productService.getSkuDetails(productSku.getProductId()));
        return map;
    }

    //获取商品分类列表
    @RequestMapping(value = "/product_category")
    @ResponseBody
    public PaginationResultData<Product> getProductCategoryList(
            @RequestBody PaginationRequestParam param){
        return productService.selectProductCategoryList(param);
    }

    //添加商品分类
    @RequestMapping(value = "/add_product_category")
    @ResponseBody
    public ResultData addProductCategory(@RequestBody Product product){
        return productService.addProductCategory(product);
    }

    //获取商品属性分类列表
    @RequestMapping(value = "/product_attribute_category")
    @ResponseBody
    public PaginationResultData<Product> getProductAttributeCategory(
            @RequestBody PaginationRequestParam param){
        return productService.selectProductAttributeCategory(param);
    }

    //添加商品属性分类
    @RequestMapping(value = "add_product_attribute_category")
    @ResponseBody
    public ResultData addProductAttributeCategory(HttpServletRequest request,
                                                  ProductAttribute productAttribute){
        return productService.addProductAttributeCategory(productAttribute);
    }

    //获取商品属性列表
    @RequestMapping(value = "/product_attribute")
    @ResponseBody
    public PaginationResultData<ProductAttribute> getProductAttribute(
            @RequestBody PaginationRequestParam param){
        return productService.selectProductAttribute(param);
    }

    //添加商品时获取商品规格属性和参数属性
    @RequestMapping(value = "get_product_attribute")
    @ResponseBody
    public Object getProductAttributeByCategoryId(HttpServletRequest request,
                                                ProductAttribute productAttribute){
        List<ProductAttribute> list = productDao.selectProductAttributeByCategoryId(productAttribute);
        List<String> attributeList = new ArrayList<String>();
        List<String> attributeIdList = new ArrayList<String>();
        List<String> inputList = new ArrayList<String>();
        List<String> inputStatus = new ArrayList<String>();
        for(ProductAttribute p : list){
            attributeIdList.add(p.getId().toString());
            attributeList.add(p.getAttributeName());
            inputList.add(p.getInputList());
            inputStatus.add(p.getInputStatus().toString());
        }
        Map<String, Object> map = new HashMap<>();
        map.put("attributeList", attributeList);
        map.put("attributeIdList", attributeIdList);
        map.put("inputList", inputList);
        map.put("inputStatus", inputStatus);
        return map;
    }

    //前往商品属性添加页面
    @RequestMapping(value = "to_add_product_attribute")
    public String toAddProductAttribute(HttpServletRequest request,
                                        ProductAttribute productAttribute,
                                        Model model){
        model.addAttribute("categoryId", productAttribute.getCategoryId());
        model.addAttribute("type", productAttribute.getType());
        model.addAttribute("productAttributeCategoryList",
                productDao.selectAllProductAttributeCategory());
        return "/online_shop/add_product_attribute";
    }

    //添加商品属性
    @RequestMapping(value = "add_product_attribute")
    @ResponseBody
    public ResultData addProductAttribute(@RequestBody ProductAttribute productAttribute){
        return productService.addProductAttribute(productAttribute);
    }

    @RequestMapping(value = "to_update_product_attribute")
    public String toUpdateProductAttribute(HttpServletRequest request,
                                           ProductAttribute attribute,
                                           Model model){
        ProductAttribute productAttribute = productDao.selectProductAttributeById(attribute);
        model.addAttribute("attribute", productAttribute);
        model.addAttribute("productAttributeCategoryList",
                productDao.selectAllProductAttributeCategory());
        return "online_shop/update_product_attribute";
    }

    //更新商品属性
    @RequestMapping(value = "update_product_attribute")
    @ResponseBody
    public ResultData updateProductAttribute(@RequestBody ProductAttribute productAttribute){
        return productService.updateProductAttribute(productAttribute);
    }

    //删除商品分类
    @RequestMapping(value = "delete_product_category")
    @ResponseBody
    public ResultData deleteProductCategory(@RequestBody Product product){
        return productService.deleteProductCategory(product.getCategoryId());
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
    public ResultData deleteProductAttributeCategory(@RequestBody Product product){
        return productService.deleteProductAttributeCategory(product.getCategoryId());
    }

    //删除商品属性
    @RequestMapping(value = "delete_product_attribute")
    @ResponseBody
    public ResultData deleteProductAttribute(@RequestBody Product product){
        return productService.deleteProductAttribute(product.getId());
    }

    //上传图片
    @RequestMapping(value = "/upload_picture")
    @ResponseBody
    public ResultData upload(@RequestPart("file") MultipartFile image) throws IOException {

        String fileName = image.getOriginalFilename();
        image.transferTo(new File(storageLocationRoot + storageLocationImage + "/" + fileName));

        String filePath = storageLocationImage + "/" + fileName;

        //压缩图片
        Thumbnails.of(filePath)
                .scale(1f)
                .outputQuality(0.3f)
                .toFile(filePath);

        Map<String, Object> map = new HashMap<>();
        map.put("filePath", filePath);

        Object o = new Object();
        o.equals(map);

        ResultData resultData = new ResultData();
        resultData.setData(map);
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
