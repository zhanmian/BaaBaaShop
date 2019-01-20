package buy.baabaashop.controller;

import buy.baabaashop.common.PaginationRequestParam;
import buy.baabaashop.common.PaginationResultData;
import buy.baabaashop.common.ResultData;
import buy.baabaashop.dao.ProductDao;
import buy.baabaashop.entity.Product;
import buy.baabaashop.entity.ProductAttribute;
import buy.baabaashop.entity.ProductSku;
import buy.baabaashop.service.ProductServiceImp;
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
    public ProductServiceImp productServiceImp;

    @Resource
    public ProductDao productDao;

    @RequestMapping(value = "")
    public String home(){
        return "online_shop/index";
    }

    @RequestMapping(value = "/to_add_product")
    public String toAddProduct(Model model){
        model.addAttribute("productCategoryList",
                productDao.selectAllParentProductCategory());
        model.addAttribute("productAttributeCategoryList",
                productDao.selectAllProductAttributeCategory());
        return "online_shop/add_product";
    }

    @RequestMapping(value = "/add_product")
    @ResponseBody
    public ResultData addProduct(HttpServletRequest request){
        return productServiceImp.addProduct(request);
    }

    @RequestMapping(value = "to_product_list")
    public String toProductList(){
        return "online_shop/product_list";
    }

    @RequestMapping(value = "product_list")
    @ResponseBody
    public Object getProductList(HttpServletRequest request,
                                 PaginationRequestParam param,
                                 Model model){
        PaginationResultData<Product> pagination = productServiceImp.getProductList(param);
        model.addAttribute("pagination", pagination);
        model.addAttribute("requestParam", param);

        Map<String, Object> map = pagination.convertToMap(param.getDraw());
        return map;
    }

    @RequestMapping(value = "to_product_sku_details")
    public String toProductSkuDetails(HttpServletRequest request,
                                      Integer productId, Model model){
        model.addAttribute("productId", productId);
        return "online_shop/product_sku_details";
    }

    @RequestMapping(value = "get_sku_details")
    @ResponseBody
    public Object getSkuDetails(HttpServletRequest request, ProductSku productSku){
        Map<String, Object> map = new HashMap<>();
        map.put("draw", 0);
        map.put("data", productServiceImp.getSkuDetails(productSku.getProductId()));
        return map;
    }

    @RequestMapping(value = "/to_product_category")
    public String toProductCategoryList(){
        return "online_shop/product_category";
    }

    @RequestMapping(value = "/product_category")
    @ResponseBody
    public Object getProductCategoryList(HttpServletRequest request,
                                      PaginationRequestParam param,
                                      Model model){
        if(param.getCategoryId() ==null){
            param.setCategoryId(0);
        }
        PaginationResultData<Product> pagination = productServiceImp.selectProductCategoryList(param);
        model.addAttribute("pagination", pagination);
        model.addAttribute("requestParam", param);

        Map<String, Object> map = pagination.convertToMap(param.getDraw());
        return map;
    }

    @RequestMapping(value = "/to_add_product_category")
    public String toAddProductCategory(Model model){
        List<Product> parentCategoryList = productServiceImp.selectAllParentProductCategory();
        model.addAttribute("parentCategoryList", parentCategoryList);
        return "online_shop/add_product_category";
    }

    @RequestMapping(value = "/add_product_category")
    @ResponseBody
    public ResultData addProductCategory(@RequestBody Product product, Model model){
        return productServiceImp.addProductCategory(product);
    }

    @RequestMapping(value = "/to_product_attribute_category")
    public String toProductAttributeCategory(){
        return "online_shop/product_attribute_category";
    }

    @RequestMapping(value = "/product_attribute_category")
    @ResponseBody
    public Object getProductAttributeCategory(HttpServletRequest request,
                                        PaginationRequestParam param,
                                        Model model){
        PaginationResultData<Product> pagination = productServiceImp.selectProductAttributeCategory(param);
        model.addAttribute("pagination", pagination);
        model.addAttribute("requestParam", param);

        Map<String, Object> map = pagination.convertToMap(param.getDraw());
        return map;
    }

    @RequestMapping(value = "add_product_attribute_category")
    @ResponseBody
    public ResultData addProductAttributeCategory(HttpServletRequest request,
                                                  ProductAttribute productAttribute){
        return productServiceImp.addProductAttributeCategory(productAttribute);
    }

    @RequestMapping(value = "/to_product_attribute")
    public String toProductAttribute(HttpServletRequest request,
                                     ProductAttribute productAttribute,
                                     Model model){
        model.addAttribute("categoryId", productAttribute.getCategoryId());
        model.addAttribute("type", productAttribute.getType());
        return "/online_shop/product_attribute";
    }

    @RequestMapping(value = "/product_attribute")
    @ResponseBody
    public Object getProductAttribute(HttpServletRequest request,
                                      PaginationRequestParam param,
                                      Model model){
        PaginationResultData<ProductAttribute> pagination = productServiceImp.selectProductAttribute(param);
        model.addAttribute("pagination", pagination);
        model.addAttribute("requestParam", param);
        return pagination.convertToMap(param.getDraw());
    }

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

    @RequestMapping(value = "add_product_attribute")
    @ResponseBody
    public ResultData addProductAttribute(@RequestBody ProductAttribute productAttribute){
        return productServiceImp.addProductAttribute(productAttribute);
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

    @RequestMapping(value = "update_product_attribute")
    @ResponseBody
    public ResultData updateProductAttribute(@RequestBody ProductAttribute productAttribute){
        return productServiceImp.updateProductAttribute(productAttribute);
    }

    @RequestMapping(value = "/upload_picture")
    @ResponseBody
    public ResultData upload(@RequestPart("file") MultipartFile image) throws IOException {

        String fileName = image.getOriginalFilename();
        image.transferTo(new File(storageLocationRoot + storageLocationImage + "/" + fileName));

        String filePath = storageLocationImage + "/" + fileName;
//        String newFilePath = storageLocationImage + "/small_" + fileName;

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
